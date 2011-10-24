/**
 * 
 */
package sprinki.paivat.com.services;

import java.text.ParseException;
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
		session.flush();
		session.clear();
		
		//Create Hibernate Query
		Query query = session.createQuery("FROM AnniversaryBean");
		
		return query.list();
	}

	public List<AnniversaryBean> getAllByUserid(Integer userid) {
		
		//Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		session.flush();
		session.clear();
		
		//Create Hibernate Query
		Query query = session.createQuery("FROM AnniversaryBean WHERE userid=" + userid);
		
		return query.list();
	}
	
	public AnniversaryBean get(Integer id) {
		
		//Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		session.flush();
		session.clear();
		
		//Create Hibernate Query
		AnniversaryBean ann = (AnniversaryBean)session.get(AnniversaryBean.class, id);
		
		return ann;
	}
	
	public void add(AnniversaryBean ann) {
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			ann.dateToUnixtime();
		} catch (ParseException e) {
			System.out.println("Whoops, can't parse date.");
			e.printStackTrace();
		}
		ann.setMailed(0);
		
		session.save(ann);
		session.flush();
		session.clear();
	}
	
	public void delete(Integer id) {
		
		//Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		//Create Hibernate Query
		AnniversaryBean ann = (AnniversaryBean)session.get(AnniversaryBean.class, id);
		
		session.delete(ann);
		session.flush();
		session.clear();
	}
	
	public void edit(AnniversaryBean ann) {
		//Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		//Create Hibernate Query
		AnniversaryBean existingAnn = (AnniversaryBean)session.get(AnniversaryBean.class, ann.getId());
		
		try {
			ann.dateToUnixtime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		existingAnn.setDate(ann.getDate());
		existingAnn.setMailed(ann.getMailed());
		existingAnn.setName(ann.getName());
		
		session.saveOrUpdate(existingAnn);
		session.flush();
		session.clear();
	}
}
