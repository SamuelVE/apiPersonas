package business;

import model.Person;

import java.util.ArrayList;
import java.util.List;

public class Repo {

    private static List<Person> person = new ArrayList<>();
    public static List<Person> getPeople() {
        Person p = new Person();
        p.setId("1");
        p.setName("samuel");
        p.setLastName("vera");
        p.setAge(21);
        p.setDni(95745706l);
        p.setBirthDate("02/06/1998");
        p.setCity("Caracas");
        p.setCountry("Vzla");
        p.setState("Miranda");

        person.add(p);

        p = new Person();
        p.setId("2");
        p.setName("julian");
        p.setLastName("lopez");
        p.setAge(26);
        p.setDni(95545706l);
        p.setBirthDate("02/06/1993");
        p.setCity("Bogota");
        p.setCountry("Colombia");
        p.setState("Cundinamarca");

        person.add(p);

        p = new Person();
        p.setId("3");
        p.setName("juan");
        p.setLastName("lopez");
        p.setAge(26);
        p.setDni(95532706l);
        p.setBirthDate("02/06/1993");
        p.setCity("Bogota");
        p.setCountry("Colombia");
        p.setState("Cundinamarca");

        person.add(p);

        p = new Person();
        p.setId("4");
        p.setName("juan");
        p.setLastName("lopez");
        p.setAge(26);
        p.setDni(95545706l);
        p.setBirthDate("02/06/1993");
        p.setCity("Bogota");
        p.setCountry("Colombia");
        p.setState("Cundinamarca");

        person.add(p);

        p = new Person();
        p.setId("5");
        p.setName("Juan");
        p.setLastName("lopez");
        p.setAge(23);
        p.setDni(95546506l);
        p.setBirthDate("02/06/1993");
        p.setCity("Bogota");
        p.setCountry("Colombia");
        p.setState("Cundinamarca");

        person.add(p);
        return person;
    }
}
