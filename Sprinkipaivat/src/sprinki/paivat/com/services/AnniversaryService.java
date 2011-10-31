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
	public List<AnniversaryBean> getAllWithDate() {
		
		//Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		session.flush();
		session.clear();
		
		//Create Hibernate Query
		Query query = session.createQuery("FROM AnniversaryBean");
		
		List<AnniversaryBean> anniversaries = query.list();
		
		for(AnniversaryBean anniversary: anniversaries)
		{
			anniversary.setDate(DateService.unixtimeToDate(anniversary.getDate()));
		}
		
		return anniversaries;
	}
	
	@SuppressWarnings("unchecked")
	public List<AnniversaryBean> getAllWithUnixtime() {
		
		//Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		session.flush();
		session.clear();
		
		//Create Hibernate Query
		Query query = session.createQuery("FROM AnniversaryBean");
		
		List<AnniversaryBean> anniversaries = query.list();
		
		return anniversaries;
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
		
		ann.setDate(DateService.unixtimeToDate(ann.getDate()));
		
		return ann;
	}
	
	public void add(AnniversaryBean ann) {
		
		Session session = sessionFactory.getCurrentSession();
		
		ann.setDate(DateService.dateToUnixtime(ann.getDate()));
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
		ann.setDate(DateService.dateToUnixtime(ann.getDate()));

		
		existingAnn.setDate(ann.getDate());
		existingAnn.setMailed(0); //Nollaks aina editoinnin jälkee perkele
		existingAnn.setName(ann.getName());
		
		session.saveOrUpdate(existingAnn);
		session.flush();
		session.clear();
	}
	
	public void editMailed(AnniversaryBean ann) {
		Session session = sessionFactory.getCurrentSession();
		AnniversaryBean existingAnn = (AnniversaryBean)session.get(AnniversaryBean.class, ann.getId());
		existingAnn.setMailed(ann.getMailed());
		
		session.saveOrUpdate(existingAnn);
		session.flush();
		session.clear();		
	}
}
