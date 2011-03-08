package com.cadenceone.eqhs.hamcrest;

import com.cadenceone.eqhs.MultipleEqualsAndHashCodeContractChecker;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Collection;


public class AllHaveDifferentHashCodes<E> extends TypeSafeMatcher<Collection<E>>{

    @Override
    protected boolean matchesSafely(Collection<E> item) {
       return new MultipleEqualsAndHashCodeContractChecker(item).allHaveDifferentHashCodes();
    }

    @Override
    protected void describeMismatchSafely(Collection<E> item, Description mismatchDescription) {
        mismatchDescription.appendText("at least two collection objects have same hashCode, should be different");
    }

    /**
     * Does every item in collection have different hashCodes
     * 
     * e.g.
     * assertFalse(a.hashCode() == b.hashCode())
     */
    @Factory
    public static <E> Matcher<Collection<E>> allHaveDifferentHashCodes() {
        return new AllHaveDifferentHashCodes<E>();
    }


    public void describeTo(Description description) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
