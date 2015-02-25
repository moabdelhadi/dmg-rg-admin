package com.dmg.admin.view;

import java.util.Date;
import java.util.List;

import com.dmg.admin.bean.Transaction;
import com.dmg.admin.service.TransactionService;
import com.dmg.admin.ui.ComponentUtil;
import com.dmg.admin.ui.CustomFilterDecorator;
import com.dmg.admin.ui.CustomFilterGenerator;
import com.dmg.admin.ui.CustomPagedFilterControlConfig;
import com.dmg.admin.ui.CustomPagedFilterTable;
import com.dmg.admin.util.ApproveStatusEnum;
import com.dmg.admin.util.ViewUtil;
import com.dmg.core.exception.DataAccessLayerException;
import com.vaadin.addon.tableexport.CustomTableHolder;
import com.vaadin.addon.tableexport.ExcelExport;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomTable;
import com.vaadin.ui.CustomTable.ColumnGenerator;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.VerticalLayout;

public class TransactionsView extends VerticalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8536891631036164352L;

	public static final String NAME = "transactions";
	private CustomPagedFilterTable pagedTable;
	private List<Transaction> transactions;
	private final TransactionService transactionService;
	BeanItemContainer<Transaction> container = new BeanItemContainer<Transaction>(Transaction.class);
	private ExcelExport excelExport;
	private final Navigator navigator;

	public TransactionsView(Navigator navigator) {
		this.navigator = navigator;
		initView();
		transactionService = new TransactionService();
		setSizeFull();
		setSpacing(true);
	}

	public void initView() {
		pagedTable = new CustomPagedFilterTable();
		pagedTable.setFilterDecorator(new CustomFilterDecorator());
		pagedTable.setFilterGenerator(new CustomFilterGenerator());
		pagedTable.setFilterBarVisible(true);
		pagedTable.setImmediate(true);

		pagedTable.addContainerProperty("contractNo", String.class, null);
		pagedTable.addContainerProperty("city", String.class, null);
		pagedTable.addContainerProperty("status", String.class, null);
		pagedTable.addContainerProperty("creationDate", Date.class, null);
		pagedTable.addContainerProperty("updateDate", Date.class, null);
		pagedTable.addContainerProperty("merchTxnRef", String.class, null);
		pagedTable.addContainerProperty("amount", String.class, null);
		pagedTable.addContainerProperty("approveStatusEnum", ApproveStatusEnum.class, null);

		pagedTable.setColumnHeader("contractNo", "Contract #.");
		pagedTable.setColumnHeader("city", "City");
		pagedTable.setColumnHeader("status", "Status");
		pagedTable.setColumnHeader("creationDate", "Creation Date");
		pagedTable.setColumnHeader("updateDate", "Update Date");
		pagedTable.setColumnHeader("merchTxnRef", "Txn Ref.");
		pagedTable.setColumnHeader("amount", "Amount");
		pagedTable.setColumnHeader("approveStatusEnum", "Approve Status");

		pagedTable.setContainerDataSource(container);
		pagedTable.setSizeFull();

		pagedTable.setVisibleColumns("contractNo", "city", "status", "creationDate", "updateDate", "merchTxnRef", "amount", "approveStatusEnum");

		pagedTable.addGeneratedColumn("approveStatusEnum", new ColumnGenerator() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 2467347978301925174L;

			@Override
			public HorizontalLayout generateCell(final CustomTable source, final Object itemId, Object columnId) {
				HorizontalLayout horizontalLayout = new HorizontalLayout();
				horizontalLayout.setSpacing(true);

				Label label = new Label();
				switch (((Transaction) itemId).getApproveStatusEnum()) {
				case APPROVED:
					label.setCaption(ApproveStatusEnum.APPROVED.getName());
					label.setIcon(new ThemeResource("img/approved.png"));
					horizontalLayout.addComponent(label);
					horizontalLayout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);
					return horizontalLayout;
				case REJECTED:
					label.setCaption(ApproveStatusEnum.REJECTED.getName());
					label.setIcon(new ThemeResource("img/rejected.png"));
					horizontalLayout.addComponent(label);
					horizontalLayout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);
					return horizontalLayout;

				default:
					label.setCaption(ApproveStatusEnum.PENDING.getName());
					label.setIcon(new ThemeResource("img/pending.png"));
					horizontalLayout.addComponent(label);
					horizontalLayout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);
					return horizontalLayout;

				}

			}

		});

		/*
		 * pagedTable.setColumnExpandRatio("billNumber", 0.30F);
		 * pagedTable.setColumnExpandRatio("accountNumber", 0.30F);
		 * pagedTable.setColumnExpandRatio("paymentDate", 0.10F);
		 * pagedTable.setColumnExpandRatio("status", 0.10F);
		 * pagedTable.setColumnExpandRatio("amount", 0.20F);
		 */

		/*
		 * Grid grid = new Grid(pagedTable); grid.setExportEnabled(false);
		 * grid.resetFilters();
		 */
		setMargin(true);

		CustomPagedFilterControlConfig config = new CustomPagedFilterControlConfig();

		HorizontalLayout pagerControls = pagedTable.createControls(config);
		Button button = new Button("Export");
		button.setIcon(new ThemeResource(ViewUtil.EXCEL_ICON));
		button.addClickListener(new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				CustomTableHolder pagedTableHolder = new CustomTableHolder(pagedTable);
				excelExport = new ExcelExport(pagedTableHolder);
				excelExport.excludeCollapsedColumns();
				excelExport.setReportTitle("Transactions Report");
				excelExport.export();
			}
		});
		addComponent(ComponentUtil.initMenuButton(navigator, StartView.NAME, "Go back to the main menu"));
		addComponent(button);
		addComponent(pagedTable);
		addComponent(pagerControls);

		setExpandRatio(button, 0.05F);
		setExpandRatio(pagedTable, 0.90F);
		setExpandRatio(pagerControls, 0.05F);
	}

	private void reloadResult() {
		try {
			transactions = transactionService.listTransactions();
		} catch (DataAccessLayerException e) {
			Notification.show("Error", "There was a DB error while retrieving transactions please contact help disk", Type.ERROR_MESSAGE);
		}
		container.removeAllItems();
		for (Transaction transaction : transactions) {
			container.addBean(transaction);
		}
		pagedTable.setCurrentPage(1);
		pagedTable.setCaption("Events found (" + transactions.size() + ")");
		pagedTable.setImmediate(true);

	}

	@Override
	public void enter(ViewChangeEvent event) {

		reloadResult();

	}

}
