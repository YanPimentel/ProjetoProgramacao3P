package projetoProgramacao.projetoProgramacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projetoProgramacao.projetoProgramacao.entities.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
