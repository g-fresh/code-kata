package me.gfresh.kata.nucleotidecount;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.MapAssert.entry;
import static org.junit.Assert.assertEquals;

import org.junit.*;

public class DNATest {

	@Test(expected = IllegalArgumentException.class)
	public void constructor_should_throw_exception_on_null_argument() {
		new DNA(null);
	}

	@Test
	public void count_should_return_zero_if_DNA_does_not_contain_specified_nuleotide() {
		assertEquals(0, new DNA("").count('A'));
		assertEquals(0, new DNA("GATA").count('C'));
	}

	@Test
	public void count_should_return_count_of_specified_nuleotide() {
		DNA dna = new DNA("GGGGGTAACCCGG");
		assertEquals(2, dna.count('A'));
		assertEquals(3, dna.count('C'));
		assertEquals(7, dna.count('G'));
		assertEquals(1, dna.count('T'));
	}

	@Test(expected = IllegalArgumentException.class)
	public void count_should_throw_exception_on_invalid_nuleotide_argument() {
		new DNA("ACGT").count('X');
	}

	@Test(expected = IllegalArgumentException.class)
	public void count_should_throw_exception_on_invalid_nuleotide_in_dna() {
		new DNA("ACGTX").count('A');
	}

	@Test(expected = IllegalArgumentException.class)
	public void count_should_throw_exception_on_null_argument() {
		new DNA("ACGT").count(null);
	}

	@Test
	public void nucleotideCounts_should_return_zero_on_empty_DNA_strand() {
		assertThat(new DNA("").nucleotideCounts())
			.hasSize(4)
			.includes(entry('A', 0), entry('C', 0), entry('G', 0), entry('T', 0));
	}

	@Test
	public void nucleotideCounts_should_return_correct_counts() {
		assertThat(new DNA("AGCTTCATTCTGACTGCAACGGGCAATATGTCTCTGTGTGGATTAAAGAGTGTCTGATAGCAGC").nucleotideCounts())
			.hasSize(4)
			.includes(entry('A', 16), entry('C', 12), entry('G', 17), entry('T', 19));
		assertThat(new DNA("GGGGGTCCCGG").nucleotideCounts())
			.hasSize(4)
			.includes(entry('A', 0), entry('C', 3), entry('G', 7), entry('T', 1));
	}

	@Test
	public void nucleotideCounts_always_has_entry_for_each_nucleotide_type() {
		DNA dna = new DNA("AAAC");
		assertThat(dna.nucleotideCounts())
			.hasSize(4)
			.includes(entry('A', 3), entry('C', 1), entry('G', 0), entry('T', 0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void nucleotideCounts_should_throw_exception_on_invalid_nucleotide_in_dna() {
		new DNA("ACGTX").nucleotideCounts();
	}

	@Test
	public void using_both_count_and_nucleotideCounts_works() {
		DNA dna = new DNA("GATTAA");
		assertEquals(3, dna.count('A'));
		assertThat(dna.nucleotideCounts())
			.hasSize(4)
			.includes(entry('A', 3), entry('C', 0), entry('G', 1), entry('T', 2));
	}
}
