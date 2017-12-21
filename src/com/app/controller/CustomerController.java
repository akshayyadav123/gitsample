 package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.model.Customer;
import com.app.model.Location;
import com.app.service.ICustomerService;
import com.app.util.CodeUtil;
import com.app.util.CodecUtil;
import com.app.util.CommonUtil;
import com.app.util.LocationUtil;

@Controller
public class CustomerController {
    @Autowired
	private CommonUtil commonutil;

    @Autowired
    private CodecUtil codecUtil;

    @Autowired
    private ICustomerService service;
    
    @Autowired
    private LocationUtil util;
    @Autowired
    private CodeUtil codeUtil;
    @RequestMapping("/custReg")
    public String showCustomer(ModelMap map){
    	List<Location> loc=util.getAllLocation();             
        map.addAttribute("locList", loc) ;
           return "CustRegister";
    }
    
    public String saveCustomer(@RequestParam("customer")Customer cust,ModelMap map){
    	   int locId=service.saveCustomer(cust);
    	   map.addAttribute("locId" ,locId);
    	   String genToken=codeUtil.genToken();
    	   String genPassword=codeUtil.getPwd();
    	   
    	   
    	   
    	   //commonutil.sendEmail(cust.getCustAddr(),, text)
    	   
    	return "";
    }
    
    public String editCustomer(){
    	
    	 return "";
    }
    
    public String updateCustomer(){
    	
    	return "";
    }
    
    public String deleteCustomer(){
    	
    	
    	return "";
    }
}
