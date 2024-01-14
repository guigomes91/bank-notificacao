package br.com.gomes.banknotificacao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailClienteInput {

	private String nome;
	private String email;
	private String assunto;
	private String mensagem;
}
