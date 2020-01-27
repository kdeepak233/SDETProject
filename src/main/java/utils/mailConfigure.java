package utils;

import java.util.Properties;
import java.util.Scanner;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class mailConfigure{
    public static void autotrigger(){

        final String username = "swam1042973@gmail.com";
        final String password="Deep@7167" ;
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        
        // Properties props = new Properties();
//            props.put("mail.smtp.socketFactory.port", "587");
//            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//            props.put("mail.smtp.socketFactory.fallback", "true");
		/*
		 * props.put("mail.smtp.host", "smtp-mail.outlook.com");
		 * props.put("mail.smtp.port", "587");
		 * props.put("mail.smtp.starttls.enable","true"); props.put("mail.smtp.auth",
		 * "true");
		 */  
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try 
        {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("kdeepak233@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("kdeepak233@gmail.com"));
            message.setSubject("AUTOMATION REPORT");
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Hi\n\n This is a 201 Automation Report \n\n\n\nThanks,\nK.Deepak");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart();
            String filename = System.getProperty("user.dir")+"/ExtentReport/extent.html";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } 
        catch (MessagingException e) 
        {
            throw new RuntimeException(e);
        }
    }
}