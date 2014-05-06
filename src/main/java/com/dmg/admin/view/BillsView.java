package com.dmg.admin.view;

import java.util.Date;
import java.util.List;

import com.dmg.admin.bean.Bill;
import com.dmg.admin.service.BillService;
import com.dmg.admin.ui.CustomFilterDecorator;
import com.dmg.admin.ui.CustomFilterGenerator;
import com.dmg.admin.ui.CustomPagedFilterControlConfig;
import com.dmg.admin.ui.CustomPagedFilterTable;
import com.dmg.core.exception.DataAccessLayerException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.VerticalLayout;

public class BillsView extends VerticalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4787817640065298241L;
	public static final String NAME = "bills";
	private CustomPagedFilterTable pagedTable;
	private List<Bill> bills;
	private final BillService billService;
	BeanItemContainer<Bill> container = new BeanItemContainer<Bill>(Bill.class);

	public BillsView() {
		billService = new BillService();
		setSizeFull();
		setSpacing(true);
	}

	public void initView() {
		pagedTable = new CustomPagedFilterTable();
		pagedTable.setFilterDecorator(new CustomFilterDecorator());
		pagedTable.setFilterGenerator(new CustomFilterGenerator());
		pagedTable.setFilterBarVisible(true);
		pagedTable.setImmediate(true);

		pagedTable.addContainerProperty("billNumber", Integer.class, null);
		pagedTable.addContainerProperty("accountNumber", String.class, null);
		pagedTable.addContainerProperty("fromDate", Date.class, null);
		pagedTable.addContainerProperty("toDate", Date.class, null);
		pagedTable.addContainerProperty("amount", Integer.class, null);

		pagedTable.setColumnHeader("billNumber", "Bill Number");
		pagedTable.setColumnHeader("accountNumber", "Account Number");
		pagedTable.setColumnHeader("fromDate", "From");
		pagedTable.setColumnHeader("toDate", "To");
		pagedTable.setColumnHeader("amount", "Amount");

		pagedTable.setContainerDataSource(container);
		pagedTable.setSizeFull();

		pagedTable.setVisibleColumns("billNumber", "accountNumber", "fromDate", "toDate", "amount");

		pagedTable.setColumnExpandRatio("billNumber", 0.30F);
		pagedTable.setColumnExpandRatio("accountNumber", 0.30F);
		pagedTable.setColumnExpandRatio("fromDate", 0.10F);
		pagedTable.setColumnExpandRatio("toDate", 0.10F);
		pagedTable.setColumnExpandRatio("amount", 0.20F);

		/*Grid grid = new Grid(pagedTable);
		grid.setExportEnabled(false);
		grid.resetFilters();*/
		setMargin(true);

		CustomPagedFilterControlConfig config = new CustomPagedFilterControlConfig();

		HorizontalLayout pagerControls = pagedTable.createControls(config);

		addComponent(pagedTable);
		addComponent(pagerControls);

		setExpandRatio(pagedTable, 0.80F);
		setExpandRatio(pagerControls, 0.05F);
	}

	private void reloadResult() {
		try {
			bills = billService.listBills();
		} catch (DataAccessLayerException e) {
			Notification.show("Error", "There was a DB error while retrieving events please contact help disk", Type.ERROR_MESSAGE);
		}
		container.removeAllItems();
		for (Bill event : bills) {
			container.addBean(event);
		}
		pagedTable.setCurrentPage(1);
		pagedTable.setCaption("Events found (" + bills.size() + ")");
		pagedTable.setImmediate(true);

	}

	@Override
	public void enter(ViewChangeEvent event) {
		initView();
		reloadResult();

	}

}
