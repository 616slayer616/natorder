package org.padler.natorder;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ZeroFlavorTest {

    private final NaturalOrderComparator naturalOrderComparator = new NaturalOrderComparator(true, Flavor.ZERO);

    @Test
    void simple() {
        List<String> unsorted = Arrays.asList("2", "3", "1");
        List<String> sorted = Arrays.asList("1", "2", "3");

        unsorted.sort(naturalOrderComparator);

        assertThat(unsorted).isEqualTo(sorted);
    }

    @Test
    void differentLengthEquality() {
        int compare = naturalOrderComparator.compare("1-2", "1-02");
        int compare2 = naturalOrderComparator.compare("1-02", "1-2");

        assertTrue(compare < 0);
        assertTrue(compare2 > 0);
    }

    @Test
    void sameLengthEquality() {
        int compare = naturalOrderComparator.compare("pic 5", "pic05");
        int compare2 = naturalOrderComparator.compare("pic05", "pic 5");

        assertTrue(compare < 0);
        assertTrue(compare2 > 0);
    }

    @Test
    void zero() {
        int compare = naturalOrderComparator.compare("pic010", "pic 010");
        int compare2 = naturalOrderComparator.compare("pic 010", "pic010");

        assertTrue(compare < 0);
        assertTrue(compare2 > 0);
    }

    @Test
    void leadingZeroes() {
        List<String> unsorted = Arrays.asList("0003", "03");
        List<String> sorted = Arrays.asList("03", "0003");

        unsorted.sort(naturalOrderComparator);

        assertThat(unsorted).isEqualTo(sorted);
    }

    @Test
    void wikipediaTest() {
        List<String> unsorted = Arrays.asList("z11", "z2");
        List<String> sorted = Arrays.asList("z2", "z11");

        unsorted.sort(naturalOrderComparator);

        assertThat(unsorted).isEqualTo(sorted);
    }

    @Test
    void wikipediaTestStable() {
        List<String> unsorted = Arrays.asList("z2", "z11");
        List<String> sorted = Arrays.asList("z2", "z11");

        unsorted.sort(naturalOrderComparator);

        assertThat(unsorted).isEqualTo(sorted);
    }

    @Test
    void files() {
        List<String> unsorted = Arrays.asList("file1.txt", "file2.txt", "file10.txt");
        List<String> sorted = Arrays.asList("file1.txt", "file2.txt", "file10.txt");

        unsorted.sort(naturalOrderComparator);

        assertThat(unsorted).isEqualTo(sorted);
    }

    @Test
    void versions() {
        List<String> unsorted = Arrays.asList("1.2.9.1", "1.2.10.5");
        List<String> sorted = Arrays.asList("1.2.9.1", "1.2.10.5");

        unsorted.sort(naturalOrderComparator);

        assertThat(unsorted).isEqualTo(sorted);
    }

    @Test
    void versionsStable() {
        List<String> unsorted = Arrays.asList("1.2.10.5", "1.2.9.1");
        List<String> sorted = Arrays.asList("1.2.9.1", "1.2.10.5");

        unsorted.sort(naturalOrderComparator);

        assertThat(unsorted).isEqualTo(sorted);
    }

    @Test
    void hyphens() {
        List<String> unsorted = Arrays.asList("a-1", "a1-1", "a2-1");
        List<String> sorted = Arrays.asList("a-1", "a1-1", "a2-1");

        unsorted.sort(naturalOrderComparator);

        assertThat(unsorted).isEqualTo(sorted);
    }

    @Test
    void underscores() {
        List<String> unsorted = Arrays.asList("a_1", "a1_1", "a2_1");
        List<String> sorted = Arrays.asList("a_1", "a1_1", "a2_1");

        unsorted.sort(naturalOrderComparator);

        assertThat(unsorted).isEqualTo(sorted);
    }

    @Test
    void middleZero() {
        List<String> unsorted = Arrays.asList("abc0def", "abc#def", "abc*def", "abc1def", "abc2def", "abc10def", "abc20def");
        List<String> sorted = Arrays.asList("abc#def", "abc*def", "abc0def", "abc1def", "abc2def", "abc10def", "abc20def");

        unsorted.sort(naturalOrderComparator);

        assertThat(unsorted).isEqualTo(sorted);
    }
}
