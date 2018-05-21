package com.hiberus.exam.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUser;

	@NotBlank
	private String name;

	@NotBlank
	private String surname;

	@OneToOne
	private SocialNetwork favouriteNetwork;

	@ManyToMany()
	@JoinTable(name = "user_socialnetwork", joinColumns = { @JoinColumn(name = "idUser") }, inverseJoinColumns = { @JoinColumn(name = "idSocialNetwork") })
	@JsonIgnore
	private List<SocialNetwork> networks;

	public User() {
		super();
	}

	public User(@NotNull String name, @NotNull String surname) {
		super();
		this.name = name;
		this.surname = surname;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public SocialNetwork getFavouriteNetwork() {
		return favouriteNetwork;
	}

	public void setFavouriteNetwork(SocialNetwork favouriteNetwork) {
		this.favouriteNetwork = favouriteNetwork;
	}

	public List<SocialNetwork> getNetworks() {
		return Optional.ofNullable(networks).isPresent() ? this.networks : new ArrayList<SocialNetwork>();
	}

	public void setNetworks(List<SocialNetwork> networks) {
		this.networks = networks;
	}

}
