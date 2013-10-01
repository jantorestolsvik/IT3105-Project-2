/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.ntnu.gps.states;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jani
 */
public class GraphColorState extends AbstractState {
    private int [][] E;
    private double [][] V;
    
    public GraphColorState(String file) {
        try {
            Scanner in = new Scanner(new FileReader(file));
            in.useLocale(Locale.US);
            int NV = in.nextInt();
            int NE = in.nextInt();
            V = new double[NV][3];
            E = new int[NE][2];
            for (int i = 0; i < NV; i++) {
                V[i][0] = (double)in.nextInt();
                V[i][1] = in.nextDouble();
                V[i][2] = in.nextDouble();
            }
            for (int i = 0; i < NE; i++) {
                E[i][0] = in.nextInt();
                E[i][1] = in.nextInt();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GraphColorState.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean solved() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
