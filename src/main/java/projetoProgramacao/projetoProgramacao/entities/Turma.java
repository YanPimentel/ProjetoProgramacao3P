package projetoProgramacao.projetoProgramacao.entities;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "turma")
    private List<Aluno> alunos;




    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "turmas")
    private List<Professor> professores;



    public Turma(Long id, String nome, List<Aluno> alunos, List<Professor> professores) {
        this.id = id;
        this.nome = nome;
        this.alunos = alunos;
        this.professores = professores;
    }

    public Turma() {
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

    public List<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }


}
