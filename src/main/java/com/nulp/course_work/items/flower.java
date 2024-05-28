package com.nulp.course_work.items;

import com.nulp.course_work.constants.flowertype;

import java.util.Date;
import java.util.Objects;



public class flower {
    private int id;
    private flowertype Flowertype;
    private String colour;
    private double length;
    private double price;
    private Date date = null;

    private static final int maxSizeOfColour = 70;

    public flower() {}

    public flower(flowertype type, String colour, double length, double price){
        this.colour = colour;
        this.Flowertype = type;
        this.length = length;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public flowertype getflowertype() {
        return Flowertype;
    }

    public String getColour() {
        return colour;
    }

    public double getLength() {
        return length;
    }

    public double getPrice() {
        return price;
    }

    public java.sql.Date getDate() {
        return (java.sql.Date) date;
    }

    @Override
    public String toString() {
        return "flower{" +
                "id=" + id +
                ", flowertype=" + Flowertype +
                ", colour='" + colour + '\'' +
                ", length=" + length +
                ", price=" + price +
                ", date=" + date +
                '}' + '\n';
    }

    public static flowerbuilder builder(){
        return new flowerbuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        flower flower = (flower) o;
        return id == flower.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    public static class flowerbuilder{

        private flower flower;

        flowerbuilder() {
            this.flower = new flower();
        }

        public flowerbuilder setId(int id) {
            flower.id = id;
            return this;
        }

        public flowerbuilder setflowertype(flowertype flowertype) {
            flower.Flowertype = flowertype;
            return this;
        }

        public flowerbuilder setColour(String colour) {
            flower.colour = colour;
            return this;
        }

        public flowerbuilder setLength(double length) {
            flower.length = length;
            return this;
        }

        public flowerbuilder setPrice(double price) {
            flower.price = price;
            return this;
        }

        public flowerbuilder setDate(Date date) {
            flower.date = date;
            return this;
        }




        public flower built(){
            return flower;
        }

    }
}
