package by.epam.university.entity;

import java.io.Serializable;

public class Specialty implements Serializable {

	

	private static final long serialVersionUID = 6299088859721507637L;
	
	private int id;
	private String name;
	private int plan;
	private int year;
	private TypeStudy typeStudy;
	private Faculty faculty;
	
	public Specialty() {
		// TODO Auto-generated constructor stub
	}

	
	
	public Specialty(int id) {
		super();
		this.id = id;
	}

	public Specialty(String name, int plan, int year, TypeStudy typeStudy, Faculty faculty) {
		super();
		this.name = name;
		this.plan = plan;
		this.year = year;
		this.typeStudy = typeStudy;
		this.faculty = faculty;
	}

	public Specialty(int id, String name, TypeStudy typeStudy, Faculty faculty) {
		super();
		this.id = id;
		this.name = name;
		this.typeStudy = typeStudy;
		this.faculty = faculty;
	}



	public Specialty(int id, String name, int plan, int year, TypeStudy typeStudy, Faculty faculty) {
		super();
		this.id = id;
		this.name = name;
		this.plan = plan;
		this.year = year;
		this.typeStudy = typeStudy;
		this.faculty = faculty;
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

	public int getPlan() {
		return plan;
	}

	public void setPlan(int plan) {
		this.plan = plan;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public TypeStudy getTypeStudy() {
		return typeStudy;
	}

	public void setTypeStudy(TypeStudy typeStudy) {
		this.typeStudy = typeStudy;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	@Override
	public String toString() {
		return "Specialty [id=" + id + ", name=" + name + ", plan=" + plan + ", year=" + year + ", typeStudy="
				+ typeStudy + ", faculty=" + faculty + "]";
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
		Specialty other = (Specialty) obj;
		if (faculty == null) {
			if (other.faculty != null) {
				return false;
			}
		} else if (!faculty.equals(other.faculty)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
	
		if (typeStudy == null) {
			if (other.typeStudy != null) {
				return false;
			}
		} else if (!typeStudy.equals(other.typeStudy)) {
			return false;
		}
		if (year != other.year) {
			return false;
		}
		
		if (typeStudy.getId() != other.getTypeStudy().getId()) {
			return false;
		}
		
		if (faculty.getId() != other.getFaculty().getId()) {
			return false;
		}
		return true;
	}
	
	
	
}
