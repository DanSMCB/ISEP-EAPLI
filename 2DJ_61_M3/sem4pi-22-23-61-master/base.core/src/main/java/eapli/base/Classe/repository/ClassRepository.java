package eapli.base.Classe.repository;

import eapli.base.Classe.domain.Classe;
import eapli.base.ExtraClasse.domain.ExtraClasse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClassRepository extends JpaRepository<Classe, Long> {

    Optional<Classe> ofIdentity(Long id);

    @Override
    List<Classe> findAll();

    @Override
    Classe save(Classe classe);

    @Override
    void delete(Classe classe);

    Classe save(ExtraClasse newExtraClasse);
}
