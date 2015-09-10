package com.dmg.admin.view;

import com.dmg.admin.auth.SessionHandler;
import com.dmg.admin.ui.ComponentUtil;
import com.dmg.admin.ui.CustomFilterDecorator;
import com.dmg.admin.ui.CustomFilterGenerator;
import com.dmg.admin.ui.CustomPagedFilterControlConfig;
import com.dmg.admin.ui.CustomPagedFilterTable;
import com.dmg.admin.util.ViewUtil;
import com.dmg.core.bean.UserAccount;
import com.dmg.core.bean.UserAccountsAUH;
import com.dmg.core.bean.UserAccountsDU;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
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
import com.vaadin.ui.CustomTable;
import com.vaadin.ui.CustomTable.ColumnGenerator;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class UsersView extends VerticalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4787817640065298241L;
	public static final String NAME = "users";
	private CustomPagedFilterTable pagedTable;
	BeanItemContainer<UserAccount> container = new BeanItemContainer<UserAccount>(UserAccount.class);
	JPAContainer jpaContainer;
	private ExcelExport excelExport;
	private Button editBtn;
	private final Navigator navigator;
	private String city = "";

	public UsersView(Navigator navigator) {
		this.navigator = navigator;
		initView();
		setSizeFull();
		setSpacing(true);
	}

	private void initPagedTable() {
		city = SessionHandler.get().getCity();
		if ("DUBAI".equals(city)) {
			jpaContainer = JPAContainerFactory.makeBatchable(UserAccountsDU.class, "dmg-rg-admin");
		} else {
			jpaContainer = JPAContainerFactory.makeBatchable(UserAccountsAUH.class, "dmg-rg-admin");
		}
		pagedTable.setContainerDataSource(jpaContainer);
		pagedTable.setCaption("Bills found (" + jpaContainer.size() + ")");
		pagedTable.setVisibleColumns("name", "email", "contractNo", "city", "buildingNumber", "appartmentNumber", "phone", "mobile", "pobox", "status", "enable");
	}

	public void initView() {

		pagedTable = new CustomPagedFilterTable();
		pagedTable.setFilterDecorator(new CustomFilterDecorator());
		pagedTable.setFilterGenerator(new CustomFilterGenerator());
		pagedTable.setFilterBarVisible(true);
		pagedTable.setImmediate(true);

		pagedTable.addContainerProperty("name", String.class, null);
		pagedTable.addContainerProperty("email", String.class, null);
		pagedTable.addContainerProperty("contractNo", String.class, null);
		pagedTable.addContainerProperty("city", String.class, null);
		pagedTable.addContainerProperty("buildingNumber", String.class, null);
		pagedTable.addContainerProperty("appartmentNumber", String.class, null);
		pagedTable.addContainerProperty("phone", String.class, null);
		pagedTable.addContainerProperty("mobile", String.class, null);
		pagedTable.addContainerProperty("pobox", String.class, null);
		pagedTable.addContainerProperty("status", Integer.class, null);
		//		pagedTable.addContainerProperty("activationString", String.class, null);
		pagedTable.addContainerProperty("enabled", Boolean.class, null);

		pagedTable.setColumnHeader("name", "Name");
		pagedTable.setColumnHeader("email", "Email");
		pagedTable.setColumnHeader("contractNo", "Contract #");
		pagedTable.setColumnHeader("city", "city");
		pagedTable.setColumnHeader("buildingNumber", "Building #");
		pagedTable.setColumnHeader("appartmentNumber", "Apartment #");
		pagedTable.setColumnHeader("phone", "Phone");
		pagedTable.setColumnHeader("mobile", "Mobile");
		pagedTable.setColumnHeader("pobox", "P.O.Box");
		pagedTable.setColumnHeader("status", "STATUS");
		pagedTable.setColumnHeader("enable", "Enable");

		pagedTable.setSizeFull();

		pagedTable.setColumnExpandRatio("name", 0.10F);
		pagedTable.setColumnExpandRatio("email", 0.10F);
		pagedTable.setColumnExpandRatio("contractNo", 0.20F);
		pagedTable.setColumnExpandRatio("city", 0.05F);
		pagedTable.setColumnExpandRatio("buildingNumber", 0.05F);
		pagedTable.setColumnExpandRatio("appartmentNumber", 0.05F);
		pagedTable.setColumnExpandRatio("phone", 0.10F);
		pagedTable.setColumnExpandRatio("mobile", 0.10F);
		pagedTable.setColumnExpandRatio("pobox", 0.05F);
		pagedTable.setColumnExpandRatio("status", 0.05F);
		pagedTable.setColumnExpandRatio("enable", 0.10F);

		pagedTable.addGeneratedColumn("enable", new ColumnGenerator() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Image generateCell(final CustomTable source, final Object itemId, Object columnId) {
				UserAccount userAccount;
				Image icon;
				if ("DUBAI".equals(city)) {
					userAccount = (UserAccountsDU) jpaContainer.getItem(itemId).getEntity();
				} else {
					userAccount = (UserAccountsAUH) jpaContainer.getItem(itemId).getEntity();
				}
				if (userAccount.getEnable()) {
					icon = new Image(null, new ThemeResource("img/enable.png"));

				} else {
					icon = new Image(null, new ThemeResource("img/disable.png"));

				}
				return icon;

			}

		});

		pagedTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void itemClick(ItemClickEvent itemClickEvent) {
				editBtn.setVisible(true);
				editBtn.setDescription("Click here to edit user info!");
			}
		});

		pagedTable.setSelectable(true);
		/*
		 * Grid grid = new Grid(pagedTable); grid.setExportEnabled(false);
		 * grid.resetFilters();
		 */
		setMargin(true);

		CustomPagedFilterControlConfig config = new CustomPagedFilterControlConfig();
		HorizontalLayout pagerControls = pagedTable.createControls(config);

		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setWidth("100%");

		editBtn = new Button("Edit");
		editBtn.setIcon(new ThemeResource(ViewUtil.EDIT_ICON));

		editBtn.addClickListener(new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				navigator.navigateTo(UpdateUserView.NAME + "/" + pagedTable.getValue());

			}
		});
		Button exportBtn = new Button("Export");
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
				excelExport.setReportTitle("Users Report");
				excelExport.export();
			}
		});
		Label label = new Label();
		buttonLayout.addComponent(exportBtn);
		buttonLayout.addComponent(label);
		buttonLayout.addComponent(editBtn);
		buttonLayout.setExpandRatio(exportBtn, 0.10F);
		buttonLayout.setExpandRatio(label, 0.84F);
		buttonLayout.setExpandRatio(editBtn, 0.06F);

		addComponent(ComponentUtil.initMenuButton(navigator, StartView.NAME, "Go back to the main menu"));
		addComponent(buttonLayout);
		addComponent(pagedTable);
		addComponent(pagerControls);

		setExpandRatio(buttonLayout, 0.05F);
		setExpandRatio(pagedTable, 0.90F);
		setExpandRatio(pagerControls, 0.05F);

	}

	@Override
	public void enter(ViewChangeEvent event) {
		initPagedTable();
		editBtn.setVisible(false);

	}

}
