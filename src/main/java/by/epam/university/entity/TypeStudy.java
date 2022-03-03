package by.epam.university.entity;

import java.io.Serializable;

public class TypeStudy implements Serializable {

	private static final long serialVersionUID = 4189608301740363923L;
	
	private int id;
	private String typeName;

	public TypeStudy() {
	}
	
	public TypeStudy(int id) {
		super();
		this.id = id;
	}

	public TypeStudy(String typeName) {
		super();
		this.typeName = typeName;
	}

	public TypeStudy(int id, String typeName) {
		super();
		this.id = id;
		this.typeName = typeName;
	}

	@Override
	public String toString() {
		return "TypeStudy [id=" + id + ", typeName=" + typeName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((typeName == null) ? 0 : typeName.hashCode());
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
		TypeStudy other = (TypeStudy) obj;
		if (typeName == null) {
			if (other.typeName != null) {
				return false;
			}
		} else if (!typeName.equals(other.typeName)) {
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

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
