package controller;

@Component
public class Controlador {

    @Autowired
    private ControladorPersona controladorPersona;

    switch (System.getProperty("opcionMenu")) {
        case 1:
            controladorPersona.crearPersona();
            break;
        case 2:
            controladorPersona.modificarPersona();
            break;
    }


}
