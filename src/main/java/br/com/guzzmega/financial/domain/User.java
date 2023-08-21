package br.com.guzzmega.financial.domain;

import br.com.guzzmega.financial.exception.ValidationException;

public class User {

	private Long id;
	private String name;
	private String email;
	private String password;

	public User(Long id, String name, String email, String password){
		validate(name,email,password);

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

	public Long getId(){
		return id;
	}

	public String getName(){
		return name;
	}

	public String getEmail(){
		return email;
	}

	public String getPassword(){
		return password;
	}
}