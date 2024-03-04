package apoiiiexpocanina;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Sergio Luis Morillo & Juan David Delgado
 */
public class APOIIIExpoCanina {

       private ArrayList<Perro> misPerros = new ArrayList<Perro>();
    private Scanner lector = new Scanner(System.in);;

    public static void main(String[] args) {
        APOIIIExpoCanina exposicion = new APOIIIExpoCanina();
        exposicion.mostrarMenu();

    }

    public void mostrarMenu() {
        boolean activo = true;
        do {
            System.out.println("__ MENU DE OPCIONES __\n"
                    + "1. Registrar un nuevo perro\n"
                    + "2. Modificar la información de un perro\n"
                    + "3. Eliminar a un perro\n"
                    + "4. Mostrar lista de perros registrados\n"
                    + "5. Mostrar información de un perro específico\n"
                    + "6. Ver perro ganador de la exposición\n"
                    + "7. Ver perro con el menor puntaje\n"
                    + "8. Ver perro más viejo\n"
                    + "9. Terminar programa\n");

            int opcion = lector.nextInt();
            switch (opcion) {
                case 1:
                	try {
                    agregarPerro();
                	} catch(NombreDuplicadoException e) {
                    System.out.println(e.getMessage());
                	}
                    break;
                case 2:
                    modificarPerro();
                    break;
                case 3:
                    eliminarPerro();
                    break;
                case 4:
                    mostrarPerros();
                    break;
                case 5:
                    mostrarInfoPerro();
                    break;
                case 6:
                    buscarGanador();
                    break;
                case 7:
                    buscarMenorPuntaje();
                    break;
                case 8:
                    buscarPerroMasViejo();
                    break;
                case 9:
                    activo = false;
                    System.out.println("Programa terminado");
                    break;
                default:
                    System.out.println("Opcion no valida, intentalo nuevamente");
            }
        } while (activo);
    }
   public void agregarPerro() throws NombreDuplicadoException {

    System.out.println("Ingrese el nombre del perro:");
    String nombre = lector.nextLine();
    if (!misPerros.isEmpty()) {
        for (Perro p : misPerros) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                throw new NombreDuplicadoException();
            }
        }
    }
 
    System.out.println("Ingrese la raza del perro:");
    String raza = lector.nextLine();

    System.out.println("Ingrese la edad del perro:");
    int edad = lector.nextInt();
    lector.nextLine(); 

    System.out.println("Ingrese la puntuación del perro:");
    int puntos = lector.nextInt();
    lector.nextLine(); 

    System.out.println("Ingrese la foto del perro:");
    String foto = lector.nextLine();

    Perro perroNuevo = new Perro(nombre, raza, edad, puntos, foto);
    misPerros.add(perroNuevo);

    System.out.println("Perro registrado exitosamente!");
}

//-------------------------------------------------------------------------------------------------
    private void modificarPerro() {
         
        if (misPerros.isEmpty()) {
        System.out.println("No hay perros registrados.");
        return;
    }

    System.out.println("Ingrese el índice (posicion 0 - x) del perro que desea modificar:");
    int indice = lector.nextInt();

    if (indice < 0 || indice >= misPerros.size()) {
        System.out.println("Índice inválido.");
        return;
    }

    Perro perroAModificar = misPerros.get(indice);

    System.out.println("Ingrese el nuevo nombre del perro:");
    String nombre = lector.next();
    perroAModificar.setNombre(nombre);

    System.out.println("Ingrese la nueva raza del perro:");
    String raza = lector.next();
    perroAModificar.setRaza(raza);

    System.out.println("Ingrese la nueva edad del perro:");
    int edad = lector.nextInt();
    perroAModificar.setEdad(edad);

    System.out.println("Ingrese la nueva puntuación del perro:");
    int puntuacion = lector.nextInt();
    perroAModificar.setPuntos(puntuacion);

    System.out.println("Perro modificado exitosamente!");

    }
//-------------------------------------------------------------------------------------------------
    private void eliminarPerro() {
        
    if (misPerros.isEmpty()) {
        System.out.println("No hay perros registrados.");
        return;
    }

    System.out.println("Ingrese el índice (posicion 0 - x) del perro que desea eliminar:");
    int indice = lector.nextInt();

    if (indice < 0 || indice >= misPerros.size()) {
        System.out.println("Índice inválido.");
        return;
    }

    misPerros.remove(indice);
    System.out.println("Perro eliminado exitosamente.");

    }
//-------------------------------------------------------------------------------------------------
    private void mostrarPerros() {
    if (misPerros.isEmpty()) {
        System.out.println("No hay perros registrados.");
        return;
    }

    System.out.println("Lista de perros registrados:");
    for (int i = 0; i < misPerros.size(); i++) {
        Perro perroActual = misPerros.get(i);
        System.out.printf("%d. %s (%s)\n", i + 1, perroActual.getNombre(), perroActual.getRaza());
    }

    }
//-------------------------------------------------------------------------------------------------
    private void mostrarInfoPerro() {
       if (misPerros.isEmpty()) {
        System.out.println("No hay perros registrados.");
        return;
    }

    System.out.println("Ingrese el índice (posicion 0 - x) del perro cuya información desea ver:");
    int indice = lector.nextInt();

    if (indice < 0 || indice >= misPerros.size()) {
        System.out.println("Índice inválido.");
        return;
    }

    Perro perroSeleccionado = misPerros.get(indice);
    System.out.printf("Nombre: %s\n", perroSeleccionado.getNombre());
    System.out.printf("Raza: %s\n", perroSeleccionado.getRaza());
    System.out.printf("Puntuación: %d\n", perroSeleccionado.getPuntos());

    }
//-------------------------------------------------------------------------------------------------
 private void buscarGanador() {
  if (misPerros.isEmpty()) {
    System.out.println("No hay perros registrados.");
    return;
  }

  int indiceGanador = 0;
  for (int i = 1; i < misPerros.size(); i++) {
    if (misPerros.get(i).getPuntos() > misPerros.get(indiceGanador).getPuntos()) {
      indiceGanador = i;
    }
  }

  System.out.printf("El ganador es: %s (%s) con una puntuación de %d.\n",
      misPerros.get(indiceGanador).getNombre(),
      misPerros.get(indiceGanador).getRaza(),
      misPerros.get(indiceGanador).getPuntos());
}
    //-------------------------------------------------------------------------------------------------
    private void buscarMenorPuntaje() {
       if (misPerros.isEmpty()) {
        System.out.println("No hay perros registrados.");
        return;
    }

    Perro perroConMenorPuntaje = misPerros.get(0);
    for (Perro perroActual : misPerros) {
        if (perroActual.getPuntos()< perroConMenorPuntaje.getPuntos()) {
            perroConMenorPuntaje = perroActual;
        }
    }

    System.out.printf("El perro con menor puntuación es: %s (%s) con una puntuación de %d.\n", perroConMenorPuntaje.getNombre(), perroConMenorPuntaje.getRaza(), perroConMenorPuntaje.getPuntos());

    }
//-------------------------------------------------------------------------------------------------
    private void buscarPerroMasViejo() {
      if (misPerros.isEmpty()) {
        System.out.println("No hay perros registrados.");
        return;
    }

    Perro perroMasViejo = misPerros.get(0);
    for (Perro perroActual : misPerros) {
        if (perroActual.getEdad() > perroMasViejo.getEdad()) {
            perroMasViejo = perroActual;
        }
    }

    System.out.printf("El perro más viejo es: %s (%s) con una edad de %d años.\n", perroMasViejo.getNombre(), perroMasViejo.getRaza(), perroMasViejo.getEdad());
    }

	
}