package com.flowable.myflowable;

import org.apache.commons.codec.binary.StringUtils;
import org.flowable.task.service.delegate.BaseTaskListener;
import org.flowable.task.service.delegate.DelegateTask;
import org.flowable.task.service.delegate.TaskListener;


public class MyTaskListener implements TaskListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7038349227884726146L;

	@Override
	public void notify(DelegateTask delegateTask) {
		
		if (StringUtils.equals(BaseTaskListener.EVENTNAME_ASSIGNMENT, delegateTask.getEventName())){
			
			System.out.println(BaseTaskListener.EVENTNAME_ASSIGNMENT);
			String manager = delegateTask.getVariable("manager", String.class);
			if (StringUtils.equals(manager, "gaoqiang")) {
				delegateTask.setAssignee("jinhong");
			} else if (StringUtils.equals(manager, "zhangsan")) {
				delegateTask.setAssignee("liudehua");
			}
			
		} else if (StringUtils.equals(BaseTaskListener.EVENTNAME_CREATE, delegateTask.getEventName())) {
			System.out.println(BaseTaskListener.EVENTNAME_CREATE);
		} else if (StringUtils.equals(BaseTaskListener.EVENTNAME_COMPLETE, delegateTask.getEventName())) {
			System.out.println(BaseTaskListener.EVENTNAME_COMPLETE);
		} else if (StringUtils.equals(BaseTaskListener.EVENTNAME_DELETE, delegateTask.getEventName())) {
			System.out.println(BaseTaskListener.EVENTNAME_DELETE);
		}
		
		String taskDefinitionKey = delegateTask.getTaskDefinitionKey();
		
		String processInstanceId = delegateTask.getProcessInstanceId();
		String executionId = delegateTask.getExecutionId();
		String id = delegateTask.getId();
		String manager = delegateTask.getVariable("manager", String.class);
		System.out.println(manager);
		
		
		
	}
	
}
