package gwtDemo.client;

import gwtDemo.client.framework.AppController;
import gwtDemo.client.framework.AppInjector;
import gwtDemo.client.framework.api.ClientSession;
import gwtDemo.client.framework.api.ClientSessionProvider;
import gwtDemo.client.framework.api.NavigationManager;
import gwtDemo.client.framework.api.NavigationManagerProvider;
import gwtDemo.shared.User;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.RootPanel;

public class AppEntryPoint implements EntryPoint {
	private final AppInjector injector = GWT.create(AppInjector.class);
	
    @Override
	public void onModuleLoad() {
    	NavigationManagerProvider.INSTANCE = new NavigationManager(RootPanel.get(), injector);
    	ClientSessionProvider.INSTANCE = new ClientSession(new User());
    	
        // start application
        AppController appController = new AppController(injector);
        appController.start();
    }
}