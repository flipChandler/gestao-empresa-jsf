package br.com.felipesantos.erp.repository;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;

import br.com.felipesantos.erp.enums.TipoEmpresa;
import br.com.felipesantos.erp.model.Empresa;
import br.com.felipesantos.erp.model.RamoAtividade;

public class CamadaPersistenciaPosCdi {
	
	@Inject
	private static RamoAtividadeRepository ramoAtividadeRepository;
	
	@Inject
	private static EmpresaRepository empresaRepository;
	
	public static void main(String[] args) {
		
		
		RamoAtividade ramoAtividade = new RamoAtividade();
		ramoAtividade.setDescricao("INFORMÁTICA");
		ramoAtividadeRepository.save(ramoAtividade);
		
		List<RamoAtividade> listaRamoAtividades = ramoAtividadeRepository.buscarPorDescricao("");
		List<Empresa> listaEmpresas = empresaRepository.buscarPorNomeFantasia("");
		System.out.println(listaEmpresas);
		
		Empresa empresa = new Empresa();
		empresa.setNomeFantasia("John Doe");
		empresa.setCnpj("42.952.300/0001-45");
		empresa.setRazaoSocial("John Doe MEI");
		empresa.setTipoEmpresa(TipoEmpresa.MEI);
		empresa.setDataFundacao(LocalDate.now());
		empresa.setRamoAtividade(listaRamoAtividades.get(0));
		
		empresaRepository.save(empresa);		
		
		// CHECK SE A INSERÇÃO FUNCIONOU
		List<Empresa> listaEmpresas2 = empresaRepository.buscarPorNomeFantasia("");
		System.out.println(listaEmpresas2);
	}
}
