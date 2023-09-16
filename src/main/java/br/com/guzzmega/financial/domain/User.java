package br.com.guzzmega.financial.domain;

import br.com.guzzmega.financial.exception.ValidationException;

import java.util.Objects;

public class User {

	private Long id;
	private String name;
	private String email;
	private String password;

	public User(Long id, String name, String email, String password){
		this.validate(name,email,password);

		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	private void validate(String name, String email, String password){
		if(name == null){
			throw new ValidationException("Name is mandatory");
		}
		if(email == null){
			throw new ValidationException("Email is mandatory");
		}
		if(password == null){
			throw new ValidationException("Password is mandatory");
		}
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) return true;
		if (!(obj instanceof User user)) return false;
		return Objects.equals(getName(), user.getName()) &&
				Objects.equals(getEmail(), user.getEmail()) &&
				 Objects.equals(getPassword(), user.getPassword());
	}

	@Override
	public String toString() {
		return "User { id=" + id + ", name='" + name + "'" + ", email='" + email + "'" + ", password='" + password + "'" + " }";
	}

	@Override
	public int hashCode() {
		return Objects.hash(getName(), getEmail(), getPassword());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}