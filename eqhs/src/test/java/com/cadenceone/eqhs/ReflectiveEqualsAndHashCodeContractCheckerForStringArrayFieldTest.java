package com.cadenceone.eqhs;

import com.cadenceone.eqhs.fixtures.CorrectClassFixture;
import com.cadenceone.eqhs.fixtures.Fixtures;
import com.cadenceone.eqhs.fixtures.ReflectiveEqualsAndHashCodeContractCheckerForFieldTest;

public class ReflectiveEqualsAndHashCodeContractCheckerForStringArrayFieldTest extends ReflectiveEqualsAndHashCodeContractCheckerForFieldTest {

    private static class CorrectClass extends CorrectClassFixture {
        private String[] field;

        @Override public Object getField() {return field;}
        @Override public int getHashCodeBuilderRandomSeedOne() {return 53;}
        @Override public int getHashCodeBuilderRandomSeedTwo() {return 67;}
    }

    private static class IncorrectFieldClassReturnsTrueAndSameHashCode extends Fixtures.ObjectReturnsTrueAndSameHashCode {

        public IncorrectFieldClassReturnsTrueAndSameHashCode() {}
        private String[] field;
        public Object getField() {return field;}
    }

    private static class IncorrectFieldClassReturnsTrueAndDifferentHashCode extends Fixtures.ObjectReturnsTrueAndDifferentHashCode {

        public IncorrectFieldClassReturnsTrueAndDifferentHashCode() {}
        private String[] field;
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
