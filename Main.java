/* Descripcion: Clase que realiza el problema de la mochila
* Autor: Víctor del Valle del Apio
* e-mail: victorvalleapio@gmail.com
* Fecha: 03-07-2013
*/

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
public class Main {

	public static void main(String[] args) throws IOException {
		
		Vector<Double> probReinasTimeVA = new Vector<Double>();
		Vector<Double> probReinasTimeRandom = new Vector<Double>();
		Vector<Double> probReinasTimeHibrido = new Vector<Double>();
		Vector<Double> probReinasTimeRP = new Vector<Double>();
		
		BufferedWriter bufferedWriter1 = new BufferedWriter(new FileWriter("prueba.txt"));
		bufferedWriter1.write("n" + " " + "VA" + " " + "random" + " " + "hibrido" + " " 		
							+"RP");
		bufferedWriter1.newLine();
		
		for(int tam = tamInicial; tam < 16; tam++){
			System.out.println(tam);
			probReinasTimeVA.add((double)0);
			probReinasTimeRandom.add((double)0);
			probReinasTimeHibrido.add((double)0);
			probReinasTimeRP.add((double)0);
			for(int rep = 0; rep < 300; rep++){
				probReinasVA(rep, probReinasTimeVA, tam);
				probReinasRandom(rep, probReinasTimeRandom, tam);
				probReinasHibrido(rep, probReinasTimeHibrido, tam, tam/2);
				probReinasRP(rep, probReinasTimeRP, tam);
				if(rep%10 == 0) System.out.print("\t" + rep + " \n");
			}
			bufferedWriter1.write(tam + " " + redondear(probReinasTimeVA.get(tam-4)) + " " + 
					redondear(probReinasTimeRandom.get(tam-4)) + " " + 
					redondear(probReinasTimeHibrido.get(tam-4)) + " " +
					redondear(probReinasTimeRP.get(tam-4)));
			bufferedWriter1.newLine();
			
		}
		bufferedWriter1.close();
		return;
	}
	
	public static void probReinasVA(int ind, Vector<Double> vector, int n){
		ReinasVA reinas = new ReinasVA(n);
		long ini = System.currentTimeMillis();
		int max = 200;
		if(n >= 4 && n < 10) max = 200;
		else if(n >= 10 && n < 14) max = 150;
		else if(n >= 14 && n < 19) max = 30;
		else if(n >= 19 && n < 25) max = 30;
		else if(n >= 25 && n < 30) max = 15;
		for(int i = 0; i < max; i++){
			reinas.reinas();
		}
		long end = System.currentTimeMillis();
		long last = end - ini;
		Double formerMean = vector.get(n - tamInicial);
		Double mean = (formerMean*(ind) + last)/(ind+1);
		vector.set(n- tamInicial, mean);		
	}
	public static void probReinasRandom(int ind, Vector<Double> vector, int n){
		ReinasRandom reinas = new ReinasRandom(n);
		long ini = System.currentTimeMillis();
		int max = 200;
		if(n >= 4 && n < 10) max = 200;
		else if(n >= 10 && n < 14) max = 150;
		else if(n >= 14 && n < 19) max = 30;
		else if(n >= 19 && n < 25) max = 30;
		else if(n >= 25 && n < 30) max = 15;
		for(int i = 0; i < max; i++){
			reinas.reinas();
		}
		long end = System.currentTimeMillis();
		long last = end - ini;
		Double formerMean = vector.get(n - tamInicial);
		Double mean = (formerMean*(ind) + last)/(ind+1);
		vector.set(n- tamInicial, mean);		
	}
	
	public static void probReinasHibrido(int ind, Vector<Double> vector, int n, int prob){
		ReinasHibrido reinas = new ReinasHibrido(n);
		long ini = System.currentTimeMillis();
		int max = 200;
		if(n >= 4 && n < 10) max = 200;
		else if(n >= 10 && n < 14) max = 150;
		else if(n >= 14 && n < 19) max = 30;
		else if(n >= 19 && n < 25) max = 30;
		else if(n >= 25 && n < 30) max = 15;
		for(int i = 0; i < max; i++){
			reinas.reinas(prob);
		}
		long end = System.currentTimeMillis();
		long last = end - ini;
		Double formerMean = vector.get(n - tamInicial);
		Double mean = (formerMean*(ind) + last)/(ind+1);
		vector.set(n- tamInicial, mean);		
	}

	public static void probReinasRP(int ind, Vector<Double> vector, int n){
		ReinasRP reinas = new ReinasRP(n);
		long ini = System.currentTimeMillis();
		int max = 200;
		if(n >= 4 && n < 10) max = 200;
		else if(n >= 10 && n < 14) max = 150;
		else if(n >= 14 && n < 19) max = 30;
		else if(n >= 19 && n < 25) max = 30;
		else if(n >= 25 && n < 30) max = 15;
		for(int i = 0; i < max; i++){
			reinas.reinas();
		}
		long end = System.currentTimeMillis();
		long last = end - ini;
		Double formerMean = vector.get(n - tamInicial);
		Double mean = (formerMean*(ind) + last)/(ind+1);
		vector.set(n- tamInicial, mean);		
	}
	
	public static double redondear(double numero)
	{
	      return Math.rint(numero*1000)/1000;
	}
	
	static private int tamInicial = 4;

}
