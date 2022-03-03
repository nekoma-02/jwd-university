package by.epam.university.service.mail;

import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.university.entity.User;
import by.epam.university.service.exception.ServiceException;

public class MailSender {

	private static Logger logger = LogManager.getLogger();
	
	private static MailSender instance;
	private final static String FROM = "jwd.university@gmail.com";
	private final static String PASSWORD = "12345sasha";
	private Properties props;

	public static MailSender getInstance() {
		if (instance == null) {
			instance = new MailSender();
		}
		return instance;
	}

	private MailSender() {
		ResourceBundle bundle = ResourceBundle.getBundle("mail");
		props = System.getProperties();
		props.put(MailParameter.MAIL_AUTH, bundle.getString(MailParameter.MAIL_AUTH));
		props.put(MailParameter.MAIL_HOST, bundle.getString(MailParameter.MAIL_HOST));
		props.put(MailParameter.MAIL_PORT, bundle.getString(MailParameter.MAIL_PORT));
		props.put(MailParameter.MAIL_SOCKETFACTORY, bundle.getString(MailParameter.MAIL_SOCKETFACTORY));
		props.put(MailParameter.MAIL_STARTTLS, bundle.getString(MailParameter.MAIL_STARTTLS));
	}

	public void sendMail(User user, String messageText) throws ServiceException {
		String to = user.getEmail();

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(FROM, PASSWORD);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(FROM));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setText(messageText);
			Transport.send(message);

		} catch (MessagingException e) {
			logger.log(Level.ERROR, e);
			throw new ServiceException("Exception during mail sending", e);
		}
	}

}
