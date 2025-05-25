package projetoProgramacao.projetoProgramacao.service;


import org.springframework.stereotype.Service;
import projetoProgramacao.projetoProgramacao.entities.Disciplina;
import projetoProgramacao.projetoProgramacao.repository.DisciplinaRepository;

import java.util.List;

@Service
public class DisciplinaService {


    private final DisciplinaRepository disciplinaRepository;

    public DisciplinaService(DisciplinaRepository disciplinaRepository) {
        this.disciplinaRepository = disciplinaRepository;
    }

    public Disciplina cadastrarDisciplina(Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    public List<Disciplina> listarDisciplinas() {
        return disciplinaRepository.findAll();
    }

    public Disciplina buscarPorId(Long id) {
        return disciplinaRepository.findById(id).orElse(null);
    }


    public Disciplina editarDisciplina(Long id, Disciplina disciplinaAtualizada) {
        return disciplinaRepository.findById(id).map(disciplina -> {
            disciplina.setNome(disciplinaAtualizada.getNome());
            return disciplinaRepository.save(disciplina);
        }).orElse(null);
    }

    public boolean excluirDisciplina(Long id) {
        return disciplinaRepository.findById(id).map(disciplina -> {
            disciplinaRepository.delete(disciplina);
            return true;
        }).orElse(false);
    }



}
