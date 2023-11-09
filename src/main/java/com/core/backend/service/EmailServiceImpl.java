package com.core.backend.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.core.backend.repository.EmailRepository;
import com.core.backend.util.ServiceUtils;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired private EmailRepository emailRepository;
	
	@Autowired private Environment env;
	
    public String mailSmtpSslProtocols() {
        return env.getProperty("mail.smtp.ssl.protocols");
    }

    public String mailPop3sSslProtocols() {
        return env.getProperty("mail.pop3s.ssl.protocols");
    }
    
    public String listRecipients() {
        return env.getProperty("mail.list.recipients");
    }
    
    public String mailSmtpHost() {
        return env.getProperty("mail.smtp.host");
    }
    
    public String mailSmtpPort() {
        return env.getProperty("mail.smtp.port");
    }
    
    public String mailSmtpAuth() {
        return env.getProperty("mail.smtp.auth");
    }
    
    public String mailSmtpStarttlsEnable() {
        return env.getProperty("mail.smtp.starttls.enable");
    }
    
    public String mailSmtpSocketFactoryFallback() {
        return env.getProperty("mail.smtp.socket.factory.fallback");
    }
    
    public String mailAppGmailAccount() {
        return env.getProperty("mail.app.gmail.account");
    }
    
    public String mailAppGmailPassword() {
        return env.getProperty("mail.app.gmail.password");
    }

    public String mailAppGmailNoReplay() {
        return env.getProperty("mail.app.gmail.no.replay");
    }
    
	@Override
	public boolean sendEmailXlsx(String asunto, String mensaje, MultipartFile file) {

		boolean send = false;
		
		try {
			
			System.out.println(asunto);
			System.out.println(mensaje);
			System.out.println(file.getSize());
			
			Date dateNow = null;
			String dateString = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM", new Locale("ES", "MX"));				
			dateNow = this.emailRepository.getDateNow();			
			dateString = sdf.format(dateNow);	
			
			Properties properties = System.getProperties();			
			System.setProperty("mail.smtp.ssl.protocols", mailSmtpSslProtocols());
			System.setProperty("mail.pop3s.ssl.protocols", mailPop3sSslProtocols());
	
			List<String> listRecipients = new ArrayList<String>();	
			listRecipients.add(listRecipients());
	
			properties.put("mail.smtp.host", 					mailSmtpHost());
			properties.put("mail.smtp.port", 					mailSmtpPort());
			properties.put("mail.smtp.auth", 					mailSmtpAuth());
			properties.put("mail.smtp.starttls.enable", 		mailSmtpStarttlsEnable());
			properties.put("mail.smtp.socketFactory.fallback", 	mailSmtpSocketFactoryFallback());
						
			Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(mailAppGmailAccount(), mailAppGmailPassword());
				}
			});
			
			session.setDebug(true);
			
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(mailAppGmailNoReplay()));
			for (String recipient : listRecipients) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			}
			
			message.setSubject(asunto, "utf-8");
			BodyPart texto = new MimeBodyPart();
			texto.setText(mensaje + " Fecha " + dateString);
					
			
			InputStream is = file.getInputStream();
			byte[] xlsBytes = ServiceUtils.readFileFromStream(is);
			ByteArrayDataSource bads = new ByteArrayDataSource(xlsBytes, "application/vnd.ms-excel");
			String fileName = file.getOriginalFilename()+".xlsx";
			
			BodyPart adjunto = new MimeBodyPart();
			adjunto.setDataHandler(new DataHandler(bads));
			adjunto.setFileName(fileName);
			
			MimeMultipart multiParte = new MimeMultipart();
			multiParte.addBodyPart(texto);
			multiParte.addBodyPart(adjunto);			
			message.setContent(multiParte);
			Transport.send(message);
			send = true;
			
		} catch (MessagingException | IOException me) {
			me.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return send;
		
	}
	
}
