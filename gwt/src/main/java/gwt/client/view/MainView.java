package co.tackmann.gwt.client.view;

import co.tackmann.gwt.client.resource.i18n.Messages;
import co.tackmann.gwt.client.resource.i18n.RemoteMessages;
import co.tackmann.gwt.client.view.presenter.MainViewPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.*;

import java.util.Collection;

public class MainView extends Composite implements MainViewPresenter.Display {
    private static MainViewUiBinder uiBinder = GWT.create(MainViewUiBinder.class);

    @UiTemplate("mainView.ui.xml")
    interface MainViewUiBinder extends UiBinder<HTMLPanel, MainView> { }

    private final Messages messages;
    private final RemoteMessages remoteMessages;

    @UiField
    HeaderComponent headerComponent;

    public MainView() {
        initWidget(uiBinder.createAndBindUi(this));
        messages = GWT.create(Messages.class);
        remoteMessages = new RemoteMessages();

        populateBanner();
    }

    private void populateBanner() {
        Collection<String> bannerMessages = remoteMessages.bannerMessages();
        
        // add messages to banner
        /*for(String bannerMsg : bannerMessages) {
            bannerPanel.add(HTMLHelper.asH2(bannerMsg));
        }
         bannerPanel.showWidget(0);
        */
        // create timer that shifts messages
        /*Timer timer = new Timer() {
            public void run() {
                int index = bannerPanel.getVisibleWidget();
                index++;
                if (index == bannerPanel.getWidgetCount()) {
                    index = 0;
                }
                bannerPanel.showWidget(index);
            }
        };
        timer.scheduleRepeating(10000);
        */
    }
    
    @Override
    public Widget asWidget() {
        return this;
    }
}







