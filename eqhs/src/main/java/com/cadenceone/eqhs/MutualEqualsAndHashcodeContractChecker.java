package com.cadenceone.eqhs;

public class MutualEqualsAndHashcodeContractChecker {

    private final Object leftObject;
    private final Object rightObject;

    public MutualEqualsAndHashcodeContractChecker(Object leftObject, Object rightObject) {
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
        return leftObject.equals(rightObject) && rightObject.equals(leftObject);
    }
    
    private boolean haveSameHashCode() {
        return leftObject.hashCode() == rightObject.hashCode();
    }
}
