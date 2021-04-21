package com.flowable.myflowable;

import java.util.ArrayList;
import java.util.List;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngines;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;
import org.flowable.engine.delegate.event.AbstractFlowableEngineEventListener;
import org.flowable.engine.delegate.event.FlowableMultiInstanceActivityCompletedEvent;
import org.flowable.engine.delegate.event.FlowableMultiInstanceActivityEvent;

public class MyMutilInstanceTask extends AbstractFlowableEngineEventListener {

	@Override
	protected void multiInstanceActivityStarted(FlowableMultiInstanceActivityEvent event) {
		// TODO Auto-generated method stub
		System.out.println("started");
		String processInstanceId = event.getProcessInstanceId();
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		String activitiId = event.getActivityId();
		List<String> users = new ArrayList<String>();
		users.add("wujing");
		users.add("aobama");
		processEngine.getRuntimeService().setVariable(processInstanceId, "mygrouplist", users);
	}

	@Override
	protected void multiInstanceActivityCompleted(FlowableMultiInstanceActivityCompletedEvent event) {
		// TODO Auto-generated method stub
		System.out.println("completed");
	}

	@Override
	protected void multiInstanceActivityCompletedWithCondition(FlowableMultiInstanceActivityCompletedEvent event) {
		// TODO Auto-generated method stub
		System.out.println("condition completed");
	}

	
	
	
}
