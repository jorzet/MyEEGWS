/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package email;

import businessRules.Palabras;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.catalina.User;

/**
 *
 * @author Jorge
 */
public class GoogleMail {
    
    public void init(){
        
    }
    
    public static int sendConfirmationCode(String to){
        int result;
        final String username = "proyecto.terminal.myeeg@gmail.com";
        final String password = "proyectoterminal2";
        
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(to));
            message.setSubject(Palabras.EMAIL_RESTART_PASSWORD_SUBJECT);
            message.setText(Palabras.YOUR_CONFIRMATION_DODE_IS
                + Code.generateConfirmationCode());

            Transport.send(message);

            System.out.println("Done");
            return 0;
        } catch (MessagingException e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    public static int sendEmailAndPassword(String emailUser, String passwordUser, boolean isRestore){
        int result;
        final String username = "proyecto.terminal.myeeg@gmail.com";
        final String password = "proyectoterminal2";
        
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(emailUser));
            if(isRestore)
                message.setSubject(Palabras.EMAIL_RESTART_PASSWORD_SUBJECT);
            else
                message.setSubject(Palabras.EMAIL_SINGUP_SUBJECT);
            
            message.setText(Palabras.YOUR_EMAIL_IS + emailUser + "\n\n"
                    + Palabras.YOUR_PASSWORD_IS + passwordUser);

            Transport.send(message);

            System.out.println("Done");
            return 0;
        } catch (MessagingException e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    
}
