package backpropogationANN;

import java.util.*;


public class Brain {
    
    
    private List<Neuron> inputNeurons;
    private List<Neuron> hiddenNeurons;
    private List<Neuron> outputNeurons;
    
    int numInputs;
    int numHidden;
    int numOutputs;
    int numLayers;
    
    double targetValue;
    double learningCoefficient = .1;
    int [][] trainingValues;
    int guess;
    
    int [][] numPresets = {
    		{0, 1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 0},//0
    		{0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0},//1
    		{0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0},//2
    		{0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0},//3
    		{0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0},//4
    		{0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0},//5
    		{0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 0},//6
    		{0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0},//7
    		{0, 1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 0},//8
    		{0, 1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0},//9
    		
    };
    
    public Brain(int numInputs, int numLayers, int numHidden, int numOutputs){
        this.numInputs = numInputs;
        this.numHidden = numHidden;
        this.numOutputs = numOutputs;
        this.numLayers = numLayers;
        
        
        inputNeurons = new ArrayList<Neuron>();
        for(int i = 0; i < numInputs; i++){
            inputNeurons.add(new InputNeuron(i));
        }
        

	    hiddenNeurons = new ArrayList<Neuron>();
	    for(int j = 0; j < numHidden; j++){
	        hiddenNeurons.add(new HiddenNeuron(inputNeurons));
	    }
        
	        
        outputNeurons = new ArrayList<Neuron>();
        for(int i = 0; i < numOutputs; i++){
            if(numHidden==0){
                outputNeurons.add(new OutputNeuron(inputNeurons));
            }else{
                outputNeurons.add(new OutputNeuron(hiddenNeurons));
            }
            
        }
        
        trainingValues = new int [numOutputs][numInputs];
        
    }
    
    
    public void  update(int [] inputValues){
        //System.out.println("------------------");
        //System.out.println("Inputs");
        //System.out.println("------------------");
        
        int i =0;
        for(Neuron neuron : inputNeurons){
            neuron.update(inputValues[i]);
            //System.out.println(neuron.getOutput());
            i++;
        }
        
        //System.out.println("------------------");
        //System.out.println("Hidden");
        //System.out.println("------------------");
        

	        for(Neuron neuron : hiddenNeurons){
	            neuron.update();
	            //System.out.println(Arrays.toString(neuron.getAllWeights()));
	        }
        
        
        //System.out.println("------------------");
        //System.out.println("Outputs");
        //System.out.println("------------------");
        
        double highest = 0.0;
        for(Neuron neuron: outputNeurons){
            neuron.update();
            //System.out.println(Arrays.toString(neuron.getAllWeights()));
            //System.out.println("Output = " + neuron.getOutput());
            
            if(neuron.getOutput() > highest){
                highest = neuron.getOutput();
                guess = outputNeurons.indexOf(neuron);
            }
        }
        
        //System.out.println(guess);
    }
  
    public void train(int answer){
      
        double [] outputErrors = new double[numOutputs];
        double [] hiddenErrors = new double[numHidden];

        
        int i =0;
        for(Neuron neuron : outputNeurons){
            
            targetValue = (answer==i) ? 1 : 0;
            
            // Get errors for the outputs
            outputErrors[i] = neuron.getOutput()*(1-neuron.getOutput())*(targetValue-neuron.getOutput());
            
            //System.out.println("Number of weights = " + neuron.getNumWeights());
            // New Weights
            
            for(int j = 0; j < neuron.getNumWeights(); j++){
                
                //System.out.println("Old Weight - " + neuron.getWeight(j));
                //System.out.println("New Weight - " + (neuron.getWeight(j)+(learningCoefficient*outputErrors[i]*hiddenNeurons.get(j).getOutput())));
                
                neuron.setWeight(j, neuron.getWeight(j)+(learningCoefficient*outputErrors[i]*hiddenNeurons.get(j).getOutput()));
            }
            i++;
        }
        
        i = 0;
        for(Neuron neuron : hiddenNeurons){
            
            double sum = 0;
            for(int j = 0; j < outputNeurons.size(); j++){
                sum += outputErrors[j]*outputNeurons.get(j).getWeight(i);
            }
    
            hiddenErrors[i] = neuron.getOutput()*(1-neuron.getOutput())*(sum);
            
            for(int j = 0; j < neuron.getNumWeights(); j++){
                neuron.setWeight(j, neuron.getWeight(j)+(learningCoefficient*hiddenErrors[i]*inputNeurons.get(j).getOutput()));
            }
            
            i++;
        }
        
    }
    
    
    public int getGuess(){
        return guess;
    }
    
    public Neuron getNeurons(int i, int j){
    	if(i == 0){
    		return inputNeurons.get(j);
    	}else if(i== 1){
    		return hiddenNeurons.get(j);
    	}else if(i== 2){
    		return outputNeurons.get(j);
    	}else{
    		return new Neuron();
    	}
    }
    
    
    
    
}
