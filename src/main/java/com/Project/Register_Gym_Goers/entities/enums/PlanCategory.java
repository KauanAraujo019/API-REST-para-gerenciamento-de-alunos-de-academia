package com.Project.Register_Gym_Goers.entities.enums;

import java.util.Objects;

public enum PlanCategory {

    MONTHLY(1),
    QUARTERLY(2),
    SEMESTER(3),
    ANNUAL(4);

    private final Integer code;

    PlanCategory(Integer code){
        this.code = code;
    }

    public Integer getCode(){
        return code;
    }


    public static PlanCategory findById(Integer id){
        for (PlanCategory value : PlanCategory.values()){
            if (Objects.equals(value.getCode(), id)){
                return value;
            }

        }

        throw new IllegalArgumentException("PlanCategory ID invalid");

    }




}
