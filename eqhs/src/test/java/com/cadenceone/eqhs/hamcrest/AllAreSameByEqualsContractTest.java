package com.cadenceone.eqhs.hamcrest;

import static com.cadenceone.eqhs.hamcrest.AllAreSameByEqualsContract.allAreSameByEqualsContract;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.hamcrest.Matcher;
import org.junit.Test;

import com.cadenceone.eqhs.fixtures.Fixtures;

import java.util.List;


public class AllAreSameByEqualsContractTest extends AbstractMatcherTest {

    @Test
    public void testMatchesWhenAllAreEqualsAndHaveSameHashcode() {
        assertMatches("matcher fails and should succeed",
            allAreSameByEqualsContract(),
            asList(new Fixtures.ObjectReturnsTrueAndSameHashcode(), new Fixtures.ObjectReturnsTrueAndSameHashcode()));
    }

   @Test
   public void testDoesNotMatchWhenAllAreNotEqual() {
        List<Object> objects = asList(new Fixtures.ObjectReturnsTrueAndSameHashcode(), new Fixtures.ObjectReturnsFalseAndSameHashcode());

        assertDoesNotMatch("matcher succeeds and should fail",
            allAreSameByEqualsContract(),
            objects);

        assertMismatchDescription("at least two objects are NOT equal or have different hashcodes, should be equal and have same hashcode",
            allAreSameByEqualsContract(),
            objects);
   }

    @Test
    public void testDoesNotMatchWhenAllDifferentHashcodes() {
        List<Object> objects = asList(new Fixtures.ObjectReturnsTrueAndSameHashcode(), new Fixtures.ObjectReturnsTrueAndDifferentHashcode());

        assertDoesNotMatch("matcher succeeds and should fail",
            allAreSameByEqualsContract(),
            objects);

        assertMismatchDescription("at least two objects are NOT equal or have different hashcodes, should be equal and have same hashcode",
            allAreSameByEqualsContract(),
            objects);
    }

    protected Matcher<?> createMatcher() {
        return allAreSameByEqualsContract();
    }
}