package backpropogationANN;  
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;

/**
 * Write a description of class Screen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Screen1 extends JFrame
{
    private JPanel panel;
    public static Button [] buttons;
    
    private Button startButton;
    private Button trainButton;
    private Button trainAllButton;
    private Button trainPreButton;
    private JCheckBox box;
    private static boolean selected = false;
    
    private Brain brain;
    private Display dis;
    
    int inputs = 25;
    int hidden = 10;
    int output = 10;
    int layers = 1;
    
    public Screen1(){
        brain = new Brain(inputs, layers ,hidden, output);
        dis = new Display(brain);
        
        setVisible(true);
        setSize(600,850);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        panel = new JPanel();
        panel.setLayout(new GridLayout((int)Math.sqrt(inputs)+1,(int)Math.sqrt(inputs)));
        try{
        	UIManager.setLookAndFeel(new MetalLookAndFeel());
        }catch(UnsupportedLookAndFeelException e){
        	
        }
        
        buttons = new Button[inputs];
        for(int i = 0; i < buttons.length; i++){
            buttons[i] = new Button(buttons,brain);
            buttons[i].addActionListener(buttons[i]);
            buttons[i].setMargin(new Insets(0,0,0,0));
            buttons[i].setBackground(Color.WHITE);
            panel.add(buttons[i]);
        }
        
        startButton = new Button("Start", buttons, brain, dis);
        startButton.addActionListener(startButton);
        
        trainButton = new Button("Train", buttons, brain, dis);
        trainButton.addActionListener(trainButton);
        
        trainAllButton = new Button("Train All", buttons, brain, dis);
        trainAllButton.addActionListener(trainAllButton);
        
        trainPreButton = new Button("Train Presets", buttons, brain, dis);
        trainPreButton.addActionListener(trainPreButton);
        
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
              AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
              setSelected(abstractButton.getModel().isSelected());
            }
          };
        
        box = new JCheckBox("Continuous Guess");
        box.addActionListener(actionListener);
        
        
        panel.add(startButton);
        panel.add(trainButton);
        panel.add(trainAllButton);
        panel.add(trainPreButton);
        panel.add(box);
        
        add(panel);
        
        
    }
    
    
    public static void main(String [] args){
        Screen1 screen = new Screen1();
    }


	public static boolean getSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}


}
