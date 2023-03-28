package fr.istic.taa.jaxrs.dao;

import fr.istic.taa.jaxrs.domain.Fiche;


import java.util.List;

public class FicheDAO extends GenericDaoJpaImpl<Fiche, Long>{
    public List<Fiche> getFicheByTitle(String title){

        return this.entityManager.createQuery("select f from Fiche as f where f.title = :title")
                .setParameter("title", title).getResultList();
    }
    public List<Fiche> getAllFiches(){

        return this.entityManager.createQuery("select f from Fiche as f", Fiche.class)
                .getResultList();
    }

    public Fiche getFicheByID(Long id){

        return (Fiche) this.entityManager.createQuery("select p from Fiche as p where p.id = :id")
                .setParameter("id", id).getSingleResult();
    }

}