package com.cadenceone.eqhs;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;


public class MultipleEqualsAndHashCodeContractCheckerTest {
    
    @Test(expected=IllegalArgumentException.class)
    public void whenCallingWithNoObjects_thenComplains(){  
        new MultipleEqualsAndHashCodeContractChecker().getCheckers();
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void whenCallingWithOneObject_thenComplains(){  
        new MultipleEqualsAndHashCodeContractChecker(new Object()).getCheckers();
    }
    
    @Test
    public void givenTwoObjects_thenReturnsOneChecker(){  
        List<MutualEqualsAndHashCodeContractChecker> checkers = new MultipleEqualsAndHashCodeContractChecker(new Object(), new Object()).getCheckers();
          
        assertThat(checkers.size(), is(1));
    }
    
    @Test
    public void givenThreeObjects_thenReturnsFourCheckers(){  
        List<MutualEqualsAndHashCodeContractChecker> checkers = new MultipleEqualsAndHashCodeContractChecker(new Object(), new Object(), new Object()).getCheckers();
          
        assertThat(checkers.size(), is(3));
    }
    
    @Test
    public void givenFourObjects_thenReturnsFiveCheckers(){  
        List<MutualEqualsAndHashCodeContractChecker> checkers = new MultipleEqualsAndHashCodeContractChecker(new Object(), new Object(), new Object(), new Object()).getCheckers();
          
        assertThat(checkers.size(), is(6));
    }
    
    @Test
    public void givenSomeCheckers_whenAllAreSameByEqualsContract_thenAreSameByEqualsContractIsCalledOnAllMethods(){  
        MutualEqualsAndHashCodeContractChecker checker1 = mock(MutualEqualsAndHashCodeContractChecker.class);
        MutualEqualsAndHashCodeContractChecker checker2 = mock(MutualEqualsAndHashCodeContractChecker.class);
        when(checker1.areSameByEqualsContract()).thenReturn(true);
        when(checker2.areSameByEqualsContract()).thenReturn(true);
        
        new MultipleEqualsAndHashCodeContractChecker(Arrays.asList(checker1, checker2)).allAreSameByEqualsContract();
          
        verify(checker1).areSameByEqualsContract();
        verify(checker2).areSameByEqualsContract();
    }

    @Test
    public void givenSomeCheckers_whenAllAreDifferentByEqualsContract_thenAreDifferentByEqualsContractIsCalledOnAllMethods(){
        MutualEqualsAndHashCodeContractChecker checker1 = mock(MutualEqualsAndHashCodeContractChecker.class);
        MutualEqualsAndHashCodeContractChecker checker2 = mock(MutualEqualsAndHashCodeContractChecker.class);
        when(checker1.areDifferentByEqualsContract()).thenReturn(true);
        when(checker2.areDifferentByEqualsContract()).thenReturn(true);

        new MultipleEqualsAndHashCodeContractChecker(Arrays.asList(checker1, checker2)).allAreDifferentByEqualsContract();

        verify(checker1).areDifferentByEqualsContract();
        verify(checker2).areDifferentByEqualsContract();
    }

     @Test
    public void givenSomeCheckers_whenAllHaveDifferentHashcodes_thenHaveDifferentHashCodesIsCalledOnAllMethods(){
        MutualEqualsAndHashCodeContractChecker checker1 = mock(MutualEqualsAndHashCodeContractChecker.class);
        MutualEqualsAndHashCodeContractChecker checker2 = mock(MutualEqualsAndHashCodeContractChecker.class);
        when(checker1.haveDifferentHashCodes()).thenReturn(true);
        when(checker2.haveDifferentHashCodes()).thenReturn(true);

        new MultipleEqualsAndHashCodeContractChecker(Arrays.asList(checker1, checker2)).allHaveDifferentHashCodes();

        verify(checker1).haveDifferentHashCodes();
        verify(checker2).haveDifferentHashCodes();
    }

    @Test
    public void givenSomeCheckers_whenAllDontHaveDifferentHashcodes_thenHaveDifferentHashCodesIsCalledOnAllMethods(){
        MutualEqualsAndHashCodeContractChecker checker1 = mock(MutualEqualsAndHashCodeContractChecker.class);
        MutualEqualsAndHashCodeContractChecker checker2 = mock(MutualEqualsAndHashCodeContractChecker.class);
        when(checker1.haveDifferentHashCodes()).thenReturn(true);
        when(checker2.haveDifferentHashCodes()).thenReturn(false);

        new MultipleEqualsAndHashCodeContractChecker(Arrays.asList(checker1, checker2)).allHaveDifferentHashCodes();

        verify(checker1).haveDifferentHashCodes();
        verify(checker2).haveDifferentHashCodes();
    }
    
    
}
