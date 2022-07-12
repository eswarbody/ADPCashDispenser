package com.example.adp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.adp.model.Change;
import com.example.adp.service.BillChangeService;




@RestController
@RequestMapping("/api")
public class BillChangeController {
	@Autowired
	BillChangeService billService;
	
	
	@RequestMapping(value="/getChange", method=RequestMethod.GET)
	public Change readEmployees(@RequestParam("bill") double bill) {
		
	    return billService.getChange(bill);
	}
	
	
}



