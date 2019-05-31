package br.com.principal.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("conversorCpf")
public class ConversorCpf implements Converter<Long> {

	@Override
	public Long getAsObject(FacesContext context, UIComponent component, String cpf) {
		if (cpf.trim().equals("")) {
            return null;
        } else {
           cpf = cpf.replace("-", "");
           cpf = cpf.replace(".", "");
           return new Long(cpf);
       }
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Long value) {
		return value.toString();
	}
}