package project;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

class SendMail {
	static String user;
	static char[] pwd;
	ErrorMessage err;

	public void send(String to, String sub, String msg, String attachmentPath) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		String pwd1 = String.valueOf(pwd);
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, pwd1);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(sub);

			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(msg);

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			if (attachmentPath != null && !attachmentPath.isEmpty()) {
				MimeBodyPart attachmentBodyPart = new MimeBodyPart();
				DataSource source = new FileDataSource(attachmentPath);
				attachmentBodyPart.setDataHandler(new DataHandler(source));
				attachmentBodyPart.setFileName(new File(attachmentPath).getName());
				multipart.addBodyPart(attachmentBodyPart);
			}

			message.setContent(multipart);

			Transport.send(message);

			new ShowMessage();
		} catch (MessagingException e) {
			err = new ErrorMessage(e.toString());
		}
	}

	public void recieveCred(UserCredentials u) {
		user = u.getUserName();
		pwd = u.getPwd();
	}
}
