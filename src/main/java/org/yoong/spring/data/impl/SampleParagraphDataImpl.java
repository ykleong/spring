package org.yoong.spring.data.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.yoong.spring.data.ParagraphData;

/**
 * 
 * Static Sample Paragraphs that are initially read from a file within 'resources'.
 * 
 * @author ykleong
 */
@Component
public class SampleParagraphDataImpl implements ParagraphData {

    private static String PARAGRAPH = null;

    public SampleParagraphDataImpl() {

        if (PARAGRAPH == null) {
            InputStream is = SampleParagraphDataImpl.class.getResourceAsStream("/static/sample_paragraphs.txt");
            PARAGRAPH = new BufferedReader(new InputStreamReader(is)).lines().collect(Collectors.joining("\n"));
        }

    }

    public String getParagraph() {
        return PARAGRAPH;
    }

}
