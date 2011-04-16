package com.cadenceone.eqhs;

import com.cadenceone.eqhs.fixtures.CorrectClassFixture;
import com.cadenceone.eqhs.fixtures.Fixtures;
import com.cadenceone.eqhs.fixtures.ReflectiveEqualsAndHashCodeContractCheckerForFieldTest;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ReflectiveEqualsAndHashCodeContractCheckerForBooleanFieldTest extends ReflectiveEqualsAndHashCodeContractCheckerForFieldTest {

    private static class CorrectClass extends CorrectClassFixture {
        private boolean field;

        @Override public Object getField() {return field;}
        @Override public int getHashCodeBuilderRandomSeedOne() {return 37;}
        @Override public int getHashCodeBuilderRandomSeedTwo() {return 43;}
    }

    private static class IncorrectFieldClassReturnsTrueAndSameHashCode extends Fixtures.ObjectReturnsTrueAndSameHashCode {

        public IncorrectFieldClassReturnsTrueAndSameHashCode() {}
        private boolean field;
        public Object getField() {return field;}
    }

    private static class IncorrectFieldClassReturnsTrueAndDifferentHashCode extends Fixtures.ObjectReturnsTrueAndDifferentHashCode {

        public IncorrectFieldClassReturnsTrueAndDifferentHashCode() {}
        private boolean field;
        public Object getField() {return field;}
    }

    @Override
    protected Class getCorrectClass() {
        return CorrectClass.class;
    }

    @Override
    protected Class getIncorrectReturnsTrueAndSameHashCodeClass() {
        return IncorrectFieldClassReturnsTrueAndSameHashCode.class;
    }

    @Override
    protected Class getIncorrectReturnsTrueAndDifferentHashCodeClass() {
        return IncorrectFieldClassReturnsTrueAndDifferentHashCode.class;
    }



}
