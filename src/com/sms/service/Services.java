package com.sms.service;

import com.sms.dao.repository.InstructorRepo;
import com.sms.dao.repository.CourseRepo;
import com.sms.dao.repository.StudentRepo;
import com.sms.model.Course;
import com.sms.model.Instructor;
import com.sms.model.Student;

import java.util.List;

public class Services implements IService{

    InstructorRepo instructorRepo = new InstructorRepo();

    StudentRepo studentRepo = new StudentRepo();

    CourseRepo courseRepo = new CourseRepo();

    @Override
    public List<Student> getAllStudent() {
        return studentRepo.getAllData();
    }

    @Override
    public Student findStudentById(long id) {
        return studentRepo.findById(id);
    }

    @Override
    public void createStudent(Student item) {
        studentRepo.create(item);
    }

    @Override
    public void updateStudent(Student item) {
        studentRepo.update(item);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepo.delete(id);
    }

    @Override
    public List<Course> getAllCourse() {
        return courseRepo.getAllData();
    }

    @Override
    public Course findCourseById(long id) {
        return courseRepo.findById(id);
    }

    @Override
    public void createCourse(Course item) {
        courseRepo.create(item);
    }

    @Override
    public void updateCourse(Course item) {
        courseRepo.update(item);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepo.delete(id);
    }

    @Override
    public List<Instructor> getAllInstructor() {
        return instructorRepo.getAllData();
    }

    @Override
    public Instructor findInstructorById(long id) {
        return instructorRepo.findById(id);
    }

    @Override
    public void createInstructor(Instructor item) {
        instructorRepo.create(item);
    }

    @Override
    public void updateInstructor(Instructor item) {
        instructorRepo.update(item);
    }

    @Override
    public void deleteInstructor(Long id) {
        instructorRepo.delete(id);
    }
}
