package com.example.assignment2.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.assignment2.models.Student;
import com.example.assignment2.models.StudentRepository;

import jakarta.servlet.http.HttpServletResponse;


@Controller
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/users/view")
    public String getAllUsers(Model model) {
        List<Student> users = studentRepository.findAll();
        model.addAttribute("us", users);
        return "users/showAll";
    }

    @PostMapping("/users/add")
    public String addUser(@RequestParam Map<String, String> newuser, HttpServletResponse response, Model model) {
        String newName = newuser.get("name");
        int newWeight = Integer.parseInt(newuser.get("weight"));
        int newHeight = Integer.parseInt(newuser.get("height"));
        String newHair = newuser.get("hair");
        float newGPA = Float.parseFloat(newuser.get("gpa"));

        // Generate a unique ID for the user
        System.out.println("added user with id: " + newuser.get("uid"));

        studentRepository.save(new Student(newName, newWeight, newHeight, newHair, newGPA));

        List<Student> users = studentRepository.findAll();
        model.addAttribute("us", users);

        return "users/showAll";
    }
    // I might need to add a getmapping to get the information of each user to the form.
    // @GetMapping("/users/{id}/edit")
    // public String editUser(@PathVariable("id") Long id, Model model){
    //     Optional<Student> currentStudent = studentRepository.findById(id);
    //     model.addAttribute("user", currentStudent);
    //     return "something";
    // }
    @PostMapping("/users/{uid}/save")
    public String saveUser(@PathVariable("uid") int uid, @RequestParam String name, @RequestParam String gpa, @RequestParam String height, @RequestParam String weight, @RequestParam String hair, Model model) {
        Optional<Student> optionalStudent = studentRepository.findById(uid);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            student.setName(name);
            student.setGpa(Float.parseFloat(gpa));
            student.setHeight(Integer.parseInt(height));
            student.setWeight(Integer.parseInt(weight));
            student.setHair(hair);
            studentRepository.save(student);
            // I can use user.delete() to delete
            System.out.println("saving user" + student.getName()+ " with id: " + student.getUid());
        }
    
        List<Student> users = studentRepository.findAll();
        model.addAttribute("us", users);
    
        return  "users/showAll";
    }
    @PostMapping("/users/{uid}/delete")
    public String deleteUser(@PathVariable("uid") int uid, Model model) {
        studentRepository.deleteById(uid);
        return  "redirect:/users/view";
    }
    @GetMapping("/users/goToAdd")
    public String goToAdd(Model model){
        return "redirect:/add.html";
    }
    @GetMapping("/users/viewAll")
    public String goToViewAll(Model model) {
        List<Student> users = studentRepository.findAll();
        model.addAttribute("us", users);
        return "redirect:/users/view";
    }

}
