/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.ntnu.gps.states;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.AbstractModalGraphMouse;
import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ScalingControl;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.apache.commons.collections15.Transformer;
import org.apache.commons.collections15.TransformerUtils;

/**
 *
 * @author Jani
 */
public class GraphColorState extends AbstractState {
    private int [][] edges;
    private double [][] coordinates;
    private int [] color;
    private Color [] colors;
    private int K;
    
    public GraphColorState(String file, int K) {
        this.K = K;
        try {
            Scanner in = new Scanner(new FileReader(file));
            in.useLocale(Locale.US);
            int NV = in.nextInt();
            int NE = in.nextInt();
            color = new int[NV];
            colors = new Color[5];
            colors[0]=Color.BLUE;
            colors[1]=Color.BLACK;
            colors[2]=Color.GREEN;
            colors[3]=Color.YELLOW;
            colors[4]=Color.CYAN;
            coordinates = new double[NV][2];
            edges = new int[NE][2];
            for (int i = 0; i < NV; i++) {
                int index = in.nextInt();
                coordinates[index][0] = in.nextDouble()*7;
                coordinates[index][1] = in.nextDouble()*7;
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

    private GraphColorState(int [] colorClone, int [][] edges, double [][] coordinates, Color [] colors, int K) {
        this.color = colorClone;
        this.edges = edges;
        this.coordinates = coordinates;
        this.colors = colors;
        this.K = K;
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
    
    public void display() {
        Graph<Integer, String> g = new SparseMultigraph<Integer, String>();
        
        Map<Integer, Point2D> map = new HashMap<Integer, Point2D>();
        
        for (int i = 0; i < coordinates.length; i++) {
            g.addVertex(i);
            map.put((Integer)i, new Point2D.Double(this.coordinates[i][0], this.coordinates[i][1]));
        }
        for (int i = 0; i < edges.length; i++) {
            g.addEdge(Integer.toString(i), edges[i][0], edges[i][1]);
        }
        
        //Transformer<Integer, Point2D> vertexLocations = TransformerUtils.mapTransformer(map);
        //Layout<Integer, String> layout = new StaticLayout<Integer, String>(g, vertexLocations);
        Layout<Integer, String> layout = new KKLayout<Integer, String>(g);
        
        layout.setSize(new Dimension(300,300)); // sets the initial size of the layout space
        
        final VisualizationViewer<Integer,String> vv = new VisualizationViewer<Integer,String>(layout);
        vv.setPreferredSize(new Dimension(350,350)); //Sets the viewing area size
        
        Transformer<Integer,Paint> vertexColor = new Transformer<Integer,Paint>() {
            public Paint transform(Integer i) {
                return colors[color[i]];
            }
        };
        
        vv.getRenderContext().setVertexFillPaintTransformer(vertexColor);
        
        JFrame frame = new JFrame("Simple Graph View");
        Container content = frame.getContentPane();
        final GraphZoomScrollPane panel = new GraphZoomScrollPane(vv);
        content.add(panel);
        final AbstractModalGraphMouse graphMouse = new DefaultModalGraphMouse<String,Number>();
        vv.setGraphMouse(graphMouse);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final ScalingControl scaler = new CrossoverScalingControl();
        
        JButton plus = new JButton("+");
        plus.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 scaler.scale(vv, 1.1f, vv.getCenter());
             }
         });
         JButton minus = new JButton("-");
         minus.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 scaler.scale(vv, 1/1.1f, vv.getCenter());
             }
         });

         JButton reset = new JButton("reset");
         reset.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                        vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT).setToIdentity();
                        vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW).setToIdentity();
                }});
         
         JPanel controls = new JPanel();
         controls.add(plus);
         controls.add(minus);
         controls.add(reset);
         content.add(controls, BorderLayout.SOUTH);
 
         frame.pack();
         frame.setVisible(true);
    }

    @Override
    public int evaluation() {
        int eval = 0;
        for (int i = 0; i < edges.length; i++) {
            if (color[edges[i][0]] != color[edges[i][1]]) {
                eval++;
            }
        }
        return eval;
    }

    public AbstractState randomNeighbourState() {
        GraphColorState returner = this.clone();
    	int color = (int) (Math.random()*K);
    	int node = (int) (Math.random()*this.color.length);
    	
    	returner.changeColor(node, color);
    	
    	return returner;
    }
    
    public GraphColorState clone(){
        int [] colorClone = color.clone();
    	GraphColorState returner = new GraphColorState(colorClone, edges, coordinates, colors, K);
    	return returner;
    }
}
