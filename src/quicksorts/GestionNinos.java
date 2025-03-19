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
  public void ordenarPorEdad(boolean ascendente) {
    if (listaNinos.size() <= 1) return;
    
    Nino[] ninos = listaNinos.toArray(new Nino[0]);
    
    // QuickSort implementado en una sola función
    quickSort(ninos, 0, ninos.length - 1, ascendente);
    
    // Actualizar la lista
    listaNinos.clear();
    for (Nino nino : ninos) {
        listaNinos.add(nino);
    }
}

private void quickSort(Nino[] ninos, int inicio, int fin, boolean ascendente) {
    if (inicio < fin) {
        Nino pivote = ninos[fin];
        int i = inicio - 1;
        
        for (int j = inicio; j < fin; j++) {
            if ((ascendente && ninos[j].getEdad() <= pivote.getEdad()) || 
                (!ascendente && ninos[j].getEdad() >= pivote.getEdad())) {
                i++;
                Nino temp = ninos[i];
                ninos[i] = ninos[j];
                ninos[j] = temp;
            }
        }
        
        Nino temp = ninos[i + 1];
        ninos[i + 1] = ninos[fin];
        ninos[fin] = temp;
        
        int posicionPivote = i + 1;
        
        quickSort(ninos, inicio, posicionPivote - 1, ascendente);
        quickSort(ninos, posicionPivote + 1, fin, ascendente);
    }
}

}
