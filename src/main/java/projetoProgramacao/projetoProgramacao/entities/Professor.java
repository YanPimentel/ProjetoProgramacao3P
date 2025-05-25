package projetoProgramacao.projetoProgramacao.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Professor extends Pessoa {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "professor_turma",
            joinColumns = @JoinColumn(name = "professor_id"),
            inverseJoinColumns = @JoinColumn(name = "turma_id")
    )
    private List<Turma> turmas;



    @ManyToMany(fetch = FetchType.EAGER)
    private List<Disciplina> disciplinas;

    public Professor(List<Turma> turmas, List<Disciplina> disciplinas) {
        this.turmas = turmas;
        this.disciplinas = disciplinas;
    }

    public Professor() {
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }
}
