package com.dmg.admin.view;

import com.dmg.admin.util.VersionReader;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class StartView extends VerticalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7804954222498858807L;
	public static final String NAME = "";

	public StartView(final Navigator navigator) {
		setSizeFull();
		String version = VersionReader.getInstance().getVersion();
		Label versionLabel = new Label("DMG-RG-ADMIN version (" + version + ")");

		CustomLayout customLayout = new CustomLayout("startPage");

		customLayout.addComponent(versionLabel, "version");
		addComponent(customLayout);
		addStyleName("bg-image");

		setComponentAlignment(customLayout, Alignment.MIDDLE_CENTER);
	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

}
