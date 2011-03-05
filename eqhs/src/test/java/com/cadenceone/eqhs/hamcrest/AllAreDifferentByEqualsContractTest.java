package com.cadenceone.eqhs.hamcrest;

import com.cadenceone.eqhs.fixtures.Fixtures;

import org.hamcrest.Matcher;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static com.cadenceone.eqhs.hamcrest.AllAreDifferentByEqualsContract.allAreDifferentByEqualsContract;
import static java.util.Arrays.asList;


public class AllAreDifferentByEqualsContractTest extends AbstractMatcherTest {

    @Test
    public void testMatchesWhenAllAreNotEqual() {
        assertMatches("matcher fails and should succeed",
            allAreDifferentByEqualsContract(),
            asList(new Fixtures.ObjectReturnsTrueAndSameHashcode(), new Fixtures.ObjectReturnsFalseAndSameHashcode()));
    }

    @Test
    @Ignore
    public void testDoesNotMatchWhenAllAreNotEqual() {
        List<Fixtures.ObjectReturnsTrueAndSameHashcode> objects = asList(new Fixtures.ObjectReturnsTrueAndSameHashcode(), new Fixtures.ObjectReturnsTrueAndSameHashcode());

//        assertDoesNotMatch("matcher succeeds and should fail",
//            allAreDifferentByEqualsContract(),
//            objects);
//
//        assertMismatchDescription("at least two collection objects are equal, should be different",
//            allAreDifferentByEqualsContract(),
//            objects);
     }

    protected Matcher<?> createMatcher() {
        return allAreDifferentByEqualsContract();
    }


}
