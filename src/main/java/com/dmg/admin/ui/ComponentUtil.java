package com.dmg.admin.ui;

import com.dmg.admin.MainUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.BaseTheme;

public class ComponentUtil {

	public static HorizontalLayout initMenuButton(final String destination) {
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		Button mainMenuButton = new Button("Go back to the main menu");
		mainMenuButton.setWidth("12%");
		mainMenuButton.setStyleName(BaseTheme.BUTTON_LINK);
		mainMenuButton.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				MainUI.getCurrent().getNavigator().navigateTo(destination);
			}
		});
		horizontalLayout.addComponent(mainMenuButton);
		// horizontalLayout.setMargin(true);
		return horizontalLayout;

	}

}
