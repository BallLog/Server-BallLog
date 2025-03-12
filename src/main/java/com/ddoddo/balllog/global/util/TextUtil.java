package com.ddoddo.balllog.global.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TextUtil {

    private static final Pattern graphemePattern = Pattern.compile("\\X");

    public static int countGraphemeClusters(String text) {
        if (text == null) {
            return 0;
        }

        final Matcher graphemeMatcher = graphemePattern.matcher(text);

        return (int) graphemeMatcher.results().count();
    }
}
