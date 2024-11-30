package com.example.Best_Prac.Entity;

import jakarta.persistence.*;
import jakarta.persistence.*;

@Entity
@Table(name = "Users")
public class UserModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String userName;
	private String email;
	private String companyName;
	private String password;

	@ManyToOne // Change to ManyToOne if multiple users can have the same role
	@JoinColumn(name = "role_id", referencedColumnName = "id") // Ensure the column name matches your DB schema
	private RoleModel role;

	public UserModel() {}

	public UserModel(String userName, String email, String companyName, String password, RoleModel role) {
		this.userName = userName;
		this.email = email;
		this.companyName = companyName;
		this.password = password;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RoleModel getRole() {
		return role;
	}

	public void setRole(RoleModel role) {
		this.role = role;
	}
}


