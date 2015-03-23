package com.dmg.admin.view;

import com.dmg.admin.service.BillService;
import com.dmg.admin.ui.BillDisplayLayout;
import com.dmg.admin.ui.ComponentUtil;
import com.dmg.core.bean.Bill;
import com.dmg.core.exception.DataAccessLayerException;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class BillDetailsView extends VerticalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2710413237601726018L;
	private final Navigator navigator;
	public static final String NAME = "bill-details";
	private final BillService billService;
	private String id;
	private Bill bill;
	private Panel panel;

	private BillDisplayLayout billDisplayLayout;

	public BillDetailsView(Navigator navigator) {
		this.navigator = navigator;
		billService = new BillService();
		setSizeFull();
		setSpacing(true);
		setMargin(true);
		initView();
	}

	private void initView() {
		panel = new Panel("Bill Details");
		panel.setWidth("80%");
		addComponent(ComponentUtil.initMenuButton(navigator, BillsView.NAME, "Go back to bills view"));
		addComponent(panel);
		setExpandRatio(panel, 0.95F);
		setComponentAlignment(panel, Alignment.MIDDLE_CENTER);

	}

	@Override
	public void enter(ViewChangeEvent event) {
		id = event.getParameters();
		try {
			bill = billService.getBill(Long.parseLong(id));

			billDisplayLayout = new BillDisplayLayout(bill);

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
