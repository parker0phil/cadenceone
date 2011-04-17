package com.cadenceone.eqhs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class MultipleEqualsAndHashCodeContractChecker {
    
    private List<MutualEqualsAndHashCodeContractChecker> checkers = new ArrayList<MutualEqualsAndHashCodeContractChecker>();
    
    private List<MutualEqualsAndHashCodeContractChecker> generateCheckers(Collection objects){
        List<MutualEqualsAndHashCodeContractChecker> checkers = new ArrayList<MutualEqualsAndHashCodeContractChecker>();
        if (objects.size() < 2) throw new IllegalArgumentException("Must provide more two or more objects to generate checkers from.");      
        int leftCount = 0;
        int rightCount = 0;
         for(Object left: objects){           
             for(Object right: objects){  
                 if (leftCount != rightCount && rightCount > leftCount){
                     checkers.add(new MutualEqualsAndHashCodeContractChecker(left, right));
                 }
                 rightCount++;
             }
             rightCount = 0;
             leftCount++;
         }    
         return checkers;
    }
    
    public MultipleEqualsAndHashCodeContractChecker(Object... objects){
        this.checkers = generateCheckers(Arrays.asList(objects));
    }
    
    public MultipleEqualsAndHashCodeContractChecker(Collection objects){
        this.checkers = generateCheckers(objects);
    }
    
    public MultipleEqualsAndHashCodeContractChecker(List<MutualEqualsAndHashCodeContractChecker> checkers){
        this.checkers = checkers;
    }
    
    public List<MutualEqualsAndHashCodeContractChecker> getCheckers(){
        return checkers;          
    }
    
    public boolean allAreSameByEqualsContract(){
        for(MutualEqualsAndHashCodeContractChecker it: checkers){
            if (!it.areSameByEqualsContract()){
                return false;
            }
        }
        return true;
    }

    public boolean allAreDifferentByEqualsContract() {
        for(MutualEqualsAndHashCodeContractChecker it: checkers){
            if (!it.areDifferentByEqualsContract()){
                return false;
            }
        }
        return true;
    }

    public boolean allHaveDifferentHashCodes() {
        for(MutualEqualsAndHashCodeContractChecker it: checkers){
            if (!it.haveDifferentHashCodes()){
                return false;
            }
        }
        return true;
    }


}
