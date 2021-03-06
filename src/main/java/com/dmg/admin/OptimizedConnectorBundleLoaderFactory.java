package com.dmg.admin;

import java.util.HashSet;
import java.util.Set;

import com.google.gwt.core.ext.typeinfo.JClassType;
import com.vaadin.server.widgetsetutils.ConnectorBundleLoaderFactory;
import com.vaadin.shared.ui.Connect.LoadStyle;

public class OptimizedConnectorBundleLoaderFactory extends ConnectorBundleLoaderFactory {
	private final Set<String> eagerConnectors = new HashSet<String>();
	{
		eagerConnectors.add(com.vaadin.client.ui.ui.UIConnector.class.getName());
		eagerConnectors.add(com.vaadin.client.ui.customlayout.CustomLayoutConnector.class.getName());
		eagerConnectors.add(com.vaadin.client.ui.textfield.TextFieldConnector.class.getName());
		eagerConnectors.add(com.vaadin.client.ui.passwordfield.PasswordFieldConnector.class.getName());
		eagerConnectors.add(com.vaadin.client.ui.button.ButtonConnector.class.getName());
		eagerConnectors.add(com.vaadin.client.ui.orderedlayout.VerticalLayoutConnector.class.getName());
		eagerConnectors.add(com.vaadin.client.ui.label.LabelConnector.class.getName());
		eagerConnectors.add(org.tepi.filtertable.gwt.client.ui.FilterTableConnector.class.getName());
		eagerConnectors.add(com.vaadin.client.ui.combobox.ComboBoxConnector.class.getName());
		eagerConnectors.add(com.vaadin.client.ui.orderedlayout.HorizontalLayoutConnector.class.getName());
	}

	@Override
	protected LoadStyle getLoadStyle(JClassType connectorType) {
		if (eagerConnectors.contains(connectorType.getQualifiedBinaryName())) {
			return LoadStyle.EAGER;
		} else {
			// Loads all other connectors immediately after the initial view has
			// been rendered
			return LoadStyle.DEFERRED;
		}
	}
}
