package me.gfresh.kata.say;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class SayTest {

	@Test
	public void testSplit() throws Exception {
		assertEquals(list(0), Say.split(0));
		assertEquals(list(9), Say.split(9));
		assertEquals(list(12), Say.split(12));
		assertEquals(list(99), Say.split(99));
		assertEquals(list(999), Say.split(999));
		assertEquals(list(1, 0), Say.split(1000));
		assertEquals(list(1, 1), Say.split(1001));
		assertEquals(list(100, 999), Say.split(100999));
		assertEquals(list(2, 1), Say.split(2001));
		assertEquals(list(10, 0), Say.split(10000));
		assertEquals(list(5, 0, 42), Say.split(5000042));
		assertEquals(list(2, 147, 483, 647), Say.split(Integer.MAX_VALUE));
	}

	private List<Integer> list(Integer... numbers) {
		return Arrays.asList(numbers);
	}

	@Test
	public void testInEnglish() throws Exception {
		System.out.println(Say.inEnglish(0));
		System.out.println(Say.inEnglish(1024));
		System.out.println(Say.inEnglish(102400012));
		System.out.println(Say.inEnglish(42));
		System.out.println(Say.inEnglish(1000123));
	}
}
