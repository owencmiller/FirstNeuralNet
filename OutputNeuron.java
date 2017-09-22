package backpropogationANN;
import java.util.*;

public class OutputNeuron extends Neuron{
	
	private List<Neuron> hiddens;
	
	public OutputNeuron(List<Neuron> hiddens){
		
		this.hiddens = hiddens;
		weights = new double[hiddens.size()];
		
		for(int i = 0; i < hiddens.size(); i++){
			weights[i] = Math.random()*2-1; 
		}
	}
	
	public void update(){
		double sum = 0;
		for(int i = 0; i < hiddens.size(); i++){
			sum += weights[i]*hiddens.get(i).getOutput();
		}
		output = sigmoidActivationFunction(sum);
	}
	

}
