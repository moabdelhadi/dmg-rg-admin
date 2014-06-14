package com.dmg.admin.ui;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.themes.BaseTheme;

public class ComponentUtil {

	public static HorizontalLayout initMenuButton(final Navigator navigator, final String destination, final String label) {
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		Button mainMenuButton = new Button(label);
		mainMenuButton.setWidth("12%");
		mainMenuButton.setStyleName(BaseTheme.BUTTON_LINK);
		mainMenuButton.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				navigator.navigateTo(destination);
			}
		});
		horizontalLayout.addComponent(mainMenuButton);
		// horizontalLayout.setMargin(true);
		return horizontalLayout;

	}

}
