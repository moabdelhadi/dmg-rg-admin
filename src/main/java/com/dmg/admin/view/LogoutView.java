package com.dmg.admin.view;

import com.dmg.admin.auth.SessionHandler;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;

public class LogoutView extends VerticalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7076050714285118246L;
	public static final String NAME = "logout";

	public LogoutView(final Navigator navigator) {

	}

	@Override
	public void enter(ViewChangeEvent event) {
		SessionHandler.logout();
		event.getNavigator().navigateTo(StartView.NAME);
	}

}
