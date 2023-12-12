package br.com.gomes.banknotificacao.service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.com.gomes.banknotificacao.dto.ClienteDTO;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

	@Autowired
	private Configuration configuration;

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendEmail(ClienteDTO user) throws MessagingException, IOException, TemplateException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
		
		helper.setSubject("Bem vindo ao Gomes Bank");
		helper.setTo(user.getEmail());
		String emailContent = getEmailContent(user);
		helper.setText(emailContent, true);
		
		javaMailSender.send(mimeMessage);
	}

	String getEmailContent(ClienteDTO user) throws IOException, TemplateException {
		StringWriter stringWriter = new StringWriter();
		Map<String, Object> model = new HashMap<>();
		model.put("user", user);
		configuration.getTemplate("email.ftlh").process(model, stringWriter);
		return stringWriter.getBuffer().toString();
	}
}
