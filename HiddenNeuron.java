package backpropogationANN;
import java.util.*;

public class HiddenNeuron extends Neuron{
	
	private List<Neuron> inputs;
	
	
	public HiddenNeuron(List<Neuron> inputs){
		
		this.inputs = inputs;
		weights = new double[inputs.size()];
		
		for(int i = 0; i < inputs.size(); i++){
			weights[i] = Math.random()*2-1; 
		}
	}
	
	public void update(){
		double sum = 0;
		for(int i = 0; i < inputs.size(); i++){
			sum += weights[i]*inputs.get(i).getOutput();
		}
		//System.out.println("Sum = " + sum);
		output = sigmoidActivationFunction(sum);
	}
	
}
