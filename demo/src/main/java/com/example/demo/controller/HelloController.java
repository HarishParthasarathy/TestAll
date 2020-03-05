package com.example.demo.controller;


import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Employee;
import com.example.demo.pojo.EmployeeRepository;

@RestController
@RequestMapping("/api/v1")
public class HelloController {

	@Autowired
    private EmployeeRepository employeeRepository;
	
	@RequestMapping(value="/employees",method=RequestMethod.GET)
    public Iterable < Employee > getAllEmployees() {
        return employeeRepository.findAll();
    }
	
	@GetMapping("/employees/{id}")
    public Optional<Employee> getAllEmployees(@PathVariable(value="id") Long empid) {
        return employeeRepository.findById(empid);
    }
	
	
	@RequestMapping("/")
	public String Index() {
		System.out.println("*");
		return "First Spring Boot Program ";
	}
	
	 @PostMapping("/employees")
	    public Employee createEmployee(@Valid @RequestBody Employee employee) {
	        return employeeRepository.save(employee);
	    }
	 

	 @PutMapping("/employees/{id}")
	    public String updateEmployee(@PathVariable(value = "id") Long empId,@Valid @RequestBody Employee employee){ 
		 Employee emp= employeeRepository.getOne(empId);	 
		 emp.setEmailId(employee.getEmailId());
		 emp.setFirstName(employee.getFirstName());
		 emp.setLastName(employee.getLastName());
		 employeeRepository.save(emp);
		 return "The employee with id ="+ empId +"is updated";
		 }
		 
	 
	 @DeleteMapping("/employees/{id}")
	    public String deleteEmployee(@PathVariable(value="id") Long empId) {
	     Employee e;    
	     e=employeeRepository.getOne(empId);
		 employeeRepository.deleteById(empId);
	         return e.getFirstName()+" "+ e.getLastName()+" has been deleted";  
	    
	 }
	 
}
