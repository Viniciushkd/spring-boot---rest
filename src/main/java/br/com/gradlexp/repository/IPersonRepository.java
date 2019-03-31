package br.com.gradlexp.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.gradlexp.bean.Person;

public interface IPersonRepository extends CrudRepository<Person, Long> {

	List<Person> findByFirstName(String firstName);
	
}