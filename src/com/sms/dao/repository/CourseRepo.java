package com.sms.dao.repository;

import com.sms.dao.ICrud;
import com.sms.model.Course;
import com.sms.util.EntityManagerUtil;
import java.util.List;

import static com.sms.maintest.TestClass.entityManager;

public class CourseRepo implements ICrud<Course> {

    @Override
    public List<Course> getAllData() {
        isOpenEntityManager();
        return entityManager.createQuery("from Course", Course.class).getResultList();
    }

    @Override
    public Course findById(long id) {
        isOpenEntityManager();
        return entityManager.find(Course.class,id);
    }

    @Override
    public void create(Course item) {
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
    public Course update(Course item) {
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
            entityManager.remove(entityManager.getReference(Course.class, id));
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
        }finally {
            EntityManagerUtil.closeEntityManager(entityManager);
        }
    }

    public void isOpenEntityManager(){
        if(!entityManager.isOpen())
            entityManager = EntityManagerUtil.getEntityManager("mysqlPU");
    }
}
