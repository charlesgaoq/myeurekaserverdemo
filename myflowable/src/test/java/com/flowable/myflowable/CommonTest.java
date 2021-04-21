package com.flowable.myflowable;

import org.junit.Test;

public class CommonTest {
	
	@Test
	public void strtoint() {
		String gg = "1000";
		System.out.println(Integer.parseInt(gg));
		System.out.println(Integer.valueOf(gg));
	}
	
}
