package ru.job4j.passport.scheduling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ru.job4j.passport.model.Passport;
import ru.job4j.passport.service.PassportService;

import java.util.List;

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduler.enabled", matchIfMissing = true)
public class SchedulerConfig {

    @Autowired
    private KafkaTemplate<Integer, String> template;

    private final PassportService service;

    public SchedulerConfig(PassportService service) {
        this.service = service;
    }

    @Scheduled(fixedDelay = 10000)
    public void checkUnavailablePassports() {
        List<Passport> passports = service.findByIsActiveIsFalse();
        for (Passport passport : passports) {
            template.send("unavailable_passports", String.format("%d - %d", passport.getSeria(), passport.getNumber()));
        }
    }
}
