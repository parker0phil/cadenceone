package com.cadenceone.eqhs;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;


public class MultipleEqualsAndHashcodeContractCheckerTest {
    
    @Test(expected=IllegalArgumentException.class)
    public void whenCallingWithNoObjects_thenComplains(){  
        new MultipleEqualsAndHashcodeContractChecker().getCheckers();
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void whenCallingWithOneObject_thenComplains(){  
        new MultipleEqualsAndHashcodeContractChecker(new Object()).getCheckers();
    }
    
    @Test
    public void givenTwoObjects_thenReturnsOneChecker(){  
        List<MutualEqualsAndHashcodeContractChecker> checkers = new MultipleEqualsAndHashcodeContractChecker(new Object(), new Object()).getCheckers();
          
        assertThat(checkers.size(), is(1));
    }
    
    @Test
    public void givenThreeObjects_thenReturnsFourCheckers(){  
        List<MutualEqualsAndHashcodeContractChecker> checkers = new MultipleEqualsAndHashcodeContractChecker(new Object(), new Object(), new Object()).getCheckers();
          
        assertThat(checkers.size(), is(3));
    }
    
    @Test
    public void givenFourObjects_thenReturnsFiveCheckers(){  
        List<MutualEqualsAndHashcodeContractChecker> checkers = new MultipleEqualsAndHashcodeContractChecker(new Object(), new Object(), new Object(), new Object()).getCheckers();
          
        assertThat(checkers.size(), is(6));
    }
    
    @Test
    public void givenSomeCheckers_whenAllAreSameByEqualsContract_thenAreSameByEqualsContractIsCalledOnAllMethods(){  
        MutualEqualsAndHashcodeContractChecker checker1 = mock(MutualEqualsAndHashcodeContractChecker.class);
        MutualEqualsAndHashcodeContractChecker checker2 = mock(MutualEqualsAndHashcodeContractChecker.class);
        when(checker1.areSameByEqualsContract()).thenReturn(true);
        when(checker2.areSameByEqualsContract()).thenReturn(true);
        
        new MultipleEqualsAndHashcodeContractChecker(Arrays.asList(checker1, checker2)).allAreSameByEqualsContract();
          
        verify(checker1).areSameByEqualsContract();
        verify(checker2).areSameByEqualsContract();
    }

    @Test
    public void givenSomeCheckers_whenAllAreDifferentByEqualsContract_thenAreDifferentByEqualsContractIsCalledOnAllMethods(){
        MutualEqualsAndHashcodeContractChecker checker1 = mock(MutualEqualsAndHashcodeContractChecker.class);
        MutualEqualsAndHashcodeContractChecker checker2 = mock(MutualEqualsAndHashcodeContractChecker.class);
        when(checker1.areDifferentByEqualsContract()).thenReturn(true);
        when(checker2.areDifferentByEqualsContract()).thenReturn(true);

        new MultipleEqualsAndHashcodeContractChecker(Arrays.asList(checker1, checker2)).allAreDifferentByEqualsContract();

        verify(checker1).areDifferentByEqualsContract();
        verify(checker2).areDifferentByEqualsContract();
    }
    
    
}
