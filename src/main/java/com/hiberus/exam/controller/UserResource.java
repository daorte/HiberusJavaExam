package com.hiberus.exam.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hiberus.exam.dto.FavouriteNetworkDTO;
import com.hiberus.exam.entity.SocialNetwork;
import com.hiberus.exam.entity.User;
import com.hiberus.exam.exception.SocialNetworkCreationException;
import com.hiberus.exam.exception.UserModificationException;
import com.hiberus.exam.exception.UserNetworksNotFoundException;
import com.hiberus.exam.exception.UserNotFoundException;
import com.hiberus.exam.service.UserService;

@RestController
public class UserResource {

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return userService.retrieveAllUsers();
	}

	@GetMapping("/users/{id}")
	public Resource<User> retrieveStudent(@PathVariable long id) {
		Optional<User> user = userService.retrieveUser(id);

		if (!user.isPresent()) {
			throw new UserNotFoundException("ERR-001");
		}

		Resource<User> resource = new Resource<User>(user.get());

		return resource;
	}

	@GetMapping("/users/{id}/networks")
	public List<SocialNetwork> retrieveUserSocialNetworks(@PathVariable long id) {
		Optional<List<SocialNetwork>> networks = userService.retrieveUserSocialNetworks(id);

		if (!networks.isPresent()) {
			throw new UserNetworksNotFoundException("ERR-002");
		}

		return networks.get();

	}

	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User newUser = userService.createUser(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getIdUser()).toUri();

		return ResponseEntity.created(location).build();
	}

	@PostMapping("/users/{id}/networks")
	public ResponseEntity<SocialNetwork> createSocialNetwork(@PathVariable long id, @Valid @RequestBody SocialNetwork socialNetwork) {
		Optional<SocialNetwork> newSocialNetwork = userService.createUserSocialNetwork(id, socialNetwork);

		if (!newSocialNetwork.isPresent()) {
			throw new SocialNetworkCreationException("ERR-003");
		}

		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/users/{id}/fav")
	public ResponseEntity<User> favoriteSocialNetwork(@PathVariable long id, @Valid @RequestBody FavouriteNetworkDTO favouriteNetwork) {
		Optional<User> updatedUser = userService.favoriteSocialNetwork(id, favouriteNetwork);

		if (!updatedUser.isPresent()) {
			throw new UserModificationException("ERR-004");
		}

		return ResponseEntity.noContent().build();
	}
}
