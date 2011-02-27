package com.cadenceone.eqhs;

import java.util.ArrayList;
import java.util.List;

public class MutualEqualsAndHashcodeContractCheckerFactory {
    
    
    public List<MutualEqualsAndHashcodeContractChecker> getCheckers(Object...objects){
        if (objects.length < 2) throw new IllegalArgumentException("Must provide more two or more objects to generate checkers from.");
        List<MutualEqualsAndHashcodeContractChecker> checkers = new ArrayList<MutualEqualsAndHashcodeContractChecker>();
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
}
