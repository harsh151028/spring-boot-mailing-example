package com.harsh.mailDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;


@SpringBootApplication
public class MailDemoApplication implements CommandLineRunner {

	@Autowired
	private JavaMailSender javaMailSender;

	public static void main(String[] args) {
		SpringApplication.run(MailDemoApplication.class, args);
	}

	@Override
	public void run(String... args) {

		System.out.println("Sending Email...");

		try {

			sendEmail();
			sendEmailWithAttachment();

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Done");

	}
	public  void sendEmail() {

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo("harsh@gmail.com");
		msg.setSubject("Testing from Spring Boot");
		msg.setText("Hello World \n Spring Boot Email");

		javaMailSender.send(msg);

	}
	void sendEmailWithAttachment() throws MessagingException, IOException {

		MimeMessage msg = javaMailSender.createMimeMessage();

		// true = multipart message
		MimeMessageHelper helper = new MimeMessageHelper(msg, true);
		helper.setTo("harsh@gmail.com");

		helper.setSubject("Testing from Spring Boot java application by harsh dwevedi ");

		//default = text/plain
		//helper.setText("Check attachment for image!");

		// true = text/html
		helper.setText("<h1>Check attachment for image!</h1>", true);

		helper.addAttachment("hd.png",new File("/home/harsh/IdeaProjects/mailDemo/src/main/resources/phd.jpg"));

		javaMailSender.send(msg);

	}

}
