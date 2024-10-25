package eapli.base.Classe.repository;

import eapli.base.Classe.domain.Classe;
import eapli.base.Classe.repository.ClassRepository;
import eapli.base.ExtraClasse.domain.ExtraClasse;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class ClassRepositoryImpl implements ClassRepository {

    private final List<Classe> classes = new ArrayList<>();

    @Override
    public Optional<Classe> ofIdentity(Long id) {
        return classes.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    @Override
    public List<Classe> findAll() {
        return new ArrayList<>(classes);
    }

    @Override
    public Classe save(Classe classe) {
        classes.add(classe);
        return classe;
    }

    @Override
    public void delete(Classe classe) {
        classes.remove(classe);
    }

    @Override
    public Classe save(ExtraClasse newExtraClasse) {
        return null;
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Classe> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Classe> findAll(Sort sort) {
        return null;

    }

    @Override
    public Page<Classe> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Classe> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public <S extends Classe> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Classe> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Classe> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Classe> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Classe> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Classe getOne(Long aLong) {
        return null;
    }

    @Override
    public Classe getById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Classe> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Classe> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Classe> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Classe> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Classe> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Classe> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Classe, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

}
