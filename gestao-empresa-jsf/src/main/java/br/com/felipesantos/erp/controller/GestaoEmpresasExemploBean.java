package br.com.felipesantos.erp.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.felipesantos.erp.enums.TipoEmpresa;
import br.com.felipesantos.erp.model.Empresa;

@Named
@ViewScoped
public class GestaoEmpresasExemploBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{empresa}")
	private Empresa empresa = new Empresa();
	
	public Empresa getEmpresa() {
		return empresa;
	}	
	
	public TipoEmpresa[] getTiposEmpresa() {
		return TipoEmpresa.values();
	}
	
	public void salvar() {
		System.out.println(
				"Razao Social: " +  empresa.getRazaoSocial()
				+ "\nNome Fantasia: " + empresa.getNomeFantasia()
				+ "\nTipo: " + empresa.getTipoEmpresa());
	}
	
	public String ajudaGestaoEmpresas() {
		// exibe a url ajuda-gestao-empresas.xhtml | se omitir, mesmo navegando pra ajuda, não altera a url, permanecendo index.xhtml
		return "ajuda-gestao-empresas?faces-redirect=true"; 
	}
}
