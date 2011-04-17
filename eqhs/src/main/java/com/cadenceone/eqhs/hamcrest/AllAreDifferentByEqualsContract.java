package com.cadenceone.eqhs.hamcrest;

import com.cadenceone.eqhs.MultipleEqualsAndHashCodeContractChecker;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Collection;


public class AllAreDifferentByEqualsContract<E> extends TypeSafeMatcher<Collection<E>>{

    @Override
    protected boolean matchesSafely(Collection<E> item) {
        return new MultipleEqualsAndHashCodeContractChecker(item).allAreDifferentByEqualsContract();
    }

    @Override
    protected void describeMismatchSafely(Collection<E> item, Description mismatchDescription) {
        mismatchDescription.appendText("at least two collection objects are equal, should be different");
    }

    /**
     * Does every item in collection NOT equal each other
     * 
     * e.g.
     * assertFalse(a.equals(b))
     * assertFalse(b.equals(a))
     */
    @Factory
    public static <E> Matcher<Collection<E>> allAreDifferentByEqualsContract() {
        return new AllAreDifferentByEqualsContract<E>();
    }


    public void describeTo(Description description) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
