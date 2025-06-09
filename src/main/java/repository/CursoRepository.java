package repository;

import entity.Curso;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class CursoRepository extends GenericRepository<Curso> {

    public CursoRepository() {
		super(Curso.class);
	}

		public Curso pesquisarPorNome(String nome){
		Curso curso = null;
		try {
			// Obtém um objeto CriteriaBuilder da sessão Hibernate
			CriteriaBuilder builder = session.getCriteriaBuilder();

			// Cria uma query tipada que vai retornar objetos da classe Curso
			CriteriaQuery<Curso> criteria = builder.createQuery(Curso.class);

			// Define a entidade raiz da consulta — como se fosse o FROM Curso no SQL
			Root<Curso> root = criteria.from(Curso.class);

			// .select(root): indica que queremos retornar os próprios objetos Curso
			// .where(...): aplica um filtro na query, equivalente a WHERE nome = ?.
			criteria
				.select(root)
				.where(builder.equal(root.get("nome"), nome));

			// Compila e executa a query construída
			curso = session.createQuery(criteria).getSingleResultOrNull();
		} catch (Exception e) {
			System.out.println("Ocorreu um problema ao consultar o curso pelo nome: " + e.getMessage());
		}
		return curso;
		}
	}

