package fabrica;

import br.com.principal.constante.StatusReclamacaoSugestaoEnum;
import br.com.principal.entidade.ReclamacaoSugestaoEntidade;
import br.com.principal.tela.util.TelaUtil;

public class ReclamacaoSugestaoFabrica {
	
	public static ReclamacaoSugestaoEntidade criarComStatusAberta() {
		ReclamacaoSugestaoEntidade reclamacaoSugestaoEntidade = new ReclamacaoSugestaoEntidade();
		reclamacaoSugestaoEntidade.setStatus(StatusReclamacaoSugestaoEnum.ABERTA.getDescricao());
		return reclamacaoSugestaoEntidade;
	}
	
	public static ReclamacaoSugestaoEntidade criarComCadastroHoje() {
		ReclamacaoSugestaoEntidade reclamacaoSugestaoEntidade = new ReclamacaoSugestaoEntidade();
		reclamacaoSugestaoEntidade.setDataCriacao(TelaUtil.diaAtualEmFormatoDiaMesAno());
		return reclamacaoSugestaoEntidade;
	}
}
