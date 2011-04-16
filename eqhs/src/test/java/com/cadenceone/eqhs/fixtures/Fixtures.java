package com.cadenceone.eqhs.fixtures;

public class Fixtures {

    public static class ObjectReturnsTrueAndSameHashCode {

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return 1;
        }
    }

    public static class ObjectReturnsFalseAndSameHashCode {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            return false;
        }

        @Override
        public int hashCode() {
            return 1;
        }
    }

    public static class ObjectReturnsTrueAndDifferentHashCode {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            return false;
        }
    
        @Override
        public int hashCode() {         
            return 2;
        }        
    }

}
