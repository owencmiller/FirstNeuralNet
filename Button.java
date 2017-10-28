package backpropogationANN; 
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

/**
 * Button extension to a back propagation neural network
 * 
 * @author (Owen Miller) 
 * @version (v1.1)
 */
public class Button extends JButton implements ActionListener
{
    private String name = "";
    private Button [] buttons;
    private Object[] options;
    private Brain brain;
    private Display dis;
    private int [] inputValues;
    
    private int trainingIterations = 1000000;
    
    public Button(Button [] buttons, Brain brain){
    	super();
    	this.buttons = buttons;
        this.brain = brain;
    	
        inputValues = new int[buttons.length];
    }
    
    public Button(String name, Button [] buttons, Brain brain, Display dis){
        super(name);
        this.name = name;
        this.buttons = buttons;
        this.brain = brain;
        this.dis = dis;
        
        inputValues = new int[buttons.length];
        
        if(name.equals("Train")){
        	options = new Object[brain.numOutputs];
            
            for(int i =0; i < options.length;i++){
            	options[i] = ""+i;
            }
        }
    }
    
    public void actionPerformed(ActionEvent e){
        
        if(name.equals("Start")){
        	
            // Get Inputs
            for(int i = 0; i < inputValues.length; i++){
                if(buttons[i].getBackground().equals(Color.ORANGE)){
                    inputValues[i] = 1;
                }else{
                    inputValues[i] = 0;
                }
            }

            brain.update(inputValues);
  
            System.out.println("Did you enter " + brain.getGuess() + "?");
            
        }else if(name.equals("Train")){
            //System.out.println("Train");
        	
        	
            int trainedNum = JOptionPane.showOptionDialog(null, "Which number did you enter?", "",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, null);
            System.out.println("Stored Number = " + trainedNum);
            
            
            for(int i = 0; i < buttons.length; i++){
                if(buttons[i].getBackground().equals(Color.ORANGE)){
                    brain.trainingValues[trainedNum][i] = 1;
                }else{
                    brain.trainingValues[trainedNum][i] = 0;
                }
                System.out.print(brain.trainingValues[trainedNum][i]+ ", ");
            }
            System.out.println();
            
            for(Button button : buttons){
                button.setBackground(Color.WHITE);
            }
            
        }else if(name.equals("Train All")){
            
        	
            for(int i = 0; i < trainingIterations; i++){
            	
                brain.update(brain.trainingValues[i%brain.numOutputs]);
                brain.train(i%brain.numOutputs);
                dis.repaint();
                //System.out.println();
            }
            System.out.println("Done Training");

        }else if(name.equals("Train Presets")){
        	int guess = 0;
        	for(int i = 0; i < trainingIterations; i++){
            	
                brain.update(brain.numPresets[i % (brain.numOutputs-1)]);
                guess = brain.getGuess();
                brain.train(i % (brain.numOutputs-1));
                brain.update(brain.numPresets[i % (brain.numOutputs-1)]);
		//This line was crashing your program when someone hits "Train Presets"
                //System.out.println("Error: " + Math.pow(guess - brain.getGuess(),2));
                dis.repaint();
                
                //System.out.println();
            }
            System.out.println("Done Training");
        }else{
            
            if(getBackground().equals(Color.ORANGE)){
                setBackground(Color.WHITE);
                //System.out.println("Changed to white");
            }else{
                setBackground(Color.ORANGE);
                //System.out.println("Changed to orange");
            }
            
            if(Screen1.getSelected()){
	            for(int i = 0; i < inputValues.length; i++){
	                if(buttons[i].getBackground().equals(Color.ORANGE)){
	                    inputValues[i] = 1;
	                }else{
	                    inputValues[i] = 0;
	                }
	            }
	
	            brain.update(inputValues);
	  
	            System.out.println("Did you enter " + brain.getGuess() + "?");
            }
        }
    }
  
}
