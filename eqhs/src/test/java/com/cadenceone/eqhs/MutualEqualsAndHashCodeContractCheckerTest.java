package com.cadenceone.eqhs;

import com.cadenceone.eqhs.fixtures.Fixtures;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


public class MutualEqualsAndHashCodeContractCheckerTest {
    
    @Test
    public void givenTwoObjectsThatBothReturnEquals_thenReturnTrue(){               
        //execute
        boolean areEqualsByContract = new MutualEqualsAndHashCodeContractChecker(new Fixtures.ObjectReturnsTrueAndSameHashcode(), new Fixtures.ObjectReturnsTrueAndSameHashcode()).areSameByEqualsContract();
        
        //assert
        assertThat(areEqualsByContract, is(true));        
    }
    
    @Test
    public void givenTwoObjectsThatBothReturnNotEquals_thenReturnFalse(){              
        //execute
        boolean areEqualsByContract = new MutualEqualsAndHashCodeContractChecker(new Fixtures.ObjectReturnsFalseAndSameHashcode(), new Fixtures.ObjectReturnsFalseAndSameHashcode()).areSameByEqualsContract();
        
        //assert
        assertThat(areEqualsByContract, is(false));        
    }
    
    @Test
    public void givenTwoObjectsWithOnlyLeftReturningEquals_thenReturnFalse(){               
        //execute
        boolean areEqualsByContract = new MutualEqualsAndHashCodeContractChecker(new Fixtures.ObjectReturnsTrueAndSameHashcode(), new Fixtures.ObjectReturnsFalseAndSameHashcode()).areSameByEqualsContract();
        
        //assert
        assertThat(areEqualsByContract, is(false));        
    }
    
    @Test
    public void givenTwoObjectsWithOnlyRightReturningEquals_thenReturnFalse(){               
        //execute
        boolean areEqualsByContract = new MutualEqualsAndHashCodeContractChecker(new Fixtures.ObjectReturnsFalseAndSameHashcode(), new Fixtures.ObjectReturnsTrueAndSameHashcode()).areSameByEqualsContract();
        
        //assert
        assertThat(areEqualsByContract, is(false));        
    }
    
    @Test
    public void givenTwoObjectsThatBothReturnEqualsButWithDifferentHashCodes_thenReturnFalse(){               
        //execute
        boolean areEqualsByContract = new MutualEqualsAndHashCodeContractChecker(new Fixtures.ObjectReturnsTrueAndSameHashcode(), new Fixtures.ObjectReturnsTrueAndDifferentHashcode()).areSameByEqualsContract();
        
        //assert
        assertThat(areEqualsByContract, is(false));        
    }

    @Test
    public void givenTwoObjectsWithDifferentHashCodes_thenReturnTrue(){
        //execute
        boolean haveDifferentHashCodes = new MutualEqualsAndHashCodeContractChecker(new Fixtures.ObjectReturnsTrueAndSameHashcode(), new Fixtures.ObjectReturnsTrueAndSameHashcode()).haveDifferentHashCodes();

        //assert
        assertThat(haveDifferentHashCodes, is(false));
    }

    @Test
    public void givenTwoObjectsWithSameHashCodes_thenReturnTrue(){
        //execute
        boolean haveDifferentHashCodes = new MutualEqualsAndHashCodeContractChecker(new Fixtures.ObjectReturnsTrueAndSameHashcode(), new Fixtures.ObjectReturnsTrueAndDifferentHashcode()).haveDifferentHashCodes();

        //assert
        assertThat(haveDifferentHashCodes, is(true));
    }

}



