package com.dmg.admin.view;

import java.util.Date;
import java.util.List;

import com.dmg.admin.bean.Bill;
import com.dmg.admin.service.BillService;
import com.dmg.admin.ui.ComponentUtil;
import com.dmg.admin.ui.CustomFilterDecorator;
import com.dmg.admin.ui.CustomFilterGenerator;
import com.dmg.admin.ui.CustomPagedFilterControlConfig;
import com.dmg.admin.ui.CustomPagedFilterTable;
import com.dmg.admin.util.ViewUtil;
import com.dmg.core.exception.DataAccessLayerException;
import com.vaadin.addon.tableexport.CustomTableHolder;
import com.vaadin.addon.tableexport.ExcelExport;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomTable;
import com.vaadin.ui.CustomTable.ColumnGenerator;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
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
	private ExcelExport excelExport;
	private final Navigator navigator;

	public BillsView(Navigator navigator) {
		this.navigator = navigator;
		initView();
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

		pagedTable.addContainerProperty("billNumber", String.class, null);
		pagedTable.addContainerProperty("accountNumber", String.class, null);
		pagedTable.addContainerProperty("fromDate", Date.class, null);
		pagedTable.addContainerProperty("toDate", Date.class, null);
		pagedTable.addContainerProperty("amount", Double.class, null);
		pagedTable.addContainerProperty("payed", Boolean.class, null);

		pagedTable.setColumnHeader("billNumber", "Bill Number");
		pagedTable.setColumnHeader("accountNumber", "Account Number");
		pagedTable.setColumnHeader("fromDate", "From");
		pagedTable.setColumnHeader("toDate", "To");
		pagedTable.setColumnHeader("amount", "Amount");
		pagedTable.setColumnHeader("payed", "Payed");

		pagedTable.setContainerDataSource(container);
		pagedTable.setSizeFull();

		pagedTable.setVisibleColumns("billNumber", "accountNumber", "fromDate", "toDate", "amount", "payed");
		pagedTable.addGeneratedColumn("payed", new ColumnGenerator() {

			@Override
			public Image generateCell(final CustomTable source, final Object itemId, Object columnId) {

				if (((Bill) itemId).isPayed()) {
					Image img = new Image(null, new ThemeResource("../runo/icons/16/ok.png"));
					return img;
				} else {
					Image img = new Image(null, new ThemeResource("../runo/icons/16/cancel.png"));
					return img;

				}

			}

		});

		pagedTable.setColumnExpandRatio("billNumber", 0.20F);
		pagedTable.setColumnExpandRatio("accountNumber", 0.20F);
		pagedTable.setColumnExpandRatio("fromDate", 0.15F);
		pagedTable.setColumnExpandRatio("toDate", 0.15F);
		pagedTable.setColumnExpandRatio("amount", 0.20F);
		pagedTable.setColumnExpandRatio("payed", 0.10F);

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

			@Override
			public void buttonClick(ClickEvent event) {
				CustomTableHolder pagedTableHolder = new CustomTableHolder(pagedTable);
				excelExport = new ExcelExport(pagedTableHolder);
				excelExport.excludeCollapsedColumns();
				excelExport.setReportTitle("Bills Report");
				excelExport.export();
			}
		});
		addComponent(ComponentUtil.initMenuButton(navigator, ""));
		addComponent(button);
		addComponent(pagedTable);
		addComponent(pagerControls);

		setExpandRatio(button, 0.05F);
		setExpandRatio(pagedTable, 0.90F);
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

		reloadResult();

	}

}
