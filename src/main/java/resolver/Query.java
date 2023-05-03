package resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.narvasoft.graphqldemo.model.User;
import com.narvasoft.graphqldemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {
	private UserRepository userRepository;
	//private TutorialRepository tutorialRepository;

	@Autowired
	public Query(UserRepository userRepository) {
		this.userRepository = userRepository;
		//this.tutorialRepository = tutorialRepository;
	}

	public Iterable<User> findAllUsers() {
		return userRepository.findAll();
	}



	public long countUsers() {
		return userRepository.count();
	}



}
