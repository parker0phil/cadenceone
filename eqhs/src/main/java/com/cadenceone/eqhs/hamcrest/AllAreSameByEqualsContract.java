package com.cadenceone.eqhs.hamcrest;

import java.util.Collection;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import com.cadenceone.eqhs.MultipleEqualsAndHashcodeContractChecker;


public class AllAreSameByEqualsContract<E> extends TypeSafeMatcher<Collection<E>>{
   
    @Override
    public void describeTo(Description description) {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected boolean matchesSafely(Collection<E> item) {
        return new MultipleEqualsAndHashcodeContractChecker(item).allAreSameByEqualsContract();
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


}
