package sprinki.paivat.com.services.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import sprinki.paivat.com.domain.AnniversaryBean;
import sprinki.paivat.com.domain.UserBean;
import sprinki.paivat.com.services.AnniversaryService;
import sprinki.paivat.com.services.UserService;

@ContextConfiguration(locations = {"classpath:/test-applicationContext.xml",
		"classpath:/test-hibernate-context.xml",
		"classpath:/test-security-context.xml"})

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class AnniversaryTest {

	@Autowired
	private AnniversaryService anniversaryService;
	@Autowired
	private UserService userService;
	
	private UserBean u1;
	private UserBean u2;
	
	private AnniversaryBean a1;
	private AnniversaryBean a2;
	
	@Before
	public void SetUp() {
		
		u1 = new UserBean();
		u1.setUsername("lol");
		u1.setDateofbirth("12.12.1986");
		u1.setEmail("lol@sprinkipaivat.fi");
		u1.setPassword("lol");
		userService.add(u1);
		
		u2 = new UserBean();
		u2.setUsername("moi");
		u2.setDateofbirth("11.11.2011");
		u2.setEmail("kikkamokkeli@sprinkipaivat.fi");
		u2.setPassword("moi");
		userService.add(u2);
		
		a1 = new AnniversaryBean();
		a1.setName("testa1");
		a1.setDate("11.11.2011");
		a1.setUserid(userService.getByUsername("lol").getUserid());
		anniversaryService.add(a1);
		
		a2 = new AnniversaryBean();
		a2.setName("testa2");
		a2.setDate("12.12.2012");
		a2.setUserid(userService.getByUsername("moi").getUserid());
		anniversaryService.add(a2);
	}
	
	@Test
	public final void testAnniversaryService() {
		AnniversaryBean ab = anniversaryService.get(a1.getId());
		assertEquals(ab.getName(),a1.getName());
		
		ab = anniversaryService.getAllByUserid(u2.getUserid()).get(0);
		assertEquals(ab.getDate(),a2.getDate());
	}
	
	@Test
	public final void testPersist() {
		
		AnniversaryBean a3 = new AnniversaryBean();
		a3.setDate("10.10.2010");
		a3.setName("testa3");
		a3.setUserid(userService.getByUsername("moi").getUserid());
		anniversaryService.add(a3);
		
		a3.setMailed(1);
		assertNotNull(a3.getId());
		AnniversaryBean persistedAnn = anniversaryService.get(a3.getId());
		
		//This test should fail
		assertEquals(a3.getMailed(),persistedAnn.getMailed());
	}
	
	@Test
	public final void testEdit() {
		
		AnniversaryBean testAnniversary = anniversaryService.get(a1.getId());
		assertEquals(testAnniversary.getId(),a1.getId());
		
		testAnniversary.setMailed(1);
		anniversaryService.edit(testAnniversary);
		
		assertEquals(testAnniversary.getMailed(),anniversaryService.get(a1.getId()).getMailed());
	}
	
	@Test
	public final void testDelete() {
		
		int id = a1.getId();
		anniversaryService.delete(id);
		assertNull(anniversaryService.get(id));	
	}

}
