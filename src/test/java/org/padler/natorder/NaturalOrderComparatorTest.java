package org.padler.natorder;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NaturalOrderComparatorTest {

    private final NaturalOrderComparator naturalOrderComparator = new NaturalOrderComparator(true);

    private final List<String> sorted = Arrays.asList("1-2", "1-02", "1-20", "10-20", "fred", "jane", "pic01",
            "pic2", "pic02", "pic02a", "pic3", "pic4", "pic 4 else", "pic 5", "pic05",
            "pic 5 something", "pic 6", "pic   7", "pic100", "pic100a", "pic120", "pic121",
            "pic02000", "tom", "x2-g8", "x2-y7", "x2-y08", "x8-y8");
    private final List<String> unsorted = Arrays.asList("1-2", "1-02", "1-20", "10-20", "fred", "jane", "pic01",
            "pic2", "pic02", "pic02a", "pic3", "pic4", "pic 4 else", "pic 5", "pic05",
            "pic 5 something", "pic 6", "pic   7", "pic100", "pic100a", "pic120", "pic121",
            "pic02000", "tom", "x2-g8", "x2-y7", "x2-y08", "x8-y8");

    @Test
    void simple() {
        List<String> unsorted = Arrays.asList("2", "3", "1");
        List<String> sorted = Arrays.asList("1", "2", "3");

        unsorted.sort(naturalOrderComparator);

        assertThat(unsorted).isEqualTo(sorted);
    }

    @Test
    void simpleWithChars() {
        List<String> unsorted = Arrays.asList("2", "3", "1B", "1A", "1");
        List<String> sorted = Arrays.asList("1", "1A", "1B", "2", "3");

        unsorted.sort(naturalOrderComparator);

        assertThat(unsorted).isEqualTo(sorted);
    }

    @Test
    void sortHouseNumbers() {
        List<String> unsorted = Arrays.asList("A", "1007A", "1A", "1B", "2A", "8052A", "2165A", "4472A", "2500A", "4604A");
        List<String> sorted = Arrays.asList("1A", "1B", "2A", "1007A", "2165A", "2500A", "4472A", "4604A", "8052A", "A");
        unsorted.sort(naturalOrderComparator);

        assertThat(unsorted).isEqualTo(sorted);
    }

    @Test
    void defaultTest() {
        unsorted.sort(naturalOrderComparator);

        assertThat(unsorted).isEqualTo(sorted);
    }

    @Test
    void scramble42() {
        Collections.shuffle(unsorted, new Random(42));

        unsorted.sort(naturalOrderComparator);

        assertThat(unsorted).isEqualTo(sorted);
    }

    @Test
    void scramble420() {
        Collections.shuffle(unsorted, new Random(420));

        unsorted.sort(naturalOrderComparator);

        assertThat(unsorted).isEqualTo(sorted);
    }

    @Test
    void scramble1337() {
        Collections.shuffle(unsorted, new Random(1337));

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
    void floats() {
        List<String> unsorted = Arrays.asList("0.9", "1.0c", "1.2", "1.3", "0.6", "1.1", "0.7", "0.3", "1.0b", "1.0", "0.8");
        List<String> sorted = Arrays.asList("0.3", "0.6", "0.7", "0.8", "0.9", "1.0", "1.0b", "1.0c", "1.1", "1.2", "1.3");

        unsorted.sort(naturalOrderComparator);

        assertThat(unsorted).isEqualTo(sorted);
    }

    @Test
    void floatsWithCommas() {
        List<String> unsorted = Arrays.asList("0,9", "1,0c", "1,2", "1,3", "0,6", "1,1", "0,7", "0,3", "1,0b", "1,0", "0,8");
        List<String> sorted = Arrays.asList("0,3", "0,6", "0,7", "0,8", "0,9", "1,0", "1,0b", "1,0c", "1,1", "1,2", "1,3");

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
    void ignoreWhiteSpaceText() {
        List<String> unsorted = Arrays.asList("The A", "The X", "The a", "Theme", "The x");
        List<String> sorted = Arrays.asList("The a", "The A", "Theme", "The x", "The X");

        unsorted.sort(naturalOrderComparator);

        assertThat(unsorted).isEqualTo(sorted);
    }

    @Test
    void ignoreWhiteSpaceNumber() {
        List<String> unsorted = Arrays.asList("1", "1 000 000", "10", "10 000", "10 000 000", "100", "100 000", "1000");
        List<String> sorted = Arrays.asList("1", "10", "100", "1000", "10 000", "100 000", "1 000 000", "10 000 000");

        unsorted.sort(naturalOrderComparator);

        assertThat(unsorted).isEqualTo(sorted);
    }

    @Test
    void sortTime() {
        List<String> unsorted = Arrays.asList("10:01:00", "10:09:00", "10:10:00", "10:00:00", "10:00:01");
        List<String> sorted = Arrays.asList("10:00:00", "10:00:01", "10:01:00", "10:09:00", "10:10:00");

        unsorted.sort(naturalOrderComparator);

        assertThat(unsorted).isEqualTo(sorted);
    }

}
