package com.dmg.admin.ui;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class DataRowLayout extends HorizontalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6758321648426824795L;

	public DataRowLayout(String def, String value) {
		Label defLabel = new Label("<b>" + def, ContentMode.HTML);
		Label valueLabel = new Label(value);

		setSpacing(true);
		addComponent(defLabel);
		addComponent(valueLabel);

	}

}
