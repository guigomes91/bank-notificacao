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
import br.com.gomes.banknotificacao.dto.EmailClienteInput;
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

	public void sendEmail(ClienteDTO user, EmailClienteInput emailDTO) throws MessagingException, IOException, TemplateException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
		
		helper.setSubject(emailDTO.getAssunto());
		helper.setTo(user.getEmail());
		String emailContent = getEmailContent(user, emailDTO);
		helper.setText(emailContent, true);
		
		javaMailSender.send(mimeMessage);
	}

	String getEmailContent(ClienteDTO user, EmailClienteInput emailDTO) throws IOException, TemplateException {
		StringWriter stringWriter = new StringWriter();
		Map<String, Object> model = new HashMap<>();
		model.put("user", user.getNome());
		model.put("message", emailDTO.getMensagem());
		model.put("subject", emailDTO.getAssunto());
		
		configuration.getTemplate("email.ftlh").process(model, stringWriter);
		
		return stringWriter.getBuffer().toString();
	}
}
