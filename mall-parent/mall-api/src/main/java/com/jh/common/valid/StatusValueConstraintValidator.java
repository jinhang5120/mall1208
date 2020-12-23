package com.jh.common.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

public class StatusValueConstraintValidator implements ConstraintValidator<StatusValue,Integer> {
    private Set<Integer> set;
    @Override
    public void initialize(StatusValue constraintAnnotation) {
        set = new HashSet<>();
        for (int val : constraintAnnotation.vals()) {
            set.add(val);
        }
    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        if(set!=null){
            return set.contains(integer);
        }
        return false;
    }
}
