package com.Project.Register_Gym_Goers.entities.enums;

import java.util.Objects;

public enum StatusPayment {

    WAITING_PAYMENT(1),
    PAID(2),
    OVERDUE(3);

    private final Integer code;

    StatusPayment(Integer code){
        this.code = code;
    }

    public Integer getCode(){
        return code;
    }


    public static StatusPayment findById(Integer id){
        for (StatusPayment value : StatusPayment.values()){
            if (Objects.equals(value.getCode(), id)){
                return value;
            }

        }

        throw new IllegalArgumentException("StatusPayment ID invalid");

    }








}
