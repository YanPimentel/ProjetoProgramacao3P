package projetoProgramacao.projetoProgramacao.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToMany(mappedBy = "disciplinas")
    private List<Aluno> alunos;

    @ManyToMany(mappedBy = "disciplinas")
    private List<Professor> professores;

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

    public List<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }

    public Disciplina(Long id, String nome, List<Aluno> alunos, List<Professor> professores) {
        this.id = id;
        this.nome = nome;
        this.alunos = alunos;
        this.professores = professores;
    }

    public Disciplina() {
    }
}
