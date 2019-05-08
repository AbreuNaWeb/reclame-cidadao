package br.com.principal.testes;

import br.com.principal.modelo.Notificacao;
import br.com.principal.persistencia.NotificacaoDAO;

public class NotificaoTeste {

	public static void main(String[] args) {
		Notificacao notificacao = new Notificacao();
		notificacao.setNotificacao("TESTE");
		notificacao.setStatus((byte) 1);
		new NotificacaoDAO().salvar(notificacao);
	}
}
