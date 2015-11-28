package me.gfresh.kata.robotname;

import java.util.*;
import java.util.function.*;

public final class Robot {

	private String name;

	public String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}
}

final class Factory {

	private NameGenerator generator = new NameGenerator();

	public Robot makeRobot() {
		return reset(new Robot());
	}

	public Robot reset(Robot robot) {
		robot.setName(generator.generateUniqueName());
		return robot;
	}

	private class NameGenerator {

		private Random rnd = new Random();
		private BloomFilter names = new BloomFilter(4208777,
			name ->
				new Integer[] {
					name.hashCode(),
					(name + "foo").hashCode(),
					(name + "bar").hashCode(),
					(name + "baz").hashCode()
				});

		private String generateUniqueName() {
			for (int i = 0; i < 1000; i++) {
				String name = generateRandomName();
				if (! alreadyUsed(name)) return name;
			}
			throw new RuntimeException("Ran out of available robot names");
		}

		private String generateRandomName() {
			return randomLetter() + randomLetter() + randomDigit() + randomDigit() + randomDigit();
		}

		private String randomLetter() {
			return String.valueOf((char) ('A' + rnd.nextInt(26)));
		}

		private String randomDigit() {
			return String.valueOf(rnd.nextInt(10));
		}

		private boolean alreadyUsed(String name) {
			return ! names.add(name);
		}
	}
}

/**
 * Number of unique robot names (e.g.: DX142): 676000 (26x26x1000)
 *
 * Bloom filter configuration for this use case:
 * n = 675,000, p = 0.05 (1 in 20) → m = 4,208,777 (513.77KB), k = 4
 *
 * n - number of elements (names)
 * p - false positive probability
 * m - number of bits
 * k - number of hash functions
 *
 * {@link https://en.wikipedia.org/wiki/Bloom_filter}
 * {@link http://hur.st/bloomfilter}
 */
final class BloomFilter {

	private int size;
	private Function<String, Integer[]> hashFunctions;
	private BitSet bits;

	public BloomFilter(int size, Function<String, Integer[]> hashFunctions) {
		this.size = size;
		this.hashFunctions = hashFunctions;
		this.bits = new BitSet(size);
	}

	boolean add(String element) {
		boolean wasPresent = true;
		for (int hash : hashFunctions.apply(element)) {
			int pos = bitPosition(hash);
			wasPresent &= bits.get(pos);
			bits.set(pos);
		}
		return ! wasPresent;
	}

	private int bitPosition(int hash) {
		return Math.abs(hash) % size;
	}
}
