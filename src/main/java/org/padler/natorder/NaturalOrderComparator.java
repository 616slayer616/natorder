package org.padler.natorder;

import java.util.Comparator;
import java.util.Objects;

public class NaturalOrderComparator implements Comparator<String> {

    private final boolean caseSensitive;

    public NaturalOrderComparator() {
        this.caseSensitive = false;
    }

    public NaturalOrderComparator(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }


    private int compareRight(String a, String b) {
        int bias = 0;
        int i = 0;

        // The longest run of digits wins. That aside, the greatest
        // value wins, but we can't know that it will until we've scanned
        // both numbers to know that they have the same magnitude, so we
        // remember it in BIAS.
        for (; ; i++) {
            char ca = charAt(a, i);
            char cb = charAt(b, i);

            boolean isADigit = isDigitOrSeparator(ca);
            boolean isBDigit = isDigitOrSeparator(cb);

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
        StringBuilder numberA = new StringBuilder();
        StringBuilder numberB = new StringBuilder();

        while (true) {
            // Only count the number of zeroes leading the last number compared
            nza = nzb = 0;

            ca = charAt(o1, ia);
            cb = charAt(o2, ib);

            // skip over leading spaces or zeros
            while (isSpaceOrSeparatorOrEmpty(ca)) {
                if (ca == '0') {
                    nza++;
                    numberA.append(ca);
                } else {
                    // Only count consecutive zeroes
                    nza = 0;
                }

                ++ia;
                ca = charAt(o1, ia);
            }

            while (isSpaceOrSeparatorOrEmpty(cb)) {
                if (cb == '0') {
                    nzb++;
                    numberB.append(cb);
                } else {
                    // Only count consecutive zeroes
                    nzb = 0;
                }

                ++ib;
                cb = charAt(o2, ib);
            }

            if (isDigitOrSeparator(ca)) {
                numberA.append(ca);
            }
            if (isDigitOrSeparator(cb)) {
                numberB.append(cb);
            }

            // Process run of digits
            if (isDigit(ca) && isDigit(cb)) {
                int bias = compareRight(o1.substring(ia), o2.substring(ib));
                if (bias != 0) {
                    return bias;
                }
            }

            if (ca == 0 && cb == 0) {
                // The strings compare the same. Use other means of comparison
                return compareEqual(o1, o2, nza, nzb, numberA.toString(), numberB.toString());
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
        return Character.isDigit(c);
    }

    private boolean isDigitOrSeparator(char c) {
        return isDigit(c) || c=='.' || c==',' || c==':';
    }

    private boolean isSpaceChar(char c) {
        return Character.isSpaceChar(c);
    }

    private boolean isSpaceOrSeparator(char c) {
        return isSpaceChar(c) || c=='_';
    }

    private boolean isSpaceOrSeparatorOrEmpty(char c) {
        return isSpaceOrSeparator(c) || c=='0';
    }

    private int compareEqual(String a, String b, int nza, int nzb, String numberA, String numberB) {
        if (!Objects.equals(numberA, numberB)) {
            double na = Double.parseDouble(numberA);
            double nb = Double.parseDouble(numberB);
            if (!Objects.equals(na, nb))
                return Double.compare(na, nb);
        }

        if (nza - nzb != 0)
            return nza - nzb;

        if (a.length() == b.length())
            return caseSensitive ? a.compareTo(b) : a.compareToIgnoreCase(b);

        return a.length() - b.length();
    }

    private char charAt(String s, int i) {
        if (caseSensitive)
            return i >= s.length() ? 0 : s.charAt(i);
        else
            return i >= s.length() ? 0 : Character.toUpperCase(s.charAt(i));
    }

}
