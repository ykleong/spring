package org.yoong.spring.counter.util;

import java.text.BreakIterator;
import java.util.HashMap;
import java.util.Map;

public final class CounterUtil {

    /**
     * Count the frequencies of each unique word in the text and return it in a map.
     * 
     * Map keys are all converted to lowercase.
     * 
     * This method assumes text is simple with only letters and punctuation (eg. no numbers or hyphenation)
     * 
     * @param paragraph
     * @return Map <String, Long> - word, frequency
     */
    public final static Map<String, Integer> countFrequencies(final String paragraph) {

        Map<String, Integer> frequencies = new HashMap<String, Integer>();

        BreakIterator boundary = BreakIterator.getWordInstance();
        boundary.setText(paragraph);

        int start = boundary.first();

        for (int end = boundary.next(); end != BreakIterator.DONE; start = end, end = boundary.next()) {

            String word = paragraph.substring(start, end).trim();

            // don't add punctuation to frequencies map
            if (word.length() == 1 && !Character.isLetter(word.charAt(0))) {
                continue;
            }

            if (!word.isEmpty()) {

                word = word.toLowerCase();

                if (frequencies.containsKey(word)) {
                    frequencies.put(word, frequencies.get(word) + 1);
                } else {
                    frequencies.put(word, 1);
                }

            }

        }

        return frequencies;
    }
}
