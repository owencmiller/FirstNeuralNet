package backpropogationANN;

public class InputNeuron extends Neuron{
	
	int num;
	
	public InputNeuron(int num){
		this.num = num;
	}
	
	public void update(int input){
	    
	    output = input;
		
	}
	
}
