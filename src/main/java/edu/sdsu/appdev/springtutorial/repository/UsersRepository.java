package edu.sdsu.appdev.springtutorial.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import edu.sdsu.appdev.springtutorial.model.*;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UsersRepository extends CrudRepository<Users, Integer> {

	Optional<Users> findByUsername(String name);

}
