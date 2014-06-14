package com.dmg.admin.view;

import com.dmg.admin.auth.SessionHandler;
import com.dmg.admin.auth.util.UserUtil;
import com.dmg.admin.exception.InvalidCredentialsException;
import com.dmg.admin.exception.PasswordRequirementException;
import com.dmg.admin.exception.PasswordsDoNotMatchException;
import com.dmg.admin.exception.TooShortPasswordException;
import com.dmg.admin.ui.ComponentUtil;
import com.dmg.core.exception.DataAccessLayerException;
import com.google.gwt.thirdparty.guava.common.base.Strings;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Runo;

public class ChangePasswordView extends VerticalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7076050714285118246L;
	public static final String NAME = "changePassword";
	private final Navigator navigator;

	public ChangePasswordView(final Navigator navigator) {
		setSizeFull();
		this.navigator = navigator;
		setMargin(true);
	}

	private void initView() {
		CustomLayout customLayout = new CustomLayout("changePassword");
		customLayout.setWidth("30%");
		final PasswordField oldPassword = new PasswordField();
		oldPassword.setInputPrompt("password");
		oldPassword.setWidth("100%");
		customLayout.addComponent(oldPassword, "oldPassword");

		final PasswordField newPassword = new PasswordField();
		newPassword.setInputPrompt("password");
		newPassword.setWidth("100%");
		customLayout.addComponent(newPassword, "newPassword");

		final PasswordField newPasswordVeri = new PasswordField();
		newPasswordVeri.setInputPrompt("password");
		newPasswordVeri.setWidth("100%");
		customLayout.addComponent(newPasswordVeri, "newPasswordVeri");

		final Button loginButton = new Button("Modify");
		loginButton.addStyleName(Runo.BUTTON_BIG);
		loginButton.setClickShortcut(KeyCode.ENTER);
		customLayout.addComponent(loginButton, "modifyButton");

		addComponent(ComponentUtil.initMenuButton(navigator, StartView.NAME, "Go back to the main menu"));
		addComponent(customLayout);

		setExpandRatio(customLayout, 0.95F);

		setComponentAlignment(customLayout, Alignment.MIDDLE_CENTER);

		loginButton.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				if (Strings.isNullOrEmpty(oldPassword.getValue()) || Strings.isNullOrEmpty(newPassword.getValue()) || Strings.isNullOrEmpty(newPasswordVeri.getValue())) {
					Notification.show("Error", "Please fill all the fields", Type.ERROR_MESSAGE);
					return;
				}

				try {
					UserUtil.changePassword(SessionHandler.get(), oldPassword.getValue(), newPassword.getValue(), newPasswordVeri.getValue());
				} catch (InvalidCredentialsException e) {
					Notification.show("Error", "Invalid credentials", Type.ERROR_MESSAGE);
					return;
				} catch (TooShortPasswordException e) {
					Notification.show("Error", "New Password is too short", Type.ERROR_MESSAGE);
					return;
				} catch (PasswordsDoNotMatchException e) {
					Notification.show("Error", "Passwords do not match", Type.ERROR_MESSAGE);
					return;
				} catch (PasswordRequirementException e) {
					Notification.show("Error", e.getMessage(), Type.ERROR_MESSAGE);
					return;
				} catch (DataAccessLayerException e) {
					Notification.show("Error", "System Error", Type.ERROR_MESSAGE);
					return;
				}

				// navigate back to the intended place
				Notification.show("Success", SessionHandler.get().getFullName() + ", your password has been changed", Type.TRAY_NOTIFICATION);
				navigator.navigateTo(StartView.NAME);
			}
		});
	}

	@Override
	public void enter(ViewChangeEvent event) {
		removeAllComponents();
		initView();

	}

}
