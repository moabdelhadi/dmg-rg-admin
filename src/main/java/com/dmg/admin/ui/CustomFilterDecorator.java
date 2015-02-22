package com.dmg.admin.ui;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Locale;

import org.tepi.filtertable.FilterDecorator;
import org.tepi.filtertable.numberfilter.NumberFilterPopupConfig;

import com.dmg.admin.util.StatusEnum;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.datefield.Resolution;

@SuppressWarnings("serial")
public class CustomFilterDecorator implements FilterDecorator, Serializable {

	@Override
	public String getEnumFilterDisplayName(Object propertyId, Object value) {
		if ("approved".equals(propertyId)) {
			StatusEnum statusEnum = (StatusEnum) value;
			switch (statusEnum) {
			case APPROVED:
				return StatusEnum.APPROVED.getName();
			case REJECTED:
				return StatusEnum.REJECTED.getName();
			default:
				return StatusEnum.PENDING.getName();
			}
		}
		return null;

	}

	@Override
	public Resource getEnumFilterIcon(Object propertyId, Object value) {
		if ("approved".equals(propertyId)) {
			StatusEnum statusEnum = (StatusEnum) value;
			switch (statusEnum) {
			case APPROVED:
				return new ThemeResource("img/approved.png");
			case REJECTED:
				return new ThemeResource("img/rejected.png");
			default:
				return new ThemeResource("img/pending.png");
			}
		}
		return null;
	}

	@Override
	public String getBooleanFilterDisplayName(Object propertyId, boolean value) {
		if ("enable".equals(propertyId)) {
			return value ? "enabled" : "disabled";
		}
		if ("payed".equals(propertyId)) {
			return value ? "payed" : "not payed";
		}
		// returning null will output default value
		return null;
	}

	@Override
	public Resource getBooleanFilterIcon(Object propertyId, boolean value) {
		if ("enable".equals(propertyId) || "payed".equals("payed")) {
			return value ? new ThemeResource("img/enable.png") : new ThemeResource("img/disable.png");
		}
		return null;
	}

	@Override
	public String getFromCaption() {
		return "Start date:";
	}

	@Override
	public String getToCaption() {
		return "End date:";
	}

	@Override
	public String getSetCaption() {
		// use default caption
		return null;
	}

	@Override
	public String getClearCaption() {
		// use default caption
		return null;
	}

	@Override
	public boolean isTextFilterImmediate(Object propertyId) {
		// use text change events for all the text fields
		return true;
	}

	@Override
	public int getTextChangeTimeout(Object propertyId) {
		// use the same timeout for all the text fields
		return 500;
	}

	@Override
	public String getAllItemsVisibleString() {
		return "Show all";
	}

	@Override
	public Resolution getDateFieldResolution(Object propertyId) {
		return Resolution.DAY;
	}

	public DateFormat getDateFormat(Object propertyId) {
		return DateFormat.getDateInstance(DateFormat.SHORT, new Locale("fi", "FI"));
	}

	@Override
	public boolean usePopupForNumericProperty(Object propertyId) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getDateFormatPattern(Object propertyId) {
		return "dd/MM/yyyy";
	}

	@Override
	public Locale getLocale() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NumberFilterPopupConfig getNumberFilterPopupConfig() {
		// TODO Auto-generated method stub
		return null;
	}
}
