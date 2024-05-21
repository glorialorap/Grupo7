import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);

    public void menu() throws IOException {
        int op = 0;
        do {
            System.out.println("----MENU----");
            System.out.println("1. Crear archivo");
            System.out.println("2. Modificar");
            System.out.println("3. Borrar");
            System.out.println("4. Leer archivo");
            System.out.println("0. Salir");
            op = scanner.nextInt();
            switch (op) {
                case 1:
                    crearArchivo();
                    break;
                case 2:
                    modificarArchivo();
                    break;
                case 3:
                    eliminarArchivo();
                    break;
                    
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Elija una opción válida");
            }

        } while (op != 0);
    }

    public void crearArchivo() throws IOException {
        System.out.println("Ingrese nombre del equipo");
        String equipo = scanner.next();
    
        System.out.println("Ingrese la cantidad de partidos jugados");
        int partidos = scanner.nextInt();
    
        System.out.println("Ingrese la cantidad de partidos ganados");
        int partidosGanados = scanner.nextInt();
    
        System.out.println("Ingrese la cantidad de partidos empatados");
        int partidosEmpatados = scanner.nextInt();
    
        System.out.println("Ingrese la cantidad de partidos perdidos");
        int partidosPerdidos = scanner.nextInt();
    
        System.out.println("Ingrese la cantidad de puntos");
        int puntos = scanner.nextInt();
    
        File file = new File("Equipos");
    
        // Abre el FileWriter en modo append
        FileWriter writer = new FileWriter(file, true);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
    
        bufferedWriter.write("Equipo ingresado: " + equipo + "\n");
        bufferedWriter.write("Partidos jugados: " + partidos + "\n");
        bufferedWriter.write("Partidos ganados: " + partidosGanados + "\n");
        bufferedWriter.write("Partidos empatados: " + partidosEmpatados + "\n");
        bufferedWriter.write("Partidos perdidos: " + partidosPerdidos + "\n");
        bufferedWriter.write("Puntos: " + puntos + "\n");
        bufferedWriter.write("\n"); // Añade una línea en blanco para separar los equipos
    
        bufferedWriter.flush();
        bufferedWriter.close();
    
        System.out.println("Archivo actualizado con el nuevo equipo");
    }
    

    public void modificarArchivo() throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el nombre del equipo cuya información desea modificar:");
        String equipoBuscado = scanner.nextLine().trim();

        File file = new File("Equipos");
        if (!file.exists()) {
            System.out.println("El archivo no existe.");
            return;
        }

        File tempFile = new File("Equipos_temp.txt");

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile));

        String linea;
        boolean equipoEncontrado = false;
        while ((linea = bufferedReader.readLine()) != null) { // lee todas las lineas del archivo
            if (linea.startsWith("Equipo ingresado:")) {
                String nombreEquipo = linea.substring("Equipo ingresado: ".length());
                if (nombreEquipo.equals(equipoBuscado)) {
                    equipoEncontrado = true;
                    System.out.println("Equipo encontrado, ingrese la nueva información:");

                    System.out.println("Ingrese la cantidad de partidos jugados:");
                    int nuevosPartidosJugados = Integer.parseInt(scanner.nextLine());
                    System.out.println("Ingrese la cantidad de partidos ganados");
                    int nuevosPartidosGanados = Integer.parseInt(scanner.nextLine());
                    System.out.println("Ingrese la cantidad de partidos empatados");
                    int nuevosPartidosEmpatados = Integer.parseInt(scanner.nextLine());
                    System.out.println("Ingrese la cantidad de partidos perdidos");
                    int nuevosPartidosPerdidos = Integer.parseInt(scanner.nextLine());
                    System.out.println("Ingrese la cantidad de puntos");
                    int nuevosPuntos = Integer.parseInt(scanner.nextLine());

                    bufferedWriter.write("Equipo ingresado: " + nombreEquipo + "\n");
                    bufferedWriter.write("Partidos jugados: " + nuevosPartidosJugados + "\n");
                    bufferedWriter.write("Partidos ganados: " + nuevosPartidosGanados + "\n");
                    bufferedWriter.write("Partidos empatados: " + nuevosPartidosEmpatados + "\n");
                    bufferedWriter.write("Partidos perdidos: " + nuevosPartidosPerdidos + "\n");
                    bufferedWriter.write("Puntos: " + nuevosPuntos + "\n");

                    // Leer y omitir las líneas antiguas del equipo
                    bufferedReader.readLine(); // Saltar la línea antigua "Partidos jugados"
                    bufferedReader.readLine();
                    bufferedReader.readLine();
                    bufferedReader.readLine();
                    bufferedReader.readLine();
                } else {
                    bufferedWriter.write(linea + "\n");

                }
            } else {
                bufferedWriter.write(linea + "\n");
            }
        }

        bufferedReader.close();
        bufferedWriter.close();

        if (!equipoEncontrado) {
            System.out.println("Equipo no encontrado.");
            tempFile.delete();
        } else {
            file.delete();
            tempFile.renameTo(file);
            System.out.println("Archivo modificado exitosamente.");
        }
    }

    public void eliminarArchivo() throws IOException {
        System.out.println("Ingrese el nombre del equipo el cual desea eliminar:");
        String equipoAEliminar = scanner.next().trim();

        File file = new File("Equipos");
        if (!file.exists()) {
            System.out.println("El archivo no existe.");
            return;
        }

        File tempFile = new File("Equipos_temp.txt");

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile));

        String linea;
        boolean equipoEncontrado = false;
        while ((linea = bufferedReader.readLine()) != null) {
            if (linea.startsWith("Equipo ingresado:")) {
                String nombreEquipo = linea.substring("Equipo ingresado: ".length());
                if (nombreEquipo.equals(equipoAEliminar)) {
                    equipoEncontrado = true;
                    // Leer y omitir las líneas correspondientes a la información del equipo
                    bufferedReader.readLine(); // Partidos jugados
                    bufferedReader.readLine(); // Partidos ganados
                    bufferedReader.readLine(); // Partidos empatados
                    bufferedReader.readLine(); // Partidos perdidos
                    bufferedReader.readLine(); // Puntos
                } else {
                    bufferedWriter.write(linea + "\n");
                }
            } else {
                bufferedWriter.write(linea + "\n");
            }
        }

        bufferedReader.close();
        bufferedWriter.close();

        if (!equipoEncontrado) {
            System.out.println("Equipo no encontrado.");
            tempFile.delete();
        } else {
            file.delete();
            tempFile.renameTo(file);
            System.out.println("Equipo eliminado exitosamente.");
        }
    }

}