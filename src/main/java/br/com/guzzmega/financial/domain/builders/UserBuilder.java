package br.com.guzzmega.financial.domain.builders;

import br.com.guzzmega.financial.domain.User;

public class UserBuilder {
	private Long id;
	private String name;
	private String email;
	private String password;

	public User build() {
		return new User(getId(), getName(), getEmail(), getPassword());
	}

	public static UserBuilder getOneUser() {
		UserBuilder builder = new UserBuilder();
		initializeDefaultValues(builder);
		return builder;
	}

	private static void initializeDefaultValues(UserBuilder builder) {
		builder.setId(1L);
		builder.setName("João");
		builder.setEmail("joao@gmail.com");
		builder.setPassword("joao123");
	}

	public UserBuilder withId(Long param) {
		this.setId(param);
		return this;
	}

	public UserBuilder withName(String param) {
		this.setName(param);
		return this;
	}

	public UserBuilder withEmail(String param) {
		this.setEmail(param);
		return this;
	}

	public UserBuilder withPassword(String param) {
		this.setPassword(param);
		return this;
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getPassword(){
		return password;
	}

	public void setPassword(String password){
		this.password = password;
	}
}