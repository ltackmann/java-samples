# cloud_sandbox

## Run locally
 * Test application works locally, open [http://localhost:8080](http://localhost:8080) and see content is served
 
    ```bash
    gradle bootRun -x test 
    ```
 
 * Package application
  
    ```bash
    gradle assemble 
    ```
 
 
 * Build docker image
 
    ```bash 
    docker build -t gcr.io/qurasense-dev-1/sandbox-app:v1 -f Dockerfile .
    ```
    
 * Run docker image
 
    ```bash 
    docker run -it -p 80:8080 gcr.io/qurasense-dev-1/sandbox-app:v1
    ```
  
 * Test docker images work (here we assume docker-machine is named **dev**, so we get its IP and opens it in a browser)
 
    ```bash
    docker-machine ip dev | xargs curl 
    ```
    
## Run in cloud
First we need to prepare the cloud for running our image

1. Login and set project 

    ```bash
    # login
    gcloud auth login
    
    # set default project
    gcloud config set project qurasense-dev-1 
    
    # test default project is qurasense-dev-1 
    gcloud config list project
 
    # set default zone
    gcloud config set compute/zone us-central1-c
    ```
    
2. Create cluster (note this need only be done once, also remember to change owner to your name)

    ```bash
    # create cluster
    gcloud container clusters create qurasense-cluster --num-nodes 3 --machine-type f1-micro --labels=owner=lars
    
    # check cluster is running
    gcloud container clusters list
    ```
    
Next we need to push the docker image we create above and run it on our cluster
 
1. Use **qurasense-cluster** for running images 

    ```bash
    # set default cluster
    gcloud config set container/cluster qurasense-cluster
 
    # check default cluster is qurasense-cluster
    gcloud config list container/cluster
 
    # let kubernetes default to qurasense-cluster
    gcloud container clusters get-credentials qurasense-cluster
    kubectl proxy &
    ``` 
    
2. Push docker image for use in google cloud 

    ```bash
    # push image
    gcloud docker -- push gcr.io/qurasense-dev-1/sandbox-app:v1
 
    # check it exists
    gcloud container images list --repository gcr.io/qurasense-dev-1
    ```
    
3. Start application in kubernetes managed cluster

    ```bash
    # start app
    kubectl run sandbox-app --image=gcr.io/qurasense-dev-1/sandbox-app:v1 --port=8080
   
    # check deployment is available (may take a few seconds)
    kubectl get deployments
 
    # check instances running
    kubectl get pods
    ```
4. Expose application to the outside

    ```bash
    # expose app by hosting it behind a public load balancer
    kubectl expose deployment sandbox-app --type=LoadBalancer
 
    # get external api (may take some time)
    kubectl get services
    ```

## Updating application in cloud
Unless you want your own cluster, then the above steps have already be carried out 
and a application is running at [http://104.197.13.169:8080](http://104.197.13.169:8080) to build a new version of it.

1. Connect to our default cloud cluster

    ```bash
    # login
    gcloud auth login
    
    # set default project
    gcloud config set project qurasense-dev-1 
 
    # set default cluster
    gcloud config set container/cluster qurasense-cluster
  
    # let kubernetes default to qurasense-cluster
    gcloud container clusters get-credentials qurasense-cluster
    kubectl proxy &
 
    # check you can se our deployments
    kubectl get deployments
    ```
    
2. Package updated application
     
    ```bash
    gradle assemble 
    ```
    
3. Build new v2 docker image and push it to google cloud

    ```bash
    # build image
    docker build -t gcr.io/qurasense-dev-1/sandbox-app:v2 -f Dockerfile .
    
    # push to google cloud
    gcloud docker -- push gcr.io/qurasense-dev-1/sandbox-app:v2
    ```
    
4. Update deployment to v2

    ```bash
    kubectl set image deployment/sandbox-app sandbox-app=gcr.io/qurasense-dev-1/sandbox-app:v2
    ```
    
5. If deployment fails

## CLoud samples

* Get metadata

    ```bash
    TOKEN=$(gcloud auth print-access-token)
    curl https://www.googleapis.com/compute/v1/projects/qurasense-dev-1 -H "Metadata-Flavor: Google" -H "Authorization: Bearer $TOKEN"
    ```
    
# Resources
* https://codelabs.developers.google.com/codelabs/cloud-springboot-kubernetes/index.html?index=..%2F..%2Findex#4
* https://github.com/spring-guides/gs-spring-boot/tree/master/complete

# TODO
* https://medium.com/google-cloud/distributed-tracing-spring-boot-microservices-with-stackdriver-trace-7fe42c6de3f3
