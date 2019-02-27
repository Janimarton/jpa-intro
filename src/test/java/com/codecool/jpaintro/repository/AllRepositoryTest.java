package com.codecool.jpaintro.repository;

import com.codecool.jpaintro.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class AllRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void saveOneSimple() {
        Student john = Student.builder()
                .email("john@codecool.com")
                .name("John")
                .build();

        studentRepository.save(john);

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(1);

    }

    @Test(expected = DataIntegrityViolationException.class)
    public void saveUniqueFieldTwice(){
        Student student = Student.builder()
                .email("john@codecool.com")
                .name("John")
                .build();

        studentRepository.save(student);

        Student student2 = Student.builder()
                .email("john@codecool.com")
                .name("Peter")
                .build();

        studentRepository.saveAndFlush(student2);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void emailShouldBeNotNull(){
        Student student = Student.builder()
                .name("John")
                .build();

        studentRepository.save(student);

    }

}