/* Descripcion: Clase que realiza el problema de las N reina de forma aleatoria
* Autor: Víctor del Valle del Apio
* e-mail: victorvalleapio@gmail.com
* Fecha: 13-06-2014
*/

import java.util.Random;

public class ReinasRandom {
    
	public ReinasRandom(int n){
		this.N = n;
		this.sol = new int [N];
		this.C = new boolean [N];
		this.D = new boolean [4*N - 2];
		for(int k = 0; k < N; k++){
			this.sol[k] = 0;
			this.C[k] = false;
		}
		for(int k = 0; k < 4*N-2; k++){
			this.D[k] = false;
		}
	}
	
	public boolean aleatorizar(int [] sol, int k, boolean [] C, boolean [] D){
		int cont;	
		int[] positions = new int[N]; 
		Random rnd= new Random();
		
		while (k < N){
			cont = 0;
			for(int i=0; i<N; i++){
				if(C[i] == false && D[i - k +N-1] == false && D[k + i +2*N - 2+1] == false){
					positions[cont] = i;
					cont = cont + 1;
				}	
			}
			if(cont == 0)
				return false;
			int aux = positions[rnd.nextInt(cont)];
			sol[k] = aux;
			C[sol[k]] = true;
			D[sol[k] - k +N-1] = true;
			D[k + sol[k] +2*N - 2+1] = true;
			k = k+1;
		}
		return true;
    }
    
    public void inicializar(){
    	for(int k = 0; k < N; k++){
			this.sol[k] = 0;
			this.C[k] = false;
		}
		for(int k = 0; k < 4*N-2; k++){
			this.D[k] = false;
		}
    }
    
    public void reinas(){
    	boolean solucion = false;
    	do{
    		inicializar();
    		solucion = aleatorizar(sol, 0, C, D);
    	}while(solucion == false);
    }
    
    private int N;
    private int [] sol;
    private boolean [] C;
	private boolean [] D;
    
    
}
