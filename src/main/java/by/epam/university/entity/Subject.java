package by.epam.university.entity;

import java.io.Serializable;

public class Subject implements Serializable {

	private static final long serialVersionUID = 8475003062498699908L;

	private int id;
	private String name;

	public final int getId() {
		return id;
	}

	public final void setId(int id) {
		this.id = id;
	}

	public Subject(String name) {
		super();
		this.name = name;
	}

	public Subject(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Subject(int id) {
		super();
		this.id = id;
	}
	
	public Subject() {
		super();
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id + ((name == null) ? 0 : name.hashCode());
	
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
		Subject other = (Subject) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Subject [name=" + name + "]";
	}

}
