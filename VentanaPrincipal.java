import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

/**
 * Esta clase denominada VentanaPrincipal define una interfaz gráfica
 * que permitirá crear un array de notas. Luego, se puede calcular el
 * promedio de notas, la desviación, la nota mayor y la nota menor del array.
 * 
 * @version 1.2/2020
 */

// Label y un textfield para nota (SOLO UNA) -
// Necesito un JList para verlas -
// BOTONES:  añadir nota
// eliminar una nota
// borrar todas las notas
// CALCULAR hace los promedios , desviacion ,mayor y menor como el ejercicio original

public class VentanaPrincipal extends JFrame implements ActionListener {
    private Container contenedor;
    private JLabel nota, promedio, desviacion, mayor, menor;
    private JTextField campoNota;
    private JButton calcular, limpiar, addNota;
    private JList campoLista;
    DefaultListModel<Double> modeloLista;
    JScrollPane scrollLista;

    static Notas notas = new Notas();


    public VentanaPrincipal() {
        inicio(); // Inicializa completamente la VISTA
        setTitle("Calculadora Notas");
        setSize(280, 380);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    private void inicio() {
        contenedor = getContentPane(); /* Obtiene el panel de contenidos de la ventana */
        contenedor.setLayout(null); /* Establece que el contenedor no tiene layout */

         // Campo de entrada
    nota = new JLabel("Nota:");
    nota.setBounds(20, 20, 50, 25);
    campoNota = new JTextField();
    campoNota.setBounds(80, 20, 170, 25);

    // Modelo
    modeloLista = new DefaultListModel<>();

    // Lista de notas
    campoLista = new JList<>();
    campoLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    campoLista.setModel(modeloLista);


    scrollLista = new JScrollPane();
    scrollLista.setBounds(20, 60, 230, 80);
    scrollLista.setViewportView(campoLista);

    // Botón Añadir
    addNota = new JButton("Añadir");
    addNota.setBounds(20, 150, 100, 25);
    addNota.addActionListener(this);

    // Botón Calcular
    calcular = new JButton("Calcular");
    calcular.setBounds(130, 150, 120, 25);
    calcular.addActionListener(this);

    // Botón Limpiar
    limpiar = new JButton("Limpiar");
    limpiar.setBounds(80, 185, 100, 25);
    limpiar.addActionListener(this);

    // Etiquetas de resultados
    promedio = new JLabel("Promedio = ");
    promedio.setBounds(20, 220, 240, 23);

    desviacion = new JLabel("Desviación estándar = ");
    desviacion.setBounds(20, 250, 240, 23);

    mayor = new JLabel("Valor mayor = ");
    mayor.setBounds(20, 280, 240, 23);

    menor = new JLabel("Valor menor = ");
    menor.setBounds(20, 310, 240, 23);

        // Añadir componentes
        contenedor.add(nota);
        contenedor.add(campoNota);
        contenedor.add(calcular);
        contenedor.add(limpiar);
        contenedor.add(promedio);
        contenedor.add(desviacion);
        contenedor.add(mayor);
        contenedor.add(menor);
        contenedor.add(addNota);
        contenedor.add(scrollLista);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == calcular) {

            double prom = notas.calcularPromedio();
            double desv = notas.calcularDesviacion();

            promedio.setText("Promedio = " + String.format("%.2f", prom));
            desviacion.setText("Desviación estándar = " + String.format("%.2f", desv));
            mayor.setText("Valor mayor = " + notas.calcularMayor());
            menor.setText("Valor menor = " + notas.calcularMenor());

        }

        if (evento.getSource() == limpiar) {
            campoNota.setText("");
            modeloLista.clear();
            promedio.setText("Promedio = ");
            desviacion.setText("Desviación estándar = ");
            mayor.setText("Valor mayor = ");
            menor.setText("Valor menor = ");
        }

        if (evento.getSource() == addNota) {
            addNotaALista();
        }
    }

    private void addNotaALista(){
        Double nota = Double.parseDouble(campoNota.getText());
        notas.listaNotas.add(nota);
        modeloLista.addElement(nota);
        campoNota.setText("");

    }
}