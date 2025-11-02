package com.Project.Register_Gym_Goers.entities.enums;

import java.time.LocalDate;
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


    public static Double defPricePlan(PlanCategory planCategory){

        if (planCategory.code == 1){

            return 120.00;

        }
        else if (planCategory.code == 2){

            return 330.00;

        }
        else if (planCategory.code == 3){

            return 600.00;

        }
        else if (planCategory.code == 4){

            return 1100.00;

        }

        return null;

    }

    public static int defDueDay(PlanCategory planCategory){

        if (planCategory.code == 1){

            return 1;
        }
        else if(planCategory.code == 2){

            return 3;

        }
        else if (planCategory.code == 3){

            return 6;

        }
        else if (planCategory.code == 4){

            return 12;

        }
        else {
            return 0;
        }



    }




}
