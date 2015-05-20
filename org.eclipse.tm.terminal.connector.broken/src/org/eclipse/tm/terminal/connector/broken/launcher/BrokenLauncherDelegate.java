package org.eclipse.tm.terminal.connector.broken.launcher;

import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.tm.internal.terminal.provisional.api.ITerminalConnector;
import org.eclipse.tm.internal.terminal.provisional.api.TerminalConnectorExtension;
import org.eclipse.tm.terminal.connector.broken.controls.BrokenConfigurationPanel;
import org.eclipse.tm.terminal.view.core.TerminalServiceFactory;
import org.eclipse.tm.terminal.view.core.interfaces.ITerminalService;
import org.eclipse.tm.terminal.view.core.interfaces.ITerminalService.Done;
import org.eclipse.tm.terminal.view.core.interfaces.constants.ITerminalsConnectorConstants;
import org.eclipse.tm.terminal.view.ui.interfaces.IConfigurationPanel;
import org.eclipse.tm.terminal.view.ui.interfaces.IConfigurationPanelContainer;
import org.eclipse.tm.terminal.view.ui.launcher.AbstractLauncherDelegate;

public class BrokenLauncherDelegate extends AbstractLauncherDelegate {

	@Override
	public boolean needsUserConfiguration() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IConfigurationPanel getPanel(IConfigurationPanelContainer container) {
		return new BrokenConfigurationPanel(container);
	}

	@Override
	public void execute(Map<String, Object> properties, Done done) {
		Assert.isNotNull(properties);

		// Set the terminal tab title
		String terminalTitle = "Teh Broken Term";
		if (terminalTitle != null) {
			properties.put(ITerminalsConnectorConstants.PROP_TITLE, terminalTitle);
		}

		// For SSH terminals, force a new terminal tab each time it is launched,
		// if not set otherwise from outside
		if (!properties.containsKey(ITerminalsConnectorConstants.PROP_FORCE_NEW)) {
			properties.put(ITerminalsConnectorConstants.PROP_FORCE_NEW, Boolean.TRUE);
		}

		// Get the terminal service
		ITerminalService terminal = TerminalServiceFactory.getService();
		// If not available, we cannot fulfill this request
		if (terminal != null) {
			terminal.openConsole(properties, done);
		}
	}

	@Override
	public ITerminalConnector createTerminalConnector(Map<String, Object> properties) {
    	String connectorId = (String)properties.get(ITerminalsConnectorConstants.PROP_TERMINAL_CONNECTOR_ID);
		if (connectorId == null) connectorId = "org.eclipse.tm.terminal.connector.broken.BrokenConnector"; //$NON-NLS-1$
		ITerminalConnector connector = TerminalConnectorExtension.makeTerminalConnector(connectorId);
		return connector;
	}

}
