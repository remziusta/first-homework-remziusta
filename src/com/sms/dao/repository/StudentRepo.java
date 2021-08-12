package com.sms.dao.repository;

import com.sms.dao.ICrud;
import com.sms.model.Student;
import com.sms.util.EntityManagerUtil;
import java.util.List;

import static com.sms.maintest.TestClass.entityManager;

public class StudentRepo implements ICrud<Student> {


    @Override
    public List<Student> getAllData() {
        isOpenEntityMenager();
        return entityManager.createQuery("from Student ", Student.class).getResultList();
    }

    @Override
    public Student findById(long id) {
        isOpenEntityMenager();
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

    public void isOpenEntityMenager(){
        if(entityManager != null)
            entityManager = EntityManagerUtil.getEntityManager("mysqlPU");
    }
}
