package br.com.guzzmega.financial.domain;

import br.com.guzzmega.financial.exception.ValidationException;
import org.junit.jupiter.api.*;


import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Domain: User")
public class UserTest {

	@BeforeAll
	public static void setupAll(){
		System.out.println("<-- STARTING -->");
	}

	@AfterAll
	public static void teardownAll(){
		System.out.println("<--  ENDING  -->");
	}

	//	@Autowired
	//	private User user;

	@Test
	@DisplayName("Must Create a Valid User")
	public void mustCreateValidUser(){
		User user = new User(1L, "João", "joao@gmail.com", "joao123");

		assertAll(
				"Create Valid User",
				() -> assertEquals(1L, (double) user.getId()),
				() -> assertEquals("João", user.getName()),
				() -> assertEquals("joao@gmail.com", user.getEmail()),
				() -> assertEquals("joao123", user.getPassword())
		);
	}

	@Test
	@DisplayName("Must Reject a User Without Name")
	public void mustRejectUserWithoutName(){
		ValidationException exception = assertThrows(ValidationException.class, () -> {
			new User(1L, null, "joao@gmail.com", "joao123");
		});
		assertEquals("Name is mandatory", exception.getMessage());
	}

	@Test
	@DisplayName("Must Reject a User Without Email")
	public void mustRejectUserWithoutEmail(){
		ValidationException exception = assertThrows(ValidationException.class, () -> {
			new User(1L, "João", null, "joao123");
		});
		assertEquals("Email is mandatory", exception.getMessage());
	}

	@Test
	@DisplayName("Must Reject a User Without Password")
	public void mustRejectUserWithoutPassword(){
		ValidationException exception = assertThrows(ValidationException.class, () -> {
			new User(1L, "João", "joao@gmail.com", null);
		});
		assertEquals("Password is mandatory", exception.getMessage());
	}
}