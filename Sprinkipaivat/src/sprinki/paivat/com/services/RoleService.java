/**
 * 
 */
package sprinki.paivat.com.services;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sprinki.paivat.com.domain.AnniversaryBean;
import sprinki.paivat.com.domain.Role;

/**
 * @author Marma
 *
 */

@Service("roleService")
@Transactional
public class RoleService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Role> getAll() {
		
		//Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		session.flush();
		session.clear();
		
		//Create Hibernate Query
		Query query = session.createQuery("FROM Role");
		
		List<Role> roles = query.list();
		
		return roles;
	}
	
	public Role get(int id) {
		
		//Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		//Create Hibernate Query
		Role role = (Role)session.get(Role.class, id);
		
		return role;
	}
}
