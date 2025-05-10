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
public class VentanaPrincipal extends JFrame implements ActionListener {
    private Container contenedor;
    private JLabel nota, promedio, desviacion, mayor, menor;
    private JTextField campoNota;
    private JButton calcular, limpiar;
    private JList campoLista;

    ListaNotas listaNotas;

    public VentanaPrincipal() {
        inicio(); // Inicializa completamente la VISTA
        setTitle("Notas");
        setSize(280, 380);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    private void inicio() {
        contenedor = getContentPane(); /* Obtiene el panel de contenidos de la ventana */
        contenedor.setLayout(null); /* Establece que el contenedor no tiene layout */

        // Nota 1
        nota = new JLabel("Notas:");
        nota.setBounds(20, 20, 135, 23);
        campoNota = new JTextField();
        campoNota.setBounds(105, 20, 135, 23);

        // Botón Calcular
        calcular = new JButton("Calcular");
        calcular.setBounds(20, 170, 100, 23);
        calcular.addActionListener(this);

        // Botón Limpiar
        limpiar = new JButton("Limpiar");
        limpiar.setBounds(125, 170, 80, 23);
        limpiar.addActionListener(this);

        campoLista = new JList<>();
        campoLista.getModel();

        // Etiquetas para resultados
        promedio = new JLabel("Promedio = ");
        promedio.setBounds(20, 210, 200, 23);

        desviacion = new JLabel("Desviación estándar = ");
        desviacion.setBounds(20, 240, 200, 23);

        mayor = new JLabel("Valor mayor = ");
        mayor.setBounds(20, 270, 120, 23);

        menor = new JLabel("Valor menor = ");
        menor.setBounds(20, 300, 120, 23);

        // Añadir componentes
        contenedor.add(nota);
        contenedor.add(campoNota);
        contenedor.add(calcular);
        contenedor.add(limpiar);
        contenedor.add(promedio);
        contenedor.add(desviacion);
        contenedor.add(mayor);
        contenedor.add(menor);
        contenedor.add(campoLista);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == calcular) {
            Notas notas = new Notas();

            ArrayList<Double> uso = notas.getListaNotas();
            uso.add(Double.parseDouble(campoNota.getText()));

            notas.setListaNotas(uso);

            notas.calcularPromedio();
            notas.calcularDesviacion();
            double prom = notas.calcularPromedio();
            double desv = notas.calcularDesviacion();

            promedio.setText("Promedio = " + String.format("%.2f", prom));
            desviacion.setText("Desviación estándar = " + String.format("%.2f", desv));
            mayor.setText("Valor mayor = " + notas.calcularMayor());
            menor.setText("Valor menor = " + notas.calcularMenor());

            if (evento.getSource() == limpiar) {
                campoNota.setText("");
            }
        }
    }
}