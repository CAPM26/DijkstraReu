/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package Dijkstra;

/**
 *
 * @author alejo
 */


public class Dijkstra {
    private static final String[] municipios = {"San Felipe", "San Martín Zapotitlan", "Nuevo San Carlos", "San Sebastián",
    "El Asintal", "Retalhuleu", "Santa Cruz Muluá", "Champerico", "San Andrés Villa Seca"};
    private static final double[][] graph = {
            {0.0, 2.6, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 4.9},   // S. Felipe
            {2.6, 0, 0, 0, 0, 0, 3.5, 0, 4.5},    // Zapotitlan
            {0, 0, 0, 9.4, 12.1, 9.2, 0, 0, 0},     // San Carlos
            {0, 0, 9.4, 0, 0, 6.7, 4, 0, 0},     // San Sebastian
            {0,0,12.1, 0, 0, 14.8, 0, 0, 0},     // El Asintal
            {0, 0, 9.2, 6.7, 14.8, 0, 11.2, 36.8, 0},     // Retalhuleu
            {0, 3.5, 0, 4, 0, 11.2, 0, 0, 5.3},  //Santa Cruz 
            {0, 0, 0, 0, 0, 36.8, 0, 0, 0},     //Champerico
            {5.9, 4.5, 0, 0, 0, 0, 5.3, 0, 0} //San Andres
        };
    
    public String calculateShortestPaths(int source) {
        int n = graph.length;
        double[] dist = new double[n];
        boolean[] visited = new boolean[n];
        int[] prev = new int[n];
        
        for (int i = 0; i < n; i++) {
            dist[i] = (double)Integer.MAX_VALUE;
            prev[i] = -1;
            visited[i] = false;
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
        resultBuilder.append("Distancias mínimas desde el origen ").append("(").append(municipios[source]).append("):\n\n");
        for (int i = 0; i < n; i++) {
            resultBuilder.append((i+1)).append(") ").append("Desde: ").append(municipios[source]).append(" hasta: ")
            .append(municipios[i]).append("->").append(Math.round(dist[i]*100.0)/100.0).append("KM\n");
            resultBuilder.append("Ruta: ");
            printPath(prev, i, resultBuilder);
            resultBuilder.append("\n\n");
        }

        return resultBuilder.toString();
    }
    
    private void printPath(int[] prev, int dest, StringBuilder resultBuilder) {
        if (prev[dest] != -1) {
            printPath(prev, prev[dest], resultBuilder);
        }
        resultBuilder.append("->").append(municipios[dest]).append("(").append(dest).append(")");
    }
    
//    public void main(String[] args) {
// 
//        int source = 0; // Índice del país de origen (Guatemala)
//        
//        calculateShortestPaths(source);
//    }
}

