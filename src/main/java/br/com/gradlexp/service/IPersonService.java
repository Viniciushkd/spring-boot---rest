package br.com.gradlexp.service;

import br.com.gradlexp.bean.Person;

public interface IPersonService {
	
	public void savePerson(Person person);
	public Iterable<Person> getAllPerson();
	public Person getPersonById(long id);
	public void updatePerson(Long id, Person customer);
	public void deletePerson(Long id);

}
