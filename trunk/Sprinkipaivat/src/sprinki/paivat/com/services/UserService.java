package sprinki.paivat.com.services;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sprinki.paivat.com.domain.UserBean;

@Service("userService")
@Transactional
public class UserService {
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	 
	public void add(UserBean user) {
		Session session = sessionFactory.getCurrentSession();		
		session.save(user);
		session.flush();
		session.clear();
	}
	
	public UserBean get(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		session.flush();
		session.clear();
		
		UserBean user = (UserBean) session.get(UserBean.class, id);
		return user;
	}
	
	public void edit(UserBean user) {
		Session session = sessionFactory.getCurrentSession();		
		UserBean userB = (UserBean) session.get(UserBean.class, user.getUserid());
		userB.setDateofbirth(user.getDateofbirth());
		userB.setEmail(user.getEmail());
		session.saveOrUpdate(userB);
		session.flush();
		session.clear();
	}
	
	public void delete(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		session.flush();
		session.clear();
		
		UserBean user = (UserBean) session.get(UserBean.class, id);
		session.delete(user);
	}
	
	public UserBean getByUsername(String username) {
		Session session = sessionFactory.getCurrentSession();
		session.flush();
		session.clear();
		
		Query query = session.createQuery("FROM UserBean WHERE username = '" + username + "'");
		UserBean user = (UserBean)query.uniqueResult();
		return user;	
	}
}
