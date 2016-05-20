package by.kliuchnik.project.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User extends AbstractModel {

	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + ", role=" + role + "]";
	}

	@Column
	private String name;

	@Column
	private String password;
	

    @Enumerated(value = EnumType.ORDINAL)
	@Column
	private Role role;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
