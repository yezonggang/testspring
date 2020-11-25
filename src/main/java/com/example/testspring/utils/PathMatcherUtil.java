package com.example.testspring.utils;

import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class PathMatcherUtil
{

    public PathMatcherUtil() {
    }

    public static boolean match(String matchPath, String path) {
        PathMatcherUtil.SpringAntMatcher springAntMatcher = new PathMatcherUtil.SpringAntMatcher(matchPath, true);
        return springAntMatcher.matches(path);
    }

    public static boolean matches(Collection<String> list, String path) {
        Iterator var2 = list.iterator();

        PathMatcherUtil.SpringAntMatcher springAntMatcher;
        do {
            if (!var2.hasNext()) {
                return false;
            }

            String s = (String)var2.next();
            springAntMatcher = new PathMatcherUtil.SpringAntMatcher(s, true);
        } while(!springAntMatcher.matches(path));

        return true;
    }

    private interface Matcher {
        boolean matches(String var1);

        Map<String, String> extractUriTemplateVariables(String var1);
    }

    private static class SpringAntMatcher implements PathMatcherUtil.Matcher {
        private final AntPathMatcher antMatcher;
        private final String pattern;

        private SpringAntMatcher(String pattern, boolean caseSensitive) {
            this.pattern = pattern;
            this.antMatcher = createMatcher(caseSensitive);
        }

        public boolean matches(String path) {
            return this.antMatcher.match(this.pattern, path);
        }

        public Map<String, String> extractUriTemplateVariables(String path) {
            return this.antMatcher.extractUriTemplateVariables(this.pattern, path);
        }

        private static AntPathMatcher createMatcher(boolean caseSensitive) {
            AntPathMatcher matcher = new AntPathMatcher();
            matcher.setTrimTokens(false);
            matcher.setCaseSensitive(caseSensitive);
            return matcher;
        }
    }
}
