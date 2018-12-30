package com.dmg.admin.view;

import java.util.Date;

import com.dmg.admin.auth.SessionHandler;
import com.dmg.admin.ui.ComponentUtil;
import com.dmg.admin.ui.CustomFilterDecorator;
import com.dmg.admin.ui.CustomFilterGenerator;
import com.dmg.admin.ui.CustomPagedFilterControlConfig;
import com.dmg.admin.ui.CustomPagedFilterTable;
import com.dmg.admin.util.ViewUtil;
import com.dmg.core.bean.NewUserRegistrationAUH;
import com.dmg.core.bean.NewUserRegistrationDu;
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
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
//import com.dmg.client.simplepayment.views.BillPopupUI;

public class NewRequestsView extends VerticalLayout implements View {

	private static final int EXPORT_LIMIT = 100;
	/**
	 * 
	 */
	private static final long serialVersionUID = -4787817640065298241L;
	public static final String NAME = "newusers";
	private CustomPagedFilterTable pagedTable;

	JPAContainer jpaContainer;
	/*BeanItemContainer<Bill> container = new BeanItemContainer<Bill>(Bill.class);*/
	private ExcelExport excelExport;
	private final Navigator navigator;
	private Button detailsBtn;
	private final Window newUserWindow = new Window();
	private String city = "";
	private final Button exportBtn = new Button("Export");

	public NewRequestsView(Navigator navigator) {
		this.navigator = navigator;
		initView();
		setSizeFull();
		setSpacing(true);
	}

//	private void initPagedTable() {
//		city = SessionHandler.get().getCity();
//		if ("DUBAI".equals(city)) {
//			jpaContainer = JPAContainerFactory.makeBatchable(NewUserRegistrationDu.class, "dmg-rg-admin");
//		} else {
//			jpaContainer = JPAContainerFactory.makeBatchable(NewUserRegistrationAUH.class, "dmg-rg-admin");
//		}
//		pagedTable.setContainerDataSource(jpaContainer);
//		pagedTable.setCaption("New Clients found (" + jpaContainer.size() + ")");
//		pagedTable.setVisibleColumns("name", "email", "buildingNumber", "appartmentNumber", "phone", "mobile", "meterReading", "refNo");
//	}

	public void initView() {
		pagedTable = new CustomPagedFilterTable();
		pagedTable.setFilterDecorator(new CustomFilterDecorator());
		pagedTable.setFilterGenerator(new CustomFilterGenerator());
		pagedTable.setFilterBarVisible(true);
		pagedTable.setImmediate(true);

		pagedTable.addContainerProperty("name", String.class, null);
		pagedTable.addContainerProperty("email", String.class, null);
		pagedTable.addContainerProperty("buildingNumber", String.class, null);
		pagedTable.addContainerProperty("appartmentNumber", String.class, null);
		pagedTable.addContainerProperty("phone", String.class, null);
		pagedTable.addContainerProperty("mobile", String.class, null);
		pagedTable.addContainerProperty("meterReading", Image.class, null);
		pagedTable.addContainerProperty("refNo", Date.class, null);

		pagedTable.setColumnHeader("name", "Name");
		pagedTable.setColumnHeader("email", "Email");
		pagedTable.setColumnHeader("buildingNumber", "Building");
		pagedTable.setColumnHeader("appartmentNumber", "Apartment");
		pagedTable.setColumnHeader("phone", "Phone");
		pagedTable.setColumnHeader("mobile", "Mobile");
		pagedTable.setColumnHeader("meterReading", "Meter Reading");
		pagedTable.setColumnHeader("refNo", "Ref. No,");

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

//		pagedTable.addGeneratedColumn("payEnum", new ColumnGenerator() {
//
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			public Image generateCell(final CustomTable source, final Object itemId, Object columnId) {
//				Bill bill;
//				Image icon;
//				if ("DUBAI".equals(city)) {
//					bill = (BillDu) jpaContainer.getItem(itemId).getEntity();
//				} else {
//					bill = (BillAUH) jpaContainer.getItem(itemId).getEntity();
//				}
//				if (!StringUtils.isEmpty(bill.getLastReceivedPayReference())) {
//					icon = new Image(null, new ThemeResource("img/enable.png"));
//				} else {
//					icon = new Image(null, new ThemeResource("img/disable.png"));
//				}
//				icon.setDescription(bill.getPayEnum().getType());
//				return icon;
//			}
//
//		});

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
				pagedTable.setCaption("New UserFound (" + itemSize + ")");
				if (itemSize > EXPORT_LIMIT) {
					exportBtn.setVisible(false);
				} else {
					exportBtn.setVisible(true);
				}

			}
		});

		detailsBtn = new Button("Details");
		detailsBtn.setIcon(new ThemeResource(ViewUtil.EDIT_ICON));
		
//		BrowserWindowOpener opener = new BrowserWindowOpener(NewUserPopupUI.class);
//		opener.setFeatures("");
//		opener.setParameter("accountId", bill.getContractNo());
//		opener.setParameter("billId", bill.getId().toString());
//		opener.setParameter("billCity", bill.getCity().toString());
//		opener.extend(detailsBtn);
		

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
				excelExport.setReportTitle("New Users Report");
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
		
		
		Page.getCurrent().open("/dmg-rg-client/views/contract/contract.html", "UserContarct", true);
		
//		Button button = billViews.get(counter);
//		BrowserWindowOpener opener = new BrowserWindowOpener(BillPopupUI.class);
//		opener.setFeatures("");
//		opener.setParameter("accountId", bill.getContractNo());
//		opener.setParameter("billId", bill.getId().toString());
//		opener.setParameter("billCity", bill.getCity().toString());
//		opener.extend(button);
//		
//		if (!newUserWindow.isAttached()) {
//			newUserWindow.center();
//			newUserWindow.setWidth("80%");
//			NewUserRegistration newUser;
//			if ("DUBAI".equals(city)) {
//				newUser = (NewUserRegistrationDu) jpaContainer.getItem(pagedTable.getValue()).getEntity();
//			} else {
//				newUser = (NewUserRegistrationDu) jpaContainer.getItem(pagedTable.getValue()).getEntity();
//			}
//			
//			Resource res = new ExternalResource("/dmg-rg-client");
//			final ResourceReference rr = ResourceReference.create(res, content, "email");
//			
//			BillDisplayLayout billDisplayLayout = new BillDisplayLayout(newUser);
//			billWindow.setContent(billDisplayLayout);
//			UI.getCurrent().addWindow(billWindow);
//		}
	}

	@Override
	public void enter(ViewChangeEvent event) {
//		initPagedTable();
		detailsBtn.setVisible(false);
	}

}
