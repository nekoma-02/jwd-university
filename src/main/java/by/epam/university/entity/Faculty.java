package by.epam.university.entity;

import java.io.Serializable;

public class Faculty implements Serializable{

	private static final long serialVersionUID = 1336579684410526548L;
	
	private int id;
	private String name;
	
	public Faculty() {
	}
	
	
	
	public Faculty(int id) {
		super();
		this.id = id;
	}



	public Faculty(String name) {
		super();
		this.name = name;
	}



	public Faculty(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Faculty [id=" + id + ", name=" + name + "]";
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
		Faculty other = (Faculty) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
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

	
}
