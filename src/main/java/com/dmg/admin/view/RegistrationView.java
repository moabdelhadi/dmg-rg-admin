package com.dmg.admin.view;

import com.dmg.admin.auth.SessionHandler;
import com.dmg.admin.auth.util.UserUtil;
import com.dmg.admin.bean.User;
import com.dmg.admin.exception.EmailInvalidException;
import com.dmg.admin.exception.PasswordRequirementException;
import com.dmg.admin.exception.PasswordsDoNotMatchException;
import com.dmg.admin.exception.TooShortPasswordException;
import com.dmg.admin.exception.TooShortUsernameException;
import com.dmg.admin.exception.UsernameExistsException;
import com.dmg.core.exception.DataAccessLayerException;
import com.google.gwt.thirdparty.guava.common.base.Strings;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Runo;

public class RegistrationView extends VerticalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7076050714285118246L;
	public static final String NAME = "register";

	public RegistrationView(final Navigator navigator) {
		setSizeFull();
		Panel panel = new Panel();
		panel.setWidth("30%");
		FormLayout formLayout = new FormLayout();
		panel.setContent(formLayout);
		panel.setCaption("Resgister New Admin User");
		formLayout.setWidth("100%");
		formLayout.setMargin(true);

		final TextField fullName = new TextField("Full Name");

		fullName.setInputPrompt("Full Name");
		fullName.setWidth("150%");
		formLayout.addComponent(fullName);

		final TextField username = new TextField("Username");

		username.setInputPrompt("Username");
		username.setWidth("150%");
		formLayout.addComponent(username);

		final PasswordField password = new PasswordField("Password");

		password.setInputPrompt("password");
		password.setWidth("150%");
		formLayout.addComponent(password);

		final PasswordField passwordVer = new PasswordField("Password Again");

		passwordVer.setInputPrompt("password");
		passwordVer.setWidth("150%");
		formLayout.addComponent(passwordVer);

		final TextField email = new TextField("Email");

		email.setInputPrompt("Email address");
		email.setWidth("150%");
		formLayout.addComponent(email);

		final Button loginButton = new Button("Register");
		loginButton.addStyleName(Runo.BUTTON_BIG);
		formLayout.addComponent(loginButton);

		addComponent(panel);

		setComponentAlignment(panel, Alignment.MIDDLE_CENTER);

		loginButton.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				User user = null;

				if (Strings.isNullOrEmpty(fullName.getValue()) || Strings.isNullOrEmpty(username.getValue()) || Strings.isNullOrEmpty(password.getValue())
						|| Strings.isNullOrEmpty(passwordVer.getValue()) || Strings.isNullOrEmpty(email.getValue())) {
					Notification.show("Error", "Please fill all the fields", Type.ERROR_MESSAGE);
					return;
				}
				try {
					user = UserUtil.registerUser(username.getValue(), password.getValue(), passwordVer.getValue(), email.getValue(), fullName.getValue());
				} catch (TooShortPasswordException e) {
					Notification.show("Error", "Password is too short", Type.ERROR_MESSAGE);
					return;

				} catch (TooShortUsernameException e) {
					Notification.show("Error", "Username is too short", Type.ERROR_MESSAGE);
					return;

				} catch (PasswordsDoNotMatchException e) {
					Notification.show("Error", "Passwords do not match", Type.ERROR_MESSAGE);
					return;
				} catch (UsernameExistsException e) {
					Notification.show("Error", "Username already exists", Type.ERROR_MESSAGE);
					return;
				} catch (PasswordRequirementException e) {
					Notification.show("Error", e.getMessage(), Type.ERROR_MESSAGE);
					return;
				} catch (EmailInvalidException e) {
					Notification.show("Error", "Email is invalid", Type.ERROR_MESSAGE);
					return;
				} catch (DataAccessLayerException e) {
					Notification.show("Error", "System Error", Type.ERROR_MESSAGE);
					e.printStackTrace();
				}

				SessionHandler.setUser(user);

				// navigate back to the intended place
				navigator.navigateTo(StartView.NAME);
			}

		});

	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

}
