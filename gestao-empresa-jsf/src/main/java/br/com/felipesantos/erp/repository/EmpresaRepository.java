package br.com.felipesantos.erp.repository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.felipesantos.erp.model.Empresa;

public class EmpresaRepository {
	
	@Inject
	private EntityManager entityManager;
	
	public EmpresaRepository() {
	}

	public EmpresaRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}	
	
	public Empresa findById(Long id) {
		return entityManager.find(Empresa.class, id);
	}
	
	public List<Empresa> buscarPorNomeFantasia(String nomeFantasia) {
		String jpql = "from Empresa where nomeFantasia like :nomeFantasia";
		TypedQuery<Empresa> query = entityManager.createQuery(jpql, Empresa.class);
		query.setParameter("nomeFantasia", nomeFantasia + "%");
		return query.getResultList();
	}
	
	public Empresa save(Empresa empresa) {
		return entityManager.merge(empresa);
	}
	
	public void delete(Empresa empresa) {
		empresa = findById(empresa.getId());
		entityManager.remove(empresa);
	}
}
