package com.example.demo.Model;


import lombok.Data;

@Data
public class BodyDataForm {
    private long id;

    private String name;

    private Double age;

    private Double sex;

    private Double height;

    private Double weight;

    private Double fat;

    private Double muscle;


    public BodyData toEntity(){

        return BodyData.builder()
                .name(name)
                .age(age)
                .sex(sex)
                .height(height)
                .weight(weight)
                .fat(fat)
                .muscle(muscle)
                .build();
    }
}
