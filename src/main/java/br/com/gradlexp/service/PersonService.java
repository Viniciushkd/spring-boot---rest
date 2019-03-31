package br.com.gradlexp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gradlexp.bean.Person;
import br.com.gradlexp.repository.IPersonRepository;

@Service
public class PersonService implements IPersonService {
	
	@Autowired
	private IPersonRepository personRepository;
	@Override
	public Person getPersonById(long id) {
		return personRepository.findById(id).get();
	}
	@Override
	public List<Person> getPersonByFirstName(String firstName) {
		return personRepository.findByFirstName(firstName);
	}
	@Override
	public void savePerson(Person person) {
		personRepository.save(person);
	}
	@Override
	public Iterable<Person> getAllPerson() {
		return personRepository.findAll();
	}
	@Override
	public void updatePerson(Long id, Person person) {
		Person personSelect = getPersonById(id);
		personSelect.setFirstName(person.getFirstName());
		personSelect.setLastName(person.getLastName());
		personRepository.save(personSelect);
	}
	@Override
	public void deletePerson(Long id) {
		Person person = getPersonById(id);
		personRepository.delete(person);
	}

}
