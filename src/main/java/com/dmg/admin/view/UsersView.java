package com.dmg.admin.view;

import java.util.List;

import com.dmg.admin.bean.UserAccount;
import com.dmg.admin.service.UserAccountService;
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

public class UsersView extends VerticalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4787817640065298241L;
	public static final String NAME = "users";
	private CustomPagedFilterTable pagedTable;
	private List<UserAccount> userAccounts;
	private final UserAccountService accountService;
	BeanItemContainer<UserAccount> container = new BeanItemContainer<UserAccount>(UserAccount.class);
	private ExcelExport excelExport;

	public UsersView() {
		initView();
		accountService = new UserAccountService();
		setSizeFull();
		setSpacing(true);
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
		pagedTable.addContainerProperty("poboxCity", String.class, null);
		pagedTable.addContainerProperty("activationString", String.class, null);

		pagedTable.setColumnHeader("name", "Name");
		pagedTable.setColumnHeader("email", "Email");
		pagedTable.setColumnHeader("contractNo", "Contract #");
		pagedTable.setColumnHeader("city", "city");
		pagedTable.setColumnHeader("buildingNumber", "Building #");
		pagedTable.setColumnHeader("appartmentNumber", "Apartment #");
		pagedTable.setColumnHeader("phone", "Phone");
		pagedTable.setColumnHeader("mobile", "Mobile");
		pagedTable.setColumnHeader("pobox", "P.O.Box");
		pagedTable.setColumnHeader("poboxCity", "P.O.Box City");
		pagedTable.setColumnHeader("activationString", "Activation");

		pagedTable.setContainerDataSource(container);
		pagedTable.setSizeFull();

		pagedTable.setVisibleColumns("name", "email", "contractNo", "city", "buildingNumber", "appartmentNumber", "phone", "mobile", "pobox", "poboxCity", "activationString");

		pagedTable.setColumnExpandRatio("name", 0.10F);
		pagedTable.setColumnExpandRatio("email", 0.10F);
		pagedTable.setColumnExpandRatio("contractNo", 0.20F);
		pagedTable.setColumnExpandRatio("city", 0.05F);
		pagedTable.setColumnExpandRatio("buildingNumber", 0.05F);
		pagedTable.setColumnExpandRatio("appartmentNumber", 0.05F);
		pagedTable.setColumnExpandRatio("phone", 0.10F);
		pagedTable.setColumnExpandRatio("mobile", 0.10F);
		pagedTable.setColumnExpandRatio("pobox", 0.05F);
		pagedTable.setColumnExpandRatio("poboxCity", 0.05F);
		pagedTable.setColumnExpandRatio("activationString", 0.15F);

		pagedTable.setSelectable(true);
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
				excelExport.setReportTitle("Users Report");
				excelExport.export();
			}
		});
		addComponent(ComponentUtil.initMenuButton(""));
		addComponent(button);
		addComponent(pagedTable);
		addComponent(pagerControls);

		setExpandRatio(button, 0.05F);
		setExpandRatio(pagedTable, 0.90F);
		setExpandRatio(pagerControls, 0.05F);
	}

	private void reloadResult() {
		try {
			userAccounts = accountService.lisUsers();
		} catch (DataAccessLayerException e) {
			Notification.show("Error", "There was a DB error while retrieving Users please contact help disk", Type.ERROR_MESSAGE);
		}
		container.removeAllItems();
		for (UserAccount userAccount : userAccounts) {
			container.addBean(userAccount);
		}
		pagedTable.setCurrentPage(1);
		pagedTable.setCaption("Events found (" + userAccounts.size() + ")");
		pagedTable.setImmediate(true);

	}

	@Override
	public void enter(ViewChangeEvent event) {

		reloadResult();

	}

}
