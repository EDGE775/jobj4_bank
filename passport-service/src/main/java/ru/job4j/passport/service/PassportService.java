package ru.job4j.passport.service;

import org.springframework.stereotype.Service;
import ru.job4j.passport.model.Passport;
import ru.job4j.passport.repository.PassportRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PassportService {

    private final PassportRepository repository;

    public PassportService(PassportRepository repository) {
        this.repository = repository;
    }

    public Passport save(Passport passport) {
        return repository.save(passport);
    }

    public Optional<Passport> findById(int id) {
        return repository.findById(id);
    }

    public void delete(Passport passport) {
        repository.delete(passport);
    }

    public List<Passport> findAll() {
        return StreamSupport.stream(
                repository.findAll().spliterator(), false
        ).collect(Collectors.toList());
    }

    public List<Passport> findBySeria(int seria) {
        return repository.findBySeria(seria);
    }

    public  List<Passport> findByIsActiveIsFalse() {
        return repository.findByIsActive(false);
    }
}
