package com.core.backend.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.core.backend.service.UserService;
import com.core.backend.util.ResponseHandler;

import com.core.backend.model.MessageEnum;

@RestController
@RequestMapping("/mail")
public class EmailController {

	@Autowired
	public UserService userService;

	@PostMapping("/send")	
	public @ResponseBody ResponseEntity<?> getDateNow() {
		
		Date dateNow = null;
		String dateString = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM", new Locale("ES", "MX"));
				
		try {
			
			dateNow = this.userService.getDateNow();			
			dateString = sdf.format(dateNow);	
			String asunto = "Archivo excel enviado en la fecha " + dateString ;
			Properties properties = System.getProperties();			
			System.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
			System.setProperty("mail.pop3s.ssl.protocols", "TLSv1.2");

			List<String> listRecipients = new ArrayList<String>();	
			listRecipients.add("elialvarezromero11@gmail.com");

			properties.put("mail.smtp.host", 					"smtp.gmail.com");
			properties.put("mail.smtp.port", 					"587");
			properties.put("mail.smtp.auth", 					"true");
			properties.put("mail.smtp.starttls.enable", 		"true");
			properties.put("mail.smtp.auth", 					"true");
			properties.put("mail.smtp.socketFactory.fallback", 	"true");
						
			Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("elialvarezromero11@gmail.com", "#### #### #### ####");
				}
			});
			
			session.setDebug(true);
			
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("google-noreply@google.com"));
			for (String recipient : listRecipients) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			}
			
			message.setSubject(asunto, "utf-8");
			BodyPart texto = new MimeBodyPart();
			texto.setText("Estimados,\n" + "este es un mensaje de prueba para enviar un archivo Excel.");
					
			FileDataSource fileDataSource = new FileDataSource("C:\\Users\\Eli\\Desktop\\file_example_XLSX_10.xlsx");
			
			BodyPart adjunto = new MimeBodyPart();			
			adjunto.setDataHandler(new DataHandler(fileDataSource));
			adjunto.setFileName("file_example_XLSX_10.xlsx");
			
			MimeMultipart multiParte = new MimeMultipart();
			multiParte.addBodyPart(texto);
			multiParte.addBodyPart(adjunto);
			
			message.setContent(multiParte);
			System.out.println("Enviando correo.");
			Transport.send(message);
			System.out.println("Envio exitoso.");

		} catch (MessagingException me) {
			me.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return ResponseHandler.generateResponseSuccess(MessageEnum.GET_DATE_OK.getMessage(), HttpStatus.OK, dateNow);
		
	}

	
}
