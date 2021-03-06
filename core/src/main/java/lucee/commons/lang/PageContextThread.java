package lucee.commons.lang;

import lucee.runtime.PageContext;
import lucee.runtime.engine.ThreadLocalPageContext;

/**
 * thead that init a PageContext with ThreadLocal, only use this Thread when you are sure it ends before the parent thread
 */
public abstract class PageContextThread extends Thread {
	
	private PageContext pageContext;

	public PageContextThread(PageContext pc){
		this.pageContext=pc;
		setDaemon(true);
	}

	@Override
	public final void run() {
		ThreadLocalPageContext.register(pageContext);// register the PageContext to this thread
		try{
			run(pageContext);
		}
		finally {
			ThreadLocalPageContext.release();
		}
		
	}
	
	public abstract void run(PageContext pageContext);
}
