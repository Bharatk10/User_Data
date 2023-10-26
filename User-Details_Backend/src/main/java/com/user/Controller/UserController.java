package com.user.Controller;

import java.awt.PageAttributes.MediaType;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.http.HttpHeaders;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.user.enity.User;
import com.user.repository.UserRepository;
import com.user.service.UserService;

import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	UserService userservice;
	
	@Autowired 
	UserRepository urespo;
	

	@GetMapping("/user")
	public void downloaddata(@ModelAttribute("field1") String field1,@ModelAttribute("field2") String field2
			,@ModelAttribute("field3") String field3,@ModelAttribute("field4") String field4,HttpServletResponse response) throws Exception{
		
		response.setContentType("application/octet-stream");
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=userdata.xls";

		response.setHeader(headerKey, headerValue);
		
		userservice.generateExcel(field1,field2,field3,field4,response);
		
		response.flushBuffer();
	}
	
	@PostMapping("/user")
	public User saveUser(@ModelAttribute("first_name") String first_name, @ModelAttribute("last_name") String last_name,
			@ModelAttribute("date_of_birth") String date_of_birth, @ModelAttribute("city") String city) {
         
         
         User user=userservice.saveUser(first_name, last_name, date_of_birth, city);
         
         System.out.println(user.toString());
         
        return user;
    }
	
	@GetMapping("/")
	public List<User> getAll(){
		
		return urespo.findAll();
	}

	    
	    
}
