package br.com.felipesantos.erp.repository;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.felipesantos.erp.enums.TipoEmpresa;
import br.com.felipesantos.erp.model.Empresa;
import br.com.felipesantos.erp.model.RamoAtividade;

public class CamadaPersistencia {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("AlgaWorksPU");
		
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		RamoAtividadeRepository ramoAtividadeRepository = new RamoAtividadeRepository(em);
		EmpresaRepository empresaRepository = new EmpresaRepository(em);
		
//		RamoAtividade ramoAtividade = new RamoAtividade();
//		ramoAtividade.setDescricao("INFORMÁTICA");
//		ramoAtividadeRepository.save(ramoAtividade);
//		em.getTransaction().commit();
		
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
		
		em.getTransaction().commit();
		
		// CHECK SE A INSERÇÃO FUNCIONOU
		List<Empresa> listaEmpresas2 = empresaRepository.buscarPorNomeFantasia("");
		System.out.println(listaEmpresas2);
		
		em.close();
		emf.close();
	}
}
