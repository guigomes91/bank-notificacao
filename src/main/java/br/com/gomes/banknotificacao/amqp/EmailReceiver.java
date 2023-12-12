package br.com.gomes.banknotificacao.amqp;

import java.io.IOException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.gomes.banknotificacao.dto.ClienteDTO;
import br.com.gomes.banknotificacao.dto.EmailClienteInput;
import br.com.gomes.banknotificacao.service.EmailService;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EmailReceiver {

	@Autowired
	private EmailService emailService;

	@RabbitListener(queues = "notifications")
	public void receiveEmail(EmailClienteInput email) {
		log.info("Received {} and send email client!", email.getEmail());

		try {
			emailService.sendEmail(new ClienteDTO("", email.getEmail()));
		} catch (MessagingException | IOException | TemplateException e) {
			e.printStackTrace();
		}		
	}
}
