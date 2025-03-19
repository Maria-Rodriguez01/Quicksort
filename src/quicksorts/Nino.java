/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quicksorts;

/**
 *
 * @author Maria Gabriela
 */
public class Nino {
    String nombre;
    int edad;
    
    public Nino(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public int getEdad() {
        return edad;
    }
    
    @Override
    public String toString() {
        return nombre + " - " + edad + " años";
    }
}
