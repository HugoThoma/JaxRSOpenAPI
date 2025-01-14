package fr.istic.taa.jaxrs.dao;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public abstract class GenericDaoJpaImpl<T, PK extends Serializable>
        implements GenericDAO<T, PK> {

    protected Class<T> entityClass;

    protected EntityManager entityManager ;

    public GenericDaoJpaImpl() {
        this.entityManager = EntityManagerHelper.getEntityManager();
        ParameterizedType genericSuperclass = (ParameterizedType) getClass()
                .getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass
                .getActualTypeArguments()[0];
    }

    @Override
    public T save(T t) {
        this.entityManager.persist(t);
        return t;
    }

    @Override
    public T read(PK id) {
        return this.entityManager.find(entityClass, id);
    }

    @Override
    public T update(T t) {
        return this.entityManager.merge(t);
    }

    @Override
    public void delete(T t) {
        t = this.entityManager.merge(t);
        this.entityManager.remove(t);
    }
}
