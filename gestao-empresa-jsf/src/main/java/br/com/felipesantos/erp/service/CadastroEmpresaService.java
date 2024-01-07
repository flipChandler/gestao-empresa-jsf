package br.com.felipesantos.erp.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.felipesantos.erp.model.Empresa;
import br.com.felipesantos.erp.repository.EmpresaRepository;
import br.com.felipesantos.erp.util.Transacional;

public class CadastroEmpresaService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EmpresaRepository empresaRepository;
	
	@Transacional
	public void salvar(Empresa empresa) {
		empresaRepository.save(empresa);
	}
	
	@Transacional
	public void excluir(Empresa empresa) {
		empresaRepository.delete(empresa);
	}
}
