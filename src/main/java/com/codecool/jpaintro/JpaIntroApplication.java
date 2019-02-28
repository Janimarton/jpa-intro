package com.codecool.jpaintro;

import com.codecool.jpaintro.entity.Address;
import com.codecool.jpaintro.entity.Student;
import com.codecool.jpaintro.repository.AddressRepository;
import com.codecool.jpaintro.repository.StudentRepository;
import com.sun.jndi.cosnaming.IiopUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;

@SpringBootApplication
public class JpaIntroApplication {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AddressRepository addressRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaIntroApplication.class, args);
    }

    @Bean
    @Profile("production")
    public CommandLineRunner init() {
        return args -> {
            Student john = Student.builder()
                    .email("john@codecool.com")
                    .name("John")
                    .birthDate(LocalDate.of(1980, 3, 5))
                    .address(Address.builder().city("Miskolc").country("Hungary").build())
                    .phoneNumber("555-6666")
                    .phoneNumber("555-7777")
                    .phoneNumber("555-8888")
                    .build();
            john.calculateAge();

            studentRepository.save(john);

        };
    }

}
