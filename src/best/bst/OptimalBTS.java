package best.bst;

import java.text.DecimalFormat;


public class OptimalBTS {

	public static DecimalFormat df = new DecimalFormat("#.00");
	
	// Ki 节点的概率
	public static double[] p = {0, 0.15, 0.10, 0.05, 0.10, 0.20};
	// Di 节点的概率
	public static double[] q = {0.05, 0.10, 0.05, 0.05, 0.05, 0.10};
	// 辅助概率记录表
	public static double[][] e = new double[p.length + 1][p.length];
	// 辅助记录下标
	public static double[][] w = new double[q.length + 1][q.length];
	// 记录Ki~Kj的根
	public static int[][] root = new int[p.length + 1][p.length + 1];
	
	
	public static void OptimalBTS(int n) {
		for(int i = 1; i < n + 1; i++){
			e[i][i-1] = q[i-1];
			w[i][i-1] = q[i-1];
		}
		for(int l = 1; l < n; l++){
			for(int i = 1; i < n-l+1; i++){
				int j = i + l - 1;
				e[i][j] = Double.MAX_VALUE;
				w[i][j] = w[i][j-1] + p[j] + q[j];
				for(int r = i; i < j; i++){
					double t = e[i][r-1] + e[r+1][j] + w[i][j];
					if(t < e[i][j]){
						e[i][j] = t;
						root[i][j]=r;  
					}
				}
			}
		}
	}
	
	public static void OptimalBTS_Print(){
		
	}
	
	public static void printMatrixE(){
		for(int i = 0; i < e.length; i++){
			for(int j = 0; j < e[i].length; j++){
				//System.out.printf("%.2f\t", e[i][j]);
				System.out.printf(df.format(e[i][j]) + "\t");
			}
			System.out.println();
		}
	}
	public static void printMatrixW(){
		for(int i = 0; i < w.length; i++){
			for(int j = 0; j < w[i].length; j++){
				//System.out.printf("%.2f\t", w[i][j]);
				System.out.printf(df.format(w[i][j]) + "\t");
			}
			System.out.println();
		}
	}
	public static void printMatrixRoot(){
		for(int i = 0; i < root.length; i++){
			for(int j = 0; j < root[i].length; j++){
				System.out.print(root[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args){
		OptimalBTS(p.length);
		printMatrixE();
		System.out.println();
		printMatrixW();
		System.out.println();
		printMatrixRoot();
	}
}
