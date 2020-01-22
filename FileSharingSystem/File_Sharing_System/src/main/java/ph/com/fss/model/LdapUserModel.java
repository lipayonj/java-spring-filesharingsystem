package ph.com.fss.model;

public class LdapUserModel {
	
	private String username;
	
	private String password;

	@Override
	public String toString() {
		return "LdapUserModel [username=" + username + ", password=" + password
				+ "]";
	}

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
}
