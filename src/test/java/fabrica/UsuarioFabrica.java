package fabrica;

import br.com.principal.constante.StatusUsuarioEnum;
import br.com.principal.constante.TipoUsuarioEnum;
import br.com.principal.entidade.UsuarioEntidade;

public class UsuarioFabrica {
	
	public static UsuarioEntidade criarComCPF() {
		UsuarioEntidade usuarioEntidade = new UsuarioEntidade();
		usuarioEntidade.setCpf(3647651192L);
		usuarioEntidade.setSenha("12345");
		return usuarioEntidade;
	}
	
	public static UsuarioEntidade criarBloqueado() {
		UsuarioEntidade usuarioEntidade = new UsuarioEntidade();
		usuarioEntidade.setCpf(3647651192L);
		usuarioEntidade.setSenha("12345");
		usuarioEntidade.setStatus(StatusUsuarioEnum.BLOQUEADO.getDescricao());
		return usuarioEntidade;
	}
	
	public static UsuarioEntidade criarAgente() {
		UsuarioEntidade usuarioEntidade = new UsuarioEntidade();
		usuarioEntidade.setCpf(3647651192L);
		usuarioEntidade.setSenha("12345");
		usuarioEntidade.setTipo(TipoUsuarioEnum.AGENTE.getDescricao());
		return usuarioEntidade;
	}
	
	public static UsuarioEntidade criarAdministrador() {
		UsuarioEntidade usuarioEntidade = new UsuarioEntidade();
		usuarioEntidade.setCpf(3647651192L);
		usuarioEntidade.setSenha("12345");
		usuarioEntidade.setTipo(TipoUsuarioEnum.ADMINISTRADOR.getDescricao());
		return usuarioEntidade;
	}
}
