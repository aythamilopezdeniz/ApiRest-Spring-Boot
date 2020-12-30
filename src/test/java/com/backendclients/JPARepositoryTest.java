package com.backendclients;

import org.junit.Assert;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import com.backendclients.model.entity.Client;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import com.backendclients.model.repository.ClientRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
public class JPARepositoryTest {

	@Autowired
	private ClientRepository repository;
	
	@Test
	@Order(1)
	public void testInsertRepository() {
		Client emp = new Client();
        emp.setName("Lokesh");
        emp.setSurname("Gupta");
        emp.setAge(46);
        emp.setEmail("howtodoinjava@gmail.com");
        
        repository.save(emp);
        
        Assert.assertNotNull(emp.getId());
	}
	
	@Test
	@Order(2)
	public void testUpdateRepository() {
		Client obj = (Client) repository.findAll().toArray()[0];
		obj.setSurname("Monserrat");
		repository.save(obj);
		Assert.assertEquals("Monserrat", ((Client) repository.findAll().toArray()[0]).getSurname());
	}
	
	@Test
	@Order(3)
	public void testRemoveRepository() {
		repository.deleteById(((Client) repository.findAll().toArray()[0]).getId());
		assertThat(repository.count()).isEqualTo(0);
	}
}