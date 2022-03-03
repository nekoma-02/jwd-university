package by.epam.university.entity;

import java.io.Serializable;

public class Privilege implements Serializable {
	
	private static final long serialVersionUID = 5904983485707069271L;
	
	private int id;
	private String name;
	
	public Privilege() {
	}
	
	

	public Privilege(String name) {
		super();
		this.name = name;
	}



	public Privilege(int id) {
		super();
		this.id = id;
	}



	public Privilege(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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



	@Override
	public String toString() {
		return "Privilege [id=" + id + ", name=" + name + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Privilege other = (Privilege) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}
	
	

}
