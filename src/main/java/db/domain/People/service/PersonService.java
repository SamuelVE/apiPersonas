package db.domain.People.service;

import db.domain.People.model.Person;
import java.util.List;

public interface PersonService {

    List<Person> post(Person person);

    List<Person> update(Person person);

    Person getById(String id);

    List<Person> delete(String id);

    List<Person> findAll();

    List<Person> refresh();

    List<Person> filterByAge(String filter);

    List<Person> filterByName(String name);

    List<Person> filterByAgeAndName(String ageFilter, String name);
}
