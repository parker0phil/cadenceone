package com.cadenceone.eqhs.fixtures;

public class ObjectEqualsHashcodeFixtures {

    public static class ObjectReturnsTrueAndSameHashcode{
        @Override
        public boolean equals(Object obj) {
            return true;
        }
    
        @Override
        public int hashCode() {         
            return 1;
        }        
    }

    public static class ObjectReturnsFalseAndSameHashcode{
        @Override
        public boolean equals(Object obj) {
            return false;
        }  
        
        @Override
        public int hashCode() {         
            return 1;
        }  
    }

    public static class ObjectReturnsTrueAndDifferentHashcode{
        @Override
        public boolean equals(Object obj) {
            return true;
        }
    
        @Override
        public int hashCode() {         
            return 2;
        }        
    }

}
