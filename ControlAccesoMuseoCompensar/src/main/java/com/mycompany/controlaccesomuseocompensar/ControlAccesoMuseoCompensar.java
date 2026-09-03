/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.controlaccesomuseocompensar;

/**
 *
 * @author Ing. Fernando Gómez
 */
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ControlAccesoMuseoCompensar {
    
    //Declaramos la matriz, el vector y un contador como objetos 
    //Se declaran privados para que solo esta clase los pueda acceder
    
    private String [][] matrizVisitantes = new String[50][4];    
    private double [] valoresAPagar = new double [50];
    private int contadorVisitantes = 0;
   
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new FormularioAcceso().setVisible(true);
        });
    }
    
    //Metodo para registo de visitantes
    public void registrarVisitantes(String nombre, String identidad, int edad,
                                    String categoria,double valor){
        if(contadorVisitantes < matrizVisitantes.length){
            
            matrizVisitantes[contadorVisitantes][0] = nombre;
            matrizVisitantes[contadorVisitantes][1] = identidad;
            matrizVisitantes[contadorVisitantes][2] = String.valueOf(edad);
            matrizVisitantes[contadorVisitantes][3] = categoria;

            valoresAPagar[contadorVisitantes] = valor;

            contadorVisitantes++;
            
            //Nota de registro agregado
            JOptionPane.showMessageDialog(
                    null, "Visitante registrado exitosamente ", 
                    "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    //Método para exportar visitantes
    public void exportarVisitantes(){               
        JFileChooser selector = new JFileChooser();
        
        int resultado = selector.showSaveDialog(null);
        double valorTotal = 0;//Variable que tendra el valor total a pagar
        
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = selector.getSelectedFile();
            try {
                FileWriter escribir = new FileWriter(archivo);
                PrintWriter salida = new PrintWriter(escribir);
                
                //Encabezados de la tabla
                salida.printf("%-20s %-15s %-8s %-18s %-15s%n",
                        "Nombre", "Identidad", "Edad", "Categoría", "Valor a pagar");
                salida.println("-----------------------------------------------------------------------");

                //Recorremos los visitantes registrados
                for (int i = 0; i < contadorVisitantes; i++) {

                    salida.printf("%-20s %-15s %-8s %-18s $%-14.2f%n",
                        matrizVisitantes[i][0],
                        matrizVisitantes[i][1],
                        matrizVisitantes[i][2],
                        matrizVisitantes[i][3],
                        valoresAPagar[i]);
                    
                    //Acumulamos el valor de cada visitante
                    valorTotal += valoresAPagar[i];                    
                    //Mostramos el valor total
                    salida.println("------------------------------");                      
                }                        
                salida.printf("VALOR TOTAL A PAGAR: $%.2f%n", valorTotal);
            salida.close();

            } catch (IOException e) {
                JOptionPane.showMessageDialog(
                null,"Error al guardar el archivo.");
                    
             }
                        JOptionPane.showMessageDialog(
                    null, "La información se ha exportado exitosamente.", 
                    "Confirmación exportación ", 
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
                    JOptionPane.showMessageDialog(
                    null, "Usted ha cancelado la exportación del archivo.",
                            "Exportación cancelada ",                      
                    JOptionPane.INFORMATION_MESSAGE);
           }
    }
}