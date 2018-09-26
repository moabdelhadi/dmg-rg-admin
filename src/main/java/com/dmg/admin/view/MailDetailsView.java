package com.dmg.admin.view;

import com.dmg.admin.service.BillService;
import com.dmg.admin.service.SendEmailService;
import com.dmg.admin.ui.BillDisplayLayout;
import com.dmg.admin.ui.ComponentUtil;
import com.dmg.admin.ui.MailStausDisplayLayout;
import com.dmg.core.bean.Bill;
import com.dmg.core.bean.SendInv;
import com.dmg.core.exception.DataAccessLayerException;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class MailDetailsView extends VerticalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2710413237601726018L;
	private final Navigator navigator;
	public static final String NAME = "mail-details";
	private final SendEmailService sendMailService;
	private String id;
	private SendInv mail;
	private Panel panel;

	private MailStausDisplayLayout maildetailDisplayLayout;

	public MailDetailsView(Navigator navigator) {
		this.navigator = navigator;
		sendMailService = new SendEmailService();
		setSizeFull();
		setSpacing(true);
		setMargin(true);
		initView();
	}

	private void initView() {
		panel = new Panel("EMail Details");
		panel.setWidth("80%");
		addComponent(ComponentUtil.initMenuButton(navigator, SendEmailStatusList.NAME, "Go back to mails view"));
		addComponent(panel);
		setExpandRatio(panel, 0.95F);
		setComponentAlignment(panel, Alignment.MIDDLE_CENTER);

	}

	@Override
	public void enter(ViewChangeEvent event) {
		id = event.getParameters();
		try {
			mail = sendMailService.getMail(Long.parseLong(id));

			maildetailDisplayLayout = new MailStausDisplayLayout(mail);

			panel.setContent(billDisplayLayout);
		} catch (NumberFormatException | DataAccessLayerException e) {
			if (e instanceof NumberFormatException) {
				Notification.show("ID entered is not correct", Type.ERROR_MESSAGE);
				navigator.navigateTo("");
			} else {
				Notification.show("DB error - Please contact system admin", Type.ERROR_MESSAGE);
			}
		}
	}
}
