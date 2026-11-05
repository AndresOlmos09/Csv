/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.csv;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 *
 * @author Estudiante
 */
public class CSV {

    private static final String RUTA = "Datos.csv";
    private static final String SEPARADOR = ",";
    private static final String CABECERA = "nombre,edad,carrera";

    public static List<String []> leerFilas() throws IOException{
        List<String[]> filas = new ArrayList<>();
        if(!new File(RUTA).exists()) return filas;
       
        try(FileReader fr = new FileReader(RUTA, StandardCharsets.UTF_8); BufferedReader br = new BufferedReader(fr)){
            String linea;
            boolean primero = true;
            while((linea = br.readLine()) != null){
                if(primero){
                    primero = false; continue;
                }
               
                if(!linea.trim().isEmpty()){
                    filas.add(linea.split(SEPARADOR));
                }
            }
        }
       
        return filas;
    }
   
    public static String leerTexto() throws IOException{
        if(!new File(RUTA).exists()) return "El archivo Datos.csv no existe todavía";
       
        StringBuilder sb = new StringBuilder();
        List<String[]> filas = leerFilas();
        sb.append("Total de registros: ").append(filas.size()).append("\n\n");
        sb.append(String.format("%-15s %-6s %-15s%n", "NOMBRE", "EDAD", "CARRERA"));
       
        sb.append("-".repeat(40)).append("\n");
       
        for(String []fila : filas){
            if(fila.length >= 3){
                sb.append(String.format("%-15s %-6s %-15s%n", fila[0].trim(), fila[1].trim(), fila[2].trim()));
            }
        }
       
        return sb.toString();
    }
    
    public static void Escribir(String nombre, int edad, String carrera) throws IOException{
        boolean esNuevo= !new File(RUTA).exists();
        try(FileWriter fw = new FileWriter(RUTA, StandardCharsets.UTF_8, true);
                PrintWriter pw = new PrintWriter(fw)){
            if(esNuevo){
                pw.println(CABECERA);
            }
            
            pw.println(nombre + SEPARADOR + edad + SEPARADOR + carrera);
        }
        
    }
    
    
    
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        String salir = null;
        
        //Mostrando estudiantes ya registrados previamente
        try{
        System.out.println(leerTexto());
        
        }catch(IOException ex){
        
        System.out.println("Fallo por " + ex.getMessage());
            
        }
        
        
        //Ingresando un estudiante 
        try{
            Escribir("carlos",18,"civil");
        } catch(IOException ex){
            System.out.println("Fallo escritura por "+ ex.getMessage());
        }
        
        
        //Registro de estudiantes en consola con scanner (OPCIÓN 1)
        do{
            try {

            System.out.println("\nRegistrar Estudiante");

            System.out.print("Ingrese el nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Ingrese la edad: ");
            int edad = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Ingrese la carrera: ");
            String carrera = scanner.nextLine();

            Escribir(nombre, edad, carrera);

            System.out.println("\nEl estudiante se ha registrado correctamente");

        } catch (IOException ex) {

            System.out.println("Fallo escritura por: " + ex.getMessage());

        } catch (java.util.InputMismatchException ex) {

            System.out.println("Error: La edad debe ser un número entero.");
            scanner.nextLine();
        }
            
        System.out.print("\n¿Desea registrar otro estudiante? (s/n): ");
        salir = scanner.nextLine(); 
        
        }while(salir.equalsIgnoreCase("s"));
        
        
        //Mostrando nuevamente estudiantes despues de agregar
        try{
            System.out.println("\nLISTA DE ESTUDIANTES DESPUES DE ACTUALIZACION - - -  ");
            System.out.println(leerTexto());
        
        }catch(IOException ex){
        
        System.out.println("Fallo por " + ex.getMessage());
            
        }
        
    }
}
