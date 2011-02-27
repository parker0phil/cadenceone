package com.cadenceone.eqhs;

import org.junit.Test;

import com.cadenceone.eqhs.fixtures.ObjectEqualsHashcodeFixtures;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


public class MutualEqualsAndHashcodeContractCheckerTest {
    
    @Test
    public void givenTwoObjectsThatBothReturnEquals_thenReturnTrue(){               
        //execute
        boolean areEqualsByContract = new MutualEqualsAndHashcodeContractChecker(new ObjectEqualsHashcodeFixtures.ObjectReturnsTrueAndSameHashcode(), new ObjectEqualsHashcodeFixtures.ObjectReturnsTrueAndSameHashcode()).areSameByEqualsContract();
        
        //assert
        assertThat(areEqualsByContract, is(true));        
    }
    
    @Test
    public void givenTwoObjectsThatBothReturnNotEquals_thenReturnFalse(){              
        //execute
        boolean areEqualsByContract = new MutualEqualsAndHashcodeContractChecker(new ObjectEqualsHashcodeFixtures.ObjectReturnsFalseAndSameHashcode(), new ObjectEqualsHashcodeFixtures.ObjectReturnsFalseAndSameHashcode()).areSameByEqualsContract();
        
        //assert
        assertThat(areEqualsByContract, is(false));        
    }
    
    @Test
    public void givenTwoObjectsWithOnlyLeftReturningEquals_thenReturnFalse(){               
        //execute
        boolean areEqualsByContract = new MutualEqualsAndHashcodeContractChecker(new ObjectEqualsHashcodeFixtures.ObjectReturnsTrueAndSameHashcode(), new ObjectEqualsHashcodeFixtures.ObjectReturnsFalseAndSameHashcode()).areSameByEqualsContract();
        
        //assert
        assertThat(areEqualsByContract, is(false));        
    }
    
    @Test
    public void givenTwoObjectsWithOnlyRightReturningEquals_thenReturnFalse(){               
        //execute
        boolean areEqualsByContract = new MutualEqualsAndHashcodeContractChecker(new ObjectEqualsHashcodeFixtures.ObjectReturnsFalseAndSameHashcode(), new ObjectEqualsHashcodeFixtures.ObjectReturnsTrueAndSameHashcode()).areSameByEqualsContract();
        
        //assert
        assertThat(areEqualsByContract, is(false));        
    }
    
    @Test
    public void givenTwoObjectsThatBothReturnEqualsButWithDifferentHashCodes_thenReturnFalse(){               
        //execute
        boolean areEqualsByContract = new MutualEqualsAndHashcodeContractChecker(new ObjectEqualsHashcodeFixtures.ObjectReturnsTrueAndSameHashcode(), new ObjectEqualsHashcodeFixtures.ObjectReturnsTrueAndDifferentHashcode()).areSameByEqualsContract();
        
        //assert
        assertThat(areEqualsByContract, is(false));        
    }

}



