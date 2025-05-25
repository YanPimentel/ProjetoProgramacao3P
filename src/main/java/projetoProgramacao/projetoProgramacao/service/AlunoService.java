package projetoProgramacao.projetoProgramacao.service;

import org.springframework.stereotype.Service;
import projetoProgramacao.projetoProgramacao.entities.Aluno;
import projetoProgramacao.projetoProgramacao.repository.AlunoRepository;

import java.util.List;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public Aluno cadastrarAluno(Aluno aluno) {
        if (!isCpfValido(aluno.getCpf())) {
            throw new IllegalArgumentException("CPF inválido! Deve ter 11 digitos, e não pode ser generico. Tente novamente.");
        }
        return alunoRepository.save(aluno);
    }

    public List<Aluno> listarAlunos() {
        return alunoRepository.findAll();
    }

    public Aluno buscarPorId(Long id) {
        return alunoRepository.findById(id).orElse(null);
    }

    // Método privado para validar CPF simples
    private boolean isCpfValido(String cpf) {
        if (cpf == null) return false;

        // Remove tudo que não for dígito (caso tenha pontos ou traços)
        cpf = cpf.replaceAll("\\D", "");

        if (cpf.length() != 11) return false;

        // Verifica se todos os dígitos são iguais (ex: 00000000000)
        char primeiroDigito = cpf.charAt(0);
        for (int i = 1; i < cpf.length(); i++) {
            if (cpf.charAt(i) != primeiroDigito) {
                return true; // tem dígitos diferentes, CPF válido até aqui
            }
        }
        return false; // todos os dígitos iguais -> inválido
    }


    public boolean excluirAluno(Long id) {
        if (alunoRepository.existsById(id)) {
            alunoRepository.deleteById(id);
            return true;
        }
        return false;
    }


    public Aluno editarAluno(Aluno aluno) {
        if (!alunoRepository.existsById(aluno.getId())) {
            throw new IllegalArgumentException("Aluno não encontrado com ID: " + aluno.getId());
        }

        if (!isCpfValido(aluno.getCpf())) {
            throw new IllegalArgumentException("CPF inválido! Deve ter 11 dígitos e não pode ser genérico.");
        }

        return alunoRepository.save(aluno);
    }


}
