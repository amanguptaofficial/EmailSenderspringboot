package com.spring.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

	private final JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String sender;

	public EmailService(JavaMailSender javaMailSender) {
		super();
		this.javaMailSender = javaMailSender;
	}

	public String sendEmail(Email email) {
		try {
			// simpleMessage
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setFrom("Aman Gupta<" + sender + ">");
			mailMessage.setTo(email.getRecipient());
			mailMessage.setSubject(email.getSubject());
			mailMessage.setText(email.getMessage());
			this.javaMailSender.send(mailMessage);
			return "Email send Successfully";
		} catch (Exception e) {
			System.out.println(e);
			return "Email Sending error!";

		}
	}

	public String sendMailwithAttachment(Email email) {
		// MimeMessage
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setFrom("Aman gupta<" + sender + ">");
			mimeMessageHelper.setTo(email.getRecipient());
			mimeMessageHelper.setSubject(email.getSubject());
			mimeMessageHelper.setText(email.getMessage());

			// adding the attechment
			mimeMessageHelper.addAttachment(email.getAttachment().getOriginalFilename(), email.getAttachment());
			// send the mail

			this.javaMailSender.send(mimeMessage);
			return "Mail Sent SuccessFully";

		} catch (Exception e) {
			return "Mail Sending error!";
		}

	}
}
