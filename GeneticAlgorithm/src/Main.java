import java.io.IOException;

import java.util.ArrayList;
import java.util.Random;



public class Main {
	// Number of individuals in each iteration
	public static int populationSize = 40;
	// Tournament size
	public static int tourSize = 2;
	// Number of iterations
	public static int n_iter = 200;
	public static ArrayList<Candidate> population;
	public static Random random2 = new Random();
	public static int geneLength = 14;
	public static String inputLine;
	// Percentage of number of gen mutated
	public static float mutationRate = (float) 0.1;
	public static ArrayList<Candidate> populationChild;
	public static ArrayList<Candidate> populationWinners;
	// It is used for generating new population after tournament state
	public static float uniformRate = (float) 0.5;

	public static String replaceCharAt(String in, int pos, char a){
		return in.substring(0, pos)+ a + in.substring(pos+1);
	}

	public static ArrayList<Candidate> createInitPopulation() throws IOException {		
		// It always initializes population with a individual with all atributes
		Candidate c = new Candidate();
		c.setGen("1111111111111111");
		population.add(c);
		for (int j = 0; j < populationSize-1; j++) {
			Candidate candidate= new Candidate();
			candidate.setGen("");
			for (int i = 0; i < geneLength; i++) {
				double random = Math.random();
				if(random< 0.6){
					candidate.addGen("1");
				}
				else{
					candidate.addGen("0");
				}
			}
			population.add(candidate);
		}	
		System.out.println("Init population sorted");
		return population;
	}

	public static ArrayList<Candidate> tournament () throws InterruptedException{
		System.out.println("\nTournament starting...");
		ArrayList<Candidate> populationWinners= new ArrayList<Candidate>();
		Candidate[] tourSpace = new Candidate[tourSize];
		for(int i=0;i<populationSize;i++){

			for (int j = 0; j < tourSpace.length; j++) {
				tourSpace[j] = population.get(random2.nextInt(population.size()));
			}

			double winnerfit = 10000000;
			Candidate winner = null;
			for (int j = 0; j < tourSpace.length; j++) {
				if (tourSpace[j].getFitness() < winnerfit) {
					winnerfit = tourSpace[j].getFitness();
					winner = tourSpace[j];
				}
			}
			populationWinners.add(winner);		
		}

		return populationWinners;

	}

	// It replaces population by uniform replace method: winners of tournament are selected in groups of 2. Then using uniform rate, it decides wich gen of these "parents" is trasmited to next generation with x probability.
	public static ArrayList<Candidate> childGenerationUniform(){

		ArrayList<Candidate> aux = new ArrayList<Candidate>();

		for(int i=0;i<=populationSize-1; i+=2){
			Candidate parent1=populationWinners.get(i);
			Candidate parent2=populationWinners.get(i+1);
			String genSon1 = "" ;
			String genSon2 = "" ;
			for(int j=0; j<= geneLength-1; j++){
				if(random2.nextFloat()<= uniformRate){
					genSon1= genSon1 +parent2.getGen().charAt(j);
					genSon2= genSon2 +parent1.getGen().charAt(j);
				}
				else{
					genSon1= genSon1 +parent1.getGen().charAt(j);
					genSon2= genSon2 +parent2.getGen().charAt(j);	
				}
			}
			Candidate son1 = new Candidate();
			Candidate son2 = new Candidate();
			son1.setGen(genSon1);
			son2.setGen(genSon2);
			aux.add(son1);
			aux.add(son2);

		}

		return aux;
	}


	public static ArrayList<Candidate> mutate(){

		for(int i=0;i<=populationChild.size()-1;i++){
			String genoma =populationChild.get(i).getGen();
			for(int j=0; j<= geneLength-1; j++){
				if(random2.nextFloat()<=mutationRate){
					if(genoma.charAt(j)=='0') {
						populationChild.get(i).setGen(replaceCharAt(genoma,j, '1'));
					}

					else {
						populationChild.get(i).setGen(replaceCharAt(genoma,j, '0'));
					}

				}
			}

		}
		population=populationChild;
		return population;
	}


	public static void main(String[] args) throws Exception {

		population = new ArrayList <Candidate>();
		populationWinners = new ArrayList <Candidate>();
		populationChild = new ArrayList <Candidate>();

		double minp = 100000;
		double min = 200000;

		String best_ind = "100000";

		for (int j = 0; j < n_iter; j++) {
			// Individual with best result in this iteration
			int c=0;
			if (j == 0) {
				// In first iteration individuals have not fittness associated
				for(int i=0;i<populationSize;i++){
					/* Set fitness of each individuals with your fitness function 
					 * INSERT CODE HERE --> Example:
					 * population.get(i).setFitness(your_function)
					 */
					if(minp>=population.get(i).getFitness()){
						minp=population.get(i).getFitness();
						c = i;
					}
				}
			}
			populationWinners=tournament(); 
			populationChild=childGenerationUniform(); 
			population=mutate();

			// Population has change and fitness must be resolved
			for(int i=0;i<populationSize;i++){
				/* Set fitness of each individuals with your fitness function 
				 * INSERT CODE HERE --> Example:
				 * population.get(i).setFitness(your_function)
				 */
				if(minp>=population.get(i).getFitness()){
					minp=population.get(i).getFitness();
					c = i;
				}
			}

			// Check if the best local individual found in this iteration is the best found in all past iterations
			if(min>minp){
				// Gather gen and fitness
				min=minp;
				best_ind = population.get(c).getGen();
			}

		}
		System.out.println("\n***** ITERATIONS FINISHED *****");
		System.out.println("Best individual: "+best_ind);
		System.out.println("Best fitness: "+min);
	}



}





