package com.sms.maintest;

import com.sms.model.*;
import com.sms.service.Services;
import com.sms.util.EntityManagerUtil;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.Month;

public class TestClass {

    public static void main(String[] args) {
        saveTestData();
    }

    private static void saveTestData() {
        Student s1 = new Student("Remzi USTA", LocalDate.of(1996, Month.AUGUST,29),"HATAY", 'E');
        Student s2 = new Student("Ahmet USTA", LocalDate.of(2000,Month.JANUARY,18),"İSTANBUL",'E');
        Student s3 = new Student("Hatice Güzel", LocalDate.of(1992,Month.DECEMBER,21),"ISTANBUL",'K');

        Instructor i1 = new PermanentIntsructor("Koray Güney","İstanbul",9000.0);
        Instructor i2 = new VisitorInstructor("Çağlar Oflazoğlu","HATAY",7600.0);

        Course c1 = new Course("Java EE - Java Spring Boot",4);
        Course c2 = new Course("Java SE", 6);
        Course c3 = new Course("C++",9);

        s1.getStudentCourses().add(c1);
        s1.getStudentCourses().add(c2);
        s1.getStudentCourses().add(c3);
        s2.getStudentCourses().add(c1);
        s2.getStudentCourses().add(c2);
        s3.getStudentCourses().add(c2);
        s3.getStudentCourses().add(c3);

        i1.getCourses().add(c1);
        i2.getCourses().add(c2);
        i2.getCourses().add(c3);

        c1.setInstructor(i1);
        c2.setInstructor(i2);
        c3.setInstructor(i2);

        c1.getStudents().add(s1);
        c1.getStudents().add(s2);
        c2.getStudents().add(s1);
        c2.getStudents().add(s2);
        c2.getStudents().add(s3);
        c3.getStudents().add(s1);
        c3.getStudents().add(s3);

        EntityManager entityManager = EntityManagerUtil.getEntityManager("mysqlPU");

        try{
            entityManager.getTransaction().begin();

            entityManager.persist(i1);
            entityManager.persist(i2);

            entityManager.persist(c1);
            entityManager.persist(c2);
            entityManager.persist(c3);

            entityManager.persist(s1);
            entityManager.persist(s2);
            entityManager.persist(s3);

            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
        }finally {
            EntityManagerUtil.closeEntityManager(entityManager);
        }


    }

    private static int checkTestData() {
        EntityManager em = EntityManagerUtil.getEntityManager("mysqlPU");
        return em.createQuery("from Student ", Student.class).getResultList().size();
    }
}
