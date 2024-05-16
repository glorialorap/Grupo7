import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader; 
import java.io.IOException;
import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    public void menu() throws IOException{
        int op = 0;
        do{
            System.out.println("----MENU----");
            System.out.println("1. Crear archivo");
            System.out.println("2. Modificar");
            System.out.println("3. Borrar"); 
            op = scanner.nextInt();
            switch (op) {
                case 1:
                    crearArchivo();
                    break;
                case 2:
                    modificarArchivo();
                    break;
                case 3:
                    //eliminarArchivo();
                    break;
                default:
                    System.out.println("Elija una opcion valida");
            }
        } while (op != 0);
    }

    public void crearArchivo() throws IOException{
        System.out.println("Ingrese nombre del equipo");
        String equipo = scanner.next();

        System.out.println("Ingrese la cantidad de partidos jugados");
        int partidos = scanner.nextInt();

        System.out.println("Ingrese la cantidad de partidos ganados");
        int partidosGanados = scanner.nextInt();

        System.out.println("Ingrese la cantidad de partidos empatados");
        int partidosempatados = scanner.nextInt();

        System.out.println("Ingrese la cantidad de partidos perdidos");
        int partidosperdidos = scanner.nextInt();

        System.out.println("Ingrese la cantidad de puntos");
        int puntos = scanner.nextInt();

        File file = new File("Equipos");
        file.createNewFile();

        FileWriter writer = new FileWriter(file);
        writer.write("Equipo ingresado: " + equipo + "\n" +
                    "Partidos jugados: " + partidos + "\n" +
                    "Partidos ganados: " + partidosGanados + "\n" +
                    "Partidos empatados: " + partidosempatados + "\n" +
                    "Partidos perdidos: " + partidosperdidos + "\n" +
                    "Puntos: " + puntos);

        writer.flush();
        writer.close();

        System.out.println("Archivo creado");
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
        while ((linea = bufferedReader.readLine()) != null) { //lee todas las lineas del archivo
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
}