package com.cadenceone.eqhs.hamcrest;

import com.cadenceone.eqhs.fixtures.Fixtures;
import com.cadenceone.eqhs.fixtures.hamcrest.AbstractMatcherTest;
import org.hamcrest.Matcher;
import org.junit.Test;

import java.util.List;

import static com.cadenceone.eqhs.hamcrest.AllHaveDifferentHashCodes.allHaveDifferentHashCodes;
import static java.util.Arrays.asList;


public class AllHaveDifferentHashCodesTest extends AbstractMatcherTest {

    @Test
    public void testMatchesWhenAllHaveDifferentHashCodes() {
        assertMatches("matcher fails and should succeed",
            allHaveDifferentHashCodes(),
            asList(new Fixtures.ObjectReturnsTrueAndSameHashCode(), new Fixtures.ObjectReturnsTrueAndDifferentHashCode()));
    }

    @Test
    public void testDoesNotMatchWhenAllDonthaveDifferentHashCode() {
        List<Object> objects = asList((Object)new Fixtures.ObjectReturnsTrueAndSameHashCode(), new Fixtures.ObjectReturnsFalseAndSameHashCode());

        assertDoesNotMatch("matcher succeeds and should fail",
            allHaveDifferentHashCodes(),
            objects);

        assertMismatchDescription("at least two collection objects have same hashCode, should all be different",
            allHaveDifferentHashCodes(),
            objects);
     }

    protected Matcher<?> createMatcher() {
        return allHaveDifferentHashCodes();
    }


}
