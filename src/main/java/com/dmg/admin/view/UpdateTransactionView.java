package com.dmg.admin.view;

import com.dmg.admin.service.TransactionService;
import com.dmg.admin.ui.ComponentUtil;
import com.dmg.core.bean.ApproveStatusEnum;
import com.dmg.core.bean.Transaction;
import com.dmg.core.exception.DataAccessLayerException;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class UpdateTransactionView extends VerticalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2710413237601726018L;
	private final Navigator navigator;
	public static final String NAME = "update-transaction";
	private final TransactionService transactionService;
	private String id;
	private Transaction transation;
	private Panel panel;
	private Button updateBtn;

	private FormLayout layout;
	TextField contractNoField = new TextField("Contract No.");
	TextField cityField = new TextField("City");
	TextField statusField = new TextField("Status");
	TextField amountField = new TextField("Amount");
	TextField feesField = new TextField("Fees");
	ComboBox approveStatus = new ComboBox("Approve status");

	public UpdateTransactionView(Navigator navigator) {
		this.navigator = navigator;
		transactionService = new TransactionService();
		setSizeFull();
		setSpacing(true);
		setMargin(true);
		initView();
		initCustomLayout();
	}

	private void initView() {
		panel = new Panel("Update Transaction");

		panel.setWidth("35%");
		addComponent(ComponentUtil.initMenuButton(navigator, TransactionsView.NAME, "Go back to transactions view"));
		addComponent(panel);
		setExpandRatio(panel, 0.95F);
		setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
		updateBtn = new Button("Update");

		updateBtn.addClickListener(new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				try {
					if (approveStatus.getValue() != transation.getApproveStatusEnum().getName()) {
						transation.setSyncStatus(2);
						transation.setApproveStatus(ApproveStatusEnum.findStatus(approveStatus.getValue().toString()));
					}

					transactionService.update(transation);
					Notification.show("Transaction has been updated successfully!", Type.HUMANIZED_MESSAGE);
				} catch (DataAccessLayerException e) {
					Notification.show("DB ERROR when saving the changes!", Type.ERROR_MESSAGE);
				}
			}
		});
	}

	private void initCustomLayout() {
		layout = new FormLayout();
		layout.addStyleName("top-bottom-margins");

		layout.setWidth("100%");
		layout.setMargin(true);

		contractNoField.setWidth("100%");
		cityField.setWidth("100%");
		statusField.setWidth("100%");
		amountField.setWidth("100%");
		feesField.setWidth("100%");
		// setting default values for null
		contractNoField.setNullRepresentation("");
		cityField.setNullRepresentation("");
		statusField.setNullRepresentation("");
		amountField.setNullRepresentation("");
		feesField.setNullRepresentation("");

		contractNoField.setEnabled(false);
		cityField.setEnabled(false);
		statusField.setEnabled(false);
		amountField.setEnabled(false);
		feesField.setEnabled(false);

		approveStatus.addItem(ApproveStatusEnum.APPROVED.getName());
		approveStatus.setItemIcon(ApproveStatusEnum.APPROVED.getName(), new ThemeResource("img/approved.png"));

		approveStatus.addItem(ApproveStatusEnum.REJECTED.getName());
		approveStatus.setItemIcon(ApproveStatusEnum.REJECTED.getName(), new ThemeResource("img/rejected.png"));

		approveStatus.addItem(ApproveStatusEnum.PENDING.getName());
		approveStatus.setItemIcon(ApproveStatusEnum.PENDING.getName(), new ThemeResource("img/pending.png"));

		layout.addComponent(contractNoField);
		layout.addComponent(cityField);
		layout.addComponent(statusField);
		layout.addComponent(amountField);
		layout.addComponent(feesField);
		layout.addComponent(approveStatus);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		id = event.getParameters();
		try {
			transation = transactionService.getTransaction(Long.parseLong(id));
			contractNoField.setValue(transation.getContractNo());
			cityField.setValue(transation.getCity());
			statusField.setValue(transation.getStatus());
			amountField.setValue(String.valueOf(transation.getDoubleAmount()));
			feesField.setValue(transation.getFees().toString());
			approveStatus.setValue(transation.getApproveStatusEnum().getName());

			layout.addComponent(updateBtn);

			panel.setContent(layout);
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
