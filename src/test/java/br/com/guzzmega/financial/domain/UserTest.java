package br.com.guzzmega.financial.domain;

import br.com.guzzmega.financial.domain.builders.UserBuilder;
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

	@Test
	@DisplayName("Must Create a Valid User")
	public void mustCreateValidUser(){
		User user = UserBuilder.getOneUser().create();

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
			UserBuilder.getOneUser().withName(null).create();
		});
		assertEquals("Name is mandatory", exception.getMessage());
	}

	@Test
	@DisplayName("Must Reject a User Without Email")
	public void mustRejectUserWithoutEmail(){
		ValidationException exception = assertThrows(ValidationException.class, () -> {
			UserBuilder.getOneUser().withEmail(null).create();
		});
		assertEquals("Email is mandatory", exception.getMessage());
	}

	@Test
	@DisplayName("Must Reject a User Without Password")
	public void mustRejectUserWithoutPassword(){
		ValidationException exception = assertThrows(ValidationException.class, () -> {
			UserBuilder.getOneUser().withPassword(null).create();
		});
		assertEquals("Password is mandatory", exception.getMessage());
	}

}