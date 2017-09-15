public class Candidate  {
	private String gen;
	private double fitness;
	private int similarIndividals;
	
	public String getGen() {
		return gen;
	}
	public void setGen(String gen) {
		this.gen = gen;
	}
	public double getFitness() {
		return fitness;
	}
	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
	public void addGen(String gen){
		this.gen+=gen;
	}
	public int getSimilarIndividals() {
		return similarIndividals;
	}
	public void setSimilarIndividals(int similarIndividals) {
		this.similarIndividals = similarIndividals;
	}
  
}

