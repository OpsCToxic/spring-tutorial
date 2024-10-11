package edu.sdsu.appdev.springtutorial;


import org.springframework.data.repository.CrudRepository;


//import edu.sdsu.appdev.springtutorial.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {

}
