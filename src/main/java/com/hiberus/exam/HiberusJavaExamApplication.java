package com.hiberus.exam;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.hiberus.exam.entity.SocialNetwork;
import com.hiberus.exam.entity.User;
import com.hiberus.exam.repository.SocialNetworkRepository;
import com.hiberus.exam.repository.UserRepository;

@SpringBootApplication
public class HiberusJavaExamApplication {

	public static void main(String[] args) {
		SpringApplication.run(HiberusJavaExamApplication.class, args);
	}

	/**
	 * Generamos registros de prueba de Usuarios y de Redes Sociales
	 * 
	 * @param userRepository
	 * @param socialNetworkRepository
	 * @return
	 */
	@Bean
	public CommandLineRunner runner(UserRepository userRepository, SocialNetworkRepository socialNetworkRepository) {
		return (args) -> {
			SocialNetwork sn1 = new SocialNetwork("Red Social Uno", "https://www.rs1.com");
			SocialNetwork sn2 = new SocialNetwork("Red Social Dos", "https://www.rs1.com");

			User user1 = new User("Usuario", "Uno");
			List<SocialNetwork> listUser1 = new ArrayList<>();
			listUser1.add(sn1);
			listUser1.add(sn2);
			user1.setNetworks(listUser1);
			user1.setFavouriteNetwork(sn1);

			User user2 = new User("Usuario", "Dos");
			List<SocialNetwork> listUser2 = new ArrayList<>();
			listUser2.add(sn1);
			listUser2.add(sn2);
			user2.setNetworks(listUser2);
			user2.setFavouriteNetwork(sn2);

			socialNetworkRepository.save(sn1);
			socialNetworkRepository.save(sn2);

			userRepository.save(user1);
			userRepository.save(user2);
		};
	}
}
