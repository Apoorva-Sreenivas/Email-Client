package project;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;    
import javax.mail.internet.*;    
class SendMail{  
	static String user;
	static char[] pwd;
	ErrorMessage err;
//    public  void send(String to,String sub,String msg){  
//          //Get properties object    
////    	System.out.println("Called");
//          Properties props = new Properties();    
//          props.put("mail.smtp.host", "smtp.gmail.com");    
//          props.put("mail.smtp.socketFactory.port", "465");    
//          props.put("mail.smtp.socketFactory.class",    
//                    "javax.net.ssl.SSLSocketFactory");    
//          props.put("mail.smtp.auth", "true");    
//          props.put("mail.smtp.port", "465");    
//          //get Session   
//          String pwd1 = String.valueOf(pwd);
//          Session session = Session.getInstance(props,    
//           new javax.mail.Authenticator() {    
//           protected PasswordAuthentication getPasswordAuthentication() {    
//           return new PasswordAuthentication(user,pwd1);  
//           }    
//          });    
//          //compose message    
//          try {    
//           MimeMessage message = new MimeMessage(session);    
//           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
//           message.setSubject(sub);    
//           message.setText(msg);    
//           //send message  
//           Transport.send(message);    
//           //System.out.println("message sent successfully"); 
//           new ShowMessage();
//          } catch (MessagingException e) {
//        	  
//        	  //throw new RuntimeException(e);
//        	  err = new ErrorMessage(e.toString());
//        	  }    
//             
//    }  
    public void send(String to, String sub, String msg, String attachmentPath) {
        // Get properties object
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        // Get Session
        String pwd1 = String.valueOf(pwd);
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pwd1);
            }
        });
        // Compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(sub);

            // Create the message part with the content
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(msg);

            // Create a multipart message
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // Add attachment, if available
            if (attachmentPath != null && !attachmentPath.isEmpty()) {
                MimeBodyPart attachmentBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(attachmentPath);
                attachmentBodyPart.setDataHandler(new DataHandler(source));
                attachmentBodyPart.setFileName(new File(attachmentPath).getName());
                multipart.addBodyPart(attachmentBodyPart);
            }

            // Set the complete message parts
            message.setContent(multipart);

            // Send message
            Transport.send(message);
            // System.out.println("message sent successfully");
            new ShowMessage();
        } catch (MessagingException e) {
            err = new ErrorMessage(e.toString());
        }
    }

    public void recieveCred(UserCredentials u)
	{
		user = u.getUserName();
		pwd= u.getPwd();
	}
}  
//public class SendMail{    
// public static void main(String[] args) {    
//     //from,password,to,subject,message  
//     Mailer.send("apoorvasreenivas.cse.22@gmail.com","tyswncocuxprkgaa","apoorvasreeni28@gmail.com","hello javatpoint","How r u?");  
//     //change from, password and to  
// }    
//}   