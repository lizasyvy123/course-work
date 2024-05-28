package com.nulp.course_work.constants;


import java.util.Objects;

import static java.lang.Boolean.TRUE;


public enum ordersort {
    DESC (1, "In descending order"),

    ASC (2, "In ascending order" );

    private final int numType;
    private final String name;

    ordersort(int type, String name) {
        this.numType = type;
        this.name = name;
    }



    public static ordersort getordersortype(int indx){
        for(ordersort type : ordersort.values()){
            if(type.numType == indx){
                return type;
            }
        }
        return null;
    }

    public static ordersort getordersortype(String indx){
        for(ordersort type : ordersort.values()){
            if(Objects.equals(type.name, indx)){
                return type;
            }
        }
        return null;
    }


}
