package ru.job4j.passport.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.passport.controller.exception.ObjectNotFoundException;
import ru.job4j.passport.model.Passport;
import ru.job4j.passport.service.PassportService;

import java.util.List;

@RestController
public class PassportController {
    private final PassportService passportService;

    public PassportController(PassportService passportService) {
        this.passportService = passportService;
    }

    @PostMapping("/save")
    public ResponseEntity<Passport> savePassport(@RequestBody Passport passport) {
        return new ResponseEntity<>(passportService.save(passport), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updatePassport(@RequestParam int id,
                                               @RequestBody Passport passport) {
        Passport passportDB = passportService.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(Passport.class));
        passportDB.setActive(passport.isActive());
        passportDB.setNumber(passport.getNumber());
        passportDB.setSeria(passport.getSeria());
        passportService.save(passportDB);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deletePassport(@RequestParam int id) {
        Passport passportDB = passportService.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(Passport.class));
        passportService.delete(passportDB);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find")
    public ResponseEntity<List<Passport>> getAllPassports() {
        return new ResponseEntity<>(passportService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/seria")
    public ResponseEntity<List<Passport>> getAllPassportsWithSeria(@RequestParam int seria) {
        return new ResponseEntity<>(passportService.findBySeria(seria), HttpStatus.OK);
    }

    @GetMapping("/unavailable")
    public ResponseEntity<List<Passport>> getUnavailablePassports() {
        return new ResponseEntity<>(passportService.findByIsActiveIsFalse(), HttpStatus.OK);
    }
}
