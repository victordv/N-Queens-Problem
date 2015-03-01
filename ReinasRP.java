/* Descripcion: Clase que realiza el problema de las N reinas con ramificacion y poda
* Autor: Víctor del Valle del Apio
* e-mail: victorvalleapio@gmail.com
* Fecha: 13-06-2014
*/

import java.util.Comparator;
import java.util.PriorityQueue;

public class ReinasRP {

	public ReinasRP(int n) {
		this.N = n;
		this.sol = new int[N];
		this.C = new boolean[N];
		this.D = new boolean[4 * N - 2];
		for (int k = 0; k < N; k++) {
			this.sol[k] = 0;
			this.C[k] = false;
		}
		for (int k = 0; k < 4 * N - 2; k++) {
			this.D[k] = false;
		}
	}

	public class NodoComparador<Nodo> implements Comparator<Nodo> {

		public int compare(Nodo arg0, Nodo arg1) {
			Float item1 = (float) ((ReinasRP.Nodo) arg0).cota;
			Float item2 = (float) ((ReinasRP.Nodo) arg1).cota;
			if (item1.compareTo(item2) == 0) {
				Float k1 = (float) ((ReinasRP.Nodo) arg0).k;
				Float k2 = (float) ((ReinasRP.Nodo) arg1).k;
				return k1.compareTo(k2);
			} else
				return item1.compareTo(item2);
		}

	}

	public class Nodo implements Cloneable {
		public Nodo() {
			this.k = -1;
			this.sol = new int[N];
			this.C = new boolean[N];
			this.D = new boolean[4 * N - 2];
			for (int k = 0; k < N; k++) {
				this.sol[k] = 0;
				this.C[k] = false;
			}
			for (int k = 0; k < 4 * N - 2; k++) {
				this.D[k] = false;
			}
		}

		public int freePositions() {
			int num = 0;
			for (int j = 0; j < N; j++) {
				if (this.esValida(k+1,j)) {
					num++;
				}
			}
			return num;
		}

		public double calcularCota() {
			this.cota = 0;
			int num = 0;
			for (int i = k + 1; i < N; i++) {
				num = 0;
				for (int j = 0; j < N; j++) {
					if (this.esValida(i, j)) {
						num++;
					}
				}
				cota += num * i;
			}
			cota = cota / (N - k - 1);
			return cota;
		}

		public boolean esValida(int j) {
			return C[j] == false && D[j - k + N - 1] == false
					&& D[k + j + 2 * N - 2 + 1] == false;
		}

		public boolean esValida(int i, int j) {
			return C[j] == false && D[j - i + N - 1] == false
					&& D[i + j + 2 * N - 2 + 1] == false;
		}

		public Nodo clone() {
			Nodo obj = null;
			try {
				obj = (Nodo) super.clone();
				obj.C = this.C.clone();
				obj.D = this.D.clone();
				obj.sol = this.sol.clone();
			} catch (CloneNotSupportedException ex) {
				System.out.println("No se puede duplicar");
			}
			return obj;
		}

		public int k;
		private int[] sol;
		private boolean[] C;
		private boolean[] D;
		private double cota;

	}

	public Nodo nuevoNodo(Nodo y) {
		Nodo x = new Nodo();
		x = y.clone();
		x.k = y.k + 1;
		return x;
	}

	public void reinasRamificacionPoda(int[] sol) {

		ReinasRP.Nodo Y = new Nodo();
		PriorityQueue<Nodo> queue = new PriorityQueue<Nodo>(1, new NodoComparador<Nodo>());
		queue.add(Y);
		int bestBenefit = Integer.MAX_VALUE;

		while (!queue.isEmpty() && queue.peek().calcularCota() <= bestBenefit) {
			Y = queue.poll();
			for (int j = 0; j < N; j++) {

				ReinasRP.Nodo X = this.nuevoNodo(Y);
				X.sol[X.k] = j;

				if (X.esValida(j)) {
					X.C[j] = true;
					X.D[j - X.k + N - 1] = true;
					X.D[X.k + j + 2 * N - 2 + 1] = true;
					if (X.k == N - 1) {// Si es solucion
						bestBenefit = 0;
						// dibujarTablero2(X.sol);
						// dibujarTablero(X.sol);
					}

					else {
						if (X.calcularCota() <= bestBenefit)
							queue.add(X);
					}
				}
			}
		}
	}

	public void reinas() {
		reinasRamificacionPoda(sol);
	}

	public void dibujarTablero2(int[] sol) {
		for (int j = 0; j < N; j++) {
			if (j == 0)
				System.out.print("Solucion: (");
			System.out.print(sol[j]);
			if (j != N - 1)
				System.out.print(", ");
			else
				System.out.println(")");
		}
	}

	public void dibujarTablero(int[] sol) {
		for (int j = 0; j < N; j++) {
			if (j == 0)
				System.out.printf("+");
			System.out.print("------+");
		}
		System.out.println();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (j == 0)
					System.out.printf("|");
				if (sol[i] == j)
					System.out.printf("  %2d  |", sol[i]);
				else
					System.out.printf("      |", sol[i]);
			}
			System.out.println();
			for (int j = 0; j < N; j++) {
				if (j == 0)
					System.out.printf("+");
				System.out.print("------+");
			}
			System.out.println();
		}
	}
	
	private int N;
	private int[] sol;
	private boolean[] C;
	private boolean[] D;

}
