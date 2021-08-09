package com.sms.dao.repository;

import com.sms.dao.ICrud;
import com.sms.model.Student;
import com.sms.util.EntityManagerUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class StudentRepo implements ICrud<Student> {

    EntityManager entityManager = EntityManagerUtil.getEntityManager("mysqlPU");

    @Override
    public List<Student> getAllData() {
        return entityManager.createQuery("from Student ", Student.class).getResultList();
    }

    @Override
    public Student findById(long id) {
        return entityManager.find(Student.class,id);
    }

    @Override
    public void create(Student item) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(item);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
        }finally {
            EntityManagerUtil.closeEntityManager(entityManager);
        }
    }

    @Override
    public Student update(Student item) {
        try {
            entityManager.getTransaction().begin();
            return  entityManager.merge(item);
        }catch (Exception e){
            entityManager.getTransaction().rollback();
        }finally {
            entityManager.getTransaction().commit();
            EntityManagerUtil.closeEntityManager(entityManager);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.getReference(Student.class, id));
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
        }finally {
            EntityManagerUtil.closeEntityManager(entityManager);
        }
    }
}
