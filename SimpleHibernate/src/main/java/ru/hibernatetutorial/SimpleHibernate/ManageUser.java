package ru.hibernatetutorial.SimpleHibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;


public final class ManageUser {
	private final User user;
	private SessionFactory factory;

	public ManageUser(User user) {
		this.user = user;

		try {
			this.factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
		}
	}

	public int save(){
		Session session = factory.openSession();
		Transaction transaction = null;
		Integer id = null;

		try{
			transaction = session.beginTransaction();
			id = (Integer) session.save(this.user);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null){
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return id.intValue();
	}
}
