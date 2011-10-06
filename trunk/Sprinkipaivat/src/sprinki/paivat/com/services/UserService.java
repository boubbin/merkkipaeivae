package sprinki.paivat.com.services;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sprinki.paivat.com.domain.UserBean;

@Service("UserService")
@Transactional
public class UserService {
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	 
	public void add(UserBean user) {
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
	}
	public UserBean get(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		UserBean user = (UserBean) session.get(UserBean.class, id);
		return user;
	}
	public void edit(UserBean user) {
		Session session = sessionFactory.getCurrentSession();
		UserBean userB = (UserBean) session.get(UserBean.class, user.getUserid());
		userB.setDateofbirth(user.getDateofbirth());
		userB.setEmail(user.getEmail());
		session.save(userB);
		
	}
	public void delete(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		UserBean user = (UserBean) session.get(UserBean.class, id);
		session.delete(user);
	}
}
