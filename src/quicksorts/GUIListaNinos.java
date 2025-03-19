/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quicksorts;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.util.List;

/**
 *
 * @author Maria Gabriela
 */
public class GUIListaNinos extends JFrame {
    private JTextArea areaNinos;
    private JButton btnOrdenar;
    private JButton btnVolver;
    private JFrame ventanaPadre;
    private GestionNinos gestionNinos;
    
    public GUIListaNinos(JFrame ventanaPadre, GestionNinos gestionNinos) {
        this.ventanaPadre = ventanaPadre;
        this.gestionNinos = gestionNinos;
        configurarVentana();
        inicializarComponentes();
        actualizarLista();
    }
    
    private void configurarVentana() {
        setTitle("Lista de Niños - Guardería");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }
    
private void inicializarComponentes() {
    // Panel principal
    JPanel panelPrincipal = new JPanel();
    panelPrincipal.setLayout(new BorderLayout());
    panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    
    // Título
    JLabel lblTitulo = new JLabel("Lista de Niños Registrados", SwingConstants.CENTER);
    lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
    panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
    
    // TextArea en lugar de JList
    areaNinos = new JTextArea();
    areaNinos.setFont(new Font("Monospaced", Font.PLAIN, 14)); // Fuente monoespaciada para mejor alineación
    areaNinos.setEditable(false); // Solo lectura
    
    // Añadir encabezado como parte del JTextArea
    JPanel panelContenido = new JPanel(new BorderLayout());
    
    JScrollPane scrollPane = new JScrollPane(areaNinos);
    panelContenido.add(scrollPane, BorderLayout.CENTER);
    
    panelPrincipal.add(panelContenido, BorderLayout.CENTER);
    
    // Panel de botones
    JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
    
    btnOrdenar = new JButton("Ordenar por Edad");
    btnOrdenar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Mostrar una opción para elegir el tipo de orden
            String[] opciones = {"Menor a Mayor", "Mayor a Menor"};
            int seleccion = JOptionPane.showOptionDialog(GUIListaNinos.this,
                    "Selecciona el orden de clasificación:",
                    "Seleccionar Orden",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]);
            
            if (seleccion == 0) {
                ordenarNinos(true);
            } else if (seleccion == 1) {
                ordenarNinos(false); 
            }
        }
    });
    
    btnVolver = new JButton("Volver");
    btnVolver.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            volverPantallaPrincipal();
        }
    });
    
    panelBotones.add(btnOrdenar);
    panelBotones.add(btnVolver);
    
    panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
    
    add(panelPrincipal);
}

private void ordenarNinos(boolean ascendente) {
    List<Nino> listaDeNinos = gestionNinos.obtenerListaNinos();
    
    if (listaDeNinos.isEmpty() || listaDeNinos.size() == 1) {
        JOptionPane.showMessageDialog(this, 
            "No hay suficientes niños para ordenar.", 
            "Información", 
            JOptionPane.INFORMATION_MESSAGE);
        return;
    }
    
    gestionNinos.ordenarPorEdad(ascendente); 
    actualizarLista();
    
    String mensaje = ascendente ? "Lista ordenada correctamente por edad (de menor a mayor)." : "Lista ordenada correctamente por edad (de mayor a menor).";
    JOptionPane.showMessageDialog(this, mensaje, "Ordenamiento Completado", JOptionPane.INFORMATION_MESSAGE);
}
    
    private void actualizarLista() {
        areaNinos.setText("");
        
        areaNinos.append("  Nombre                                    Edad\n");
        areaNinos.append("---------------------------------------------------\n");
        
        List<Nino> listaDeNinos = gestionNinos.obtenerListaNinos();
        
        if (listaDeNinos.isEmpty()) {
            areaNinos.append("  No hay niños registrados\n");
        } else {
            for (Nino nino : listaDeNinos) {
                String fila = String.format("  %-40s %d\n", nino.getNombre(), nino.getEdad());
                areaNinos.append(fila);
            }
        }
    }
    
    
    private void volverPantallaPrincipal() {
        this.dispose();
        ventanaPadre.setVisible(true);
    }
}