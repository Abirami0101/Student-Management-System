package net.javaprojects.sms.controller;

import jakarta.validation.Valid;
import net.javaprojects.sms.dto.StudentDto;
import net.javaprojects.sms.service.StudentService;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public String getAllStudents(Model model){
        List<StudentDto> students=studentService.getAllStudents();
        model.addAttribute("students",students);
        return "students";
    }

    @GetMapping("/students/new")
    public String createStudent(Model model){
        StudentDto studentDto=new StudentDto();
        model.addAttribute("student",studentDto);
        return "create_student";
    }

    @PostMapping("/students")
    public String saveStudent(@Valid @ModelAttribute("student") StudentDto student,
                              BindingResult result,
                              Model model){
        if(result.hasErrors()){
            model.addAttribute("student",student);
            return "create_student";
        }
        studentService.createStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/students/{id}/edit")
    public String editStudent(@PathVariable("id") Long id,
                              Model model){
        StudentDto student=studentService.getStudentById(id);
        model.addAttribute("student",student);
        return "edit_student";
    }

    @PostMapping("/students/{studentId}")
    public String updateStudent(@PathVariable("studentId") Long studentId,
                                @Valid @ModelAttribute StudentDto studentDto,
                                BindingResult result,
                                Model model){
        if(result.hasErrors()){
            model.addAttribute("student",studentDto);
            return "edit_student";
        }
        studentDto.setId(studentId);
        studentService.updateStudent(studentDto);
        return "redirect:/students";
    }

    @GetMapping("/students/{id}/delete")
    public String deleteStudent(@PathVariable("id") Long id){
        studentService.deleteStudent(id);
        return "redirect:/students";
    }

    @GetMapping("/students/{id}/view")
    public String viewStudent(@PathVariable("id") Long id,
                              Model model){
        StudentDto studentDto=studentService.getStudentById(id);
        model.addAttribute("student",studentDto);
        return "view_student";
    }
}
