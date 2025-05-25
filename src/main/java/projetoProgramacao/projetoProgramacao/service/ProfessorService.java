package projetoProgramacao.projetoProgramacao.service;


import org.springframework.stereotype.Service;
import projetoProgramacao.projetoProgramacao.entities.Professor;
import projetoProgramacao.projetoProgramacao.repository.ProfessorRepository;

import java.util.List;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public Professor cadastrarProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

    public List<Professor> listarProfessores() {
        return professorRepository.findAll();
    }

    public Professor buscarPorId(Long id) {
        return professorRepository.findById(id).orElse(null);
    }


    public Professor editarProfessor(Professor professor) {
        Professor existente = professorRepository.findById(professor.getId())
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));

        existente.setNome(professor.getNome());
        existente.setIdade(professor.getIdade());
        existente.setCpf(professor.getCpf());

        return professorRepository.save(existente);
    }


    public void removerProfessor(Long id) {
        if (!professorRepository.existsById(id)) {
            throw new IllegalArgumentException("Professor não encontrado para exclusão.");
        }
        professorRepository.deleteById(id);
    }



}
