package projetoProgramacao.projetoProgramacao.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.util.List;

@Entity
public class Aluno extends Pessoa {

    @ManyToOne
    private Turma turma;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Disciplina> disciplinas;


    public Aluno(Turma turma, List<Disciplina> disciplinas) {
        this.turma = turma;
        this.disciplinas = disciplinas;
    }

    public Aluno() {
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }
}
