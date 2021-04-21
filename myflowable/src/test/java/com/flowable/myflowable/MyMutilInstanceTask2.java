package com.flowable.myflowable;

import java.util.ArrayList;
import java.util.List;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;

public class MyMutilInstanceTask2 implements ExecutionListener{


	@Override
	public void notify(DelegateExecution execution) {
		
		List<String> params = new ArrayList<String>();
		params.add("liudehua");
		params.add("xijinping");
		execution.setVariable("assigneeList", params);
		
		
		
	}

	

}
