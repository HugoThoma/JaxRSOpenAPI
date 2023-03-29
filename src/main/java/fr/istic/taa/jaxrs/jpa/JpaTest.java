package fr.istic.taa.jaxrs.jpa;

import fr.istic.taa.jaxrs.domain.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


public class JpaTest {

	private EntityManager manager;

	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory factory =
				Persistence.createEntityManagerFactory("mysql");
		EntityManager manager = factory.createEntityManager();
		JpaTest test = new JpaTest(manager);

		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		try {
			test.createTickets();
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();

		test.listTickets();
		test.getallMember();
		manager.close();
		System.out.println(".. done");
	}

	private void createTickets() {
		int numOfEmployees = manager.createQuery("Select f From Fiche f", Fiche.class).getResultList().size();
		if (numOfEmployees == 0) {
			User user1 = new User("java");
			User user2 = new User("kotlin");
			SupportMember support1 = new SupportMember("Hugo");
			SupportMember support2 = new SupportMember("Rémi");
			manager.persist(user1);
			manager.persist(user2);
			manager.persist(support1);
			manager.persist(support2);
			Fiche ticket = new BugFiche("Jakab Gipsz",user1);
			ticket.setSupport(support1);
			ticket.setDateEmission(Date.valueOf(LocalDate.now()));
			ticket.setDatePriseenCharge(Date.valueOf(LocalDate.now()));
			ticket.setTags(Tags.IN_PROGRESS.toString());
			manager.persist(ticket);
			manager.persist(new FeatureRequestFiche("Captain Nemo",user1));
			manager.persist(new BugFiche("Jack Sparrow",user2));


		}
	}

	private void listTickets() {
		List<Fiche> resultList = manager.createQuery("Select t From Fiche t", Fiche.class).getResultList();
		System.out.println("num of Ticket:" + resultList.size());
		for (Fiche next : resultList) {
			System.out.println("next Ticket: " + next);
		}
	}
	//TODO : Question 5. Portez votre application et gérer au minimum une relation d’héritage, les requêtes, une requête nommée.
	public void getallMember(){
		CriteriaBuilder criteriaBuilder = this.manager.getCriteriaBuilder();
		CriteriaQuery<SupportMember> supportMemberCriteriaQuery = criteriaBuilder.createQuery(SupportMember.class);
		Root<SupportMember> root = supportMemberCriteriaQuery.from(SupportMember.class);
		supportMemberCriteriaQuery.select(root);
		//supportMemberCriteriaQuery.where(criteriaBuilder.equal(root.type(),SupportMember.class));


		manager.createQuery(supportMemberCriteriaQuery).getResultList();
		System.out.println(manager.createQuery(supportMemberCriteriaQuery).getResultList());
	}

	public void addUser(){

		CriteriaBuilder criteriaBuilder = this.manager.getCriteriaBuilder();
		CriteriaQuery<SupportMember> supportMemberCriteriaQuery = criteriaBuilder.createQuery(SupportMember.class);
		Root<SupportMember> root = supportMemberCriteriaQuery.from(SupportMember.class);
		supportMemberCriteriaQuery.select(root);
	}
}


