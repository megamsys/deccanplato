package org.megam.core.api.secure;

public class AccessToken {
	
	private double token;
	private String email;
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String tempEmail) {
		this.email  = tempEmail;
	}
	
	public double getToken() {
		return token;
	}
	
	public void setToken(double token) {
		this.token  = token;
	}

}
