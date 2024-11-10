package com.prince.student.controller;

import com.prince.common.data.dtos.ResponseStudentDto;
import com.prince.student.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {
    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ResponseStudentDto> getStudentDetails(@PathVariable Long userId){
        ResponseStudentDto responseStudentDto = studentService.findStudentDetails(userId);
        return new ResponseEntity<>(responseStudentDto, HttpStatus.OK);
    }

    @GetMapping("/check/userId/{userId}/courseId/{coruseId}")
    public ResponseEntity<Boolean> checkStudentRegisteredACourse(@PathVariable Long userId, @PathVariable Long coruseId){
        Boolean res = studentService.checkStudentRegisteredACourse(userId, coruseId);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PostMapping("/register/userid/{userId}/courseid/{courseId}")
    public ResponseEntity<String> registerCourse(@PathVariable Long userId, @PathVariable Long courseId) {
        String res = studentService.registerCourse(userId, courseId);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/remove/userid/{userId}/courseid/{courseId}")
    public ResponseEntity<String> removeCourseById(@PathVariable Long userId, @PathVariable Long courseId){
        String res = studentService.deleteCourseById(userId, courseId);
        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/removeAllCourses/{userId}")
    public ResponseEntity<Integer> removeByUserId(@PathVariable Long userId){
        Integer res = studentService.deleteByUserId(userId);
        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }
}
