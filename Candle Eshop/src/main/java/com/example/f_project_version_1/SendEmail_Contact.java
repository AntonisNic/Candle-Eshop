package com.example.f_project_version_1;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author MariaTerzi
 *
 */

public class SendEmail_Contact {
    static MimeMessage message;
    static Properties mailServerProperties;
    static Session mailSession;

    public static void main(String args[]) throws AddressException, MessagingException {
        /*//Boolean sent = createandSendMail();
        if(sent) {
            System.out.println("\n => Your Java Program has just sent an email!");
        }else {
            System.out.println("\n => Something went wrong!");
        }*/
    }

    public static boolean createandSendMail(String emailg,String subjectg,String messageg) throws AddressException, MessagingException {

        // Step1 setup properties
        System.out.println("\n 1st ===> setup Mail Server Properties..");
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        System.out.println("Mail Server Properties have been setup successfully..");

        // Step2 get session
        System.out.println("\n\n 2nd ===> get Mail Session..");
        mailSession = Session.getDefaultInstance(mailServerProperties, null);

        // Step2 create the message
        message = new MimeMessage(mailSession);
        message.addRecipient(Message.RecipientType.TO, new InternetAddress("ece318web@gmail.com"));

        message.addRecipient(Message.RecipientType.CC, new InternetAddress(emailg));
        //message.setSubject("Greetings from ECE318..");
        message.setSubject(subjectg);
        //String emailBody = "This is a test email created by ECE318 JAVAMAIL  API example. " + "<br><br> Kind Regards, <br>ECE318 Lecturer";
        String emailBody =messageg;
        message.setContent(emailBody, "text/html");
        System.out.println("Mail Session has been created successfully..");

        // Step3 get session and send email
        System.out.println("\n\n 3rd ===> Get Session and Send mail");
        Transport transport = mailSession.getTransport("smtp");
        // Enter your  gmail UserID and Password
        // if you have 2FA enabled then provide App Specific Password
        // make sure to Less secure apps is TURNED ONhttps://www.google.com/settings/security/lesssecureapps
        //Allow  app to send email from https://accounts.google.com/b/0/DisplayUnlockCaptchaand click on Continue.
        transport.connect("smtp.gmail.com", "ece318web@gmail.com", "Ece318web!!");
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();


        return true;
    }
}