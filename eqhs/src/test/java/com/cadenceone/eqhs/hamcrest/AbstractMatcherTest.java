package com.cadenceone.eqhs.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public abstract class AbstractMatcherTest {
    public static <T> void assertMatches(String message, Matcher<Collection<Object>> matcher, List<?> list) {
        assertTrue(message, matcher.matches(list));
    }

    public static <T> void assertDoesNotMatch(String message, Matcher<T> c, T arg) {
        assertFalse(message, c.matches(arg));
    }

    public static void assertDescription(String expected, Matcher<?> matcher) {
        Description description = new StringDescription();
        description.appendDescriptionOf(matcher);
        assertEquals("Expected description", expected, description.toString());
    }

    public static <T> void assertMismatchDescription(String expected, Matcher<? super T> matcher, T arg) {
        Description description = new StringDescription();
        assertFalse("Precondtion: Matcher should not match item.", matcher.matches(arg));
        matcher.describeMismatch(arg, description);
        assertEquals("Expected mismatch description", expected, description.toString());
    }

    public void testIsNullSafe() {
       // should not throw a NullPointerException
       createMatcher().matches(null);
    }

    public void testCopesWithUnknownTypes() {
        // should not throw ClassCastException
        createMatcher().matches(new UnknownType());
    }

    public static class UnknownType {
    }

    protected abstract Matcher<?> createMatcher();
}
