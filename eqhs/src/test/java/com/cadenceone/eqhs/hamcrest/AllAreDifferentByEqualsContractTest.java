package com.cadenceone.eqhs.hamcrest;

import com.cadenceone.eqhs.fixtures.Fixtures;

import com.cadenceone.eqhs.fixtures.hamcrest.AbstractMatcherTest;
import org.hamcrest.Matcher;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static com.cadenceone.eqhs.hamcrest.AllAreDifferentByEqualsContract.allAreDifferentByEqualsContract;


public class AllAreDifferentByEqualsContractTest extends AbstractMatcherTest {

    @Test
    public void testMatchesWhenAllAreNotEqual() {
        assertMatches("matcher fails and should succeed",
            allAreDifferentByEqualsContract(),
            asList(new Fixtures.ObjectReturnsTrueAndSameHashCode(), new Fixtures.ObjectReturnsFalseAndSameHashCode()));
    }

    @Test
    public void testDoesNotMatchWhenAllAreNotEqual() {
        List<Object> objects = asList((Object)new Fixtures.ObjectReturnsTrueAndSameHashCode(), new Fixtures.ObjectReturnsTrueAndSameHashCode());

        assertDoesNotMatch("matcher succeeds and should fail",
            allAreDifferentByEqualsContract(),
            objects);

        assertMismatchDescription("at least two collection objects are equal, should be different",
            allAreDifferentByEqualsContract(),
            objects);
     }

    protected Matcher<?> createMatcher() {
        return allAreDifferentByEqualsContract();
    }


}
