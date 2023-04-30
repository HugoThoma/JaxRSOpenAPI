package fr.istic.taa.jaxrs.dao;


import fr.istic.taa.jaxrs.domain.Fiche;
import fr.istic.taa.jaxrs.domain.Personne;

import java.util.List;

public class PersonneDAO extends GenericDaoJpaImpl<Personne, Long>  {
    public List<Personne> getPersonneByName(String name){

        return this.entityManager.createQuery("select p from Personne as p where p.name = :name")
                .setParameter("name", name).getResultList();
    }
    public List<Personne> getAllPersonnes(){

        return this.entityManager.createQuery("select p from Personne as p", Personne.class)
                .getResultList();
    }

    public Personne getPersonneByID(Long id){

        return (Personne) this.entityManager.createQuery("select p from Personne as p where p.id_member = :id")
                .setParameter("id", id).getSingleResult();
    }

}
