package backpropogationANN;
import java.awt.*;

import javax.swing.*;

import backpropogationANN.Brain;

public class Display extends JFrame{
	
	private Brain brain;
	private static Panel panel;
	
	private int neuronSize = 15;
	
	public Display(Brain brain){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,400);
        setLocationRelativeTo(null);
        
		this.brain = brain;
		panel = new Panel();
		add(panel);
		
		
		setResizable(true);
		setVisible(true);
	}
	
	public static void update(){
		panel.repaint();
	}
	
	
	public class Panel extends JPanel{
		
		int xSpacing;
		int ySpacingInputs;
		int ySpacingHiddens;
		int ySpacingOutputs;
		
		public Panel(){
			
		}
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D)g;
			
			xSpacing = (getWidth()/(brain.numLayers+3));
			ySpacingInputs = (getHeight()/(brain.numInputs+1));
			ySpacingHiddens = (getHeight()/(brain.numHidden+1));
			ySpacingOutputs = (getHeight()/(brain.numOutputs+1));
			
			/*
			System.out.println("Height = " + getHeight());
			System.out.println("Width = " + getWidth());
			System.out.println("xSPacingInputs = " + xSpacing);
			System.out.println("ySPacingInputs = " + ySpacingInputs);
			System.out.println("ySPacingHiddens = " + ySpacingHiddens);
			System.out.println("ySPacingOutputs = " + ySpacingOutputs);
			*/
			
			// Draw Neurons
			g.setColor(Color.BLUE);
			for(int i = 1; i < brain.numInputs+1; i++){
				g.fillOval(xSpacing-(neuronSize/2), (ySpacingInputs*i)-(neuronSize/2), neuronSize, neuronSize);
			}
			
			
			g.setColor(Color.BLACK);
			for(int k = 1; k < brain.numLayers+1; k++){
				//System.out.println();
				
				for(int i = 1; i < brain.numHidden+1; i++){
					
					g.fillOval((xSpacing*(k+1))-(neuronSize/2), (ySpacingHiddens*i)-(neuronSize/2), neuronSize, neuronSize);
					//System.out.println("Hidden Neuron Coordinates = " + (xSpacing*(k+1)-(neuronSize/2)) + ", " + ((ySpacingHiddens*i)-(neuronSize/2)));
					
					for(int j = 1; j < (k==1 ? brain.numInputs+1 : brain.numHidden+1); j++){
						if((float)(brain.getNeurons(k,i-1).getWeight(j-1)*brain.getNeurons(k,i-1).getWeight(j-1)/50) > 0.0){
							g2d.setStroke(new BasicStroke((float)(brain.getNeurons(k,i-1).getWeight(j-1)*brain.getNeurons(k,i-1).getWeight(j-1)/100)));
						}else{
							g2d.setStroke(new BasicStroke(0));
						}
						g2d.drawLine(xSpacing*k, (k==1 ? (ySpacingInputs*j) : (ySpacingHiddens*j)), xSpacing*(k+1), (ySpacingHiddens)*i);
					}
				}
			}
			
			
			for(int i = 1; i < brain.numOutputs+1; i++){
				
				g.setColor(Color.BLACK);
				for(int j = 1; j < (brain.numLayers==0 ? brain.numInputs+1 : brain.numHidden+1); j++){
					if((float)(brain.getNeurons(brain.numLayers+1,i-1).getWeight(j-1)*brain.getNeurons(brain.numLayers+1,i-1).getWeight(j-1))/50 >0){
						g2d.setStroke(new BasicStroke((float)(brain.getNeurons(brain.numLayers+1,i-1).getWeight(j-1)*brain.getNeurons(brain.numLayers+1,i-1).getWeight(j-1))/100));
					}else{
						g2d.setStroke(new BasicStroke(0));
					}
					
					
					g2d.drawLine(xSpacing*(brain.numLayers+1), (brain.numLayers==0 ? (ySpacingInputs*j) : (ySpacingHiddens*j)), (xSpacing*(brain.numLayers+2)), ((getHeight()/(brain.numOutputs+1))*i));
				}
				
				g.setColor(Color.RED);
				g.fillOval(xSpacing*(brain.numLayers+2)-(neuronSize/2), (ySpacingOutputs*i)-(neuronSize/2), neuronSize, neuronSize);
			}
		}
	}
}

