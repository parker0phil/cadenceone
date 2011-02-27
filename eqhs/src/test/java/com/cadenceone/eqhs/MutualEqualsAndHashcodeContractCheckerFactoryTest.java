package com.cadenceone.eqhs;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;




public class MutualEqualsAndHashcodeContractCheckerFactoryTest {
    
    @Test(expected=IllegalArgumentException.class)
    public void whenCallingWithNoObjects_thenComplains(){  
        new MutualEqualsAndHashcodeContractCheckerFactory().getCheckers();
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void whenCallingWithOneObject_thenComplains(){  
        new MutualEqualsAndHashcodeContractCheckerFactory().getCheckers(new Object());
    }
    
    @Test
    public void givenTwoObjects_thenReturnsOneChecker(){  
        List<MutualEqualsAndHashcodeContractChecker> checkers = new MutualEqualsAndHashcodeContractCheckerFactory().getCheckers(new Object(), new Object());
          
        assertThat(checkers.size(), is(1));
    }
    
    @Test
    public void givenThreeObjects_thenReturnsFourCheckers(){  
        List<MutualEqualsAndHashcodeContractChecker> checkers = new MutualEqualsAndHashcodeContractCheckerFactory().getCheckers(new Object(), new Object(), new Object());
          
        assertThat(checkers.size(), is(3));
    }
    
    @Test
    public void givenFourObjects_thenReturnsFiveCheckers(){  
        List<MutualEqualsAndHashcodeContractChecker> checkers = new MutualEqualsAndHashcodeContractCheckerFactory().getCheckers(new Object(), new Object(), new Object(), new Object());
          
        assertThat(checkers.size(), is(6));
    }
}
