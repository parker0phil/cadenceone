package com.cadenceone.eqhs;

import com.cadenceone.eqhs.fixtures.Fixtures;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ReflectiveEqualsAndHashCodeContractCheckerForBooleanFieldTest {

    @Test
    public void givenClassWithPrimitiveBooleanField_whenEqualsIsImplementedCorrectly_testPasses() throws Exception{
        new  CorrectPrimitiveBooleanFieldClass();
        boolean meetsEquals = new ReflectiveEqualsAndHashCodeContractUsingSpecificFieldsChecker(CorrectPrimitiveBooleanFieldClass.class, "booleanField").implementsEqualsCorrectly();

        assertTrue(meetsEquals);
    }

    @Test
    public void givenClassWithPrimitiveBooleanField_whenEqualsIsImplementedIncorrectly_testFails() throws Exception{
        boolean meetsEquals = new ReflectiveEqualsAndHashCodeContractUsingSpecificFieldsChecker(IncorrectReturnsTrueAndSameHashCodePrimitiveBooleanFieldClass.class, "booleanField").implementsEqualsCorrectly();

        assertFalse(meetsEquals);
    }

     @Test
    public void givenClassWithPrimitiveBooleanField_whenHashCodeIsImplementedIncorrectly_testFails() throws Exception{
        boolean meetsEquals = new ReflectiveEqualsAndHashCodeContractUsingSpecificFieldsChecker(IncorrectReturnsTrueAndDifferentHashCodePrimitiveBooleanFieldClass.class, "booleanField").implementsEqualsCorrectly();

        assertFalse(meetsEquals);
    }


    private static class CorrectPrimitiveBooleanFieldClass {
        private boolean booleanField;

        public CorrectPrimitiveBooleanFieldClass() {
        }

        public boolean isBooleanField() {
            return booleanField;
        }

        public boolean equals(Object obj) {
            if (obj == null) { return false; }
            if (obj == this) { return true; }
            if (obj.getClass() != getClass()) {
                return false;
            }
            CorrectPrimitiveBooleanFieldClass rhs = (CorrectPrimitiveBooleanFieldClass) obj;
           return new EqualsBuilder().append(booleanField, rhs.isBooleanField()).isEquals();
        }

        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(booleanField).toHashCode();
        }
    }

    private static class IncorrectReturnsTrueAndSameHashCodePrimitiveBooleanFieldClass extends Fixtures.ObjectReturnsTrueAndSameHashCode {

        public IncorrectReturnsTrueAndSameHashCodePrimitiveBooleanFieldClass() {
        }

        private boolean booleanField;

        public boolean isBooleanField() {
            return booleanField;
        }
    }

    private static class IncorrectReturnsTrueAndDifferentHashCodePrimitiveBooleanFieldClass extends Fixtures.ObjectReturnsTrueAndDifferentHashCode {

        public IncorrectReturnsTrueAndDifferentHashCodePrimitiveBooleanFieldClass() {
        }

        private boolean booleanField;

        public boolean isBooleanField() {
            return booleanField;
        }
    }
}
