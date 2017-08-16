package com.rbc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.rbc.Main;

public class MainTest {

	@Test
	public void addItemToArrayListTest() {
		Main.add("abc");
		assertEquals("abc", Main.getList().get(0));
	}
}