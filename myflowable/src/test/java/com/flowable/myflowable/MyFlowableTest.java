package com.flowable.myflowable;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.flowable.bpmn.model.EndEvent;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.bpmn.model.Process;
import org.flowable.bpmn.model.StartEvent;
import org.flowable.common.engine.api.delegate.event.FlowableEngineEventType;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.Test;


public class MyFlowableTest {
	
	private ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResourceDefault().buildProcessEngine();
	
	
	@Test
	public void testSayHello() {
		System.out.println("jells");
	}
	
	
	@Test
	public void flowableEngineInit() {
		ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResourceDefault();
		ProcessEngine processEngine = configuration.buildProcessEngine();
		if (null != processEngine) {
			System.out.println("流程引擎初始化完成");
		}
	}
	
	@Test
	public void flowableDeployMent() {
		Deployment deploymentId = processEngine.getRepositoryService().createDeployment().addClasspathResource("MyProcess.bpmn").deploy();
		String id = deploymentId.getId();
		System.out.println("deploymentId:" + id);
		ProcessDefinition prodef = processEngine.getRepositoryService().createProcessDefinitionQuery().deploymentId(id).singleResult();
		System.out.println("流程定义ID:" + prodef.getId());
	}
	
	@Test
	public void startProcess() {
		String processDefinitionId = "myProcess:1:4";
		ProcessInstance proInstance = processEngine.getRuntimeService().startProcessInstanceById(processDefinitionId);
		System.out.println("processInstance:" + proInstance.getId());
	}
	
	@Test
	public void startProcess2() {
//		String processDefinitionId = "secondmodel:1:f1ecfe81-9c66-11eb-aabe-dc1ba1f6992f";
//		String processDefinitionId = "mutiinstancekey:1:107b3f09-9e65-11eb-97d5-dc1ba1f6992f";
//		String processDefinitionId = "kskkkk:1:e9c5e766-9e9c-11eb-8b83-dc1ba1f6992f";
//		String processDefinitionId = "fourmodelkey:1:5510f51e-9ccf-11eb-b96a-dc1ba1f6992f";
//		processEngine.getRuntimeService().addEventListener(new MyMutilInstanceTask(),
//				FlowableEngineEventType.MULTI_INSTANCE_ACTIVITY_STARTED,FlowableEngineEventType.MULTI_INSTANCE_ACTIVITY_COMPLETED,
//				FlowableEngineEventType.MULTI_INSTANCE_ACTIVITY_COMPLETED_WITH_CONDITION);
		processEngine.getRuntimeService().addEventListener(new GlobalTaskAssignmentListener(),
				FlowableEngineEventType.ACTIVITY_CANCELLED,FlowableEngineEventType.ACTIVITY_COMPLETED,
				FlowableEngineEventType.PROCESS_CANCELLED,FlowableEngineEventType.PROCESS_COMPLETED,
				FlowableEngineEventType.PROCESS_CREATED,FlowableEngineEventType.PROCESS_STARTED,FlowableEngineEventType.TASK_ASSIGNED,
				FlowableEngineEventType.TASK_COMPLETED,FlowableEngineEventType.TASK_CREATED);
//		String processDefinitionId = "thirdmodelkey:1:6e0c7a6d-9c6a-11eb-aabe-dc1ba1f6992f";
		String processDefinitionId = "thirdmodelkey-0420:1:76582b9f-a1be-11eb-b884-dc1ba1f6992f";
		Map<String,Object> variables = new HashMap<String,Object>();
		variables.put("leader", "gaoqiang");
//		variables.put("manager", "gaoqiang");
//		variables.put("manager", "gaoqiang");
		ProcessInstance proInstance = processEngine.getRuntimeService().startProcessInstanceById(processDefinitionId,variables);
//		ProcessInstance proInstance = processEngine.getRuntimeService().startProcessInstanceById(processDefinitionId);
//		ProcessInstance proInstance = processEngine.getRuntimeService().startProcessInstanceById(processDefinitionId);
		System.out.println("processInstance:" + proInstance.getId());
//		processEngine.getRuntimeService().createExecutionQuery().processInstanceId(proInstance.getId()).singleResult();
		List<Task> taskList = processEngine.getTaskService().createTaskQuery().processInstanceId(proInstance.getId()).list();
		for (Task task : taskList) {
			
			String assignee = task.getAssignee();
			System.out.println(assignee);
			processEngine.getTaskService().complete(task.getId());
		}
		System.out.println("即将审批任务");
		System.out.println("完成了审批任务");
		Task task2 = processEngine.getTaskService().createTaskQuery().processInstanceId(proInstance.getId()).singleResult();
		String assignee2 = task2.getAssignee();
		System.out.println(assignee2);
		processEngine.getTaskService().complete(task2.getId());
		System.out.println("完成了审批任务");
	}
	
	
	
	
	@Test
	public void complete() {
		String processInstanceId = "2501";
		Task task = processEngine.getTaskService().createTaskQuery().processInstanceId(processInstanceId).singleResult();
		String taskId = task.getId();
		System.out.println("taskId:"+taskId);
		processEngine.getTaskService().complete(taskId);
		System.out.println("user_1 完成了节点任务");
		ProcessInstance proInstance = processEngine.getRuntimeService().createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		String currentActivityId = processEngine.getRuntimeService().createExecutionQuery().executionId(task.getExecutionId()).singleResult().getActivityId();
//		String currentActivityId = processEngine.getRuntimeService().createExecutionQuery().processInstanceId(processInstanceId).parentId(processInstanceId).
		System.out.println("当前活动节点的ID:"+currentActivityId);
		String defId = proInstance.getProcessDefinitionId();
		Process p = processEngine.getRepositoryService().getBpmnModel(defId).getProcesses().get(0);
		Collection<FlowElement> c = p.getFlowElements();
		String newActivityId = null;
		for (FlowElement flow : c) {
//			if (flow instanceof EndEvent) {
//				System.out.println(flow.getId());
//				System.out.println(flow.getName());
//				newActivityId = flow.getId();
//			}
			if (flow instanceof StartEvent) {
				System.out.println(flow.getId());
				System.out.println(flow.getName());
				newActivityId = flow.getId();
			}
		}
		if (null == newActivityId) {
			System.out.println("获取结束节点失败");
			return;
		}
		try {
			processEngine.getRuntimeService().createChangeActivityStateBuilder().processInstanceId(processInstanceId)
				.moveActivityIdTo(currentActivityId, newActivityId).changeState();
			System.out.println("chanced!");
			ProcessInstance proInstance2 = processEngine.getRuntimeService().createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
			if (null == proInstance2) {
				System.out.println("节点跳转成功");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
