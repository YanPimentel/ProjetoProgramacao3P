package projetoProgramacao.projetoProgramacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projetoProgramacao.projetoProgramacao.entities.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
