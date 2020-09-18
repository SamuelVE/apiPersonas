package business;

import enums.ErrorsTestProject;
import model.Person;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class PersonBusiness {
    private List<Person> person = new ArrayList<>();
    private String errorMessage;

    public PersonBusiness() {
        person = Repo.getPeople();
    }

    private Person getPerson(Predicate<Person> predicate) {
        return person.stream().filter(predicate).findFirst().orElse(null);
    }

    public Person getPerson(String id) {
        return getPerson(p -> p.getId().equals(id));
    }

    private Person getPerson(Long dni) {
        return getPerson(p -> p.getDni().equals(dni));
    }

    private List<Person> getPeople(Predicate<Person> predicate) {
        return person.stream().filter(predicate).collect(Collectors.toList());
    }

    private List<Person> getPeople(Predicate<Person> predicate, List<Person> people) {
        return people.stream().sorted(Comparator.comparing(Person::getAge)).filter(predicate).collect(Collectors.toList());
    }

    public List<Person> getPeopleByName(String name) {
        return getPeople(p-> p.getName().equalsIgnoreCase(name));
    }

    public List<Person> getPeopleByAgeRange(String name, String ageRange) {
        List<Person> people;
        if (isOk(name, ageRange)) {
            people = getPeopleByName(name);
            int age = Integer.valueOf(ageRange.replaceAll("[^0-9]", ""));
            if (ageRange.contains(">")) {
                people = getPeople(p -> p.getAge() > age, people);
            } else if (ageRange.contains("<")) {
                people = getPeople(p -> p.getAge() < age, people);
            } else {
                people = getPeople(p -> p.getAge().equals(age), people);
            }
        } else {
            people = getError();
        }
        return people;
    }

    public List<Person> getAll() {
        return person;
    }

    public List<Person> createPerson(Person newPerson) {
        Optional<Person> existingPerson = Optional.ofNullable(getPerson(newPerson.getDni()));
        if (!existingPerson.isPresent() && isOk(newPerson)) {
            newPerson.setId(String.valueOf(person.size() + 1));
            person.add(newPerson);
            return getAll();
        } else {
            if (existingPerson.isPresent()) {
                errorMessage = ErrorsTestProject.EXISTING_PERSON.getMessage();
            }
            return getError();
        }
    }

    public List<Person> updatePerson(Person body) {
        Optional<Person> existingPerson = Optional.ofNullable(getPerson(body.getDni()));
        if (existingPerson.isPresent() && isOk(body)) {
            body.setId(existingPerson.get().getId());
            person.set(person.indexOf(existingPerson.get()), body);
            return getAll();
        } else {
            if (!existingPerson.isPresent()) {
                errorMessage = ErrorsTestProject.UNEXISTENT_PERSON.getMessage();
            }
            return getError();
        }
    }

    public List<Person> removePerson(Long dni) {
        Optional<Person> existingPerson = Optional.ofNullable(getPerson(dni));
        if (existingPerson.isPresent()) {
            person.remove(existingPerson.get());
        }
        return getAll();
    }

    public List<Person> refresh() {
        person = new ArrayList<>();
        return person;
    }

    public boolean isOk(String value, String age) {
        Boolean result = Boolean.TRUE;
        if (value != null && !areThisOK(value)) {
            errorMessage = ErrorsTestProject.TEXT_EXCEED_LIMITS.getMessage();
            result = Boolean.FALSE;
        }
        if (age != null && !isAgeOK(age)) {
            errorMessage = ErrorsTestProject.NEGATIVE_AGE.getMessage();
            result = Boolean.FALSE;
        }
        return result;
    }

    private boolean areThisOK(String... strings) {
        Boolean result = Boolean.TRUE;
        if (Arrays.stream(strings).anyMatch(s -> s.length() >= 15)) {
            errorMessage = ErrorsTestProject.TEXT_EXCEED_LIMITS.getMessage();
            result = Boolean.FALSE;
        }
        return result;
    }

    private boolean isAgeOK(String ageRange) {
        Boolean result = Boolean.TRUE;
        if (ageRange.contains("-") || ageRange.replaceAll("[^0-9]", "").equals("0") || ageRange == null) {
            errorMessage = ErrorsTestProject.NEGATIVE_AGE.getMessage();
            result = Boolean.FALSE;
        }
        return result;
    }

    private boolean isOk(Person person) {
        if (person.getDni() == 0) {
            errorMessage = ErrorsTestProject.DNI_IS_EMPTY.getMessage();
        }
        return areThisOK(person.getName(), person.getBirthDate(), person.getCity(), person.getCountry(), person.getLastName(), person.getState())
                && isAgeOK(String.valueOf(person.getAge()))
                && (person.getDni() != 0);
    }

    public List<Person> getError() {
        Person p = new Person();
        p.setErrors(Collections.singletonMap("errors",  errorMessage));
        return Collections.singletonList(p);
    }

}
