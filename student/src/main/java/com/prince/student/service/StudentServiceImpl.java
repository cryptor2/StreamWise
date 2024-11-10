package com.prince.student.service;

import com.prince.common.data.dtos.CourseDetailsDto;
import com.prince.common.data.dtos.CourseDto;
import com.prince.common.data.dtos.ResponseStudentDto;
import com.prince.common.data.dtos.UserDto;
import com.prince.common.data.entities.Course;
import com.prince.common.data.entities.CourseRegistration;
import com.prince.common.data.entities.User;
import com.prince.data.commons.exceptions.ResourNotFoundException;
import com.prince.student.Feign.FeignClientUserService;
import com.prince.student.Feign.FeignClientCourse;
import com.prince.student.repository.CourseRegistrationRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{

    private final ModelMapper modelMapper;
    CourseRegistrationRepository courseRegistrationRepository;
    private final FeignClientUserService feignClientUserService;
    private final FeignClientCourse feignClientCourse;

    @Autowired
    public StudentServiceImpl(CourseRegistrationRepository courseRegistrationRepository, ModelMapper modelMapper, FeignClientUserService feignClientUserService, FeignClientCourse feignClientCourse) {
        this.courseRegistrationRepository = courseRegistrationRepository;
        this.modelMapper = modelMapper;
        this.feignClientUserService = feignClientUserService;
        this.feignClientCourse = feignClientCourse;
    }
    public ResponseStudentDto findStudentDetails(Long userId){
        UserDto userDto = feignClientUserService.userDetails(userId).orElseThrow(() -> new ResourNotFoundException("Student", "userid", userId));

        User user = modelMapper.map(userDto, User.class);

        List<CourseDetailsDto> courses = courseRegistrationRepository.findAllByUser(user).stream().map(c -> modelMapper.map(c, CourseDetailsDto.class)).collect(Collectors.toList());
        ResponseStudentDto res = new ResponseStudentDto();
        res.setUserId(user.getId());
        res.setEmail(user.getEmail());
        res.setUserName(user.getUsername());
        res.setRegistrations(courses);
        return res;
    }

    @Override
    public Boolean checkStudentRegisteredACourse(Long userId, Long courseId) {
        UserDto userDto = feignClientUserService.userDetails(userId).orElseThrow(() -> new ResourNotFoundException("Student", "userid", userId));
        CourseDto courseDto = feignClientCourse.getCourseDetails(courseId).getBody();
        Course course = modelMapper.map(courseDto, Course.class);
        User user = modelMapper.map(userDto, User.class);

        return courseRegistrationRepository.existsByUserAndCourse(user, course);
    }

    @Transactional
    public String registerCourse(Long userId, Long courseId){
        UserDto userDto = feignClientUserService.userDetails(userId).orElseThrow(() -> new ResourNotFoundException("Student", "userid", userId));
        CourseDto courseDto = feignClientCourse.getCourseDetails(courseId).getBody();
        Course course = modelMapper.map(courseDto, Course.class);
        User user = modelMapper.map(userDto, User.class);
        boolean res = courseRegistrationRepository.existsByUserAndCourse(user, course);

        if(res) return "Already registered";

        CourseRegistration courseRegistration = new CourseRegistration();
        courseRegistration.setCourse(course);
        courseRegistration.setUser(user);
        try{
            courseRegistrationRepository.save(courseRegistration);
            return "Registered";
        }catch (Exception e){
            return "Registration Failed";
        }
    }

    @Transactional
    public String deleteCourseById(Long userId, Long courseId){
        UserDto userDto = feignClientUserService.userDetails(userId).orElseThrow(() -> new ResourNotFoundException("Student", "userid", userId));
        User user = modelMapper.map(userDto, User.class);

        CourseDto courseDto = feignClientCourse.getCourseDetails(courseId).getBody();
        Course course = modelMapper.map(courseDto, Course.class);
        Integer res = courseRegistrationRepository.deleteByUserAndCourse(user, course);
        if(res >0) return "Deleted";
        return "Failed";
    }

    @Transactional
    public Integer deleteByUserId(Long userId){
        UserDto userDto = feignClientUserService.userDetails(userId).orElse(null);

        if(userDto == null) return 0;
        else {
            User user = modelMapper.map(userDto, User.class);

            return courseRegistrationRepository.deleteByUser(user);
        }
    }

}
