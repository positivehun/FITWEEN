package com.example.demo.Model;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BodyData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @Column
    private Double age;

    @Column
    private Double sex;

    @Column
    private Double muscle;

    @Column
    private Double fat;

    @Column
    private Double height;

    @Column
    private Double weight;


    @Builder
    public BodyData(String name, Double age,Double sex,Double muscle,Double fat,Double height,Double weight){
        this.age = age;
        this.name = name;
        this.sex = sex;
        this.muscle = muscle;
        this.fat = fat;
        this.height = height;
        this.weight = weight;

    }

}
