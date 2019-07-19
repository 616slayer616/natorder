package org.padler.natorder;

import java.util.Comparator;

public class NaturalOrderComparator implements Comparator<String> {

    private int compareRight(String a, String b) {
        int bias = 0;
        int ia = 0;
        int ib = 0;

        // The longest run of digits wins. That aside, the greatest
        // value wins, but we can't know that it will until we've scanned
        // both numbers to know that they have the same magnitude, so we
        // remember it in BIAS.
        for (; ; ia++, ib++) {
            char ca = charAt(a, ia);
            char cb = charAt(b, ib);

            boolean isADigit = isDigit(ca);
            boolean isBDigit = isDigit(cb);
            if (!isADigit && !isBDigit) {
                return bias;
            }
            if (!isADigit) {
                return -1;
            }
            if (!isBDigit) {
                return 1;
            }

            if (bias == 0) {
                if (ca < cb) {
                    bias = -1;
                } else if (ca > cb) {
                    bias = 1;
                }
            }
        }
    }

    public int compare(String o1, String o2) {
        int ia = 0;
        int ib = 0;
        int nza;
        int nzb;
        char ca;
        char cb;

        while (true) {
            // Only count the number of zeroes leading the last number compared
            nza = nzb = 0;

            ca = charAt(o1, ia);
            cb = charAt(o2, ib);

            // skip over leading spaces or zeros
            while (Character.isSpaceChar(ca) || ca == '0') {
                if (ca == '0') {
                    nza++;
                } else {
                    // Only count consecutive zeroes
                    nza = 0;
                }

                ++ia;
                ca = charAt(o1, ia);
            }

            while (Character.isSpaceChar(cb) || cb == '0') {
                if (cb == '0') {
                    nzb++;
                } else {
                    // Only count consecutive zeroes
                    nzb = 0;
                }

                ++ib;
                cb = charAt(o2, ib);
            }

            // Process run of digits
            if (Character.isDigit(ca) && Character.isDigit(cb)) {
                int bias = compareRight(o1.substring(ia), o2.substring(ib));
                if (bias != 0) {
                    return bias;
                }
            }

            if (ca == 0 && cb == 0) {
                // The strings compare the same. Use other means of comparison
                return compareEqual(o1, o2, nza, nzb);
            }
            if (ca < cb) {
                return -1;
            }
            if (ca > cb) {
                return 1;
            }

            ++ia;
            ++ib;
        }
    }

    private boolean isDigit(char c) {
        return Character.isDigit(c) || c == '.' || c == ',';
    }

    private int compareEqual(String a, String b, int nza, int nzb) {
        if (nza - nzb != 0)
            return nza - nzb;

        if (a.length() == b.length())
            return a.compareTo(b);

        return a.length() - b.length();
    }

    private char charAt(String s, int i) {
        return i >= s.length() ? 0 : s.charAt(i);
    }

}
