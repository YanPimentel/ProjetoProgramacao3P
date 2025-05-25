package projetoProgramacao.projetoProgramacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projetoProgramacao.projetoProgramacao.entities.Disciplina;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
}
