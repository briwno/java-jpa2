package repository;

import entity.Aluno;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class AlunoRepository extends GenericRepository<Aluno> {
	
    public AlunoRepository() {
		super(Aluno.class);
	}

	public Aluno pesquisarPeloCpf(String cpf) {
    	Aluno aluno = null;
    	try {
			// Obtém um objeto CriteriaBuilder da sessão Hibernate
    		// É a “fábrica” de expressões e cláusulas (where, equal, like, etc).
    		CriteriaBuilder builder = session.getCriteriaBuilder();
			
			// Cria uma query tipada que vai retornar objetos da classe Aluno
			CriteriaQuery<Aluno> criteria = builder.createQuery(Aluno.class);
			
			// Define a entidade raiz da consulta — como se fosse o FROM Aluno no SQL
			Root<Aluno> root = criteria.from(Aluno.class);
			
			// .select(root): indica que queremos retornar os próprios objetos Aluno
			// .where(...): aplica um filtro na query, equivalente a WHERE cpf = ?.
			// builder.equal(...): cria uma condição de igualdade entre root.get("cpf") e o valor da variável cpf
			criteria
				.select(root)
	        	.where(builder.equal(root.get("cpf"), cpf));
			
			// Compila e executa a query construída
			aluno = session.createQuery(criteria).getSingleResultOrNull();
		} catch (Exception e) {
			System.out.println("Ocorreu um problema ao consultar o aluno pelo CPF: " + e.getMessage());
		}
        return aluno;
    }

}
