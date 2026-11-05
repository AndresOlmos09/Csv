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
        
        try{
        System.out.println(leerTexto());
        
        }catch(IOException ex){
        
        System.out.println("Fallo por " + ex.getMessage());
            
        }
    }
}
