/* Descripcion: Clase que realiza el problema de las N reinas con vuelta atras
* Autor: Víctor del Valle del Apio
* e-mail: victorvalleapio@gmail.com
* Fecha: 13-06-2014
*/

import java.util.Scanner;

public class ReinasVA {
    	
	public ReinasVA(int n){
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
	
    public boolean reinasVueltaAtras(int [] sol, int k, boolean [] C, boolean [] D){
    	cont = 0;
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
    			else solEncontrada = reinasVueltaAtras(sol, k+1, C, D);
    			//desmarcar
    			C[sol[k]] = false;
    			D[sol[k] - k +N-1] = false;
    			D[k + sol[k] +2*N - 2+1] = false;
    		}
    		col++;
    	}
    	return solEncontrada;
    }
    
    public void dibujarTablero2(int [] sol){
    	for(int j=0; j<N; j++){
    		if(j == 0) System.out.print("Solucion: (");
    		System.out.print(sol[j]);
    		if(j != N-1) System.out.print(", ");
    		else System.out.println(")");
    	}
    }
    
    public void dibujarTablero(int [] sol){
    	for(int j=0; j<N; j++){
    		if (j == 0) System.out.printf("+");
    		System.out.print("------+");
    	}
    	System.out.println();
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
            	if (j == 0) System.out.printf("|");
                if(sol[i] == j) System.out.printf("  %2d  |", sol[i]);
                else System.out.printf("      |", sol[i]);
            }
            System.out.println();
            for(int j=0; j<N; j++){
            	if (j == 0) System.out.printf("+");
            	System.out.print("------+");
            }
            System.out.println();
        }
    }
    
    public void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	String cad = new String();
    	System.out.print("Introduzca el tamaño del tablero NxN : ");
		boolean repetir = true;
		while(repetir){
			try{ 
				cad = sc.nextLine();
				N = Integer.parseInt(cad);
				if(N > 3) repetir = false;
				else System.out.println("N tiene que ser mayor que 3.");
			}
			catch(Exception e){System.out.println("Repite por favor.");}
    	
		}
		
		//Inicialización
		sol = new int [N];
		C = new boolean [N];
    	D = new boolean [4*N - 2];
		for(int k = 0; k < N; k++){
			sol[k] = 0;
			C[k] = false;
		}
		for(int k = 0; k < 4*N-2; k++){
			D[k] = false;
		}
		
		reinasVueltaAtras(sol, 0, C, D);
		
		if(cont == 0) System.out.println("No se ha encontrado ninguna solución. ");
		else if(cont == 1) System.out.println("Se ha encontrado " + cont + " solución.");
		else System.out.println("Se han encontrado " + cont + " soluciones.");
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
    	inicializar();
    	if(reinasVueltaAtras(sol, 0, C, D) == false) 
    		System.out.println("No se ha encontrado solucion");
    }
    
    private int N;
    private int [] sol;
    private boolean [] C;
	private boolean [] D;
	private int cont = 0;   
}
