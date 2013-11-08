package gwtDemo.client.service;

import java.util.Set;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserServiceAsync {
	void getLanguages(AsyncCallback<Set<String>> callback);

	void getCurrentLanguage(AsyncCallback<String> callback);
}