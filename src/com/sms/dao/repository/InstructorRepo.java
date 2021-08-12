package com.sms.dao.repository;

import com.sms.dao.ICrud;
import com.sms.model.Instructor;
import com.sms.util.EntityManagerUtil;


import java.util.List;

import static com.sms.maintest.TestClass.entityManager;

public class InstructorRepo implements ICrud<Instructor> {

    @Override
    public List<Instructor> getAllData() {
        isOpenEntityMenager();
        return entityManager.createQuery("from Instructor ", Instructor.class).getResultList();
    }

    @Override
    public Instructor findById(long id) {
        isOpenEntityMenager();
        return entityManager.find(Instructor.class,id);
    }

    @Override
    public void create(Instructor item) {
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
    public Instructor update(Instructor item) {
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
            entityManager.remove(entityManager.getReference(Instructor.class, id));
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
