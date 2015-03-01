/* Descripcion: Clase que realiza el problema de lan N reinas con un algoritmo hibrido
* Autor: Víctor del Valle del Apio
* e-mail: victorvalleapio@gmail.com
* Fecha: 03-07-2013
*/

import java.util.Random;

public class ReinasHibrido {
    
	public ReinasHibrido(int n){
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
	
	public boolean aleatorizar(int [] sol, int k, boolean [] C, boolean [] D, int prob){
		int cont;	
		int[] positions = new int[N]; 
		Random rnd= new Random();
		
		while (k < prob){
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
	
	public boolean reinasVA(int [] sol, int k, boolean [] C, boolean [] D){
    	int col = 0;
    	boolean solEncontrada = false;
    	while(col < N && solEncontrada == false){
    		sol[k] = col;
    		if(C[sol[k]] == false && D[sol[k] - k +N-1] == false && D[k + sol[k] +2*N - 2+1] == false){
    			//marcar
    			C[sol[k]] = true;
    			D[sol[k] - k +N-1] = true;
    			D[k + sol[k] +2*N - 2+1] = true;
    			if(k == N-1){
    				//dibujarTablero2(sol);
    				//dibujarTablero(sol);
    				solEncontrada = true;
    				return solEncontrada;
    			}
    			else solEncontrada = reinasVA(sol, k+1, C, D);
    			//desmarcar
    			C[sol[k]] = false;
    			D[sol[k] - k +N-1] = false;
    			D[k + sol[k] +2*N - 2+1] = false;
    		}
    		col++;
    	}
    	return solEncontrada;
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
    
    public void reinas(int prob){
    	boolean solucion = false;
    	do{
    		//inicializar();
    		reinasRandom(prob);
    		solucion = reinasVA(sol, prob, C, D);
    	}while(solucion == false);	
    }
    
    public void reinasRandom(int prob){
    	boolean solucion = false;
    	do{
    		inicializar();
    		solucion = aleatorizar(sol, 0, C, D, prob);
    	}while(solucion == false);
    }
    
    
    private int N;
    private int [] sol;
    private boolean [] C;
	private boolean [] D;
    
    
}
