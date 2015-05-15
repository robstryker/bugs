/*******************************************************************************
 * Copyright (c) 2011, 2015 Wind River Systems, Inc. and others. All rights reserved.
 * This program and the accompanying materials are made available under the terms
 * of the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Wind River Systems - initial API and implementation
 * Max Weninger (Wind River) - [361352] [TERMINALS][SSH] Add SSH terminal support
 *******************************************************************************/
package org.eclipse.tm.terminal.connector.broken.controls;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.tm.terminal.view.ui.interfaces.IConfigurationPanelContainer;
import org.eclipse.tm.terminal.view.ui.panels.AbstractExtendedConfigurationPanel;

/**
 * SSH wizard configuration panel implementation.
 */
public class BrokenConfigurationPanel extends AbstractExtendedConfigurationPanel {

	public BrokenConfigurationPanel(IConfigurationPanelContainer container) {
		super(container);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setupPanel(Composite parent) {
		Composite panel = new Composite(parent, SWT.NONE);
		panel.setLayout(new GridLayout());
		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
		panel.setLayoutData(data);
		setControl(panel);
	}

	@Override
	protected void saveSettingsForHost(boolean add) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void fillSettingsForHost(String host) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getHostFromSettings() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isValid() {
		setMessage(null, NONE);
		return true;
	}
}
