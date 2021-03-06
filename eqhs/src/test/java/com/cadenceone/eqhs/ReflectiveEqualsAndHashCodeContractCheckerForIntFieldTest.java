package com.cadenceone.eqhs;

import com.cadenceone.eqhs.fixtures.CorrectClassFixture;
import com.cadenceone.eqhs.fixtures.Fixtures;
import com.cadenceone.eqhs.fixtures.ReflectiveEqualsAndHashCodeContractCheckerForFieldTest;

public class ReflectiveEqualsAndHashCodeContractCheckerForIntFieldTest extends ReflectiveEqualsAndHashCodeContractCheckerForFieldTest {

    private static class CorrectClass extends CorrectClassFixture {
        private int field;

        @Override public Object getField() {return field;}
        @Override public int getHashCodeBuilderRandomSeedOne() {return 43;}
        @Override public int getHashCodeBuilderRandomSeedTwo() {return 47;}
    }

    private static class IncorrectFieldClassReturnsTrueAndSameHashCode extends Fixtures.ObjectReturnsTrueAndSameHashCode {

        public IncorrectFieldClassReturnsTrueAndSameHashCode() {}
        private int field;
        public Object getField() {return field;}
    }

    private static class IncorrectFieldClassReturnsTrueAndDifferentHashCode extends Fixtures.ObjectReturnsTrueAndDifferentHashCode {

        public IncorrectFieldClassReturnsTrueAndDifferentHashCode() {}
        private int field;
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
