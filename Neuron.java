package backpropogationANN;

public class Neuron {
    
    double output;
    protected double[] weights;
    
    public Neuron(){
    	
    }
    
    public void update(){}
    public void update(int i){}
    
    public double stepActivationFunction(double input){
        if(input < 0){
            return 0;
        }else{
            return 1;
        }
    }
    
    public double sigmoidActivationFunction(double input){
    	return 1/(1+ Math.pow(Math.E, -input));
    }
    
    
    public void setOutput(double out){
        output = out;
    }
    public double getOutput(){
        return output;
    }
    
    
    public void setWeight(int place, double weight){
    	weights[place] = weight;
    }
    public double getWeight(int place){
    	//System.out.println("place = " + place);
    	return weights[place];
    }
    public double[] getAllWeights(){
    	return weights;
    }
    public int getNumWeights(){
    	return weights.length;
    }
}
