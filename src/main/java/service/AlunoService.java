package service;

import java.util.List;
import entity.Aluno;
import repository.AlunoRepository;

public class AlunoService {
    private final AlunoRepository alunoRepository = new AlunoRepository();

    public void cadastrar(Aluno aluno) throws Exception {
        if (aluno == null) {
            throw new Exception("Aluno inválido");
        }
        String nome = aluno.getNome().trim();
        String cpf = aluno.getCpf().trim().replaceAll("[^0-9]", "");
        if (!cpf.matches("\\d+")) {
            throw new Exception("CPF deve conter apenas números");
        }

        aluno.setNome(nome);
        aluno.setCpf(cpf);

        Aluno alunoEncontrado = alunoRepository.pesquisarPeloCpf(cpf);
        if (alunoEncontrado != null) {
            throw new Exception("Já existe um aluno cadastrado com o CPF informado");
        }

        alunoRepository.inserir(aluno);
    }

    private static String getString(Aluno aluno, String nome) throws Exception {
        String cpf = aluno.getCpf().trim().replaceAll("[^0-9]", "");

        if (nome.isEmpty()) {
            throw new Exception("Nome do aluno inválido");
        }
        if (nome.length() > 100) {
            throw new Exception("Nome do aluno não pode ter mais que 100 caracteres");
        }
        if (!nome.matches("^[a-zA-ZÀ-ú\\s]+$")) {
            throw new Exception("Nome do aluno deve conter apenas letras");
        }
        if (cpf.isEmpty()) {
            throw new Exception("CPF do aluno inválido");
        }
        if (cpf.length() != 11) {
            throw new Exception("CPF deve conter 11 dígitos");
        }
        return cpf;
    }


    public List<Aluno> listarTodos() {
        return alunoRepository.pesquisarTodos();
    }

    public void atualizar(Aluno aluno) throws Exception {
        if (aluno == null) {
            throw new Exception("Aluno inválido");
        } else if (aluno.getNome().trim().isBlank()) {
            throw new Exception("Nome do aluno inválido");
        } else if(aluno.getNome().matches(".*\\d.*")) {
            throw new Exception("Nome do aluno não pode conter números");
        } else if (aluno.getCpf().trim().isBlank()) {
            throw new Exception("CPF do aluno inválido");
        } else if(!aluno.getCpf().matches("\\d+")) {
            throw new Exception("CPF deve conter apenas números");
        } else if (aluno.getCpf().trim().length() != 11) {
            throw new Exception("CPF deve conter 11 dígitos");
        }

        Aluno alunoExistente = alunoRepository.pesquisarPeloCpf(aluno.getCpf());
        if (alunoExistente != null && !alunoExistente.getId().equals(aluno.getId())) {
            throw new Exception("Já existe outro aluno cadastrado com o CPF informado");
        }

        alunoRepository.atualizar(aluno);
    }


    public void remover(Long id) throws Exception {
        Aluno aluno = alunoRepository.pesquisaPeloId(id);
        if (aluno == null) {
            throw new Exception("Aluno não encontrado");
        }
        if (!aluno.getCursos().isEmpty()) {
            throw new Exception("Não é possível excluir um aluno matriculado em cursos");
        }
        alunoRepository.remover(aluno);
    }

    public Aluno buscarPorCpf(String cpf) {
        return alunoRepository.pesquisarPeloCpf(cpf);
    }
}