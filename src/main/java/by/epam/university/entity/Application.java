package by.epam.university.entity;

import java.io.Serializable;
import java.sql.Date;


public class Application implements Serializable {

	private static final long serialVersionUID = -6269264116059419635L;

	private int id;
	private String adress;
	private int certificate;
	private Privilege privilege;
	private User user;
	private School school;
	private Specialty specialties;
	private boolean confirmation;
	private String typeDocument;
	private String idDocument;
	private String seriesPassport;
	private int numberPassport;
	private String issuedBy;
	private Date endStudyDate;

	public Application() {
		// TODO Auto-generated constructor stub
	}

	
	public Application(String adress, int certificate, Privilege privilege, User user, School school,
			Specialty specialties, boolean confirmation, String typeDocument, String idDocument, String seriesPassport,
			int numberPassport, String issuedBy, Date endStudyDate) {
		super();
		this.adress = adress;
		this.certificate = certificate;
		this.privilege = privilege;
		this.user = user;
		this.school = school;
		this.specialties = specialties;
		this.confirmation = confirmation;
		this.typeDocument = typeDocument;
		this.idDocument = idDocument;
		this.seriesPassport = seriesPassport;
		this.numberPassport = numberPassport;
		this.issuedBy = issuedBy;
		this.endStudyDate = endStudyDate;
	}
	
	public Application(int id, String adress, int certificate, Privilege privilege, User user, School school,
			Specialty specialties, boolean confirmation, String typeDocument, String idDocument, String seriesPassport,
			int numberPassport, String issuedBy, Date endStudyDate) {
		this(adress, certificate, privilege, user, school, specialties, confirmation, typeDocument, idDocument, seriesPassport, numberPassport, issuedBy, endStudyDate);
		this.id = id;
	}

	public final int getId() {
		return id;
	}

	public final void setId(int id) {
		this.id = id;
	}

	public final String getAdress() {
		return adress;
	}

	public final void setAdress(String adress) {
		this.adress = adress;
	}

	public final int getCertificate() {
		return certificate;
	}

	public final void setCertificate(int certificate) {
		this.certificate = certificate;
	}

	public final Privilege getPrivilege() {
		return privilege;
	}

	public final void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}

	public final User getUser() {
		return user;
	}

	public final void setUser(User user) {
		this.user = user;
	}

	public final School getSchool() {
		return school;
	}

	public final void setSchool(School school) {
		this.school = school;
	}

	public final Specialty getSpecialties() {
		return specialties;
	}

	public final void setSpecialties(Specialty specialties) {
		this.specialties = specialties;
	}

	public final boolean isConfirmation() {
		return confirmation;
	}

	public final void setConfirmation(boolean confirmation) {
		this.confirmation = confirmation;
	}

	public final String getTypeDocument() {
		return typeDocument;
	}

	public final void setTypeDocument(String typeDocument) {
		this.typeDocument = typeDocument;
	}

	public final String getIdDocument() {
		return idDocument;
	}

	public final void setIdDocument(String idDocument) {
		this.idDocument = idDocument;
	}

	public final String getSeriesPassport() {
		return seriesPassport;
	}

	public final void setSeriesPassport(String seriesPassport) {
		this.seriesPassport = seriesPassport;
	}

	public final int getNumberPassport() {
		return numberPassport;
	}

	public final void setNumberPassport(int numberPassport) {
		this.numberPassport = numberPassport;
	}

	public final String getIssuedBy() {
		return issuedBy;
	}

	public final void setIssuedBy(String issuedBy) {
		this.issuedBy = issuedBy;
	}

	public final Date getEndStudyDate() {
		return endStudyDate;
	}

	public final void setEndStudyDate(Date endStudyDate) {
		this.endStudyDate = endStudyDate;
	}

	@Override
	public String toString() {
		return "Application [id=" + id + ", adress=" + adress + ", certificate=" + certificate + ", privilege="
				+ privilege + ", user=" + user + ", school=" + school + ", specialties=" + specialties
				+ ", confirmation=" + confirmation + ", typeDocument=" + typeDocument + ", idDocument=" + idDocument
				+ ", seriesPassport=" + seriesPassport + ", numberPassport=" + numberPassport + ", issuedBy=" + issuedBy
				+ ", endStudyDate=" + endStudyDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adress == null) ? 0 : adress.hashCode());
		result = prime * result + certificate;
		result = prime * result + (confirmation ? 1231 : 1237);
		result = prime * result + ((endStudyDate == null) ? 0 : endStudyDate.hashCode());
		result = prime * result + id;
		result = prime * result + ((idDocument == null) ? 0 : idDocument.hashCode());
		result = prime * result + ((issuedBy == null) ? 0 : issuedBy.hashCode());
		result = prime * result + numberPassport;
		result = prime * result + ((privilege == null) ? 0 : privilege.hashCode());
		result = prime * result + ((school == null) ? 0 : school.hashCode());
		result = prime * result + ((seriesPassport == null) ? 0 : seriesPassport.hashCode());
		result = prime * result + ((specialties == null) ? 0 : specialties.hashCode());
		result = prime * result + ((typeDocument == null) ? 0 : typeDocument.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Application other = (Application) obj;
		if (adress == null) {
			if (other.adress != null)
				return false;
		} else if (!adress.equals(other.adress))
			return false;
		if (certificate != other.certificate)
			return false;
		if (confirmation != other.confirmation)
			return false;
		if (endStudyDate == null) {
			if (other.endStudyDate != null)
				return false;
		} else if (!endStudyDate.equals(other.endStudyDate))
			return false;
		if (id != other.id)
			return false;
		if (idDocument == null) {
			if (other.idDocument != null)
				return false;
		} else if (!idDocument.equals(other.idDocument))
			return false;
		if (issuedBy == null) {
			if (other.issuedBy != null)
				return false;
		} else if (!issuedBy.equals(other.issuedBy))
			return false;
		if (numberPassport != other.numberPassport)
			return false;
		if (privilege == null) {
			if (other.privilege != null)
				return false;
		} else if (!privilege.equals(other.privilege))
			return false;
		if (school == null) {
			if (other.school != null)
				return false;
		} else if (!school.equals(other.school))
			return false;
		if (seriesPassport == null) {
			if (other.seriesPassport != null)
				return false;
		} else if (!seriesPassport.equals(other.seriesPassport))
			return false;
		if (specialties == null) {
			if (other.specialties != null)
				return false;
		} else if (!specialties.equals(other.specialties))
			return false;
		if (typeDocument == null) {
			if (other.typeDocument != null)
				return false;
		} else if (!typeDocument.equals(other.typeDocument))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	
	

}
