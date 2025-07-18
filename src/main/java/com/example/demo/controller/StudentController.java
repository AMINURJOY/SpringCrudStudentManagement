package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {

    @Autowired
    private StudentService service;

    // Display home page with list of students
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listStudents", service.getAllStudents());
        return "index";  // Loads index.html
    }

    // Show form to add a new student
    @GetMapping("/showNewStudentForm")
    public String showNewStudentForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "new_student";  // Loads new_student.html
    }

    // Save new or updated student
    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student") Student student) {
        service.saveStudent(student);
        return "redirect:/";  // Redirect back to home page after saving
    }

    // Show form to edit an existing student
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") int id, Model model) {
        Student student = service.getStudentById(id);
        model.addAttribute("student", student);
        return "update_student";  // Loads update_student.html
    }

    // Delete a student by ID
    @GetMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable(value = "id") int id) {
        service.deleteStudentById(id);
        return "redirect:/";  // Redirect back to home page after deletion
    }
}
