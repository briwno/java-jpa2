package repository;

import entity.Curso;

public class CursoRepository extends GenericRepository<Curso> {

    public CursoRepository() {
		super(Curso.class);
	}
}
