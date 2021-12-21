package ru.job4j.mail.controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.job4j.mail.model.Passport;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class KafkaPassportController {

    @KafkaListener(topics = {"unavailable_passports"})
    public void onApiCall(ConsumerRecord<Integer, String> input) {
        String value = input.value();
        System.out.printf("%nПросрочен паспорт: %s%n", value);
    }
}
