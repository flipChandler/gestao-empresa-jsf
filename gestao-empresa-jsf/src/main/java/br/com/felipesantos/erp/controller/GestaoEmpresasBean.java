package br.com.felipesantos.erp.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import br.com.felipesantos.erp.controller.conversor.RamoAtividadeConverter;
import br.com.felipesantos.erp.enums.TipoEmpresa;
import br.com.felipesantos.erp.model.Empresa;
import br.com.felipesantos.erp.model.RamoAtividade;
import br.com.felipesantos.erp.repository.EmpresaRepository;
import br.com.felipesantos.erp.repository.RamoAtividadeRepository;
import br.com.felipesantos.erp.service.CadastroEmpresaService;
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
	
	@Inject
	private CadastroEmpresaService cadastroEmpresaService;
	
	private Converter RamoAtividadeConverter;
	
	private List<Empresa> empresas = new ArrayList<>();
	
	private String termoPesquisa;
	
	private Empresa empresa;
	
	public void prepararNovaEmpresa() {
		empresa = new Empresa();
	}
	
	public void salvar() {
		cadastroEmpresaService.salvar(empresa);
		if (jaHouvePesquisa()) {
			pesquisar();
		} else {
			buscarTodas();
		}
		facesMessages.info("Empresa salva com sucesso!");
		// vai atualizar a dataTable e o messages quando salvar() for invocado
		RequestContext.getCurrentInstance().update(
				Arrays.asList(
						"frm:empresasDataTable", 
						"frm:messages"));
	}
	
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
	
	private boolean jaHouvePesquisa() {
		return termoPesquisa != null && !termoPesquisa.equals("");
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
	
	public Empresa getEmpresa() {
		return empresa;
	}
	
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	public boolean isEmpresaSelecionada() {
		return empresa != null && empresa.getId() != null;
	}
	// opções pra buscar todas as empresas quando iniciar a aplicação
	//  <f:event listener="#{gestaoEmpresasBean.buscarTodas}" type="preRenderView" /> -- dentro de <h:body>
	// ou
	//  <f:metadata>
	//         <f:viewAction action="#{gestaoEmpresasBean.buscarTodas}" /> -- dentro do <h:head>
	//  </f:metadata>	
}
