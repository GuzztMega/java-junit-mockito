package br.com.guzzmega.financial.domain;

import br.com.guzzmega.financial.domain.builders.UserBuilder;
import br.com.guzzmega.financial.exception.ValidationException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

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

		User user = UserBuilder.getOneUser().build();
		assertAll(
				"Create Valid User",
				() -> assertEquals(1L, (double) user.getId()),
				() -> assertEquals("João", user.getName()),
				() -> assertEquals("joao@gmail.com", user.getEmail()),
				() -> assertEquals("joao123", user.getPassword())
		);
	}

	@DisplayName("Must Reject a User Without Field")
	@ParameterizedTest(name = "{index}. {4}")
	@CsvFileSource(
			files = "src/test/resources/mustRejectUserWithoutField.csv",
			numLinesToSkip = 1,
			nullValues = "NULL",
			delimiter = ';'
	)
//	@CsvSource(value = {
//			"1; NULL; joao@gmail.com; joao123; Name is mandatory",
//			"1; João; NULL; joao123; Email is mandatory",
//			"1; João; joao@gmail.com; NULL; Password is mandatory"
//	}, useHeadersInDisplayName = true, delimiter = ';', nullValues = "NULL")
	public void mustRejectUserWithoutField(Long id, String name, String email, String pass, String message){

		ValidationException exception = assertThrows(ValidationException.class, () -> {
			UserBuilder.getOneUser().withId(id).withName(name).withEmail(email).withPassword(pass).build();
		});
		assertEquals(message, exception.getMessage());
	}

//	ALL SUBSTITUTED FOR mustRejectUserWithoutField()
//@Test
//@DisplayName("Must Reject a User Without Name")
//public void mustRejectUserWithoutName(){
//	ValidationException exception = assertThrows(ValidationException.class, () -> {
//		UserBuilder.getOneUser().withName(null).build();
//	});
//	assertEquals("Name is mandatory", exception.getMessage());
//}
//	@Test
//	@DisplayName("Must Reject a User Without Email")
//	public void mustRejectUserWithoutEmail(){
//		ValidationException exception = assertThrows(ValidationException.class, () -> {
//			UserBuilder.getOneUser().withEmail(null).build();
//		});
//		assertEquals("Email is mandatory", exception.getMessage());
//	}
//	@Test
//	@DisplayName("Must Reject a User Without Password")
//	public void mustRejectUserWithoutPassword(){
//		ValidationException exception = assertThrows(ValidationException.class, () -> {
//			UserBuilder.getOneUser().withPassword(null).build();
//		});
//		assertEquals("Password is mandatory", exception.getMessage());
//	}
}