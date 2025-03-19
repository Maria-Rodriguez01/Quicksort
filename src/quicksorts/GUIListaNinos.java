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
        
        btnOrdenar = new JButton("Ordenar por Edad (QuickSort)");
        btnOrdenar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ordenarNinos();
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
    
    private void actualizarLista() {
        // Limpiar el área de texto
        areaNinos.setText("");
        
        // Añadir encabezado
        areaNinos.append("  Nombre                                    Edad\n");
        areaNinos.append("---------------------------------------------------\n");
        
        List<Nino> listaDeNinos = gestionNinos.obtenerListaNinos();
        
        if (listaDeNinos.isEmpty()) {
            areaNinos.append("  No hay niños registrados\n");
        } else {
            for (Nino nino : listaDeNinos) {
                // Formatear cada línea con espacios fijos para alinear columnas
                String fila = String.format("  %-40s %d\n", nino.getNombre(), nino.getEdad());
                areaNinos.append(fila);
            }
        }
    }
    
    private void ordenarNinos() {
        List<Nino> listaDeNinos = gestionNinos.obtenerListaNinos();
        
        if (listaDeNinos.isEmpty() || listaDeNinos.size() == 1) {
            JOptionPane.showMessageDialog(this, 
                "No hay suficientes niños para ordenar.", 
                "Información", 
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        gestionNinos.ordenarPorEdad();
        actualizarLista();
        
        JOptionPane.showMessageDialog(this, 
            "Lista ordenada correctamente por edad (de menor a mayor).", 
            "Ordenamiento Completado", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void volverPantallaPrincipal() {
        this.dispose();
        ventanaPadre.setVisible(true);
    }
}