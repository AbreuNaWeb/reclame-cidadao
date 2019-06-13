package fabrica;

import br.com.principal.entidade.UsuarioEntidade;

public class UsuarioFabrica {
	
	public static UsuarioEntidade criarComCPF() {
		UsuarioEntidade usuarioEntidade = new UsuarioEntidade();
		usuarioEntidade.setCpf(3647651192L);
		return usuarioEntidade;
	}
}
