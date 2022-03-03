package by.epam.university.entity;

import java.io.Serializable;

public class School implements Serializable {

	private static final long serialVersionUID = 4164757404065407809L;

	private int id;
	private String name;
	private String level;
	private String institution;

	public School() {
	}

	public School(int id) {
		super();
		this.id = id;
	}

	public School(String name, String level, String institution) {
		super();
		this.name = name;
		this.level = level;
		this.institution = institution;
	}

	public School(int id, String name, String level, String institution) {
		this(id);
		this.name = name;
		this.level = level;
		this.institution = institution;
	}

	@Override
	public String toString() {
		return "School [id=" + id + ", name=" + name + ", level=" + level + ", institution=" + institution + "]";
	}

	public final int getId() {
		return id;
	}

	public final void setId(int id) {
		this.id = id;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final String getLevel() {
		return level;
	}

	public final void setLevel(String level) {
		this.level = level;
	}

	public final String getInstitution() {
		return institution;
	}

	public final void setInstitution(String institution) {
		this.institution = institution;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((institution == null) ? 0 : institution.hashCode());
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		School other = (School) obj;
		if (institution == null) {
			if (other.institution != null)
				return false;
		} else if (!institution.equals(other.institution))
			return false;
		if (level == null) {
			if (other.level != null)
				return false;
		} else if (!level.equals(other.level))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
