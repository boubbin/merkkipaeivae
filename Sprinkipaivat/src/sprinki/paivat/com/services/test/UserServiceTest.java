package sprinki.paivat.com.services.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import sprinki.paivat.com.domain.UserBean;
import sprinki.paivat.com.services.UserService;

@ContextConfiguration(locations = {"classpath:/test-applicationContext.xml",
		"classpath:/test-hibernate-context.xml",
		"classpath:/test-security-context.xml"})

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager",defaultRollback = true)
@Transactional
public class UserServiceTest {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	@Autowired
	private UserService userService;
	
	private UserBean u1;
	private UserBean u2;
	
	
	@Before
	public void setUp() {
		Session session = sessionFactory.getCurrentSession();
		
		u1 = new UserBean();
		u1.setUsername("lol");
		u1.setDateofbirth(12121986);
		u1.setEmail("lol@sprinkipaivat.fi");
		u1.setPassword("lol");
		session.save(u1);
		
		u2 = new UserBean();
		u2.setUsername("moi");
		u2.setDateofbirth(11112011);
		u2.setEmail("kikkamokkeli@sprinkipaivat.fi");
		u2.setPassword("moi");
		session.save(u2);
	}
	
	@Test
	public final void testUserService() {
		
		UserBean ub = userService.get(u1.getUserid());
		assertEquals(ub,u1);
		
		ub = userService.getByUsername("moi");
		assertEquals(ub,u2);
	}
	
	@Test
	public final void testPersist() {
		Session session = sessionFactory.getCurrentSession();
		
		UserBean u3 = new UserBean();
		u3.setUsername("lul");
		u3.setEmail("lul@sprinkipaivat.fi");
		u3.setPassword("lul");
		session.save(u3);

		u3.setUsername("not_antti");
		assertNotNull(u3.getUserid());
		UserBean persistedUser = userService.get(u3.getUserid());

		assertEquals(persistedUser, u3);
		assertEquals(persistedUser.getUsername(), u3.getUsername());
		System.out.println(persistedUser.getUsername()+" "+u3.getUsername());
	}
	
	@Test
	public final void testEdit() {
		
		UserBean testUser = userService.get(u1.getUserid());
		assertEquals(testUser.getUserid(),u1.getUserid());
		
		testUser.setEmail("not_antti@sprinkipaivat.fi");
		userService.edit(testUser);
		
		assertEquals(testUser.getEmail(), u1.getEmail());
	}
	
	@Test
	public final void testDelete() {
		
		int id = u1.getUserid();
		userService.delete(id);
		assertNull(userService.get(id));
	}

}
