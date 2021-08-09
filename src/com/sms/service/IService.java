package com.sms.service;

import com.sms.model.Course;
import com.sms.model.Instructor;
import com.sms.model.Student;

import java.util.List;

public interface IService {
    List<Student> getAllStudent();
    Student findStudentById(long id);
    void createStudent(Student item);
    void updateStudent(Student item);
    void deleteStudent(Long id);

    List<Course> getAllCourse();
    Course findCourseById(long id);
    void createCourse(Course item);
    void updateCourse(Course item);
    void deleteCourse(Long id);

    List<Instructor> getAllInstructor();
    Instructor findInstructorById(long id);
    void createInstructor(Instructor item);
    void updateInstructor(Instructor item);
    void deleteInstructor(Long id);


}
