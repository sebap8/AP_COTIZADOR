package managers;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import beans.PresupuestoBean;


public class EmailManager extends Thread {


	private JPanel contentPane;
	private String correo =  ""; //A quien le quieres escribir.
    private String asunto = "";
    private String cuerpo = "";
    private File file=null;
    
    public EmailManager(String email,String asunto1,String cuerpo1,File f) {
    	correo=email;
    	asunto=asunto1;
    	cuerpo=cuerpo1;
    	file=f;
    }
	public void enviarConGMail(String destinatario, String asunto, String cuerpo, File f) {
	    // Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el remitente también.
		System.out.println("ENTRO METODO");
	    String remitente = "panelesaislacionespatagonicas@gmail.com";  //Para la dirección nomcuenta@gmail.com
	    String clave = "paneles2020";
	    Properties props = System.getProperties();
	    props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
	    props.put("mail.smtp.user", remitente);
	    props.put("mail.smtp.clave", "paneles2020");    //La clave de la cuenta
	    props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
	    props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
	    props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google
	    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

	    Session session = Session.getDefaultInstance(props);
	    MimeMessage message = new MimeMessage(session);

	    try {
	        message.setFrom(new InternetAddress(remitente));
	        message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario)); //Se podrían añadir varios de la misma manera
	        message.setSubject(asunto);
	        message.setText(cuerpo);
	        
        	//AGREGADO PDF
	        if(file!=null) {
	        	 BodyPart messageBodyPart = new MimeBodyPart();
		         messageBodyPart.setText(cuerpo);
		         Multipart multipart = new MimeMultipart();
		         multipart.addBodyPart(messageBodyPart);
		         messageBodyPart = new MimeBodyPart();
		         String filename = "prueba.pdf";
		         DataSource source = new FileDataSource(file);
		         messageBodyPart.setDataHandler(new DataHandler(source));
		         messageBodyPart.setFileName(filename);
		         multipart.addBodyPart(messageBodyPart);
		         message.setContent(multipart);
	        }
	        
//	         FIN AGREADO PDF
	        Transport transport = session.getTransport("smtp");
	        transport.connect("smtp.gmail.com", remitente, clave);
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();
	    }
	    catch (MessagingException me) {
	        me.printStackTrace();   //Si se produce un error
	    }
	    System.out.println("SALGO METODO");
	}


	@Override
	public void run() {
	    // Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el remitente también.
	    String remitente = "panelesaislacionespatagonicas@gmail.com";  //Para la dirección nomcuenta@gmail.com
	    String clave = "paneles2020";
	    Properties props = System.getProperties();
	    props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
	    props.put("mail.smtp.user", remitente);
	    props.put("mail.smtp.clave", "paneles2020");    //La clave de la cuenta
	    props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
	    props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
	    props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google
	    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

	    Session session = Session.getDefaultInstance(props);
	    MimeMessage message = new MimeMessage(session);

	    try {
	        message.setFrom(new InternetAddress(remitente));
	        message.addRecipient(Message.RecipientType.TO, new InternetAddress(correo)); //Se podrían añadir varios de la misma manera
	        message.setSubject(asunto);
	        message.setText(cuerpo);
	      //AGREGADO PDF
	        if(file!=null) {
	        	 BodyPart messageBodyPart = new MimeBodyPart();
	        	 messageBodyPart.setText(cuerpo);
		         Multipart multipart = new MimeMultipart();
		         multipart.addBodyPart(messageBodyPart);
		         messageBodyPart = new MimeBodyPart();
		         String filename = "prueba.pdf";
		         DataSource source = new FileDataSource(file);
		         messageBodyPart.setDataHandler(new DataHandler(source));
		         messageBodyPart.setFileName(filename);
		         multipart.addBodyPart(messageBodyPart);
		         message.setContent(multipart);
	        }
	        
//	         FIN AGREADO PDF
	        Transport transport = session.getTransport("smtp");
	        transport.connect("smtp.gmail.com", remitente, clave);
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();
	        try {
	            Thread.sleep(10*1000);
	         } catch (Exception e) {
	            System.out.println(e);
	         }
	        file.delete();
	    }
	    catch (MessagingException me) {
	        me.printStackTrace();   //Si se produce un error
	        System.out.println(me.getMessage());
			System.out.println(me.getStackTrace());
	    }
	}


	
}
