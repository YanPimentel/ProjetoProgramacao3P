package projetoProgramacao.projetoProgramacao.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import projetoProgramacao.projetoProgramacao.entities.Aluno;
import projetoProgramacao.projetoProgramacao.entities.Professor;
import projetoProgramacao.projetoProgramacao.entities.Turma;
import projetoProgramacao.projetoProgramacao.repository.AlunoRepository;
import projetoProgramacao.projetoProgramacao.repository.ProfessorRepository;
import projetoProgramacao.projetoProgramacao.repository.TurmaRepository;

import java.util.List;

@Service
public class TurmaService {

    private final TurmaRepository turmaRepository;
    private final AlunoRepository alunoRepository;
    private final ProfessorRepository professorRepository;

    public TurmaService(TurmaRepository turmaRepository, AlunoRepository alunoRepository, ProfessorRepository professorRepository) {
        this.turmaRepository = turmaRepository;
        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
    }

    public Turma cadastrarTurma(Turma turma) {
        return turmaRepository.save(turma);
    }

    public List<Turma> listarTurmas() {
        return turmaRepository.findAll();
    }

    public Turma buscarPorId(Long id) {
        return turmaRepository.findById(id).orElse(null);
    }

    public boolean adicionarAlunoNaTurma(Long idTurma, Long idAluno) {
        Turma turma = buscarPorId(idTurma);
        Aluno aluno = alunoRepository.findById(idAluno).orElse(null);

        if (turma != null && aluno != null) {
            if (turma.getAlunos().size() >= 20) {
                System.out.println("Turma já tem 20 alunos!");
                return false;
            }

            aluno.setTurma(turma);
            alunoRepository.save(aluno);
            return true;
        }

        return false;
    }

    public boolean adicionarProfessorATurma(Long idTurma, Long idProfessor) {
        Turma turma = buscarPorId(idTurma);
        Professor professor = professorRepository.findById(idProfessor).orElse(null);

        if (turma != null && professor != null) {
            // Adiciona professor na lista da turma, se ainda não estiver
            if (!turma.getProfessores().contains(professor)) {
                turma.getProfessores().add(professor);
            }
            // Adiciona turma na lista do professor, se ainda não estiver
            if (!professor.getTurmas().contains(turma)) {
                professor.getTurmas().add(turma);
            }
            turmaRepository.save(turma);
            professorRepository.save(professor);
            return true;
        }

        return false;
    }

    public Turma editarTurma(Long id, String novoNome) {
        Turma turma = buscarPorId(id);
        if (turma != null) {
            turma.setNome(novoNome);
            return turmaRepository.save(turma);
        }
        return null;
    }


    @Transactional
    public boolean excluirTurma(Long id) {
        Turma turma = buscarPorId(id);
        if (turma != null) {
            List<Aluno> alunos = alunoRepository.findAll();
            for (Aluno aluno : alunos) {
                if (aluno.getTurma() != null && aluno.getTurma().getId().equals(id)) {
                    aluno.setTurma(null);
                    alunoRepository.save(aluno);
                }
            }

            List<Professor> professores = professorRepository.findAll();
            for (Professor professor : professores) {
                if (professor.getTurmas().contains(turma)) {
                    professor.getTurmas().remove(turma);
                    professorRepository.save(professor);
                }
            }

            turmaRepository.delete(turma);
            return true;
        }
        return false;
    }




}
