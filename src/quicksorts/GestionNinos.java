/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quicksorts;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maria Gabriela
 */
public class GestionNinos {
      private List<Nino> listaNinos = new ArrayList<>();
    
    public void agregarNino(String nombre, int edad) {
        listaNinos.add(new Nino(nombre, edad));
    }
    
    public List<Nino> obtenerListaNinos() {
        return listaNinos;
    }
    
    // Algoritmo QuickSort para ordenar por edad en una sola función
    public void ordenarPorEdad() {
        if (listaNinos.size() <= 1) return;
        
        Nino[] ninos = listaNinos.toArray(new Nino[0]);
        
        // QuickSort implementado en una sola función
        quickSort(ninos, 0, ninos.length - 1);
        
        // Actualizar la lista
        listaNinos.clear();
        for (Nino nino : ninos) {
            listaNinos.add(nino);
        }
    }
    //Funcion de ordenamiento rapido
    private void quickSort(Nino[] ninos, int inicio, int fin) {
        if (inicio < fin) {
            // Seleccionar el pivote (en este caso uso el último elemento)
            Nino pivote = ninos[fin];
            int i = inicio - 1;
            
            // Proceso de particion del arreglo
            for (int j = inicio; j < fin; j++) {
                if (ninos[j].getEdad() <= pivote.getEdad()) {
                    i++;
                    // Intercambiar ninos[i] y ninos[j]
                    Nino temp = ninos[i];
                    ninos[i] = ninos[j];
                    ninos[j] = temp;
                }
            }
            
            // Poner el pivote en su posicion 
            Nino temp = ninos[i + 1];
            ninos[i + 1] = ninos[fin];
            ninos[fin] = temp;
            
            int posicionPivote = i + 1;
            
            // Llamadas recursivas dentro de los sub arreglos
            quickSort(ninos, inicio, posicionPivote - 1);
            quickSort(ninos, posicionPivote + 1, fin);
        }
    }
}
