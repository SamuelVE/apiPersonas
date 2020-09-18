package controller;

import db.domain.People.model.Person;
import db.domain.People.service.implementation.PersonServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import utils.ModelMapper;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/db/personas")
public class DBController {
    private PersonServiceImplementation personSVC;

    private ModelMapper<Person, model.Person> modelMapper;

    private Function<List<Person>, List<model.Person>> getDtos = listOfDocuments ->
            listOfDocuments.stream().map(modelMapper::modelToDto).collect(Collectors.toList());

    @Autowired
    DBController(PersonServiceImplementation personSVC,  ModelMapper<Person, model.Person> modelMapper) {
        this.personSVC = personSVC;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<model.Person> getAll() {
        System.out.println("DBController -> getDtos()");
        return getDtos.apply(personSVC.findAll());
    }

    @GetMapping("/id/{id}")
    public model.Person getPerson(@PathVariable String id){
        System.out.println("DBController -> getPerson()");
        return Optional.of(personSVC.getById(id)).map(modelMapper::modelToDto).get();
    }

    @PostMapping
    public List<model.Person> post(@Valid @RequestBody model.Person person) {
        System.out.println("DBController -> post()");
        return getDtos.apply(personSVC.post(modelMapper.dtoToModel(person)));
    }


    @PutMapping
    public List<model.Person> put(@Valid @RequestBody model.Person person){
        System.out.println("DBController -> put()");
        return getDtos.apply(personSVC.update(modelMapper.dtoToModel(person)));
    }

    @DeleteMapping("/id/{id}")
    public List<model.Person> deleteById(@PathVariable String id) {
        System.out.println("DBController -> deletedById()");
        return getDtos.apply(personSVC.delete(id));
    }

    @GetMapping(params = {"age"})
    public List<model.Person> filterByAge(@RequestParam("age") String filter) {
        System.out.println("DBController -> filterByAge()");
        return getDtos.apply(personSVC.filterByAge(filter));
    }

    @GetMapping(params = {"name"})
    public List<model.Person> filterByName(@RequestParam("name") String name) {
        System.out.println("DBController -> filterByName()");
        return getDtos.apply(personSVC.filterByName(name));
    }

    @GetMapping(params = {"name","age"})
    public List<model.Person> filterByNameAndAge(@RequestParam(value = "age", required = false) String filter, @RequestParam(value = "name", required = false) String name) {
        System.out.println("DBController -> filterByNameAndAge()");
        return getDtos.apply(personSVC.filterByAgeAndName(filter, name));
    }
}
