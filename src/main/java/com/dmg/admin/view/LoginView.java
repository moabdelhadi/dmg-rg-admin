package com.dmg.admin.view;

import javax.security.auth.login.AccountLockedException;

import com.dmg.admin.auth.SessionHandler;
import com.dmg.admin.auth.util.AuthenticationUtil;
import com.dmg.admin.exception.InvalidCredentialsException;
import com.dmg.core.exception.DataAccessLayerException;
import com.google.gwt.thirdparty.guava.common.base.Strings;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class LoginView extends VerticalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7076050714285118246L;
	public static final String NAME = "login";

	public LoginView(final Navigator navigator, final String fragmentAndParameters) {
		setSizeFull();
		// CustomLayout customLayout = new CustomLayout("login");
		/*
		 * Panel panel = new Panel("Login"); panel.set
		 */
		TabSheet tabSheet = new TabSheet();
		tabSheet.setWidth("30%");

		VerticalLayout vMainLayout = new VerticalLayout();
		VerticalLayout vFormLayout = new VerticalLayout();

		vMainLayout.addComponent(vFormLayout);
		vMainLayout.setComponentAlignment(vFormLayout, Alignment.MIDDLE_CENTER);

		tabSheet.addTab(vMainLayout, "Login", FontAwesome.USERS);
		tabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);

		vFormLayout.setStyleName("login-bg");
		vFormLayout.setMargin(true);
		vFormLayout.setSpacing(true);
		final TextField username = new TextField();
		username.setCaption("Username");
		username.setInputPrompt("username");
		username.setWidth("100%");

		username.addStyleName("inline-icon");
		username.setIcon(FontAwesome.USER);
		vFormLayout.addComponent(username);
		// vLayout.setComponentAlignment(username, Alignment.MIDDLE_CENTER);

		final PasswordField password = new PasswordField();
		password.setCaption("Password");
		password.setInputPrompt("Password");
		password.setWidth("100%");
		password.addStyleName("inline-icon");
		password.setIcon(FontAwesome.LOCK);
		vFormLayout.addComponent(password);
		// vLayout.setComponentAlignment(password, Alignment.MIDDLE_CENTER);

		final Button loginButton = new Button("Login");
		loginButton.setClickShortcut(KeyCode.ENTER);
		vFormLayout.addComponent(loginButton);
		vFormLayout.setComponentAlignment(loginButton, Alignment.MIDDLE_RIGHT);

		addComponent(tabSheet);

		setComponentAlignment(tabSheet, Alignment.MIDDLE_CENTER);

		loginButton.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				if (Strings.isNullOrEmpty(username.getValue()) || Strings.isNullOrEmpty(password.getValue())) {
					Notification.show("Error", "Please fill both Username and Password", Type.ERROR_MESSAGE);
					return;
				}

				try {
					AuthenticationUtil.authenticate(username.getValue(), password.getValue());
				} catch (AccountLockedException e) {
					Notification.show("Error", "Your account is locked please contact system admin", Type.ERROR_MESSAGE);
					return;
				} catch (InvalidCredentialsException e) {
					Notification.show("Error", "Invalid credentials", Type.ERROR_MESSAGE);
					return;
				} catch (DataAccessLayerException e) {
					Notification.show("Error", "System Error", Type.ERROR_MESSAGE);
					e.printStackTrace();
				}

				Notification.show("Welcome " + SessionHandler.get().getFullName(), "You have successfully logged in", Type.TRAY_NOTIFICATION);

				// navigate back to the intended place
				if (!fragmentAndParameters.contains(LogoutView.NAME)) {
					navigator.navigateTo(fragmentAndParameters);
				} else {
					navigator.navigateTo(StartView.NAME);
				}

			}
		});

	}

	@Override
	public void enter(ViewChangeEvent event) {
		if (SessionHandler.get() != null) {
			event.getNavigator().navigateTo(StartView.NAME);
		}
	}

}
