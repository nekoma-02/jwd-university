package by.epam.university.entity;

import java.io.Serializable;
import java.sql.Date;

public class User implements Serializable {

	private static final long serialVersionUID = 395785094217507766L;
	
	private int id;
	private String name;
	private String secondName;
	private String lastName;
	private String login;
	private String password;
	private String email;
	private Role role;
	private String gender;
	private String maritalStatus;
	private String placeOfBirth;
	private Date dateOfBirth;
	

	public User() {
	}
	
	
	public User(int id) {
		super();
		this.id = id;
	}

	

	public User(String name, String secondName, String lastName, String login, String password, String email,
			Role role) {
		super();
		this.name = name;
		this.secondName = secondName;
		this.lastName = lastName;
		this.login = login;
		this.password = password;
		this.email = email;
		this.role = role;
	}


	public User(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}


	public User(int id, String name, String secondName, String lastName, String email, String gender, String maritalStatus,
			String placeOfBirth, Date dateOfBirth) {
		this.id = id;
		this.name = name;
		this.secondName = secondName;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.maritalStatus = maritalStatus;
		this.placeOfBirth = placeOfBirth;
		this.dateOfBirth = dateOfBirth;
	}


	public User(int id, String name, String secondName, String lastName, String login, String password, String email,
			Role role) {
		super();
		this.id = id;
		this.name = name;
		this.secondName = secondName;
		this.lastName = lastName;
		this.login = login;
		this.password = password;
		this.email = email;
		this.role = role;
	}

	public User(int id, String name, String secondName, String lastName, String login, String password, String email,
			Role role, String gender, String maritalStatus, String placeOfBirth, Date dateOfBirth) {
		this(id, name, secondName, lastName, login, password, email, role);
		this.gender = gender;
		this.maritalStatus = maritalStatus;
		this.placeOfBirth = placeOfBirth;
		this.dateOfBirth = dateOfBirth;
	}

	

	public User(int id, String name, String secondName, String lastName) {
		super();
		this.id = id;
		this.name = name;
		this.secondName = secondName;
		this.lastName = lastName;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSecondName() {
		return secondName;
	}


	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}


	public final String getGender() {
		return gender;
	}


	public final void setGender(String gender) {
		this.gender = gender;
	}


	public final String getMaritalStatus() {
		return maritalStatus;
	}


	public final void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}


	public final String getPlaceOfBirth() {
		return placeOfBirth;
	}


	public final void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}


	public final Date getDateOfBirth() {
		return dateOfBirth;
	}


	public final void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((maritalStatus == null) ? 0 : maritalStatus.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((placeOfBirth == null) ? 0 : placeOfBirth.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((secondName == null) ? 0 : secondName.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (maritalStatus == null) {
			if (other.maritalStatus != null)
				return false;
		} else if (!maritalStatus.equals(other.maritalStatus))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (placeOfBirth == null) {
			if (other.placeOfBirth != null)
				return false;
		} else if (!placeOfBirth.equals(other.placeOfBirth))
			return false;
		if (role != other.role)
			return false;
		if (secondName == null) {
			if (other.secondName != null)
				return false;
		} else if (!secondName.equals(other.secondName))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", secondName=" + secondName + ", lastName=" + lastName
				+ ", login=" + login + ", password=" + password + ", email=" + email + ", role=" + role + ", gender="
				+ gender + ", maritalStatus=" + maritalStatus + ", placeOfBirth=" + placeOfBirth + ", dateOfBirth="
				+ dateOfBirth + "]";
	}

	




	


}
