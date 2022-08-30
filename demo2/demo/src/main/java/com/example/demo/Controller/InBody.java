package com.example.demo.Controller;

import com.example.demo.Model.BodyData;
import com.example.demo.Model.BodyDataForm;
import com.example.demo.Model.Percentage;
import com.example.demo.db.BodyDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class InBody {
    @CrossOrigin(origins="*")
    @PostMapping("/inbody")
    public Percentage getInfo(@RequestBody Map<String,String>info) {
        Double std_muscle = Double.parseDouble(info.get("weight") )* 0.38;

        Double std_weight = Double.parseDouble(info.get("height")) * Double.parseDouble(info.get("height")) * 22;
        if (Integer.parseInt(info.get("sex")) == 1) {
            std_weight = Double.parseDouble(info.get("height")) * Double.parseDouble(info.get("height")) * 21;
        }
        Double std_fat = Double.parseDouble(info.get("weight")) / (Double.parseDouble(info.get("height"))*Double.parseDouble(info.get("height"))) / 100 *Double.parseDouble(info.get("weight"))/2 ;


        Percentage percentage = new Percentage(); //남자면 0 여자면 1

        percentage.per_weight = Double.parseDouble(info.get("weight"))/std_weight;
        percentage.per_fat = Double.parseDouble(info.get("fat")) / std_fat;
        percentage.per_muscle = Double.parseDouble(info.get("muscle")) / std_muscle;

        // 계산식 반올림
        percentage.per_weight =(double)Math.round(percentage.per_weight*100)/100.0;
        percentage.per_fat =(double)Math.round(percentage.per_fat*100)/100.0;
        percentage.per_muscle =(double)Math.round(percentage.per_muscle*100)/100.0;

        System.out.println("std_fat:"+std_fat);
        System.out.println("std_muscle:"+std_muscle);
        System.out.println("std_weight:"+std_weight);
        saveData(info.get("name"),Double.parseDouble(info.get("age")),Double.parseDouble(info.get("sex")),Double.parseDouble(info.get("height")),Double.parseDouble(info.get("weight")),Double.parseDouble(info.get("fat")),Double.parseDouble(info.get("muscle")));




        return percentage;


    }

    @Autowired
    private BodyDataRepository bodyDataRepository;
    public void saveData(String name, Double age,Double sex,Double height,Double weight,Double fat,Double muscle){
        BodyDataForm bodyDataForm = new BodyDataForm();
        bodyDataForm.setAge(age);
        bodyDataForm.setFat(fat);
        bodyDataForm.setHeight(height);
        bodyDataForm.setName(name);
        bodyDataForm.setWeight(weight);
        bodyDataForm.setMuscle(muscle);
        bodyDataForm.setSex(sex);

        BodyData bodyData = bodyDataForm.toEntity();
        bodyDataRepository.save(bodyData);

    }
}

