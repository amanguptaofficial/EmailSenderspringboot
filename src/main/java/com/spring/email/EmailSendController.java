package com.spring.email;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailSendController {

	private final EmailService emailServicce;

	public EmailSendController(EmailService emailServicce) {
		super();
		this.emailServicce = emailServicce;
	}
 
	@GetMapping("/home")
	public String home() {
		return "index";
	}

	@PostMapping("/sendMail")
	public String sendMail(@RequestBody Email email) {
		return this.emailServicce.sendEmail(email);
	}

	@PostMapping("/sendWithAttachment")
	public String sendMailWithAttachment(@ModelAttribute Email email) {
		return this.emailServicce.sendMailwithAttachment(email);
	}
}
