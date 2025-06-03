package app;

import java.util.List;

import entity.Aluno;
import enumeration.OpcaoMenu;
import service.AlunoService;
import service.CursoService;
import util.TecladoUtil;

public class App {
	
	private static AlunoService alunoService = new AlunoService();
	private static CursoService cursoService = new CursoService();
	
	public static void imprimirMenu() {
		System.out.println("=========================");
		System.out.println("MENU PRINCIPAL");
		System.out.println("=========================");
		System.out.println(OpcaoMenu.CADASTRAR_ALUNO.getNome());
		System.out.println(OpcaoMenu.CADASTRAR_CURSO.getNome());
		System.out.println(OpcaoMenu.REALIZAR_MATRICULA.getNome());
		System.out.println(OpcaoMenu.CANCELAR_MATRICULA.getNome());
		System.out.println(OpcaoMenu.EXCLUIR_ALUNO.getNome());
		System.out.println(OpcaoMenu.EXCLUIR_CURSO.getNome());
		System.out.println(OpcaoMenu.LISTAR_ALUNOS.getNome());
		System.out.println(OpcaoMenu.LISTAR_CURSOS.getNome());
		System.out.println(OpcaoMenu.SAIR.getNome());
		System.out.println("=========================");
	}
	
	public static String leituraTecladoTexto(String mensagem) {
		System.out.print(mensagem);
		String valorDigitado = TecladoUtil.leitura();
		return valorDigitado;
	}
	
	public static long leituraTecladoNumerico(String mensagem) {
		long numeroDigitado = 0;
		try {
			System.out.print(mensagem);
			String valorDigitado = TecladoUtil.leitura();
			numeroDigitado = Long.parseLong(valorDigitado);
			return numeroDigitado;
		} catch (Exception e) {
			System.out.println("Atenção: Você deve digitar um valor númerico!");
			numeroDigitado = leituraTecladoNumerico(mensagem);
		}
		return numeroDigitado;
	}
	
	public static OpcaoMenu lerOpcaoMenu() {
		long opcaoDigitada = leituraTecladoNumerico("Informe a opção desejada: ");
		OpcaoMenu opcaoMenu = OpcaoMenu.obterPorCodigo(opcaoDigitada);
		return opcaoMenu;
	}
	
	public static void main(String[] args) {
		for(;;) {
			imprimirMenu();
			OpcaoMenu opcaoDigitada = lerOpcaoMenu();
			switch(opcaoDigitada) {
				case CADASTRAR_ALUNO -> cadastrarAluno();
				case CADASTRAR_CURSO -> cadastrarCurso();
				case REALIZAR_MATRICULA -> realizarMatricula();
				case CANCELAR_MATRICULA -> cancelarMatricula();
				case EXCLUIR_ALUNO -> excluirAluno();
				case EXCLUIR_CURSO -> excluirCurso();
				case LISTAR_ALUNOS -> listarAlunos();
				case LISTAR_CURSOS -> listarCursos();
				default -> System.out.println("Atenção: Opção inválida!");
			}			
		}
	}

	private static void listarCursos() {
		System.out.println(OpcaoMenu.LISTAR_CURSOS.getDescricao());
		// TODO: implementar
	}

	private static void listarAlunos() {
		List<Aluno> alunos = alunoService.pesquisarTodos();
		if (alunos == null || alunos.isEmpty()) {
			System.out.println("Nenhum aluno cadastrado na base de dados!");
		} else {
			System.out.println(String.format("Alunos encontrados: %d", alunos.size()));
			for (Aluno aluno : alunos) {
				System.out.println(String.format("#%d CPF: %s - Nome: %s", aluno.getId(), aluno.getCpf(), aluno.getNome()));
			}
		}		
		System.out.println();
	}

	private static void excluirCurso() {
		System.out.println(OpcaoMenu.EXCLUIR_CURSO.getDescricao());
		// TODO: implementar
	}

	private static void excluirAluno() {
		System.out.println(OpcaoMenu.EXCLUIR_ALUNO.getDescricao());
		// TODO: implementar
	}

	private static void cancelarMatricula() {
		System.out.println(OpcaoMenu.CANCELAR_MATRICULA.getDescricao());
		// TODO: implementar
	}

	private static void realizarMatricula() {
		System.out.println(OpcaoMenu.REALIZAR_MATRICULA.getDescricao());
		// TODO: implementar
	}
	
	public static void cadastrarAluno() {
		// Interface
		System.out.println(OpcaoMenu.CADASTRAR_ALUNO.getDescricao());
		String nome = leituraTecladoTexto("Digite o nome: ");
		long cpf = leituraTecladoNumerico("Digite o CPF: ");
		
		// Chama o service para realizar a inserção no banco
		try {
			Aluno aluno = new Aluno(nome, String.valueOf(cpf));
			alunoService.cadastar(aluno);
			System.out.println("Aluno cadastrado com sucesso!\n");
		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
		}
	}
	
	public static void cadastrarCurso() {
		System.out.println(OpcaoMenu.CADASTRAR_CURSO.getDescricao());
		// TODO: implementar
	}
}
