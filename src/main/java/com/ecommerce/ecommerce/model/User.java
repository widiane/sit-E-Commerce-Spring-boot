package com.ecommerce.ecommerce.model;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;


@Entity
@Table(name = "user")

public class User {
@Id
@GeneratedValue
private Long id;
@Length(min = 5, message = "*Your username must have at least 5 characters")
@NotEmpty(message = "*Please provide an user name")
private String username;
@Length(min = 5, message = "*Your password must have at least 5 characters")
@NotEmpty(message = "*Please provide your password")
private String password;
@ManyToMany(cascade = CascadeType.ALL)
@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
inverseJoinColumns = @JoinColumn(name = "role_id"))
private List<Role> roles = new ArrayList<Role>();

private String firstName;

private String lastName;

private String email;

public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public List<Role> getRoles() {
	return roles;
}
public void setRoles(List<Role> roles) {
	this.roles = roles;
}


public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public User() {
	super();
}


}