package com.cadenceone.eqhs;

import com.cadenceone.eqhs.fixtures.Fixtures;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


public class MutualEqualsAndHashcodeContractCheckerTest {
    
    @Test
    public void givenTwoObjectsThatBothReturnEquals_thenReturnTrue(){               
        //execute
        boolean areEqualsByContract = new MutualEqualsAndHashcodeContractChecker(new Fixtures.ObjectReturnsTrueAndSameHashcode(), new Fixtures.ObjectReturnsTrueAndSameHashcode()).areSameByEqualsContract();
        
        //assert
        assertThat(areEqualsByContract, is(true));        
    }
    
    @Test
    public void givenTwoObjectsThatBothReturnNotEquals_thenReturnFalse(){              
        //execute
        boolean areEqualsByContract = new MutualEqualsAndHashcodeContractChecker(new Fixtures.ObjectReturnsFalseAndSameHashcode(), new Fixtures.ObjectReturnsFalseAndSameHashcode()).areSameByEqualsContract();
        
        //assert
        assertThat(areEqualsByContract, is(false));        
    }
    
    @Test
    public void givenTwoObjectsWithOnlyLeftReturningEquals_thenReturnFalse(){               
        //execute
        boolean areEqualsByContract = new MutualEqualsAndHashcodeContractChecker(new Fixtures.ObjectReturnsTrueAndSameHashcode(), new Fixtures.ObjectReturnsFalseAndSameHashcode()).areSameByEqualsContract();
        
        //assert
        assertThat(areEqualsByContract, is(false));        
    }
    
    @Test
    public void givenTwoObjectsWithOnlyRightReturningEquals_thenReturnFalse(){               
        //execute
        boolean areEqualsByContract = new MutualEqualsAndHashcodeContractChecker(new Fixtures.ObjectReturnsFalseAndSameHashcode(), new Fixtures.ObjectReturnsTrueAndSameHashcode()).areSameByEqualsContract();
        
        //assert
        assertThat(areEqualsByContract, is(false));        
    }
    
    @Test
    public void givenTwoObjectsThatBothReturnEqualsButWithDifferentHashCodes_thenReturnFalse(){               
        //execute
        boolean areEqualsByContract = new MutualEqualsAndHashcodeContractChecker(new Fixtures.ObjectReturnsTrueAndSameHashcode(), new Fixtures.ObjectReturnsTrueAndDifferentHashcode()).areSameByEqualsContract();
        
        //assert
        assertThat(areEqualsByContract, is(false));        
    }

}



