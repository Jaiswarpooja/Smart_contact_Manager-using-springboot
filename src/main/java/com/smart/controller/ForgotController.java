package com.smart.controller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.service.EmailService;

@Controller
public class ForgotController {
	
	Random random = new Random(1000);

	@Autowired
	private EmailService emailService;
	
	@RequestMapping("/forgot")
	public String openEmailForm() {
		return "forgot_email_form";
	}
	
	@PostMapping("/send-OTP")
	public String sendOTP(@RequestParam("email") String email,
			HttpSession session) {
		
		System.out.println(" Email "+email);
		
		//generating otp of 4 digit
		
		
		int otp = random.nextInt(999999);
		
		System.out.println(" OTP "+otp);
		
		//write code to send otp to email
		
		String subject = "OTP From SCM";
		String message = " OTP = "+otp+"";
		String to = email;
		
		boolean flag = this.emailService.sendEmail(subject, message, to);
		
		if(flag) {
			
			session.setAttribute("OTP ", otp);
			return "verify_otp";
		}
		else {
			
			session.setAttribute("message", "Check your email id !!");
		    return "forgot_email_form";
	}
	}
	
	
	
	
	
	
	
	
}
