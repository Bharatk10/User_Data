package com.user.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.enity.User;
import com.user.repository.UserRepository;


import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class UserService {

	@Autowired
	UserRepository urespo;
	
	public void generateExcel(String field1,String field2,String field3,String field4,HttpServletResponse response) throws Exception {

		

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Users Data");
		HSSFRow row = sheet.createRow(0);

		
		row.createCell(0).setCellValue(field1);
		row.createCell(1).setCellValue(field2);
		row.createCell(2).setCellValue(field3);
		row.createCell(3).setCellValue(field4);

		ServletOutputStream ops = response.getOutputStream();
		workbook.write(ops);
		workbook.close();
		ops.close();

	}
	
	public User saveUser(String first_name,String last_name,String date_of_birth,String city) {
        User user = new User(first_name, last_name, date_of_birth, city);
        urespo.save(user);
        return user;
    }
}
