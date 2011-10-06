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

/**
 * @author Marma
 *
 */

@Service("anniversaryService")
@Transactional
public class AnniversaryService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<AnniversaryBean> getAll() {
		
		//Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		//Create Hibernate Query
		Query query = session.createQuery("FROM anniversaries");
		
		return query.list();
	}
	
	public AnniversaryBean get(Integer id) {
		
		//Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		//Create Hibernate Query
		AnniversaryBean ann = (AnniversaryBean)session.get(AnniversaryBean.class, id);
		
		return ann;
	}
	
	public void add(AnniversaryBean ann) {
		
		Session session = sessionFactory.getCurrentSession();
		
		session.save(ann);
	}
	
	public void delete(Integer id) {
		
		//Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		//Create Hibernate Query
		AnniversaryBean ann = (AnniversaryBean)session.get(AnniversaryBean.class, id);
		
		session.delete(ann);
	}
	
	public void edit(AnniversaryBean ann) {
		//Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		//Create Hibernate Query
		AnniversaryBean existingAnn = (AnniversaryBean)session.get(AnniversaryBean.class, ann.getId());
		
		existingAnn.setDate(ann.getDate());
		existingAnn.setMailed(ann.getMailed());
		existingAnn.setName(ann.getName());
		
		session.save(existingAnn);
	}
}
