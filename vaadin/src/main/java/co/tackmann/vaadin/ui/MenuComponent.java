package co.tackmann.vaadin.ui;

import co.tackmann.vaadin.events.MenuEvent;

import com.google.gwt.thirdparty.guava.common.eventbus.EventBus;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class MenuComponent extends CustomComponent {
	public MenuComponent(EventBus eventBus) {
		VerticalLayout layout = new VerticalLayout();
		setCompositionRoot(layout);
		eventBus.register(this);
		initLayout(layout, eventBus);
	}

	private void initLayout(final VerticalLayout layout, final EventBus eventBus) {
		layout.addComponent(getTextInputButton(eventBus));
		layout.addComponent(getNotificationButton(eventBus));
	}
	
	private Button getTextInputButton(final EventBus eventBus) {
		// TODO i18n
		final Button button = new Button("Text input");
		button.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				eventBus.post(MenuEvent.TEXT_INPUT);
			}
		});
		return button;
	}
	
	private Button getNotificationButton(final EventBus eventBus) {
		// TODO i18n
		final Button button = new Button("Notifications");
		button.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				eventBus.post(MenuEvent.NOTIFICATION);
			}
		});
		return button;
	}
}
