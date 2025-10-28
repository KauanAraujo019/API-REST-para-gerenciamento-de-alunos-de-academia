package com.Project.Register_Gym_Goers.entities.enums;

public enum StatusPayment {

    WAITING_PAYMENT(1),
    PAID(2),
    OVERDUE(3);

    private Integer code;

    StatusPayment(Integer code){
        this.code = code;
    }

    public Integer getCode(){
        return code;
    }


    public StatusPayment findById(Integer id){
        for (StatusPayment value : StatusPayment.values()){
            if (value.getCode() == id){
                return value;
            }

        }

        throw new IllegalArgumentException("StatusPayment ID invalid");

    }








}
