package service;

import java.util.List;
import entity.Aluno;
import entity.Curso;
import repository.CursoRepository;

public class CursoService {
    private final CursoRepository cursoRepository = new CursoRepository();

    public void cadastrar(Curso curso) throws Exception {
        if (curso == null) {
            throw new Exception("Curso inválido");
        }

        String nome = curso.getNome().trim();

        if (nome.isEmpty()) {
            throw new Exception("Nome do curso inválido");
        }
        if (nome.length() > 50) {
            throw new Exception("Nome do curso não pode ter mais que 50 caracteres");
        }
        if (!nome.matches("^[a-zA-ZÀ-ú\\s]+$")) {
            throw new Exception("Nome do curso deve conter apenas letras");
        }

        curso.setNome(nome);

        Curso cursoExistente = cursoRepository.pesquisarPorNome(nome);
        if (cursoExistente != null) {
            throw new Exception("Já existe um curso cadastrado com o nome informado");
        }

        cursoRepository.inserir(curso);
    }


    public List<Curso> listarTodos() {
        return cursoRepository.pesquisarTodos();
    }

    public void atualizar(Curso curso) throws Exception {
        if (curso == null) {
            throw new Exception("Curso inválido");
        } else if (curso.getNome().trim().isBlank()) {
            throw new Exception("Nome do curso inválido");
        } else if (curso.getNome().matches(".*\\d.*")) {
            throw new Exception("Nome do curso não pode conter números");
        }

        Curso cursoExistente = cursoRepository.pesquisarPorNome(curso.getNome());
        if (cursoExistente != null && !cursoExistente.getId().equals(curso.getId())) {
            throw new Exception("Já existe outro curso cadastrado com o nome informado");
        }

        cursoRepository.atualizar(curso);
    }


    public void remover(Long id) throws Exception {
        Curso curso = cursoRepository.pesquisaPeloId(id);
        if (curso == null) {
            throw new Exception("Curso não encontrado");
        }
        if (!curso.getAlunos().isEmpty()) {
            throw new Exception("Não é possível excluir um curso que possui alunos matriculados");
        }
        cursoRepository.remover(curso);
    }

    public Curso buscarPorId(Long id) {
        return cursoRepository.pesquisaPeloId(id);
    }

    public void matricularAluno(Curso curso, Aluno aluno) throws Exception {

        if (curso == null) {
            throw new Exception("Curso inválido");
        }
        if (aluno == null) {
            throw new Exception("Aluno inválido");
        }
        if (!curso.getAlunos().contains(aluno)) {
            curso.getAlunos().add(aluno);
            cursoRepository.atualizar(curso);
        } else {
            throw new Exception("Aluno já está matriculado neste curso");
        }
    }

    public void cancelarMatricula(Curso curso, Aluno aluno) throws Exception {
        if (curso.getAlunos().contains(aluno)) {
            curso.getAlunos().remove(aluno);
            cursoRepository.atualizar(curso);
        } else {
            throw new Exception("Aluno não está matriculado neste curso");
        }
    }
}