package service;

import java.util.List;

import entity.Aluno;
import repository.AlunoRepository;

public class AlunoService {

	private AlunoRepository alunoRepository = new AlunoRepository();
	
	public void cadastar(Aluno aluno) throws Exception {
		// Valida os dados do aluno
		if (aluno == null) {
			throw new Exception("Aluno inválido");	
		} else if (aluno.getNome().trim().isBlank()) {
			throw new Exception("Nome do aluno inválido");
		} else if (aluno.getCpf().trim().isBlank()) {
			throw new Exception("CPF do aluno inválido");
		} else if (aluno.getCpf().trim().length() != 11) {
			throw new Exception("CPF deve conter 11 dígitos");
		}
		
		// Verifica se já existe um aluno cadastrado com o CPF informado
		Aluno alunoEncontrado = alunoRepository.pesquisarPeloCpf(aluno.getCpf());
		if (alunoEncontrado != null) {
			throw new Exception("Já existe um aluno cadastrado com o CPF informado");
		}

		alunoRepository.inserir(aluno);
	}
	
	public void editar(Aluno aluno) {
		
	}
	
	public void listar(Aluno aluno) {
		
	}
	
	public void remover(Aluno aluno) {
		
	}

	public List<Aluno> pesquisarTodos() {
		return alunoRepository.pesquisarTodos();
	}

}
