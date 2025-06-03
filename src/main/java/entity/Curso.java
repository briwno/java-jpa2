package entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Curso")
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_curso")
	private Long id;
	
	@Column(name = "nome", length = 100, nullable = false, unique = true)
	private String nome;
	
	@ManyToMany
	@JoinTable(
		name = "tb_curso_aluno",
		joinColumns = @JoinColumn(name = "id_curso"),
		inverseJoinColumns = @JoinColumn(name = "id_aluno")
	)
	private List<Aluno> alunos;
	
	public Curso() {
		this.alunos = new ArrayList<Aluno>();
	}
	
	public Curso(String nome) {
		this.nome = nome;
		this.alunos = new ArrayList<Aluno>();
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
	public List<Aluno> getAlunos() {
		return alunos;
	}
	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

}
