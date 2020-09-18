package utils;

import db.domain.People.model.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonModelMapper implements ModelMapper<Person, model.Person> {
    @Override
    public Person dtoToModel(model.Person dto) {
        return Person.builder().
                age(dto.getAge()).
                birthDate(dto.getBirthDate()).
                city(dto.getCity()).
                country(dto.getCountry()).
                dni(dto.getDni()).
                errors(dto.getErrors()).
                id(dto.getId()).
                lastName(dto.getLastName()).
                name(dto.getName()).
                state(dto.getState()).
                build();
    }

    @Override
    public model.Person modelToDto(Person person) {
        return model.Person.builder().
                age(person.getAge()).
                birthDate(person.getBirthDate()).
                city(person.getCity()).
                country(person.getCountry()).
                dni(person.getDni()).
                errors(person.getErrors()).
                id(person.getId()).
                lastName(person.getLastName()).
                name(person.getName()).
                state(person.getState()).
                build();
    }
}
