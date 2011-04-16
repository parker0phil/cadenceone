package com.cadenceone.eqhs;

import com.cadenceone.eqhs.fixtures.CorrectClassFixture;
import com.cadenceone.eqhs.fixtures.Fixtures;
import com.cadenceone.eqhs.fixtures.ReflectiveEqualsAndHashCodeContractCheckerForFieldTest;

public class ReflectiveEqualsAndHashCodeContractCheckerForFloatArrayFieldTest extends ReflectiveEqualsAndHashCodeContractCheckerForFieldTest {

    private static class CorrectClass extends CorrectClassFixture {
        private float[] field;

        @Override public Object getField() {return field;}
        @Override public int getHashCodeBuilderRandomSeedOne() {return 53;}
        @Override public int getHashCodeBuilderRandomSeedTwo() {return 67;}
    }

    private static class IncorrectFieldClassReturnsTrueAndSameHashCode extends Fixtures.ObjectReturnsTrueAndSameHashCode {

        public IncorrectFieldClassReturnsTrueAndSameHashCode() {}
        private float[] field;
        public Object getField() {return field;}
    }

    private static class IncorrectFieldClassReturnsTrueAndDifferentHashCode extends Fixtures.ObjectReturnsTrueAndDifferentHashCode {

        public IncorrectFieldClassReturnsTrueAndDifferentHashCode() {}
        private float[] field;
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
