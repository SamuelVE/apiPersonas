package controller;

import business.PersonBusiness;
import model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonBusiness personBusiness;

    PersonController(PersonBusiness personBusiness) {
        this.personBusiness = personBusiness;
    }

    @RequestMapping("/all")
    public List<Person> getAll() {
        System.out.println("PersonController -> findAll()");
        return personBusiness.getAll();
    }

    @RequestMapping("/id/{id}")
    public Person getPerson(@PathVariable String id) {
        System.out.println("PersonController -> getPerson()");
        return personBusiness.getPerson(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public List<Person> createPerson(@RequestBody Person person) {
        System.out.println("PersonController -> createPerson()");
        return personBusiness.createPerson(person);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public List<Person> updatePerson(@RequestBody Person person) {
        System.out.println("PersonController -> updatePerson()");
        return personBusiness.updatePerson(person);
    }

    @RequestMapping(method = RequestMethod.DELETE, value="/dni/{dni}")
    public List<Person> removePerson(@PathVariable String dni) {
        System.out.println("PersonController -> removePerson()");
        return personBusiness.removePerson(Long.valueOf(dni));
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public List<Person> refresh(@RequestBody Person Person) {
        System.out.println("PersonController -> removePerson()");
        return personBusiness.refresh();
    }


    @RequestMapping()
    public List<Person> filterByName(@RequestParam String name) {
        System.out.println("PersonController -> filterByName");
        return personBusiness.isOk(name, null) ? personBusiness.getPeopleByName(name) : personBusiness.getError();
    }

    @RequestMapping(params = {"name", "age"})
    public List<Person> filterByNameAndAge(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "age") String ageRange) {
        System.out.println("PersonController -> filterByNameAndAge");
        return personBusiness.getPeopleByAgeRange(name, ageRange);
    }
}
