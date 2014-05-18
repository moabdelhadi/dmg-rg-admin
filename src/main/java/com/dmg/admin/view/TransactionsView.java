package com.dmg.admin.view;

import java.util.Date;
import java.util.List;

import com.dmg.admin.bean.Transaction;
import com.dmg.admin.service.TransactionService;
import com.dmg.admin.ui.CustomFilterDecorator;
import com.dmg.admin.ui.CustomFilterGenerator;
import com.dmg.admin.ui.CustomPagedFilterControlConfig;
import com.dmg.admin.ui.CustomPagedFilterTable;
import com.dmg.admin.util.ViewUtil;
import com.dmg.core.exception.DataAccessLayerException;
import com.vaadin.addon.tableexport.CustomTableHolder;
import com.vaadin.addon.tableexport.ExcelExport;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
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

	public TransactionsView() {
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

		pagedTable.addContainerProperty("billNumber", String.class, null);
		pagedTable.addContainerProperty("accountNumber", String.class, null);
		pagedTable.addContainerProperty("paymentDate", Date.class, null);
		pagedTable.addContainerProperty("status", String.class, null);
		pagedTable.addContainerProperty("amount", Double.class, null);

		pagedTable.setColumnHeader("billNumber", "Bill Number");
		pagedTable.setColumnHeader("accountNumber", "Account Number");
		pagedTable.setColumnHeader("paymentDate", "Date");
		pagedTable.setColumnHeader("status", "Status");
		pagedTable.setColumnHeader("amount", "Amount");

		pagedTable.setContainerDataSource(container);
		pagedTable.setSizeFull();

		pagedTable.setVisibleColumns("billNumber", "accountNumber", "paymentDate", "status", "amount");

		pagedTable.setColumnExpandRatio("billNumber", 0.30F);
		pagedTable.setColumnExpandRatio("accountNumber", 0.30F);
		pagedTable.setColumnExpandRatio("paymentDate", 0.10F);
		pagedTable.setColumnExpandRatio("status", 0.10F);
		pagedTable.setColumnExpandRatio("amount", 0.20F);

		/*Grid grid = new Grid(pagedTable);
		grid.setExportEnabled(false);
		grid.resetFilters();*/
		setMargin(true);

		CustomPagedFilterControlConfig config = new CustomPagedFilterControlConfig();

		HorizontalLayout pagerControls = pagedTable.createControls(config);
		Button button = new Button("Export");
		button.setIcon(new ThemeResource(ViewUtil.EXCEL_ICON));
		button.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				CustomTableHolder pagedTableHolder = new CustomTableHolder(pagedTable);
				excelExport = new ExcelExport(pagedTableHolder);
				excelExport.excludeCollapsedColumns();
				excelExport.setReportTitle("Transactions Report");
				excelExport.export();
			}
		});
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
