package br.com.felipesantos.erp.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.felipesantos.erp.model.Empresa;
import br.com.felipesantos.erp.repository.EmpresaRepository;

@Named
@ViewScoped
public class GestaoEmpresasBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EmpresaRepository empresaRepository;
		
	private List<Empresa> empresas = new ArrayList<>();
	
	public void buscarTodas() {
		this.empresas = empresaRepository.findAll();
	}
	
	public List<Empresa> getEmpresas() {
		return empresas;
	}
	
	// opções pra buscar todas as empresas quando iniciar a aplicação
	//  <f:event listener="#{gestaoEmpresasBean.buscarTodas}" type="preRenderView" /> -- dentro de <h:body>
	// ou
	//  <f:metadata>
	//         <f:viewAction action="#{gestaoEmpresasBean.buscarTodas}" /> -- dentro do <h:head>
	//  </f:metadata>
}
