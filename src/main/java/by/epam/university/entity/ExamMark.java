package by.epam.university.entity;

import java.io.Serializable;

public class ExamMark implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2363446644591946042L;
	private int idSubject;
	private int mark;

	public ExamMark(int idSubject, int mark) {
		super();
		this.idSubject = idSubject;
		this.mark = mark;
	}

	public ExamMark(int mark) {
		super();
		this.mark = mark;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idSubject;
		result = prime * result + mark;
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
		ExamMark other = (ExamMark) obj;
		if (idSubject != other.idSubject) {
			return false;
		}
		if (mark != other.mark) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ExamMark [idSubject=" + idSubject + ", mark=" + mark + "]";
	}

	public final int getIdSubject() {
		return idSubject;
	}

	public final void setIdSubject(int idSubject) {
		this.idSubject = idSubject;
	}

	public final int getMark() {
		return mark;
	}

	public final void setMark(int mark) {
		this.mark = mark;
	}

}
