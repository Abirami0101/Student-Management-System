package net.javaprojects.sms.service;

import net.javaprojects.sms.dto.StudentDto;

import java.util.List;

public interface StudentService {
    List<StudentDto> getAllStudents();

    void createStudent(StudentDto student);

    StudentDto getStudentById(Long id);

    void updateStudent(StudentDto studentDto);

    void deleteStudent(Long id);
}
