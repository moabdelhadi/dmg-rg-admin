package com.dmg.admin;

import com.dmg.admin.auth.SessionHandler;
import com.dmg.admin.auth.util.UserUtil;
import com.dmg.admin.view.BillsView;
import com.dmg.admin.view.ChangePasswordView;
import com.dmg.admin.view.LoginView;
import com.dmg.admin.view.LogoutView;
import com.dmg.admin.view.RegistrationView;
import com.dmg.admin.view.StartView;
import com.dmg.admin.view.TransactionsView;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

@Theme("dmg-theme")
@SuppressWarnings("serial")
public class MainUI extends UI {

	private Navigator navigator;

	@Override
	protected void init(final VaadinRequest request) {
		navigator = new Navigator(this, this);
		navigator.addView("", new StartView(navigator));
		navigator.addView(TransactionsView.NAME, new TransactionsView());
		navigator.addView(BillsView.NAME, new BillsView());
		navigator.addView(LoginView.NAME, new LoginView(navigator, StartView.NAME));
		navigator.addView(RegistrationView.NAME, new RegistrationView(navigator));
		navigator.addView(ChangePasswordView.NAME, new ChangePasswordView(navigator));
		navigator.addView(LogoutView.NAME, new LogoutView(navigator));

		navigator.addViewChangeListener(new ViewChangeListener() {

			@Override
			public boolean beforeViewChange(ViewChangeEvent event) {
				String token = request.getParameter("token");
				if (token != null && !token.equals("")) {
					if (UserUtil.validateToken(token)) {
						navigator.getDisplay().showView(new RegistrationView(navigator));
						return false;
					}
				}
				if (SessionHandler.get() == null) {
					String fragmentAndParameters = event.getViewName();
					if (event.getParameters() != null) {
						fragmentAndParameters += "/";
						fragmentAndParameters += event.getParameters();
					}
					navigator.getDisplay().showView(new LoginView(navigator, fragmentAndParameters));
					return false;

				} else {
					return true;

				}

			}

			@Override
			public void afterViewChange(ViewChangeEvent event) {
				// TODO Auto-generated method stub

			}
		});
	}

}
