package br.com.gradlexp.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.gradlexp.bean.Person;
import br.com.gradlexp.service.IPersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private IPersonService ps;
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Person person){
		try {
			ps.savePerson(person);
			return new ResponseEntity<>(new URI("/save/"+person.getId()), HttpStatus.CREATED);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping
	public @ResponseBody Iterable<Person> findAll(){
		return ps.getAllPerson();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Person> findById(@PathVariable("id") Long id){
		Person person = ps.getPersonById(id);
		return new ResponseEntity<Person>(person, HttpStatus.OK);
	}
	
	@GetMapping("/find/{firstName}")
	public List<Person> findByFirstName(@PathVariable("firstName") String firstName){
		return ps.getPersonByFirstName(firstName);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Person person){
		try {
			ps.updatePerson(id, person);
			return new ResponseEntity<>(new URI("/update/"+id), HttpStatus.CREATED);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		ps.deletePerson(id);
		try {
			return new ResponseEntity<>(new URI("/delete/"+id), HttpStatus.OK);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

		}
	}
}
