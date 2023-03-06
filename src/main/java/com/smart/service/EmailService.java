package com.smart.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	public boolean sendEmail(String subject, String message, String to) {
		
		boolean f = false;
		
		String from = "softwareindiatect@gmail.com";
		
		//variable for gmail host
		String host="smtp.gmail.com";
		
		//get the system properties
		Properties properties = System.getProperties();
		System.out.println(" Properties "+properties);
		
		//setting important information to properties object
		
		//host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		//step 1:to get the session object..
		 Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("softwareindiatect@gmail.com", "Usha@1997");
			}
		
		});
		
		session.setDebug(true);
		
		//step 2:compose the message [text,multi media]
     MimeMessage m = new MimeMessage(session);
		
		try {
		   
			//from email
			m.setFrom(from);
			
			//adding recipient
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			//adding reciepient to message
			m.setSubject(subject);
			
			//adding text to messge
			m.setText(message);
			
			//send
			//step 3:send the message using transport class
			Transport.send(m);
			
			System.out.println("send success...............");
			
			f = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return f;
		
		
		
		
	}
}


