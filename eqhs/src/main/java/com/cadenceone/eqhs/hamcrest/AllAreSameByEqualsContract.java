package com.cadenceone.eqhs.hamcrest;

import java.util.Collection;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import com.cadenceone.eqhs.MultipleEqualsAndHashCodeContractChecker;


public class AllAreSameByEqualsContract<E> extends TypeSafeMatcher<Collection<E>>{
   



    @Override
    protected boolean matchesSafely(Collection<E> item) {
        return new MultipleEqualsAndHashCodeContractChecker(item).allAreSameByEqualsContract();
    }

    @Override
    protected void describeMismatchSafely(Collection<E> item, Description mismatchDescription) {
        mismatchDescription.appendText("at least two objects are NOT equal or have different hashCodes, should be equal AND have same hashCode");
    }

    /**
     * Does every item in collection equals each other and have same hashcode
     * 
     * e.g.
     * assertTrue(a.equals(b))
     * assertTrue(b.equals(a))
     * assertTrue(a.hashCode() == b.hashCode())
     */
    @Factory
    public static <E> Matcher<Collection<E>> allAreSameByEqualsContract() {
        return new AllAreSameByEqualsContract<E>();
    }


    public void describeTo(Description description) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
