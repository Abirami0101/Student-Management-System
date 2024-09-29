package net.javaprojects.sms.service.impl;

import net.javaprojects.sms.dto.StudentDto;
import net.javaprojects.sms.entity.Student;
import net.javaprojects.sms.mapper.StudentMapper;
import net.javaprojects.sms.repository.StudentRepository;
import net.javaprojects.sms.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students=studentRepository.findAll();
        return students.stream()
                .map((student) -> StudentMapper.mapToStudentDto(student))
                .collect(Collectors.toList());

    }

    @Override
    public void createStudent(StudentDto studentDto) {
        Student student=StudentMapper.mapToStudent(studentDto);
        studentRepository.save(student);

    }

    @Override
    public StudentDto getStudentById(Long id) {
        Student student=studentRepository.findById(id).get();
        StudentDto studentDto=StudentMapper.mapToStudentDto(student);

        return studentDto;
    }

    @Override
    public void updateStudent(StudentDto studentDto) {
        studentRepository.save(StudentMapper.mapToStudent(studentDto));
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
