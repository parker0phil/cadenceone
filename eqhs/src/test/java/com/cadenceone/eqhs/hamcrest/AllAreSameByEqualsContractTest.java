package com.cadenceone.eqhs.hamcrest;

import static com.cadenceone.eqhs.hamcrest.AllAreSameByEqualsContract.allAreSameByEqualsContract;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.cadenceone.eqhs.fixtures.hamcrest.AbstractMatcherTest;
import org.hamcrest.Matcher;
import org.junit.Test;

import com.cadenceone.eqhs.fixtures.Fixtures;

import java.util.List;


public class AllAreSameByEqualsContractTest extends AbstractMatcherTest {

    @Test
    public void testMatchesWhenAllAreEqualsAndHaveSameHashCode() {
        assertMatches("matcher fails and should succeed",
            allAreSameByEqualsContract(),
            asList((Object)new Fixtures.ObjectReturnsTrueAndSameHashCode(), new Fixtures.ObjectReturnsTrueAndSameHashCode()));
    }

   @Test
   public void testDoesNotMatchWhenAllAreNotEqual() {
        List<Object> objects = asList(new Fixtures.ObjectReturnsTrueAndSameHashCode(), new Fixtures.ObjectReturnsFalseAndSameHashCode());

        assertDoesNotMatch("matcher succeeds and should fail",
            allAreSameByEqualsContract(),
            objects);

        assertMismatchDescription("at least two objects are NOT equal or have different hashCodes, should be equal AND have same hashCode",
            allAreSameByEqualsContract(),
            objects);
   }

    @Test
    public void testDoesNotMatchWhenAllDifferentHashCodes() {
        List<Object> objects = asList(new Fixtures.ObjectReturnsTrueAndSameHashCode(), new Fixtures.ObjectReturnsTrueAndDifferentHashCode());

        assertDoesNotMatch("matcher succeeds and should fail",
            allAreSameByEqualsContract(),
            objects);

        assertMismatchDescription("at least two objects are NOT equal or have different hashCodes, should be equal AND have same hashCode",
            allAreSameByEqualsContract(),
            objects);
    }

    protected Matcher<?> createMatcher() {
        return allAreSameByEqualsContract();
    }
}