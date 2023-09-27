package com.example.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Repository.StudentRepository;
import com.example.model.Student;
@Service
@Transactional
public class StudServiceImpl {
	
	
	private StudentRepository studentRepository;
	
	
    public StudServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
	
	//For save data
	public Student createStudent(Student student) {
        return studentRepository.save(student);
    }
	
	
	public List<Student> getAllData(){
		
		return studentRepository.findAll();
		
	}
	

	@Override
	public Student getStudentById(long id) {
		 Optional < Student > optional = studentRepository.findById(id);
	        Student student = null;
	        if (optional.isPresent()) {
	            student = optional.get();
	        } else {
	            throw new RuntimeException(" Student not available for id:: " + id);
	        }
	        return student;
	    }
	
	
	//For delete data
	public void deleteStudentById(long id) {
		this.studentRepository.deleteById(id);
	}

}
