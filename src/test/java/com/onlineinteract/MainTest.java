package com.onlineinteract;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MainTest {

	@Test
	public void addItemToArrayListTest() {
		Main.add("abc");
		assertEquals("abc", Main.getList().get(0));
	}
}