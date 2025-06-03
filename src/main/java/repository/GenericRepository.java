package repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import util.HibernateUtil;

public class GenericRepository<T> {

	protected Session session;
	private final Class<T> entityClass;

	public GenericRepository(Class<T> entityClass) {
		this.entityClass = entityClass;
		session = HibernateUtil.getSession();
	}

	public void inserir(T objeto) {
		try {
			session.beginTransaction();
			session.persist(objeto);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("Ocorreu um problema ao inserir o objeto (" + entityClass + "): " + e.getMessage());
		}
	}

	public void atualizar(T objeto) {
		try {
			session.beginTransaction();
			session.merge(objeto);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("Ocorreu um problema ao atualizar o objeto (" + entityClass + "): " + e.getMessage());
		}
	}

	public void remover(T objeto) {
		try {
			session.beginTransaction();
			session.remove(objeto);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("Ocorreu um problema ao remover o objeto (" + entityClass + "): " + e.getMessage());
		}
	}

	public List<T> pesquisarTodos() {
		List<T> registros = new ArrayList<>();
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<T> criteria = builder.createQuery(entityClass);
			criteria.from(entityClass);
			registros = session.createQuery(criteria).getResultList();
		} catch (Exception e) {
			System.out.println(
					"Ocorreu um problema ao consultar todos os registros (" + entityClass + "): " + e.getMessage());
		}
		return registros;
	}

	public T pesquisaPeloId(long id) {
		T objeto = null;
		try {
			objeto = session.find(entityClass, id);
		} catch (Exception e) {
			System.out.println(
					"Ocorreu um problema ao consultar o objeto pelo Id (" + entityClass + "): " + e.getMessage());
		}
		return objeto;
	}
}