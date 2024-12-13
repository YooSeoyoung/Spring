package com.dw.jdbcapp.model;

public class MileGrade {// 마일리지등급
    private String ratingName; // 등급명
    private int lowerLimitMileage;// 하한마일리지
    private int upperLimitMileage;// 상한 마일리지

    public MileGrade() {
    }

    public MileGrade(String ratingName, int lowerLimitMileage, int upperLimitMileage) {
        this.ratingName = ratingName;
        this.lowerLimitMileage = lowerLimitMileage;
        this.upperLimitMileage = upperLimitMileage;
    }

    public String getRatingName() {
        return ratingName;
    }

    public void setRatingName(String ratingName) {
        this.ratingName = ratingName;
    }

    public int getLowerLimitMileage() {
        return lowerLimitMileage;
    }

    public void setLowerLimitMileage(int lowerLimitMileage) {
        this.lowerLimitMileage = lowerLimitMileage;
    }

    public int getUpperLimitMileage() {
        return upperLimitMileage;
    }

    public void setUpperLimitMileage(int upperLimitMileage) {
        this.upperLimitMileage = upperLimitMileage;
    }

    @Override
    public String toString() {
        return "MileGrade{" +
                "RatingName=" + ratingName +
                ", LowerLimitMileage=" + lowerLimitMileage +'\''+
                ", UpperLimitMileage=" + upperLimitMileage+ '}';

    }
}
