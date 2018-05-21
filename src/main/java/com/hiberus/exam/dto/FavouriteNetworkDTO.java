package com.hiberus.exam.dto;

import javax.validation.constraints.NotNull;

public class FavouriteNetworkDTO {
	@NotNull
	private Long favourite;

	public FavouriteNetworkDTO() {
		super();
	}

	public FavouriteNetworkDTO(@NotNull Long favourite) {
		super();
		this.favourite = favourite;
	}

	public Long getFavourite() {
		return favourite;
	}

	public void setFavourite(Long favourite) {
		this.favourite = favourite;
	}
}
