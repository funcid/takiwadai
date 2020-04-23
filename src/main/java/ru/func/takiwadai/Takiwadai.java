package ru.func.takiwadai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author func 17.04.2020
 * @project ru.func.takiwadai.Takiwadai
 */
@SpringBootApplication
@EnableJpaRepositories
public class Takiwadai {
    public static void main(String[] args) {
        SpringApplication.run(Takiwadai.class, args);
    }
}
