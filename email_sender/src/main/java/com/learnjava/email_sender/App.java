package com.learnjava.email_sender;

import java.util.*;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;





public class App 

{

    public static void main( String[] args )

    {

        System.out.println( "Preparing to send msg...." );

        String to= "gayatrishinde024@gmail.com";

        String from ="nishantkumarsinghnks14@gmail.com";
        String cc= "nishant.singh@nesswadiacollege.edu.in";

        String bcc ="testmailforstorage1@gmail.com";

        String message= "Hello, this is my test msg";

        String subject = "Testing my email functionality";

        

        sendEmail(to, from, bcc,cc, message, subject);

    }



	private static void sendEmail(String to, String from, String bcc, String cc, String message, String subject) {

		//variable for email

		String host= "smtp.gmail.com";

		Properties prop =System.getProperties();

		System.out.println( "Properties are:"+ prop);

		prop.put("mail.smtp.host",host);

		prop.put("mail.smtp.port","465");

		prop.put("mail.smtp.ssl.enable","true");

		prop.put("mail.smtp.auth","true");

		//Step. 1 get session object

		Session session=Session.getInstance(prop, new Authenticator(){

			

		protected PasswordAuthentication getPasswordAuthentication() {

			

			return new PasswordAuthentication("nishantkumarsinghnks14@gmail.com","vrutvtygdlqxesut");

		}

			

		});

		session.setDebug(true);

		// step 2: compose the message

		MimeMessage m= new MimeMessage(session);

		

		try {

		m.setFrom(from);

		m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		m.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
		m.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc));

		m.setSubject(subject);

		m.setText(message);

		//send attachment 

		 MimeBodyPart mb = new MimeBodyPart();
		 String filename = "C:/Users/nisha/Downloads/G/gayatri.jpg";//change accordingly  
		 FileDataSource source = new FileDataSource(filename);  
		 mb.setDataHandler(new DataHandler(source));  
		 mb.setFileName(filename);
		 Multipart multipart = new MimeMultipart();  
		 multipart.addBodyPart(mb);
		 m.setContent(multipart);

		//step 3. send message using transport class

		Transport.send(m);

		System.out.println("Sent success");

		}

		catch(Exception e) {

			e.printStackTrace();

		}

	}

}