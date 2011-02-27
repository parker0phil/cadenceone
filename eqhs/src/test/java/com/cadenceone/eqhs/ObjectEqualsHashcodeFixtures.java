package com.cadenceone.eqhs;

public class ObjectEqualsHashcodeFixtures {

    static class ObjectReturnsTrueAndSameHashcode{
        @Override
        public boolean equals(Object obj) {
            return true;
        }
    
        @Override
        public int hashCode() {         
            return 1;
        }        
    }

    static class ObjectReturnsFalseAndSameHashcode{
        @Override
        public boolean equals(Object obj) {
            return false;
        }  
        
        @Override
        public int hashCode() {         
            return 1;
        }  
    }

    static class ObjectReturnsTrueAndDifferentHashcode{
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
