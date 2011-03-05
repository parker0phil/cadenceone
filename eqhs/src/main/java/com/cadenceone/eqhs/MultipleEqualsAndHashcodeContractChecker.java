package com.cadenceone.eqhs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class MultipleEqualsAndHashcodeContractChecker {
    
    private List<MutualEqualsAndHashcodeContractChecker> checkers = new ArrayList<MutualEqualsAndHashcodeContractChecker>();
    
    private List<MutualEqualsAndHashcodeContractChecker> generateCheckers(Collection objects){
        List<MutualEqualsAndHashcodeContractChecker> checkers = new ArrayList<MutualEqualsAndHashcodeContractChecker>();
        if (objects.size() < 2) throw new IllegalArgumentException("Must provide more two or more objects to generate checkers from.");      
        int leftCount = 0;
        int rightCount = 0;
         for(Object left: objects){           
             for(Object right: objects){  
                 if (leftCount != rightCount && rightCount > leftCount){
                     checkers.add(new MutualEqualsAndHashcodeContractChecker(left, right));
                 }
                 rightCount++;
             }
             rightCount = 0;
             leftCount++;
         }    
         return checkers;
    }
    
    public MultipleEqualsAndHashcodeContractChecker(Object...objects){
        this.checkers = generateCheckers(Arrays.asList(objects));
    }
    
    public MultipleEqualsAndHashcodeContractChecker(Collection objects){
        this.checkers = generateCheckers(objects);
    }
    
    public MultipleEqualsAndHashcodeContractChecker(List<MutualEqualsAndHashcodeContractChecker> checkers){
        this.checkers = checkers;
    }
    
    public List<MutualEqualsAndHashcodeContractChecker> getCheckers(){
        return checkers;          
    }
    
    public boolean allAreSameByEqualsContract(){
        for(MutualEqualsAndHashcodeContractChecker it: checkers){ 
            if (!it.areSameByEqualsContract()){
                return false;
            }
        }
        return true;
    }

    public boolean allAreDifferentByEqualsContract() {
        for(MutualEqualsAndHashcodeContractChecker it: checkers){
            if (!it.areDifferentByEqualsContract()){
                return false;
            }
        }
        return true;
    }
}
