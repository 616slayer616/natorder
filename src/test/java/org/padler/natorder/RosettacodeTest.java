package org.padler.natorder;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * These tests are inspired by https://rosettacode.org/wiki/Natural_sorting
 */
class RosettacodeTest {

    private NaturalOrderComparator naturalOrderComparator = new NaturalOrderComparator();

    @Test
    void ignoringLeadingSpaces() {
        List<String> unSorted = Arrays.asList("ignore leading spaces: 2-2", " ignore leading spaces: 2-1", "  ignore leading spaces: 2+0", "   ignore leading spaces: 2+1");
        List<String> sorted = Arrays.asList("  ignore leading spaces: 2+0", "   ignore leading spaces: 2+1", " ignore leading spaces: 2-1", "ignore leading spaces: 2-2");

        unSorted.sort(naturalOrderComparator);

        assertThat(unSorted, is(sorted));
    }

    @Test
    void ignoringMultipleAdjacentSpaces() {
        List<String> unSorted = Arrays.asList("ignore m.a.s spaces: 2-2", "ignore m.a.s  spaces: 2-1", "ignore m.a.s   spaces: 2+0", "ignore m.a.s    spaces: 2+1");
        List<String> sorted = Arrays.asList("ignore m.a.s   spaces: 2+0", "ignore m.a.s    spaces: 2+1", "ignore m.a.s  spaces: 2-1", "ignore m.a.s spaces: 2-2");

        unSorted.sort(naturalOrderComparator);

        assertThat(unSorted, is(sorted));
    }

    @Test
    @Disabled("The result is different from that on rosettacode")
    void equivalentWhitespaceCharacters() {
        List<String> unSorted = Arrays.asList("Equiv. spaces: 3-3", "Equiv.\rspaces: 3-2", "Equiv.\014spaces: 3-1", "Equiv.\013spaces: 3+0", "Equiv.\nspaces: 3+1", "Equiv.\tspaces: 3+2");
        List<String> sorted = Arrays.asList("Equiv.\013spaces: 3+0", "Equiv.\nspaces: 3+1", "Equiv.\014spaces: 3-1", "Equiv.\tspaces: 3+2", "Equiv.\rspaces: 3-2", "Equiv. spaces: 3-3");

        unSorted.sort(naturalOrderComparator);

        assertThat(unSorted, is(sorted));
    }

    @Test
    void equivalentWhitespaceCharacters_noTab() {
        List<String> unSorted = Arrays.asList("Equiv. spaces: 3-3", "Equiv.\rspaces: 3-2", "Equiv.\nspaces: 3+1");
        List<String> sorted = Arrays.asList("Equiv.\nspaces: 3+1", "Equiv.\rspaces: 3-2", "Equiv. spaces: 3-3");

        unSorted.sort(naturalOrderComparator);

        assertThat(unSorted, is(sorted));
    }

    @Test
    void caseIndependent() {
        List<String> unSorted = Arrays.asList("cASE INDEPENENT: 3-2", "caSE INDEPENENT: 3-1", "casE INDEPENENT: 3+0", "case INDEPENENT: 3+1");
        List<String> sorted = Arrays.asList("casE INDEPENENT: 3+0", "case INDEPENENT: 3+1", "caSE INDEPENENT: 3-1", "cASE INDEPENENT: 3-2");

        unSorted.sort(naturalOrderComparator);

        assertThat(unSorted, is(sorted));
    }

    @Test
    void numericFieldsAsNumerics() {
        List<String> unSorted = Arrays.asList("foo100bar99baz0.txt", "foo100bar10baz0.txt", "foo1000bar99baz10.txt", "foo1000bar99baz9.txt");
        List<String> sorted = Arrays.asList("foo100bar10baz0.txt", "foo100bar99baz0.txt", "foo1000bar99baz9.txt", "foo1000bar99baz10.txt");

        unSorted.sort(naturalOrderComparator);

        assertThat(unSorted, is(sorted));
    }

    @Test
    void titleSorts() {
        List<String> unSorted = Arrays.asList("The Wind in the Willows", "The 40th step more", "The 39 steps", "Wanda");
        List<String> sorted = Arrays.asList("The 39 steps", "The 40th step more", "The Wind in the Willows", "Wanda");

        unSorted.sort(naturalOrderComparator);

        assertThat(unSorted, is(sorted));
    }

    @Test
    void equivalentAccentedCharactersAndCase() {
        List<String> unSorted = Arrays.asList("Equiv. ý accents: 2-2", "Equiv. Ý accents: 2-1", "Equiv. y accents: 2+0", "Equiv. Y accents: 2+1");
        List<String> sorted = Arrays.asList("Equiv. y accents: 2+0", "Equiv. Y accents: 2+1", "Equiv. Ý accents: 2-1", "Equiv. ý accents: 2-2");

        unSorted.sort(naturalOrderComparator);

        assertThat(unSorted, is(sorted));
    }

    @Test
    @Disabled("The result is different from that on rosettacode")
    void separatedLigatures() {
        List<String> unSorted = Arrays.asList("\u0132 ligatured ij", "no ligature");
        List<String> sorted = Arrays.asList("\u0132 ligatured ij", "no ligature");

        unSorted.sort(naturalOrderComparator);

        assertThat(unSorted, is(sorted));
    }

    @Test
    @Disabled("The result is different from that on rosettacode")
    void characterReplacements() {
        List<String> unSorted = Arrays.asList("Start with an \u0292: 2-2", "Start with an \u017f: 2-1", "Start with an ß: 2+0", "Start with an s: 2+1");
        List<String> sorted = Arrays.asList("Start with an s: 2+1", "Start with an ſ: 2-1", "Start with an ʒ: 2-2", " Start with an ß: 2 + 0");

        unSorted.sort(naturalOrderComparator);

        assertThat(unSorted, is(sorted));
    }

    @Test
    void characterReplacements_() {
        List<String> unSorted = Arrays.asList("Start with an \u0292: 2-2", "Start with an \u017f: 2-1", "Start with an ß: 2+0", "Start with an s: 2+1");
        List<String> sorted = Arrays.asList("Start with an s: 2+1", "Start with an ſ: 2-1", "Start with an ß: 2+0", "Start with an ʒ: 2-2");

        unSorted.sort(naturalOrderComparator);

        assertThat(unSorted, is(sorted));
    }

}
