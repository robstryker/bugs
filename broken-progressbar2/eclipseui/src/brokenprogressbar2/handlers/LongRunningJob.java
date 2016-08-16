package brokenprogressbar2.handlers;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

public class LongRunningJob extends Job {

	private static final int INCREMENT = 1;

	private boolean useSetTaskName;
	private int TOTAL_TIME = 100000;

	/**
	 * LongRunningOperation constructor
	 * 
	 * @param indeterminate
	 *            whether the animation is unknown
	 */
	public LongRunningJob(boolean useSetTaskName) {
		super("LongRunning Example");
		this.useSetTaskName = useSetTaskName;
	}
	public LongRunningJob(boolean useSetTaskName, int total) {
		super("LongRunning Example");
		this.useSetTaskName = useSetTaskName;
		this.TOTAL_TIME = total;
	}

	/**
	 * Runs the long running operation
	 * 
	 * @param monitor
	 *            the progress monitor
	 */
	public IStatus run(IProgressMonitor monitor) {
		monitor.beginTask("Running long running operation", TOTAL_TIME);
		for (int total = 0; total < TOTAL_TIME && !monitor.isCanceled(); total += INCREMENT) {
			System.out.println("Running, loop " + total);
			// Thread.sleep(INCREMENT);
			monitor.worked(INCREMENT);

			if (total > TOTAL_TIME / 3)
				monitor.subTask("setting subtask" + total);
			// removing this line makes it finish instantly on all OS. Keeping
			// it makes it slow on OSX
			if (useSetTaskName)
				monitor.setTaskName("setTaskname " + total);
			// the click on cancel in dialog never makes it to the monitor - if
			// it did it should cancel instantly.
			if (monitor.isCanceled())
				return new Status(IStatus.CANCEL, "broken-progressbar2", "The long running operation was cancelled at step " + total);
		}
		monitor.done();
		if (monitor.isCanceled())
			return new Status(IStatus.CANCEL, "broken-progressbar2", "The long running operation was cancelled");
		return Status.OK_STATUS;
	}
}
