package com.flowable.myflowable;

import org.flowable.engine.delegate.BaseExecutionListener;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;

public class ProcessInstanceExecution implements ExecutionListener{

	@Override
	public void notify(DelegateExecution execution) {
		
		String event = execution.getEventName();
		switch (event) {
		case BaseExecutionListener.EVENTNAME_END:
			System.out.println("end");
			break;
		case BaseExecutionListener.EVENTNAME_START:
			System.out.println("start");
			break;
		case BaseExecutionListener.EVENTNAME_TAKE:
			System.out.println("take");
			break;
		default:
			break;
		}
		
		
		
	}

}
