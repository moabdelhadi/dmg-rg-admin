package com.dmg.admin.view;

import java.math.BigDecimal;

import com.dmg.admin.auth.SessionHandler;
import com.dmg.admin.ui.ComponentUtil;
import com.dmg.admin.ui.CustomFilterDecorator;
import com.dmg.admin.ui.CustomFilterGenerator;
import com.dmg.admin.ui.CustomPagedFilterControlConfig;
import com.dmg.admin.ui.CustomPagedFilterTable;
import com.dmg.admin.ui.MailStausDisplayLayout;
import com.dmg.admin.util.ViewUtil;
import com.dmg.core.bean.SendInv;
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
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class SendEmailStatusList extends VerticalLayout implements View {

	private static final int EXPORT_LIMIT = 5000;
	/**
	 * 
	 */
	private static final long serialVersionUID = -4787817640065298241L;
	public static final String NAME = "mailsListStatus";
	private CustomPagedFilterTable pagedTable;

	JPAContainer jpaContainer;
	/*BeanItemContainer<Bill> container = new BeanItemContainer<Bill>(Bill.class);*/
	private ExcelExport excelExport;
	private final Navigator navigator;
	private Button detailsBtn;
	private final Window mailDetailsWindow = new Window();
	private String city = "";
	private final Button exportBtn = new Button("Export");

	public SendEmailStatusList(Navigator navigator) {
		this.navigator = navigator;
		initView();
		setSizeFull();
		setSpacing(true);
	}

	private void initPagedTable() {
		city = SessionHandler.get().getCity();
		jpaContainer = JPAContainerFactory.makeBatchable(SendInv.class, "dmg-rg-admin");
		String[] params = {"id"};
		boolean[] desc = {false};
		jpaContainer.sort(params, desc);
		pagedTable.setContainerDataSource(jpaContainer);
		pagedTable.setCaption("EmailSendList found (" + jpaContainer.size() + ")");
		pagedTable.setVisibleColumns("company", "contractNo", "city", "ccbId", "status", "message", "title");
	}

	public void initView() {
		pagedTable = new CustomPagedFilterTable();
		pagedTable.setFilterDecorator(new CustomFilterDecorator());
		pagedTable.setFilterGenerator(new CustomFilterGenerator());
		pagedTable.setFilterBarVisible(true);
		pagedTable.setImmediate(true);

		pagedTable.addContainerProperty("company", String.class, null);
		pagedTable.addContainerProperty("contractNo", String.class, null);
		pagedTable.addContainerProperty("city", String.class, null);
		pagedTable.addContainerProperty("ccbId", String.class, null);
		pagedTable.addContainerProperty("status", BigDecimal.class, null);
		pagedTable.addContainerProperty("message", BigDecimal.class, null);
		pagedTable.addContainerProperty("title", String.class, null);

		pagedTable.setColumnHeader("company", "Company");
		pagedTable.setColumnHeader("contractNo", "Contract No.");
		pagedTable.setColumnHeader("city", "city");
		pagedTable.setColumnHeader("ccbId", "Adnoc ID");
		pagedTable.setColumnHeader("status", "SendStatus");
		pagedTable.setColumnHeader("message", "Error Message");
		pagedTable.setColumnHeader("title", "Title");


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
				pagedTable.setCaption("MAILS found (" + itemSize + ")");
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
				excelExport.setReportTitle("Mails Report");
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
		if (!mailDetailsWindow.isAttached()) {
			mailDetailsWindow.center();
			mailDetailsWindow.setWidth("80%");
			SendInv mailD=  (SendInv) jpaContainer.getItem(pagedTable.getValue()).getEntity();
			MailStausDisplayLayout mailSDisplayLayout = new MailStausDisplayLayout(mailD);
			mailDetailsWindow.setContent(mailSDisplayLayout);
			UI.getCurrent().addWindow(mailDetailsWindow);
		}
	}

	@Override
	public void enter(ViewChangeEvent event) {
		initPagedTable();
		detailsBtn.setVisible(false);
	}

}
