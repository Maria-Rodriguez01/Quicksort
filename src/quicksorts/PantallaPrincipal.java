/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quicksorts;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.*;

/**
 *
 * @author Maria Gabriela
 */
public class PantallaPrincipal extends JFrame{
    private JButton btnRegistrarNino;
    private JButton btnVerLista;
    private GestionNinos gestionNinos;
    
    public PantallaPrincipal(GestionNinos gestionNinos) {
        this.gestionNinos = gestionNinos;
        configurarVentana();
        inicializarComponentes();
        this.setVisible(true);
    }
    
    private void configurarVentana() {
        setTitle("Sistema de Guardería");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setVisible(true);
    }
    
    private void inicializarComponentes() {
        // Panel principal con imagen de fondo
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Título
        JLabel lblTitulo = new JLabel("Guardería Infantil", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
        
        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(2, 1, 10, 20));
        
        btnRegistrarNino = new JButton("Registrar Nuevo Niño");
        btnRegistrarNino.setFont(new Font("Arial", Font.PLAIN, 16));
        btnRegistrarNino.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarDialogoRegistro();
            }
        });
        
        btnVerLista = new JButton("Ver Lista de Niños");
        btnVerLista.setFont(new Font("Arial", Font.PLAIN, 16));
        btnVerLista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirPantallaLista();
            }
        });
        
        panelBotones.add(btnRegistrarNino);
        panelBotones.add(btnVerLista);
        
        JPanel panelCentro = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelCentro.add(panelBotones);
        
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);
        
        
        add(panelPrincipal);
    }
    
    private void mostrarDialogoRegistro() {
        JTextField txtNombre = new JTextField();
        JTextField txtEdad = new JTextField(); // Cambiamos de JSpinner a JTextField para ingreso manual
        
        Object[] mensaje = {
            "Nombre del niño:", txtNombre,
            "Edad (1-12 años):", txtEdad
        };
        
        int opcion = JOptionPane.showConfirmDialog(this, mensaje, "Registro de Niño", JOptionPane.OK_CANCEL_OPTION);
        
        if (opcion == JOptionPane.OK_OPTION) {
            String nombre = txtNombre.getText().trim();
            String edadStr = txtEdad.getText().trim();
            
            // Validamos que el nombre no esté vacío
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese un nombre válido.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Validamos que la edad sea numérica y esté entre 1 y 12
            try {
                int edad = Integer.parseInt(edadStr);
                
                if (edad < 1 || edad > 12) {
                    JOptionPane.showMessageDialog(this, 
                        "La edad debe estar entre 1 y 12 años.", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Si pasó todas las validaciones, registramos al niño
                gestionNinos.agregarNino(nombre, edad);
                JOptionPane.showMessageDialog(this, 
                    "Niño registrado exitosamente.", 
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
                
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, 
                    "Por favor ingrese un número válido para la edad.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void abrirPantallaLista() {
        GUIListaNinos pantallaLista = new GUIListaNinos(this, gestionNinos);
        pantallaLista.setVisible(true);
        this.setVisible(false);
    }
}
