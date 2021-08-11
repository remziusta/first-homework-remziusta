package com.sms.dao.repository;

import com.sms.dao.ICrud;
import com.sms.model.Student;
import com.sms.util.EntityManagerUtil;
import java.util.List;

import static com.sms.maintest.TestClass.entityManager;

public class StudentRepo implements ICrud<Student> {


    @Override
    public List<Student> getAllData() {
        isOpenEntityManager();
        return entityManager.createQuery("from Student ", Student.class).getResultList();
    }

    @Override
    public Student findById(long id) {
        isOpenEntityManager();
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
            entityManager = EntityManagerUtil.getEntityManager("mysqlPU");
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
            entityManager = EntityManagerUtil.getEntityManager("mysqlPU");
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
            entityManager = EntityManagerUtil.getEntityManager("mysqlPU");
        }
    }

    public void isOpenEntityManager(){
        if(!entityManager.isOpen())
            entityManager = EntityManagerUtil.getEntityManager("mysqlPU");
    }
}
