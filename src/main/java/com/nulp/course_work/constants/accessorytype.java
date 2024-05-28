package com.nulp.course_work.constants;


import java.util.Objects;



public enum accessorytype {

    JEWELRY(1, "Jewelry"),

    RIBBON (2, "Ribbon"),

    BEAR (3, "Bear"),

    GELBALL(4, "Gel ball"),

    POSTCARD(5, "Postcard"),

    HEART(6, "Heart");

    private final int numType;

    private final String name;

    accessorytype(int numType, String name) {
        this.numType = numType;
        this.name = name;
    }



    public static accessorytype getaccessorytype(int indx){
        for(accessorytype type : accessorytype.values()){
            if(type.numType == indx){
                return type;
            }
        }
        return null;
    }

    public static accessorytype getaccessorytype(String indx){
        for(accessorytype type : accessorytype.values()){
            if(Objects.equals(type.name, indx)){
                return type;
            }
        }
        return null;
    }


    public int getNumType() {
        return numType;
    }

}
