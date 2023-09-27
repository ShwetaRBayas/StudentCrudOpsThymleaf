package com.example.controller;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Service.StudServiceImpl;
import com.example.model.Student;
import com.rnt.entity.Employee;

@Controller
@RequestMapping("/student")

public class StudentController {

	
	private StudServiceImpl studServiceImpl;
   
	
	public StudentController(StudServiceImpl studServiceImpl) {
		super();
		this.studServiceImpl = studServiceImpl;
	}

	@PostMapping("/saveStudent")
    public Student createStudent(@RequestParam("name") String name,
                                 @RequestParam("nationality") String nationality,
                                 @RequestParam("gender") String gender,
                                 @RequestParam("dob") String dob,
                                 @RequestParam("image") MultipartFile image) throws IOException, ParseException {
        // Convert the date string to a Date object (you may need to adjust the date format)
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOfBirth = (Date) dateFormat.parse(dob);

        // Create a Student object and set its properties
        Student student = new Student();
        student.setName(name);
        student.setNationality(nationality);
        student.setGender(gender);
        student.setDob(dateOfBirth);
        student.setImage(image.getBytes());

        return studServiceImpl.createStudent(student);
	}
	
	@GetMapping("/students")
	public String getAll(Model model){
		model.addAttribute("listStudents", studServiceImpl.getAllData());
		return "listStudents";
	}
	
	@GetMapping("/showNewStudentForm")
    public String showNewStudentForm(Model model) {
        // create model attribute to bind form data
        Student student = new Student();
        model.addAttribute("students", student);
        return "new_student";
	}
	
	 @GetMapping("/showFormForUpdate/{id}")
	    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

	        // get student from the service
	        Student student = studServiceImpl.getEmployeeById(id);

	        // set student as a model attribute to pre-populate the form
	        model.addAttribute("student", student);
	        return "update_student";
	    }
	 
	@DeleteMapping("/{id}")
	public void deleteStudent(@PathVariable("id") Long id) {
	    
	     studServiceImpl.deleteStudentById(id);
	}
}
