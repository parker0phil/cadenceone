package com.cadenceone.eqhs.fixtures;

import com.cadenceone.eqhs.ReflectiveEqualsAndHashCodeContractUsingSpecificFieldsChecker;
import com.cadenceone.eqhs.fixtures.Fixtures;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public abstract class ReflectiveEqualsAndHashCodeContractCheckerForFieldTest {

    @Test
    public void givenClassWithPrimitiveField_whenEqualsIsImplementedCorrectly_testPasses() throws Exception{
        boolean meetsEquals = new ReflectiveEqualsAndHashCodeContractUsingSpecificFieldsChecker(getCorrectClass(), "field").implementsEqualsCorrectly();

        assertTrue(meetsEquals);
    }

    @Test
    public void givenClassWithPrimitiveField_whenEqualsIsImplementedIncorrectly_testFails() throws Exception{
        boolean meetsEquals = new ReflectiveEqualsAndHashCodeContractUsingSpecificFieldsChecker(getIncorrectReturnsTrueAndSameHashCodeClass(), "field").implementsEqualsCorrectly();

        assertFalse(meetsEquals);
    }

     @Test
    public void givenClassWithPrimitiveField_whenHashCodeIsImplementedIncorrectly_testFails() throws Exception{
        boolean meetsEquals = new ReflectiveEqualsAndHashCodeContractUsingSpecificFieldsChecker(getIncorrectReturnsTrueAndDifferentHashCodeClass(), "field").implementsEqualsCorrectly();

        assertFalse(meetsEquals);
    }

    protected abstract Class getCorrectClass();

    protected abstract Class getIncorrectReturnsTrueAndSameHashCodeClass();

    protected abstract Class getIncorrectReturnsTrueAndDifferentHashCodeClass();




}
