package service;

import entity.Curso;
import repository.CursoRepository;

public class CursoService {

	private CursoRepository cursoRepository = new CursoRepository();

	public void cadastrar(Curso curso) throws Exception {
		if (curso == null) {
			throw new Exception("Curso inválido");
		} else if (curso.getNome().trim().isBlank()) {
			throw new Exception("Nome do curso inválido");
		}
		// Verifica se já existe um curso
		Curso cursoEncontrado = cursoRepository.pesquisarPorNome(curso.getNome());
		if (cursoEncontrado != null) {
			throw new Exception("Já existe um curso cadastrado com o nome informado");
		}
		// Insere o curso no banco de dados
		cursoRepository.inserir(curso);
	}
	
	public void editar(Curso curso) {
		
	}
	
	public void listar(Curso curso) {
		
	}
	
	public void remover(Curso curso) {
		
	}

}
