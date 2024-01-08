package br.com.felipesantos.erp.controller.conversor;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.felipesantos.erp.model.RamoAtividade;

public class RamoAtividadeConverter implements Converter {
		
	private List<RamoAtividade> ramoAtividades;	

	public RamoAtividadeConverter(List<RamoAtividade> ramoAtividades) {
		this.ramoAtividades = ramoAtividades;
	}

	// na submissão do cadastro da empresa
	// de string de ramoAtividade, para o objeto de ramoAtividade
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null) {
			return null;
		}
		Long id = Long.valueOf(value);
		for (RamoAtividade ramoAtividade : ramoAtividades) {
			if (id.equals(ramoAtividade.getId())) {
				return ramoAtividade;
			}
		}
		return null;
	}
	
	// de: objeto ramoAtividade, para: string que representa o ramoAtividade 
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return null;
		}
		RamoAtividade ramoAtividade = (RamoAtividade) value;
		return ramoAtividade.getId().toString();
	}
}
