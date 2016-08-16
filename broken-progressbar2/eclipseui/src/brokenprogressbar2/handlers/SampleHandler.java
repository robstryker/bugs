package brokenprogressbar2.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class SampleHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		// To demonstrate the progress dialog operating quickly, use false
		// To demonstrate the progress dialog operating very slowly, use true
		Job longJob = new LongRunningJob(true, 300000);
		
		
		longJob.schedule();
		IProgressService progressService= PlatformUI.getWorkbench().getProgressService();
		progressService.showInDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), longJob);
		return null;
	}
}
