package by.htp.ex.bean;

import java.io.Serializable;
import java.util.Objects;

public class Role implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nameofRole;
	private boolean adminProperty;
		
	public Role(){}

	public Role(String nameofRole, boolean adminProperty) {
		super();
		this.nameofRole = nameofRole;
		this.adminProperty=adminProperty;
	}

	public String getNameofRole() {
		return nameofRole;
	}

	public void setNameofRole(String nameofRole) {
		this.nameofRole = nameofRole;
	}

	public boolean getAdminProperty() {
		return adminProperty;
	}

	public void setAdminProperty(boolean adminProperty) {
		this.adminProperty = adminProperty;
	}

	@Override
	public int hashCode() {
		return Objects.hash(adminProperty, nameofRole);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		return adminProperty == other.adminProperty && Objects.equals(nameofRole, other.nameofRole);
	}

	@Override
	public String toString() {
		StringBuilder str= new StringBuilder();
		str.append("Role [nameofRole=").append(nameofRole).append(", adminProperty=").append(adminProperty).append("]");
		return str.toString();
	}

}
