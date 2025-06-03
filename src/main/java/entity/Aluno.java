package entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_Aluno")
public class Aluno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_aluno")
	private Long id;
	
	@Column(name = "nome", length = 100, nullable = false)
	private String nome;
	
	@Column(name = "CPF", length = 11, nullable = false, unique = true)
	private String cpf;
	
	@ManyToMany(mappedBy = "alunos")
	private List<Curso> cursos;
	
	public Aluno() {
		this.cursos = new ArrayList<Curso>();
	}
	
	public Aluno(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
		this.cursos = new ArrayList<Curso>();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

}
