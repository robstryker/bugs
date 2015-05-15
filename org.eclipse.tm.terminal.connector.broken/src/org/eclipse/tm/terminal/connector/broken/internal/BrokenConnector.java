package org.eclipse.tm.terminal.connector.broken.internal;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import org.eclipse.tm.internal.terminal.provisional.api.ITerminalControl;
import org.eclipse.tm.internal.terminal.provisional.api.TerminalState;
import org.eclipse.tm.internal.terminal.provisional.api.provider.TerminalConnectorImpl;

public class BrokenConnector extends TerminalConnectorImpl {
	ByteArrayOutputStream bos;

	@Override
	public OutputStream getTerminalToRemoteStream() {
		if( bos == null ) 
			bos = new ByteArrayOutputStream();
		return bos;
	}

	@Override
	public String getSettingsSummary() {
		return "BrokenSettings";
	}
	
	public void connect(ITerminalControl control) {
		super.connect(control);
		// set state
		fControl.setState(TerminalState.CONNECTED);

		System.out.println("UI Freeze on connect");
		// Demonstrate a UI freeze
		try {
			Thread.sleep(10000);
		} catch(InterruptedException ie) {
			
		}
	}

	protected void doDisconnect() {
		System.out.println("UI Freeze on disconnect");
		// Demonstrate a UI freeze
		try {
			Thread.sleep(10000);
		} catch(InterruptedException ie) {
			
		}
	}
}
