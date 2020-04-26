package br.com.fiap.carro.autonomo.security;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JwtRequest implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;
	
	@JsonProperty(value = "usuario")
	private String username;
	
	@JsonProperty(value = "senha")
	private String password;

	public JwtRequest() {
	}

	public JwtRequest(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}