package com.cadenceone.eqhs;

public class MutualEqualsAndHashCodeContractChecker {

    private final Object leftObject;
    private final Object rightObject;

    public MutualEqualsAndHashCodeContractChecker(Object leftObject, Object rightObject) {
        this.leftObject = leftObject;
        this.rightObject = rightObject;
    }

    public boolean areSameByEqualsContract() {
        return areSameByEqualsMethod() && haveSameHashCode();
    }
    
    public boolean areDifferentByEqualsContract() {
        return !areSameByEqualsMethod();
    }
    
    private boolean areSameByEqualsMethod() {
        return  !leftObject.equals(null) &&
                !rightObject.equals(null) &&
                !leftObject.equals(new DifferentObject()) &&
                !rightObject.equals(new DifferentObject()) &&
                leftObject.equals(rightObject) &&
                rightObject.equals(leftObject);
    }
    
    private boolean haveSameHashCode() {
        return leftObject.hashCode() == rightObject.hashCode();
    }

    public boolean haveDifferentHashCodes() {
        return !haveSameHashCode();
    }

    private class DifferentObject {
    }
}
