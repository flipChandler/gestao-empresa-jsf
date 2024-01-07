package br.com.felipesantos.erp.util;

import java.io.Serializable;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Interceptor
@Transacional
@Priority(Interceptor.Priority.APPLICATION) // ativar o interceptador | o CDI estará gereciando esse interceptor
public class TransacionalInterceptor implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager entityManager;

	// dessa forma, não precisamos abrir e fechar transações nos services ou repositories
	@AroundInvoke
	public Object invoke(InvocationContext context) throws Exception {
		EntityTransaction transaction = entityManager.getTransaction();
		boolean criador = false;
		
		try {
			if (!transaction.isActive()) {
				// truque para fazer rollback no que já passou
				// senão, um futuro commit confirmaria atpe mesmo operações sem transação
				transaction.begin();
				transaction.rollback();
				
				transaction.begin();
				criador = true;	// interceptor que criou essa transação
			}
			return context.proceed();
		} catch(Exception e) {
			if (transaction != null && criador) {
				transaction.rollback();
			}
			throw e;
		} finally {
			if (transaction != null && transaction.isActive() && criador) {
				transaction.commit();	// esse commit será efetuado somente senão houve uma exception, aí seria !transaction.isActive()
			}
		}
	}
}
