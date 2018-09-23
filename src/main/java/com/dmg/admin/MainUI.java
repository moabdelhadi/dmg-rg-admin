package com.dmg.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dmg.admin.auth.SessionHandler;
import com.dmg.admin.auth.util.UserUtil;
import com.dmg.admin.view.BillsView;
import com.dmg.admin.view.ChangePasswordView;
import com.dmg.admin.view.GasMeterView;
import com.dmg.admin.view.LoginView;
import com.dmg.admin.view.LogoutView;
import com.dmg.admin.view.NewRequestsView;
import com.dmg.admin.view.RegistrationView;
import com.dmg.admin.view.SendInvEmailTemplateView;
import com.dmg.admin.view.SendInvEmailView;
import com.dmg.admin.view.StartView;
import com.dmg.admin.view.TransactionsView;
import com.dmg.admin.view.UpdateGasMeterView;
import com.dmg.admin.view.UpdateTransactionView;
import com.dmg.admin.view.UpdateUserView;
import com.dmg.admin.view.UsersView;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.shared.ui.ui.Transport;
import com.vaadin.ui.UI;

@Theme("dmg-theme")
@SuppressWarnings("serial")
@Push(value = PushMode.MANUAL, transport = Transport.LONG_POLLING)
public class MainUI extends UI {

	private Navigator navigator;
	private static Logger log = LoggerFactory.getLogger(MainUI.class);

	@Override
	protected void init(final VaadinRequest request) {
		
		navigator = new Navigator(this, this);
		navigator.addView("", new StartView(navigator));
		navigator.addView(TransactionsView.NAME, new TransactionsView(navigator));
		navigator.addView(BillsView.NAME, new BillsView(navigator));
		navigator.addView(UsersView.NAME, new UsersView(navigator));
		navigator.addView(GasMeterView.NAME, new GasMeterView(navigator));
		navigator.addView(UpdateGasMeterView.NAME, new UpdateGasMeterView(navigator));
		navigator.addView(UpdateUserView.NAME, new UpdateUserView(navigator));
		navigator.addView(UpdateUserView.NAME, new UpdateUserView(navigator));
		navigator.addView(UpdateTransactionView.NAME, new UpdateTransactionView(navigator));
		navigator.addView(LoginView.NAME, new LoginView(navigator, StartView.NAME));
		navigator.addView(RegistrationView.NAME, new RegistrationView(navigator));
		navigator.addView(ChangePasswordView.NAME, new ChangePasswordView(navigator));
		navigator.addView(LogoutView.NAME, new LogoutView(navigator));
		navigator.addView(SendInvEmailView.NAME, new SendInvEmailView(navigator));
		navigator.addView(SendInvEmailTemplateView.NAME, new SendInvEmailTemplateView(navigator));

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
				if (SessionHandler.get() == null || SessionHandler.get().getCity() == null) {
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
