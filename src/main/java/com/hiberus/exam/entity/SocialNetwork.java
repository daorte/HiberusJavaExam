package com.hiberus.exam.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class SocialNetwork {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSocialNetwork;

	@NotBlank
	private String name;

	@NotBlank
	private String url;

	public SocialNetwork() {
		super();
	}

	public SocialNetwork(@NotNull String name, @NotNull String url) {
		super();
		this.name = name;
		this.url = url;
	}

	public Long getIdSocialNetwork() {
		return idSocialNetwork;
	}

	public void setIdSocialNetwork(Long idSocialNetwork) {
		this.idSocialNetwork = idSocialNetwork;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
