package com.dmg.admin.ui;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.dmg.core.bean.Bill;
import com.dmg.core.bean.SendInv;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public class MailStausDisplayLayout extends HorizontalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 701948131350786451L;
	private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public MailStausDisplayLayout(SendInv item) {
		setSpacing(true);
		setMargin(true);
		setWidth("100%");

		// setting default values for null
		VerticalLayout verticalLayout1 = new VerticalLayout();
//		VerticalLayout verticalLayout2 = new VerticalLayout();
//		VerticalLayout verticalLayout3 = new VerticalLayout();

		verticalLayout1.addComponent(new DataRowLayout("ID:", trimString(String.valueOf(item.getId()))));
		verticalLayout1.addComponent(new DataRowLayout("CITY:", StringUtils.trimToEmpty(item.getCity())));
		verticalLayout1.addComponent(new DataRowLayout("Company:", StringUtils.trimToEmpty(item.getCompany())));
		verticalLayout1.addComponent(new DataRowLayout("Contract No:", StringUtils.trimToEmpty(item.getContractNo())));
		verticalLayout1.addComponent(new DataRowLayout("Adnoc Ref:", StringUtils.trimToEmpty(item.getCcbId())));
		verticalLayout1.addComponent(new DataRowLayout("CreationDate:", getFormattedDate(item.getCreationDate())));
		verticalLayout1.addComponent(new DataRowLayout("Update Date:", getFormattedDate(item.getUpdateDate())));
		verticalLayout1.addComponent(new DataRowLayout("Status:", StringUtils.trimToEmpty(item.getStatus())));
		verticalLayout1.addComponent(new DataRowLayout("Error Message :", StringUtils.trimToEmpty(item.getMessage())));
		verticalLayout1.addComponent(new DataRowLayout("Attachment:", StringUtils.trimToEmpty(item.getAttachment())));
		
		verticalLayout1.addComponent(new DataRowLayout("TITLE:", StringUtils.trimToEmpty(item.getTitle())));
		verticalLayout1.addComponent(new DataRowLayout("Body:", StringUtils.trimToEmpty(item.getBody())));
		
		addComponent(verticalLayout1);
//		addComponent(verticalLayout2);
//		addComponent(verticalLayout3);

		/*
		 * horizontalLayout.setExpandRatio(verticalLayout1, 0.45F);
		 * horizontalLayout.setExpandRatio(verticalLayout2, 0.45F);
		 * horizontalLayout.setExpandRatio(verticalLayout3, 0.45F);
		 */

		// TODO Auto-generated constructor stub

	}

	private String getFormattedDate(Date date) {
		if (date != null) {
			return sdf.format(date);
		}
		return "";

	}

	private String trimString(String str) {
		String trimmed = StringUtils.trimToEmpty(str);
		if (trimmed.equals("null")) {
			return "";
		}
		return trimmed;

	}
}
