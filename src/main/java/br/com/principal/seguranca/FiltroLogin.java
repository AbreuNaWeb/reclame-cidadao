package br.com.principal.seguranca;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.principal.entidade.UsuarioEntidade;

public class FiltroLogin implements Filter {

	@Override
	public void destroy() {
		//Sem ação
	}

	public void doFilter(ServletRequest requisicao, ServletResponse resposta, FilterChain filtro) throws IOException, ServletException {
		UsuarioEntidade usuario = (UsuarioEntidade) ((HttpServletRequest) requisicao).getSession().getAttribute("usuarioLogado");

		if (usuario == null) {
			String contextPath = ((HttpServletRequest) requisicao).getContextPath();
			((HttpServletResponse) resposta).sendRedirect(contextPath + "/login.xhtml");
		} else {
			filtro.doFilter(requisicao, resposta);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		//Sem ação
	}
}