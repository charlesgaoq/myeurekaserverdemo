package com.flowable.myflowable;

import java.util.Date;

import org.flowable.common.engine.api.delegate.event.FlowableEvent;
import org.flowable.common.engine.api.delegate.event.FlowableEventListener;
import org.flowable.common.engine.api.delegate.event.FlowableEventType;

public class MyFlowableEventListener2 implements FlowableEventListener {

	@Override
	public void onEvent(FlowableEvent event) {
		// TODO Auto-generated method stub
		FlowableEventType type = event.getType();
		String name = type.name();
		System.out.println(new Date() + name);
	}

	@Override
	public boolean isFailOnException() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFireOnTransactionLifecycleEvent() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getOnTransaction() {
		// TODO Auto-generated method stub
		return null;
	}

}
