package ru.tet.beans;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ru.tet.annotations.SUJournal;

@SUJournal(dtoFullClassName = "ru.tet.beans.UserDTO", templatesFolder = "templates/journal2")
public class UserModel {

	private String username;

	private String password;

	private boolean enabled = true;

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	
	

}
