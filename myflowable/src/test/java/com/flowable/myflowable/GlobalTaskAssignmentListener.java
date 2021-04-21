package com.flowable.myflowable;

import org.flowable.common.engine.api.delegate.event.FlowableEngineEntityEvent;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.delegate.event.AbstractFlowableEngineEventListener;
import org.flowable.engine.delegate.event.FlowableProcessStartedEvent;
import org.flowable.task.service.impl.persistence.entity.TaskEntityImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 设置审批人
 * @author eversec
 *
 */

public class GlobalTaskAssignmentListener extends AbstractFlowableEngineEventListener{

//	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalTaskAssignmentListener.class);

	@Override
	protected void taskAssigned(FlowableEngineEntityEvent event) {
		// TODO Auto-generated method stub
		System.out.println("enter taskAssigned");
		System.out.println("开始业务逻辑处理");
		Object obj = event.getEntity();
		//获取上一个节点设置的领导信息，然后通过process信息获取当前节点的审批设置信息
		if (obj instanceof TaskEntityImpl) {
			TaskEntityImpl taskEntityImpl = (TaskEntityImpl) obj;
			System.out.println(taskEntityImpl.getAssignee());
//			taskEntityImpl.setAssigneeValue("dongshizhang");
			taskEntityImpl.setAssignee("dongshizhang");
		}
		System.out.println("结束业务逻辑处理");
		System.out.println("leave taskAssigned");
	}

	@Override
	protected void taskCreated(FlowableEngineEntityEvent event) {
		// TODO Auto-generated method stub
		super.taskCreated(event);
		System.out.println("taskCreated");
	}

	@Override
	protected void taskCompleted(FlowableEngineEntityEvent event) {
		// TODO Auto-generated method stub
		super.taskCompleted(event);
		System.out.println("taskCompleted");
	}

	@Override
	protected void processCreated(FlowableEngineEntityEvent event) {
		// TODO Auto-generated method stub
		super.processCreated(event);
		System.out.println("processCreated");
	}

	@Override
	protected void processStarted(FlowableProcessStartedEvent event) {
		// TODO Auto-generated method stub
		super.processStarted(event);
		System.out.println("processStarted");
	}

	@Override
	protected void processCompleted(FlowableEngineEntityEvent event) {
		// TODO Auto-generated method stub
		super.processCompleted(event);
		System.out.println("processCompleted");
	}

	@Override
	protected void processCompletedWithTerminateEnd(FlowableEngineEntityEvent event) {
		// TODO Auto-generated method stub
		super.processCompletedWithTerminateEnd(event);
	}

	@Override
	protected void processCompletedWithErrorEnd(FlowableEngineEntityEvent event) {
		// TODO Auto-generated method stub
		super.processCompletedWithErrorEnd(event);
	}

	
	
	
}
