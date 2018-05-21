package com.hiberus.exam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hiberus.exam.dto.FavouriteNetworkDTO;
import com.hiberus.exam.entity.SocialNetwork;
import com.hiberus.exam.entity.User;
import com.hiberus.exam.repository.SocialNetworkRepository;
import com.hiberus.exam.repository.UserRepository;

@Component
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SocialNetworkRepository socialNetworkRespository;

	/**
	 * Retorna una lista completa de Usuarios. Cuando se retornan todos los Usuarios
	 * no se retornan las Redes Sociales asociadas. Si el Usuario tiene una Red
	 * Social favorita si se retorna
	 * 
	 * @return List
	 */
	public List<User> retrieveAllUsers() {
		return (List<User>) userRepository.findAll();
	}

	/**
	 * Retorna un Usuario por su ID
	 * 
	 * @param id
	 * @return Optional
	 */
	public Optional<User> retrieveUser(Long id) {
		return userRepository.findById(id);
	}

	/**
	 * Retorna las redes sociales de un Usuario
	 * 
	 * @param id
	 * @return Optional
	 */
	public Optional<List<SocialNetwork>> retrieveUserSocialNetworks(Long id) {
		Optional<User> userOptional = userRepository.findById(id);

		if (!userOptional.isPresent()) {
			return Optional.empty();
		}

		User user = userOptional.get();

		List<SocialNetwork> networks = user.getNetworks();
		return Optional.of(networks);
	}

	/**
	 * Crear un nuevo Usuario
	 * 
	 * @param user
	 * @return User
	 */
	public User createUser(User user) {
		return userRepository.save(user);
	}

	/**
	 * Crear una nueva Red Socil de un Usuario
	 * 
	 * @param id
	 * @param name
	 * @param url
	 * @return Optional
	 */
	public Optional<SocialNetwork> createUserSocialNetwork(Long id, SocialNetwork socialNetwork) {
		Optional<User> userOptional = userRepository.findById(id);

		if (!userOptional.isPresent()) {
			return Optional.empty();
		}

		User user = userOptional.get();

		// Validamos si el Usuario no tiene una Red Social, de ser asi esta nueva pasa a
		// ser su favorita
		if (user.getFavouriteNetwork() == null) {
			user.setFavouriteNetwork(socialNetwork);
		}

		// Agregamos la Red Social a la lista del Usuario
		user.getNetworks().add(socialNetwork);

		// Persistimos los cambios
		socialNetworkRespository.save(socialNetwork);
		userRepository.save(user);

		return Optional.of(socialNetwork);
	}

	/**
	 * Actualizacion de la Red Social favorita de un Usuario
	 * 
	 * @param id
	 * @param idSocialNetwork
	 * @return Optional
	 */
	public Optional<User> favoriteSocialNetwork(Long id, FavouriteNetworkDTO favouriteNetwork) {
		// Usuario
		Optional<User> userOptional = userRepository.findById(id);

		if (!userOptional.isPresent()) {
			return Optional.empty();
		}

		User user = userOptional.get();

		// Red Social
		Optional<SocialNetwork> socialNetworkOptional = socialNetworkRespository.findById(favouriteNetwork.getFavourite());

		if (!socialNetworkOptional.isPresent()) {
			return Optional.empty();
		}

		SocialNetwork socialNetwork = socialNetworkOptional.get();

		// Actualizacion de la Red Social Favorita
		user.setFavouriteNetwork(socialNetwork);
		userRepository.save(user);

		return Optional.of(user);
	}
}
