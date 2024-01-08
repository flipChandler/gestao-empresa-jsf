package br.com.felipesantos.erp.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.felipesantos.erp.controller.conversor.RamoAtividadeConverter;
import br.com.felipesantos.erp.enums.TipoEmpresa;
import br.com.felipesantos.erp.model.Empresa;
import br.com.felipesantos.erp.model.RamoAtividade;
import br.com.felipesantos.erp.repository.EmpresaRepository;
import br.com.felipesantos.erp.repository.RamoAtividadeRepository;
import br.com.felipesantos.erp.util.FacesMessages;

@Named
@ViewScoped
public class GestaoEmpresasBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EmpresaRepository empresaRepository;
	
	@Inject
	private RamoAtividadeRepository ramoAtividadeRepository;
	@Inject
	private FacesMessages facesMessages;
	
	private Converter RamoAtividadeConverter;
	
	private List<Empresa> empresas = new ArrayList<>();
	
	private String termoPesquisa;
	
	public void buscarTodas() {
		this.empresas = empresaRepository.findAll();
	}
	
	public void pesquisar() {
		empresas = empresaRepository.buscarPorNomeFantasia(termoPesquisa);
		if (empresas.isEmpty()) {
			facesMessages.info("Sua consulta não retornou registros!");
		}
	}
	
	public List<RamoAtividade> completarRamoAtividade(String descricao) {
		List<RamoAtividade> ramoAtividades = ramoAtividadeRepository.buscarPorDescricao(descricao);
		RamoAtividadeConverter = new RamoAtividadeConverter(ramoAtividades);
		return ramoAtividades;
	}
	
	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public String getTermoPesquisa() {
		return termoPesquisa;
	}

	public void setTermoPesquisa(String termoPesquisa) {
		this.termoPesquisa = termoPesquisa;
	}
	
	public TipoEmpresa[] getTiposEmpresa() {
		return TipoEmpresa.values();
	}
	
	public Converter getRamoAtividadeConverter() {
		return RamoAtividadeConverter;
	}
	// opções pra buscar todas as empresas quando iniciar a aplicação
	//  <f:event listener="#{gestaoEmpresasBean.buscarTodas}" type="preRenderView" /> -- dentro de <h:body>
	// ou
	//  <f:metadata>
	//         <f:viewAction action="#{gestaoEmpresasBean.buscarTodas}" /> -- dentro do <h:head>
	//  </f:metadata>	
}
