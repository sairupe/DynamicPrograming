package best.bst;

import java.text.DecimalFormat;


public class OptimalBTS {

	public static DecimalFormat df = new DecimalFormat("0.00");
	
	// Ki 节点的概率
	public static double[] p = {-1, 0.15, 0.10, 0.05, 0.10, 0.20};
	// Di 节点的概率
	public static double[] q = {0.05, 0.10, 0.05, 0.05, 0.05, 0.10};
	// 辅助概率记录表
	public static double[][] e = new double[p.length + 1][q.length];
	// 辅助记录下标
	public static double[][] w = new double[p.length + 1][q.length];
	// 记录Ki~Kj的根
	public static int[][] root = new int[p.length + 1][p.length + 1];
	
	/**
	 * 计算最优生成二叉树
	 * @param n Key节点的个数
	 */
	public static void OptimalBTS(int n) {
		for(int i = 1; i <= n + 1; i++){
			e[i][i-1] = q[i-1];
			w[i][i-1] = q[i-1];
		}
		printMatrixE();
		for(int l = 1; l <= n; l++){
			for(int i = 1; i <= n-l+1; i++){
				int j = i + l - 1;
				e[i][j] = 999;
				w[i][j] = w[i][j-1] + p[j] + q[j];
				for(int r = i; r <= j; r++){
					double t = e[i][r-1] + e[r+1][j] + w[i][j];
					if(t < e[i][j]){
						e[i][j] = t;
						root[i][j]=r;  
						printMatrixE();
					}
				}
			}
		}
	}
	
	public static void printMatrixE(){
		for(int i = 1; i < e.length; i++){
			for(int j = 0; j < e[i].length; j++){
				System.out.printf("%.2f \t", e[i][j]);
				//System.out.printf(df.format(e[i][j]) + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void printMatrixW(){
		for(int i = 1; i < w.length; i++){
			for(int j = 0; j < w[i].length; j++){
				System.out.printf("%.2f \t", w[i][j]);
				//System.out.printf(df.format(w[i][j]) + "\t");
			}
			System.out.println();
		}
	}
	
	public static void printMatrixRoot(){
		for(int i = 1; i < root.length - 1; i++){
			for(int j = 1; j < root[i].length - 1; j++){
				System.out.print(root[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	/**
	 * 打印出根节点r的左子树i和右子树j
	 * @param i
	 * @param j
	 * @param r
	 */
	public static void printOptimalBTS(int i, int j, int r){
		// 子树i和子树j的父亲根节点
		int rootNode = root[i][j];
		if (rootNode == root[1][p.length - 1])  
	    {  
	        //输出整棵树的根  
	        System.out.println("k" + rootNode + "是根");  
	        printOptimalBTS(i, rootNode - 1, rootNode);  
	        printOptimalBTS(rootNode + 1, j, rootNode);  
	        return;  
	    }  
		if (j < i - 1)  
	    {  
	        return;  
	    }  
		else if (j == i - 1)//遇到虚拟键  
	    {  
	        if (j < r)  
	        {  
	            System.out.println("d" + j + "是k" + r + "的左孩子");  
	        }  
	        else  
	        	System.out.println("d" + j + "是k" + r + "的右孩子");  
	        return;  
	    }  
	    else//遇到内部结点  
	    {  
	        if (rootNode < r)  
	        {  
	            System.out.println("k" + rootNode + "是k" + r + "的左孩子");  
	        }  
	        else  
	        	System.out.println("k" + rootNode + "是k" + r + "的右孩子"); 
	    }  
		printOptimalBTS(i, rootNode - 1, rootNode);
		printOptimalBTS(rootNode + 1, j, rootNode);
	}
	
	public static void main(String[] args){
		OptimalBTS(p.length - 1);// 节点个数
		System.out.println("===============>>>>>> Print E");
		printMatrixE();
		System.out.println("===============>>>>>> Print W");
		printMatrixW();
		System.out.println("===============>>>>>> Print Root");
		printMatrixRoot();
		System.out.println("===============>>>>>> Print Tree");
		printOptimalBTS(1, 5, -1);
	}
}
