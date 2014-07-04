package com.dmg.admin.view;

import java.math.BigDecimal;
import java.util.List;

import com.dmg.admin.bean.Bill;
import com.dmg.admin.service.BillService;
import com.dmg.admin.ui.BillDisplayLayout;
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
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

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
	private Button detailsBtn;
	private final Window billWindow = new Window();

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

		pagedTable.addContainerProperty("contractNo", String.class, null);
		pagedTable.addContainerProperty("partyName", String.class, null);
		pagedTable.addContainerProperty("buildingName", String.class, null);
		pagedTable.addContainerProperty("apartmentCode", String.class, null);
		pagedTable.addContainerProperty("amt", BigDecimal.class, null);
		pagedTable.addContainerProperty("totalAmt", BigDecimal.class, null);
		pagedTable.addContainerProperty("receivedAmt", BigDecimal.class, null);
		pagedTable.addContainerProperty("lastRecAmt", BigDecimal.class, null);
		pagedTable.addContainerProperty("otherAmt", BigDecimal.class, null);
		pagedTable.addContainerProperty("currReading", BigDecimal.class, null);
		pagedTable.addContainerProperty("lastReading", BigDecimal.class, null);

		pagedTable.setColumnHeader("contractNo", "contract #");
		pagedTable.setColumnHeader("partyName", "Party Name");
		pagedTable.setColumnHeader("buildingName", "Building Name");
		pagedTable.setColumnHeader("apartmentCode", "Apartment Code");
		pagedTable.setColumnHeader("amt", "Amount");
		pagedTable.setColumnHeader("totalAmt", "Total Amount");
		pagedTable.setColumnHeader("receivedAmt", "Received Amount");
		pagedTable.setColumnHeader("lastRecAmt", "Last Received Amount");
		pagedTable.setColumnHeader("otherAmt", "Other Amount");
		pagedTable.setColumnHeader("monthlyFee", "Monthly Fee");
		pagedTable.setColumnHeader("currReading", "Current Reading");
		pagedTable.setColumnHeader("lastReading", "Last Reading");

		pagedTable.setContainerDataSource(container);
		pagedTable.setSizeFull();

		pagedTable
				.setVisibleColumns("contractNo", "partyName", "buildingName", "apartmentCode", "amt", "totalAmt", "receivedAmt", "lastRecAmt", "otherAmt", "monthlyFee", "currReading", "lastReading");

		pagedTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
			@Override
			public void itemClick(ItemClickEvent itemClickEvent) {
				detailsBtn.setVisible(true);

			}
		});

		pagedTable.setSelectable(true);
		/*
		 * pagedTable.addGeneratedColumn("payed", new ColumnGenerator() {
		 * 
		 * @Override public Image generateCell(final CustomTable source, final
		 * Object itemId, Object columnId) {
		 * 
		 * if (((Bill) itemId).isPayed()) { Image img = new Image(null, new
		 * ThemeResource("../runo/icons/16/ok.png")); return img; } else { Image
		 * img = new Image(null, new
		 * ThemeResource("../runo/icons/16/cancel.png")); return img;
		 * 
		 * }
		 * 
		 * }
		 * 
		 * });
		 */

		/*
		 * pagedTable.setColumnExpandRatio("contractNo", 0.20F);
		 * pagedTable.setColumnExpandRatio("partyName", 0.20F);
		 * pagedTable.setColumnExpandRatio("buildingName", 0.15F);
		 * pagedTable.setColumnExpandRatio("apartmentCode", 0.15F);
		 * pagedTable.setColumnExpandRatio("amt", 0.20F);
		 * pagedTable.setColumnExpandRatio("totalAmt", 0.10F);
		 * pagedTable.setColumnExpandRatio("receivedAmt", 0.10F);
		 * pagedTable.setColumnExpandRatio("lastReaceivedAmt", 0.10F);
		 * pagedTable.setColumnExpandRatio("otherAmount", 0.10F);
		 * pagedTable.setColumnExpandRatio("monthlyFee", 0.10F);
		 * pagedTable.setColumnExpandRatio("currReading", 0.10F);
		 * pagedTable.setColumnExpandRatio("lastReading", 0.10F);
		 */

		/*
		 * Grid grid = new Grid(pagedTable); grid.setExportEnabled(false);
		 * grid.resetFilters();
		 */
		setMargin(true);
		CustomPagedFilterControlConfig config = new CustomPagedFilterControlConfig();
		HorizontalLayout pagerControls = pagedTable.createControls(config);

		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setWidth("100%");

		detailsBtn = new Button("Details");
		detailsBtn.setIcon(new ThemeResource(ViewUtil.EDIT_ICON));

		detailsBtn.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				openDetails();
				// navigator.navigateTo(BillDetailsView.NAME + "/" + ((Bill)
				// pagedTable.getValue()).getId());

			}

		});

		Button exportBtn = new Button("Export");
		exportBtn.setIcon(new ThemeResource(ViewUtil.EXCEL_ICON));
		exportBtn.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				CustomTableHolder pagedTableHolder = new CustomTableHolder(pagedTable);
				excelExport = new ExcelExport(pagedTableHolder);
				excelExport.excludeCollapsedColumns();
				excelExport.setReportTitle("Bills Report");
				excelExport.export();
			}
		});
		Label label = new Label();
		buttonLayout.addComponent(exportBtn);
		buttonLayout.addComponent(label);
		buttonLayout.addComponent(detailsBtn);
		buttonLayout.setExpandRatio(exportBtn, 0.10F);
		buttonLayout.setExpandRatio(label, 0.82F);
		buttonLayout.setExpandRatio(detailsBtn, 0.08F);

		addComponent(ComponentUtil.initMenuButton(navigator, StartView.NAME, "Go back to the main menu"));
		addComponent(buttonLayout);
		addComponent(pagedTable);
		addComponent(pagerControls);

		setExpandRatio(buttonLayout, 0.05F);
		setExpandRatio(pagedTable, 0.90F);
		setExpandRatio(pagerControls, 0.05F);
	}

	private void openDetails() {
		if (!billWindow.isAttached()) {
			billWindow.center();
			billWindow.setWidth("80%");
			BillDisplayLayout billDisplayLayout = new BillDisplayLayout((Bill) pagedTable.getValue());
			billWindow.setContent(billDisplayLayout);
			UI.getCurrent().addWindow(billWindow);
		}
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
		detailsBtn.setVisible(false);
		reloadResult();

	}

}
