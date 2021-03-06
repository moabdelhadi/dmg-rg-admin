package com.dmg.admin.view;

import java.util.Date;

import com.dmg.admin.auth.SessionHandler;
import com.dmg.admin.ui.ComponentUtil;
import com.dmg.admin.ui.CustomFilterDecorator;
import com.dmg.admin.ui.CustomFilterGenerator;
import com.dmg.admin.ui.CustomPagedFilterControlConfig;
import com.dmg.admin.ui.CustomPagedFilterTable;
import com.dmg.admin.util.ViewUtil;
import com.dmg.core.bean.ApproveStatusEnum;
import com.dmg.core.bean.Transaction;
import com.dmg.core.bean.TransactionAUH;
import com.dmg.core.bean.TransactionDu;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.tableexport.CustomTableHolder;
import com.vaadin.addon.tableexport.ExcelExport;
import com.vaadin.data.Container.ItemSetChangeEvent;
import com.vaadin.data.Container.ItemSetChangeListener;
import com.vaadin.event.ItemClickEvent;
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
import com.vaadin.ui.VerticalLayout;

public class TransactionsView extends VerticalLayout implements View {

	private static final int EXPORT_LIMIT = 5000;
	/**
	 * 
	 */
	private static final long serialVersionUID = 8536891631036164352L;
	public static final String NAME = "transactions";
	private CustomPagedFilterTable pagedTable;

	JPAContainer jpaContainer;
	//private List<Transaction> transactions;
	private ExcelExport excelExport;
	private final Navigator navigator;
	private Button editBtn;
	private String city = "";
	Button exportButton = new Button("Export");

	//	private final TransactionService transactionService;
//	BeanItemContainer<Transaction> container = new BeanItemContainer<Transaction>(Transaction.class);

	public TransactionsView(Navigator navigator) {
		this.navigator = navigator;
		initView();
//		transactionService = new TransactionService();
		setSizeFull();
		setSpacing(true);
	}
	
	private void initPagedTable() {
		city = SessionHandler.get().getCity();
		if ("DUBAI".equals(city)) {
			jpaContainer = JPAContainerFactory.makeBatchable(TransactionDu.class, "dmg-rg-admin");
			jpaContainer.sort(new String[]{"creationDate"}, new boolean[]{false});
		} else {
			jpaContainer = JPAContainerFactory.makeBatchable(TransactionAUH.class, "dmg-rg-admin");
			jpaContainer.sort(new String[]{"creationDate"}, new boolean[]{false});
		}
		
		pagedTable.setContainerDataSource(jpaContainer);
		pagedTable.setCaption("TransAction Found found (" + jpaContainer.size() + ")");
		pagedTable.setVisibleColumns("company", "contractNo", "status", "creationDate", "updateDate", "merchTxnRef", "receiptNo", "doubleAmount", "approveStatusEnum");
	}
	
	public void initView() {
		pagedTable = new CustomPagedFilterTable();
		pagedTable.setFilterDecorator(new CustomFilterDecorator());
		pagedTable.setFilterGenerator(new CustomFilterGenerator());
		pagedTable.setFilterBarVisible(true);
		pagedTable.setImmediate(true);

		pagedTable.addContainerProperty("company", String.class, null);
		pagedTable.addContainerProperty("contractNo", String.class, null);
		
//		pagedTable.addContainerProperty("city", String.class, null);
		pagedTable.addContainerProperty("status", String.class, null);
		pagedTable.addContainerProperty("creationDate", Date.class, null);
		pagedTable.addContainerProperty("updateDate", Date.class, null);
		pagedTable.addContainerProperty("merchTxnRef", String.class, null);
		pagedTable.addContainerProperty("receiptNo", String.class, null);
		pagedTable.addContainerProperty("doubleAmount", Double.class, null);
		pagedTable.addContainerProperty("approveStatusEnum", ApproveStatusEnum.class, null);

		pagedTable.setColumnHeader("company", "Company");
		pagedTable.setColumnHeader("contractNo", "Contract #.");
//		pagedTable.setColumnHeader("city", "City");
		pagedTable.setColumnHeader("status", "Status");
		pagedTable.setColumnHeader("creationDate", "Creation Date");
		pagedTable.setColumnHeader("updateDate", "Update Date");
		pagedTable.setColumnHeader("merchTxnRef", "Txn Ref.");
		pagedTable.setColumnHeader("receiptNo", "Receipt No");
		pagedTable.setColumnHeader("doubleAmount", "Amount");
		pagedTable.setColumnHeader("approveStatusEnum", "Approve Status");

//		pagedTable.setContainerDataSource(container);
		pagedTable.setSizeFull();

//		pagedTable.setVisibleColumns("contractNo",  "status", "creationDate", "updateDate", "merchTxnRef","receiptNo", "doubleAmount", "approveStatusEnum");

		pagedTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void itemClick(ItemClickEvent itemClickEvent) {
				editBtn.setVisible(true);
				editBtn.setDescription("Click here to edit a transaction!");
			}
		});
		
		pagedTable.addGeneratedColumn("approveStatusEnum", new ColumnGenerator() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 2467347978301925174L;

			@Override
			public HorizontalLayout generateCell(final CustomTable source, final Object itemId, Object columnId) {
				HorizontalLayout horizontalLayout = new HorizontalLayout();
				horizontalLayout.setSpacing(true);

				Transaction txn;
				if ("DUBAI".equals(city)) {
					txn = (TransactionDu) jpaContainer.getItem(itemId).getEntity();
				} else {
					txn = (TransactionAUH) jpaContainer.getItem(itemId).getEntity();
				}
				
				Label label = new Label();
				switch (txn.getApproveStatusEnum()) {

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

		pagedTable.setSelectable(true);

		setMargin(true);

		CustomPagedFilterControlConfig config = new CustomPagedFilterControlConfig();
		HorizontalLayout pagerControls = pagedTable.createControls(config);

		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setWidth("100%");

		pagedTable.addItemSetChangeListener(new ItemSetChangeListener() {

			@Override
			public void containerItemSetChange(ItemSetChangeEvent event) {
				int itemSize = jpaContainer.size();
				pagedTable.setCaption("Txns found (" + itemSize + ")");
				if (itemSize > EXPORT_LIMIT) {
					exportButton.setVisible(false);
				} else {
					exportButton.setVisible(true);
				}

			}
		});
		
		
		editBtn = new Button("Edit");
		editBtn.setIcon(new ThemeResource(ViewUtil.EDIT_ICON));

		editBtn.addClickListener(new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				navigator.navigateTo(UpdateTransactionView.NAME + "/" + ((Transaction) pagedTable.getValue()).getId());
			}

		});

		exportButton.setIcon(new ThemeResource(ViewUtil.EXCEL_ICON));
		exportButton.addClickListener(new ClickListener() {

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
				excelExport.setDoubleDataFormat("0.00");
				//CellStyle doubleDataStyle = excelExport.getDoubleDataStyle();
				excelExport.setExcelFormatOfProperty("doubleAmount", "0.00");
				excelExport.export();
			}
		});

		Label label = new Label();
		buttonLayout.addComponent(exportButton);
		buttonLayout.addComponent(label);
		buttonLayout.addComponent(editBtn);
		buttonLayout.setExpandRatio(exportButton, 0.10F);
		buttonLayout.setExpandRatio(label, 0.82F);
		buttonLayout.setExpandRatio(editBtn, 0.08F);

		addComponent(ComponentUtil.initMenuButton(navigator, StartView.NAME, "Go back to the main menu"));

		addComponent(buttonLayout);
		addComponent(pagedTable);
		addComponent(pagerControls);

		setExpandRatio(buttonLayout, 0.05F);
		setExpandRatio(pagedTable, 0.90F);
		setExpandRatio(pagerControls, 0.05F);
	}

//	private void reloadResult() {
//		try {
//			transactions = transactionService.listTransactions();
//		} catch (DataAccessLayerException e) {
//			Notification.show("Error", "There was a DB error while retrieving transactions please contact help disk", Type.ERROR_MESSAGE);
//		}
//		container.removeAllItems();
//		for (Transaction transaction : transactions) {
//			container.addBean(transaction);
//		}
//		pagedTable.setCurrentPage(1);
//		pagedTable.setCaption("Events found (" + transactions.size() + ")");
//		pagedTable.setImmediate(true);
//
//	}

	@Override
	public void enter(ViewChangeEvent event) {
		
		initPagedTable();
		editBtn.setVisible(false);
		//reloadResult();

	}

}
