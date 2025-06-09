package app;

import java.util.List;
import entity.Aluno;
import entity.Curso;
import enumeration.OpcaoMenu;
import service.AlunoService;
import service.CursoService;
import util.TecladoUtil;

public class App {
    
    private final static AlunoService alunoService = new AlunoService();
    private final static CursoService cursoService = new CursoService();
    
    public static void main(String[] args) {
        while (true) {
            try {
                imprimirMenu();
                OpcaoMenu opcaoDigitada = lerOpcaoMenu();
                if (opcaoDigitada == OpcaoMenu.SAIR) {
                    System.out.println("Programa finalizado!");
                    break;
                }
                processarOpcaoMenu(opcaoDigitada);
            } catch (Exception e) {
                System.out.println("ERRO: " + e.getMessage() + "\n");
            }
        }
    }

    private static void processarOpcaoMenu(OpcaoMenu opcao) {
        switch(opcao) {
            case CADASTRAR_ALUNO -> cadastrarAluno();
            case CADASTRAR_CURSO -> cadastrarCurso();
            case REALIZAR_MATRICULA -> realizarMatricula();
            case CANCELAR_MATRICULA -> cancelarMatricula();
            case EXCLUIR_ALUNO -> excluirAluno();
            case EXCLUIR_CURSO -> excluirCurso();
            case LISTAR_ALUNOS -> listarAlunos();
            case LISTAR_CURSOS -> listarCursos();
            case OPCAO_INVALIDA -> System.out.println("Opção inválida! Tente novamente.\n");
        }
    }

    private static void imprimirMenu() {
        System.out.println("=== MENU PRINCIPAL ===");
        for (OpcaoMenu opcao : OpcaoMenu.values()) {
            if (opcao != OpcaoMenu.OPCAO_INVALIDA) {
                System.out.println(opcao.getNome());
            }
        }
        System.out.println("====================");
    }
    
    private static String leituraTecladoTexto(String mensagem) {
        System.out.print(mensagem);
        return TecladoUtil.leitura();
    }
    
    private static long leituraTecladoNumerico(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Long.parseLong(TecladoUtil.leitura());
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite um número válido!");
            }
        }
    }
    
    private static OpcaoMenu lerOpcaoMenu() {
        return OpcaoMenu.obterPorCodigo(leituraTecladoNumerico("Informe a opção desejada: "));
    }
    
    private static void cadastrarAluno() {
        System.out.println(OpcaoMenu.CADASTRAR_ALUNO.getDescricao());
        try {
            String nome = leituraTecladoTexto("Nome: ");
            String cpf = String.valueOf(leituraTecladoNumerico("CPF: "));
            
            Aluno aluno = new Aluno(nome, cpf);
            alunoService.cadastrar(aluno);
            System.out.println("Aluno cadastrado com sucesso!\n");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar aluno: " + e.getMessage() + "\n");
        }
    }

    private static void cadastrarCurso() {
        System.out.println(OpcaoMenu.CADASTRAR_CURSO.getDescricao());
        try {
            String nome = leituraTecladoTexto("Nome do curso: ");
            Curso curso = new Curso(nome);
            cursoService.cadastrar(curso);
            System.out.println("Curso cadastrado com sucesso!\n");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar curso: " + e.getMessage() + "\n");
        }
    }

    private static void realizarMatricula() {
        System.out.println(OpcaoMenu.REALIZAR_MATRICULA.getDescricao());
        try {
            String cpf = String.valueOf(leituraTecladoNumerico("CPF do aluno para matrícula: "));
            Long idCurso = leituraTecladoNumerico("ID do curso para matrícula: ");

            Aluno aluno = alunoService.buscarPorCpf(cpf);
            if (aluno == null) {
                throw new Exception("Aluno não encontrado");
            }

            Curso curso = cursoService.buscarPorId(idCurso);
            if (curso == null) {
                throw new Exception("Curso não encontrado");
            }

            cursoService.matricularAluno(curso, aluno);
            System.out.println("Matrícula realizada com sucesso!\n");
        } catch (Exception e) {
            System.out.println("Erro ao realizar matrícula: " + e.getMessage() + "\n");
        }
    }

    private static void cancelarMatricula() {
        System.out.println(OpcaoMenu.CANCELAR_MATRICULA.getDescricao());
        try {
            String cpf = String.valueOf(leituraTecladoNumerico("CPF do aluno para cancelamento: "));
            Long idCurso = leituraTecladoNumerico("ID do curso para cancelamento: ");

            Aluno aluno = alunoService.buscarPorCpf(cpf);
            if (aluno == null) {
                throw new Exception("Aluno não encontrado");
            }

            Curso curso = cursoService.buscarPorId(idCurso);
            if (curso == null) {
                throw new Exception("Curso não encontrado");
            }

            cursoService.cancelarMatricula(curso, aluno);
            System.out.println("Matrícula cancelada com sucesso!\n");
        } catch (Exception e) {
            System.out.println("Erro ao cancelar matrícula: " + e.getMessage() + "\n");
        }
    }

    private static void excluirAluno() {
        System.out.println(OpcaoMenu.EXCLUIR_ALUNO.getDescricao());
        try {
            Long id = leituraTecladoNumerico("ID do aluno: ");
            alunoService.remover(id);
            System.out.println("Aluno excluído com sucesso!\n");
        } catch (Exception e) {
            System.out.println("Erro ao excluir aluno: " + e.getMessage() + "\n");
        }
    }

    private static void excluirCurso() {
        System.out.println(OpcaoMenu.EXCLUIR_CURSO.getDescricao());
        try {
            Long id = leituraTecladoNumerico("ID do curso: ");
            cursoService.remover(id);
            System.out.println("Curso excluído com sucesso!\n");
        } catch (Exception e) {
            System.out.println("Erro ao excluir curso: " + e.getMessage() + "\n");
        }
    }

    private static void listarAlunos() {
        System.out.println(OpcaoMenu.LISTAR_ALUNOS.getDescricao());
        List<Aluno> alunos = alunoService.listarTodos();
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado\n");
            return;
        }
        
        System.out.println("Lista de Alunos:");
        for (Aluno aluno : alunos) {
            System.out.println("ID: " + aluno.getId() + 
                             " | Nome: " + aluno.getNome() + 
                             " | CPF: " + aluno.getCpf());
        }
        System.out.println();
    }

    private static void listarCursos() {
        System.out.println(OpcaoMenu.LISTAR_CURSOS.getDescricao());
        List<Curso> cursos = cursoService.listarTodos();
        if (cursos.isEmpty()) {
            System.out.println("Nenhum curso cadastrado\n");
            return;
        }
        
        System.out.println("Lista de Cursos e Alunos:");
        for (Curso curso : cursos) {
            System.out.println("\nCurso: " + curso.getNome() + " (ID: " + curso.getId() + ")");
            if (curso.getAlunos().isEmpty()) {
                System.out.println("- Nenhum aluno matriculado");
            } else {
                System.out.println("Alunos matriculados:");
                for (Aluno aluno : curso.getAlunos()) {
                    System.out.println("- " + aluno.getNome() + " (CPF: " + aluno.getCpf() + ")");
                }
            }
        }
        System.out.println();
    }
}