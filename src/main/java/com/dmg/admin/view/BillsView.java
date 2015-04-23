package com.dmg.admin.view;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.dmg.admin.auth.SessionHandler;
import com.dmg.admin.ui.BillDisplayLayout;
import com.dmg.admin.ui.ComponentUtil;
import com.dmg.admin.ui.CustomFilterDecorator;
import com.dmg.admin.ui.CustomFilterGenerator;
import com.dmg.admin.ui.CustomPagedFilterControlConfig;
import com.dmg.admin.ui.CustomPagedFilterTable;
import com.dmg.admin.util.ViewUtil;
import com.dmg.core.bean.Bill;
import com.dmg.core.bean.BillAUH;
import com.dmg.core.bean.BillDu;
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
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomTable;
import com.vaadin.ui.CustomTable.ColumnGenerator;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class BillsView extends VerticalLayout implements View {

	private static final int EXPORT_LIMIT = 5000;
	/**
	 * 
	 */
	private static final long serialVersionUID = -4787817640065298241L;
	public static final String NAME = "bills";
	private CustomPagedFilterTable pagedTable;

	JPAContainer jpaContainer;
	/*BeanItemContainer<Bill> container = new BeanItemContainer<Bill>(Bill.class);*/
	private ExcelExport excelExport;
	private final Navigator navigator;
	private Button detailsBtn;
	private final Window billWindow = new Window();
	private String city = "";
	private final Button exportBtn = new Button("Export");

	public BillsView(Navigator navigator) {
		this.navigator = navigator;
		initView();
		setSizeFull();
		setSpacing(true);
	}

	private void initPagedTable() {
		city = SessionHandler.get().getCity();
		if ("DUBAI".equals(city)) {
			jpaContainer = JPAContainerFactory.makeBatchable(BillDu.class, "dmg-rg-admin");
		} else {
			jpaContainer = JPAContainerFactory.makeBatchable(BillAUH.class, "dmg-rg-admin");
		}
		pagedTable.setContainerDataSource(jpaContainer);
		pagedTable.setCaption("Bills found (" + jpaContainer.size() + ")");
		pagedTable.setVisibleColumns("contractNo", "docNo", "serialNo", "totalAmount", "receivedAmmount", "lastReceivedPayReference", "payEnum", "billDate");
	}

	public void initView() {
		pagedTable = new CustomPagedFilterTable();
		pagedTable.setFilterDecorator(new CustomFilterDecorator());
		pagedTable.setFilterGenerator(new CustomFilterGenerator());
		pagedTable.setFilterBarVisible(true);
		pagedTable.setImmediate(true);

		pagedTable.addContainerProperty("contractNo", String.class, null);
		pagedTable.addContainerProperty("docNo", String.class, null);
		pagedTable.addContainerProperty("serialNo", String.class, null);
		pagedTable.addContainerProperty("totalAmount", BigDecimal.class, null);
		pagedTable.addContainerProperty("receivedAmmount", BigDecimal.class, null);
		pagedTable.addContainerProperty("lastReceivedPayReference", String.class, null);
		pagedTable.addContainerProperty("payEnum", Image.class, null);
		pagedTable.addContainerProperty("billDate", Date.class, null);

		pagedTable.setColumnHeader("contractNo", "Contract No.");
		pagedTable.setColumnHeader("docNo", "Doc No.");
		pagedTable.setColumnHeader("serialNo", "Serial No.");
		pagedTable.setColumnHeader("totalAmount", "Total Amount");
		pagedTable.setColumnHeader("receivedAmmount", "Received Amount");
		pagedTable.setColumnHeader("lastReceivedPayReference", "Reference");
		pagedTable.setColumnHeader("payEnum", "Payed");
		pagedTable.setColumnHeader("billDate", "Bill Date");

		pagedTable.setSizeFull();

		pagedTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void itemClick(ItemClickEvent itemClickEvent) {
				detailsBtn.setVisible(true);

			}
		});

		pagedTable.addGeneratedColumn("payEnum", new ColumnGenerator() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Image generateCell(final CustomTable source, final Object itemId, Object columnId) {
				Bill bill;
				Image icon;
				if ("DUBAI".equals(city)) {
					bill = (BillDu) jpaContainer.getItem(itemId).getEntity();
				} else {
					bill = (BillAUH) jpaContainer.getItem(itemId).getEntity();
				}
				if (!StringUtils.isEmpty(bill.getLastReceivedPayReference())) {
					icon = new Image(null, new ThemeResource("img/enable.png"));
				} else {
					icon = new Image(null, new ThemeResource("img/disable.png"));
				}
				icon.setDescription(bill.getPayEnum().getType());
				return icon;
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
				pagedTable.setCaption("Bills found (" + itemSize + ")");
				if (itemSize > EXPORT_LIMIT) {
					exportBtn.setVisible(false);
				} else {
					exportBtn.setVisible(true);
				}

			}
		});

		detailsBtn = new Button("Details");
		detailsBtn.setIcon(new ThemeResource(ViewUtil.EDIT_ICON));

		detailsBtn.addClickListener(new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				openDetails();
			}

		});
		exportBtn.setVisible(false);
		exportBtn.setIcon(new ThemeResource(ViewUtil.EXCEL_ICON));
		exportBtn.addClickListener(new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

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
			Bill bill;
			if ("DUBAI".equals(city)) {
				bill = (BillDu) jpaContainer.getItem(pagedTable.getValue()).getEntity();
			} else {
				bill = (BillAUH) jpaContainer.getItem(pagedTable.getValue()).getEntity();
			}
			BillDisplayLayout billDisplayLayout = new BillDisplayLayout(bill);
			billWindow.setContent(billDisplayLayout);
			UI.getCurrent().addWindow(billWindow);
		}
	}

	@Override
	public void enter(ViewChangeEvent event) {
		initPagedTable();
		detailsBtn.setVisible(false);
	}

}
