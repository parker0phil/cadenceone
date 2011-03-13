package com.cadenceone.eqhs;

import com.cadenceone.eqhs.fixtures.Fixtures;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ReflectiveEqualsAndHashCodeContractCheckerForIntFieldTest {

    @Test
    public void givenClassWithPrimitiveIntField_whenEqualsIsImplementedCorrectly_testPasses() throws Exception{
        boolean meetsEquals = new ReflectiveEqualsAndHashCodeContractUsingSpecificFieldsChecker(CorrectPrimitiveIntFieldClass.class, "intField").implementsEqualsCorrectly();

        assertTrue(meetsEquals);
    }

    @Test
    public void givenClassWithPrimitiveIntField_whenEqualsIsImplementedIncorrectly_testFails() throws Exception{
        boolean meetsEquals = new ReflectiveEqualsAndHashCodeContractUsingSpecificFieldsChecker(IncorrectReturnsTrueAndSameHashCodePrimitiveIntFieldClass.class, "intField").implementsEqualsCorrectly();

        assertFalse(meetsEquals);
    }

     @Test
    public void givenClassWithPrimitiveIntField_whenHashCodeIsImplementedIncorrectly_testFails() throws Exception{
        boolean meetsEquals = new ReflectiveEqualsAndHashCodeContractUsingSpecificFieldsChecker(IncorrectReturnsTrueAndDifferentHashCodePrimitiveIntFieldClass.class, "intField").implementsEqualsCorrectly();

        assertFalse(meetsEquals);
    }


    private static class CorrectPrimitiveIntFieldClass {
        private int intField;

        public CorrectPrimitiveIntFieldClass() {
        }

        public int getIntField() {
            return intField;
        }

        public boolean equals(Object obj) {
            if (obj == null) { return false; }
            if (obj == this) { return true; }
            if (obj.getClass() != getClass()) {
                return false;
            }
           CorrectPrimitiveIntFieldClass rhs = (CorrectPrimitiveIntFieldClass) obj;
           return new EqualsBuilder().append(intField, rhs.getIntField()).isEquals();
        }

        public int hashCode() {
            return new HashCodeBuilder(97, 23).append(intField).toHashCode();
        }
    }

    private static class IncorrectReturnsTrueAndSameHashCodePrimitiveIntFieldClass extends Fixtures.ObjectReturnsTrueAndSameHashCode {

        public IncorrectReturnsTrueAndSameHashCodePrimitiveIntFieldClass() {
        }

        private int intField;

        public int getIntField() {
            return intField;
        }

    }

    private static class IncorrectReturnsTrueAndDifferentHashCodePrimitiveIntFieldClass extends Fixtures.ObjectReturnsTrueAndDifferentHashCode {

        public IncorrectReturnsTrueAndDifferentHashCodePrimitiveIntFieldClass() {
        }

        private int intField;

        public int getIntField() {
            return intField;
        }

    }
}
