package br.com.principal.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;

@FacesConverter("conversorDddCelular")
public class ConversorDddCelular implements Converter<String> {

	@Override
	public String getAsObject(FacesContext context, UIComponent component, String dddCelular) {
		if (dddCelular.trim().equals("")) {
			return "";
		} else {
			dddCelular = dddCelular.replace("(", "");
			dddCelular = dddCelular.replace(")", "");
			dddCelular = dddCelular.replace("-", "");
			dddCelular = StringUtils.deleteWhitespace(dddCelular);
			return dddCelular;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, String dddCelular) {
		if (dddCelular.trim().equals("")) {
			return "";
		}

		String dddCelularSemMascara = dddCelular.toString();
		String ddd = dddCelularSemMascara.substring(0, 2);
		String numeroParte1 = dddCelularSemMascara.substring(2, 7);
		String numeroParte2 = dddCelularSemMascara.substring(7, 11);
		return "(" + ddd + ") " + numeroParte1 + "-" + numeroParte2;
	}
}