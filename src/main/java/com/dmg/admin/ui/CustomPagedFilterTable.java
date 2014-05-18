package com.dmg.admin.ui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.tepi.filtertable.paged.PagedFilterTable;

import com.dmg.core.bean.AbstractPojo;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;

public class CustomPagedFilterTable extends PagedFilterTable<BeanItemContainer<AbstractPojo>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5314580346667561527L;

	public CustomPagedFilterTable() {
		super(null);
	}

	@Override
	public void changeVariables(Object source, Map<String, Object> variables) {
		if (variables.containsKey("pagelength")) {
			variables.remove("pagelength");
		}
		super.changeVariables(source, variables);
	}

	@Override
	protected String formatPropertyValue(Object rowId, Object colId, Property<?> property) {
		Object v = property.getValue();
		if (v instanceof Date) {
			Date dateValue = (Date) v;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			return sdf.format(dateValue);
		}
		return super.formatPropertyValue(rowId, colId, property);
	}

}
