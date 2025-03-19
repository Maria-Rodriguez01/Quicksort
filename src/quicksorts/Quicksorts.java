/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package quicksorts;

/**
 *
 * @author Maria Gabriela
 */
public class Quicksorts {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
try {
        System.out.println("Iniciando aplicación...");
        GestionNinos gestionNinos = new GestionNinos();
        
        javax.swing.SwingUtilities.invokeLater(() -> {
            try {
                System.out.println("Creando ventana principal...");
                PantallaPrincipal pantalla = new PantallaPrincipal(gestionNinos);
            } catch (Exception e) {
                System.err.println("Error al crear la ventana: " + e.getMessage());
                e.printStackTrace();
            }
        });
    } catch (Exception e) {
        System.err.println("Error en el main: " + e.getMessage());
        e.printStackTrace();
    }
}
    }
    
