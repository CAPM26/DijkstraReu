/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dijkstra;

/**
 *
 * @author alejo
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DijkstraGUI extends JFrame implements ActionListener {
    private JLabel originLabel, resultLabel;
    private JComboBox<String> originComboBox;
    private JTextArea resultTextArea;
    private JButton calculateButton;

    private static final String[] municipios = {"San Felipe", "San Martín Zapotitlan", "Nuevo San Carlos", "San Sebastián",
    "El Asintal", "Retalhuleu", "Santa Cruz Muluá", "Champerico", "San Andrés Villa Seca"};
    private double[][] graph = {
            {0.0, 2.6, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 4.9},   // País 0: Guatemala
            {2.6, 0, 0, 0, 0, 0, 3.5, 0, 4.5},    // País 1: El Salvador
            {0, 0, 0, 9.4, 12.1, 9.2, 0, 0, 0},     // País 2: Honduras
            {0, 0, 9.4, 0, 0, 6.7, 4, 0, 0},     // País 3: Nicaragua
            {0,0,12.1, 0, 0, 14.8, 0, 0, 0},     // País 4: Costa Rica
            {0, 0, 9.2, 6.7, 14.8, 0, 11.2, 36.8, 0},     // País 4: Costa Rica
            {0, 3.5, 0, 4, 0, 11.2, 0, 0, 5.3},
            {0, 0, 0, 0, 0, 36.8, 0, 0, 0},
            {5.9, 4.5, 0, 0, 0, 0, 5.3, 0, 0}
        };

    public DijkstraGUI() {
        setTitle("Dijkstra Algorithm - Centroamérica");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        originLabel = new JLabel("Origen:");
        originComboBox = new JComboBox<>(municipios);
        resultLabel = new JLabel("Resultados:");
        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        calculateButton = new JButton("Calcular");
        calculateButton.addActionListener(this);

        panel.add(originLabel);
        panel.add(originComboBox);
        panel.add(resultLabel);
        panel.add(new JScrollPane(resultTextArea));
        panel.add(new JLabel());
        panel.add(calculateButton);

        add(panel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            int originIndex = originComboBox.getSelectedIndex();
            calculateShortestPaths(originIndex);
        }
    }

    private void calculateShortestPaths(int source) {
        int n = graph.length;
        double[] dist = new double[n];
        boolean[] visited = new boolean[n];
        int[] prev = new int[n];

        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
            prev[i] = -1;
        }

        dist[source] = 0;

        for (int i = 0; i < n - 1; i++) {
            double minDist = (double)Integer.MAX_VALUE;
            int minIndex = -1;

            for (int j = 0; j < n; j++) {
                if (!visited[j] && dist[j] < minDist) {
                    minDist = dist[j];
                    minIndex = j;
                }
            }

            visited[minIndex] = true;

            for (int j = 0; j < n; j++) {
                if (!visited[j] && graph[minIndex][j] != 0 && dist[minIndex] != Integer.MAX_VALUE &&
                        dist[minIndex] + graph[minIndex][j] < dist[j]) {
                    dist[j] = dist[minIndex] + graph[minIndex][j];
                    prev[j] = minIndex;
                }
            }
        }

        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("Distancias mínimas desde el origen:\n");
        for (int i = 0; i < n; i++) {
            resultBuilder.append("País ").append(i).append(": ").append(dist[i]).append("\n");
            resultBuilder.append("Ruta: ");
            printPath(prev, i, resultBuilder);
            resultBuilder.append("\n\n");
        }

        resultTextArea.setText(resultBuilder.toString());
    }

    private void printPath(int[] prev, int dest, StringBuilder resultBuilder) {
        if (prev[dest] != -1) {
            printPath(prev, prev[dest], resultBuilder);
        }
        resultBuilder.append(dest).append(" ");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DijkstraGUI();
            }
        });
    }
}

