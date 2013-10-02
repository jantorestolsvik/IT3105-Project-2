/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.ntnu.gps.states;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jani
 */
public class GraphColorState extends AbstractState {
    private int [][] edges;
    private double [][] coordinates;
    private int [] color;
    private int K;
    
    public GraphColorState(String file, int K) {
        this.K = K;
        try {
            Scanner in = new Scanner(new FileReader(file));
            in.useLocale(Locale.US);
            int NV = in.nextInt();
            int NE = in.nextInt();
            color = new int[NV];
            coordinates = new double[NV][3];
            edges = new int[NE][2];
            for (int i = 0; i < NV; i++) {
                int index = in.nextInt();
                coordinates[index][0] = in.nextDouble();
                coordinates[index][1] = in.nextDouble();
                color[index] = 0;
            }
            for (int i = 0; i < NE; i++) {
                edges[i][0] = in.nextInt();
                edges[i][1] = in.nextInt();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GraphColorState.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean solved() {
        for (int i = 0; i < edges.length; i++) {
            if (color[edges[i][0]]==color[edges[i][1]]) {
                return false;
            }
        }
        return true;
    }

    public List<Integer> conflictedStates() {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < edges.length; i++) {
            if (color[edges[i][0]] == color[edges[i][1]]) {
                list.add(edges[i][0]);
                list.add(edges[i][1]);
            }
        }
        return list;
    }

    public List<Integer> leastConflictedColors(int chosenIndex) {
        List<Integer> list = new ArrayList<Integer>();
        int min = Integer.MAX_VALUE;
        int [] tempConflicts = new int[K];
        for (int i = 0; i < edges.length; i++) {
            if ((edges[i][0]==chosenIndex)) {
                tempConflicts[color[edges[i][1]]]++;
            } else if (edges[i][1]==chosenIndex) {
                tempConflicts[color[edges[i][0]]]++;
            }
        }
        for (int i = 0; i < K; i++) {
            if (tempConflicts[i] < min) {
                min = tempConflicts[i];
            }
        }
        for (int i = 0; i < K; i++) {
            if(tempConflicts[i] == min) {
                list.add(i);
            }
        }
        if (list.contains(color[chosenIndex])&&(list.size()!=1)) {
            list.remove(list.indexOf(color[chosenIndex]));
        }
        return list;
    }

    public void changeColor(int chosenIndex, int chosenColor) {
        color[chosenIndex] = chosenColor;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < edges.length; i++) {
            sb.append(color[edges[i][0]] + "," + color[edges[i][1]] + "\n");
        }
        return sb.toString();
    }
    
    
}
