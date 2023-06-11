package com.example.assignment2.models;
import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity

@Table(name="users") // this means connect to the ttable users
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid; // this is to make sure that each user have a unique id
    private String name;
    private int weight;
    private int height;
    private String hair;
    private float gpa;
    public Student() {
    }
    public Student(String newName, int newWeight, int newHeight, String newHair, float newgpa) {
        this.name = newName;
        this.weight = newWeight;
        this.height = newHeight;
        this.hair = newHair;
        this.gpa = newgpa;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public String getHair() {
        return hair;
    }
    public void setHair(String hair) {
        this.hair = hair;
    }
    public float getGpa() {
        return gpa;
    }
    public void setGpa(float gpa) {
        this.gpa = gpa;
    }
    public int getUid() {
        return uid;
    }
    public void setUid(int uid) {
        this.uid = uid;
    }
    
}