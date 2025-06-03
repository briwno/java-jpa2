package service;

import entity.Curso;
import repository.CursoRepository;

public class CursoService {

	private CursoRepository cursoRepository = new CursoRepository();

	public void cadastar(Curso curso) throws Exception {
		if (curso == null) {
			throw new Exception("Curso inválido");
		} else if (curso.getNome().trim().isBlank()) {
			throw new Exception("Nome do curso inválido");
		}
		// Verifica se já existe um aluno cadastrado com o CPF informado
		Curso cursoEncontrado = cursoRepository.buscaPorId(curso.getId());
		if (cursoEncontrado != null) {
			throw new Exception("Já existe um curso cadastrado com o nome informado");
		}
		cursoRepository.inserir(curso);
	}
	
	public void editar(Curso curso) {
		
	}
	
	public void listar(Curso curso) {
		
	}
	
	public void remover(Curso curso) {
		
	}

}
