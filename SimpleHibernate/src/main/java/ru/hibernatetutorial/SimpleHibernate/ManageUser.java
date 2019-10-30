package ru.hibernatetutorial.SimpleHibernate;

import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public final class ManageUser {
	private final User user;

	public ManageUser(User user) {
		this.user = user;
	}

	public int save() {
		final Session session = new Configuration().configure().buildSessionFactory().openSession();
		Transaction transaction = null;
		Integer id = null;

		try {
			transaction = session.beginTransaction();
			id = (Integer) session.save(this.user);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}			
			e.printStackTrace();
		} finally {
			session.close();
		}

		return id != null ? id.intValue() : 0;
	}
}
