package br.com.principal.validador;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "validadorSenha")
public class ValidadorSenha implements Validator<String>, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public void validate(FacesContext contexto, UIComponent componente, String senha) throws ValidatorException {
		if (senha.length() < 5) {
			throw new ValidatorException(new FacesMessage());
		}
	}
}