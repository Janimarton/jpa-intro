package com.codecool.jpaintro;

import com.codecool.jpaintro.entity.Address;
import com.codecool.jpaintro.entity.Location;
import com.codecool.jpaintro.entity.School;
import com.codecool.jpaintro.entity.Student;
import com.codecool.jpaintro.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;

@SpringBootApplication
public class JpaIntroApplication {

//    @Autowired
//    private StudentRepository studentRepository;
//
//    @Autowired
//    private AddressRepository addressRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaIntroApplication.class, args);
    }

    @Bean
    @Profile("production")
    public CommandLineRunner init() {
        return args -> {
//            Student john = Student.builder()
//                    .email("john@codecool.com")
//                    .name("John")
//                    .birthDate(LocalDate.of(1980, 3, 5))
//                    .address(Address.builder().city("Miskolc").country("Hungary").build())
//                    .phoneNumber("555-6666")
//                    .phoneNumber("555-7777")
//                    .phoneNumber("555-8888")
//                    .build();
//            john.calculateAge();
//
//            studentRepository.save(john);

            Address address = Address.builder()
                    .address("Nagymezo street 44")
                    .city("Budapest")
                    .country("Hungary")
                    .build();

            Address address2 = Address.builder()
                    .address("Alkotmany street 20")
                    .city("Budapest")
                    .country("Hungary")
                    .build();

            Student john = Student.builder()
                    .name("John")
                    .email("john@codecool.com")
                    .birthDate(LocalDate.of(1987, 2, 12))
                    .address(address)
                    .phoneNumber("555-5432")
                    .phoneNumber("555-1234")
                    .build();

            Student barbara = Student.builder()
                    .name("Barbara")
                    .email("barb@codecool.com")
                    .birthDate(LocalDate.of(1985, 3, 20))
                    .address(address2)
                    .phoneNumber("123-4567")
                    .phoneNumber("987-6543")
                    .build();

            School school = School.builder()
                    .location(Location.BUDAPEST)
                    .name("CodeCool Budapest")
                    .student(john)
                    .student(barbara)
                    .build();

            barbara.setSchool(school);
            john.setSchool(school);
            schoolRepository.save(school);

        };
    }

}
