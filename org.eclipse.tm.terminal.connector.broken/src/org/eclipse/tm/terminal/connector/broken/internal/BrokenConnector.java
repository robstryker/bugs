package org.eclipse.tm.terminal.connector.broken.internal;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.concurrent.LinkedBlockingQueue;

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
		fControl.setState(TerminalState.CONNECTING);

		System.out.println("UI Freeze on connect");
		final LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>();
		// Simulate something that waits 12 seconds before 'working'
		new Thread() {
			public void run() {
				try {
					Thread.sleep(12000);
				} catch(InterruptedException ie) {
				}
				try {
					queue.put(new Integer(5));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
		
		// Block on a read
		try {
			Integer obj = queue.take();
		} catch(InterruptedException ie) {
			// I've been interrupted. User must have pressed cancel.
			// Force close all the things
			fControl.setState(TerminalState.CLOSED);
			return;
		}
		
		// I completed normally
		fControl.setState(TerminalState.CONNECTED);
	}

	protected void doDisconnect() {
		System.out.println("UI Freeze on disconnect");
		final LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>();
		// Simulate something that waits 12 seconds before 'working'
		new Thread() {
			public void run() {
				try {
					Thread.sleep(12000);
				} catch(InterruptedException ie) {
				}
				try {
					queue.put(new Integer(5));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
		
		// Block on a read
		try {
			Integer obj = queue.take();
			// Normal close after some lag
			fControl.setState(TerminalState.CLOSED);
		} catch (InterruptedException e) {
			System.out.println("Interrupted exception");
			// I've been interrupted. User must have pressed cancel. I'll close everything forcefully
			fControl.setState(TerminalState.CLOSED);
			return;
		}
		// disconnected normally
		fControl.setState(TerminalState.CLOSED);
	}
}
