package br.com.principal.validador;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.caelum.stella.validation.CPFValidator;

@FacesValidator(value = "validadorCpf")
public class ValidadorCpf implements Validator<Long>, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public void validate(FacesContext contexto, UIComponent componente, Long cpf) throws ValidatorException {
		try {
			new CPFValidator().assertValid(cpf.toString());
		} catch (Exception excecao) {
			throw new ValidatorException(new FacesMessage());
		}
	}
}