package com.youdows.fightting.test.reflect;

/**
 * Created by youxiaoshuang on 16/4/4.
 */
public class Car {
    private String brand;
    private String color;
    private int maxSpeed;

    //    构造函数
    public Car() {

    }

    //    带参构造函数
    public Car(String brand, String color, int maFxSpeed) {
        this.brand = brand;
        this.color = color;
        this.maxSpeed = maxSpeed;
    }

    //未带参的方法
    public void introduce() {
        System.out.println("BROUND:" + brand + ",COLOR" + color + ",maxspeed" + maxSpeed);

    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }
}
