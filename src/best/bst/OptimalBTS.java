package best.bst;

import java.text.DecimalFormat;


public class OptimalBTS {

	public static DecimalFormat df = new DecimalFormat("0.00");
	
	// Ki �ڵ�ĸ���
	public static double[] p = {-1, 0.15, 0.10, 0.05, 0.10, 0.20};
	// Di �ڵ�ĸ���
	public static double[] q = {0.05, 0.10, 0.05, 0.05, 0.05, 0.10};
	// �������ʼ�¼��
	public static double[][] e = new double[p.length + 1][q.length];
	// ������¼�±�
	public static double[][] w = new double[p.length + 1][q.length];
	// ��¼Ki~Kj�ĸ�
	public static int[][] root = new int[p.length + 1][p.length + 1];
	
	/**
	 * �����������ɶ�����
	 * @param n Key�ڵ�ĸ���
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
	 * ��ӡ�����ڵ�r��������i��������j
	 * @param i
	 * @param j
	 * @param r
	 */
	public static void printOptimalBTS(int i, int j, int r){
		// ����i������j�ĸ��׸��ڵ�
		int rootNode = root[i][j];
		if (rootNode == root[1][p.length - 1])  
	    {  
	        //����������ĸ�  
	        System.out.println("k" + rootNode + "�Ǹ�");  
	        printOptimalBTS(i, rootNode - 1, rootNode);  
	        printOptimalBTS(rootNode + 1, j, rootNode);  
	        return;  
	    }  
		if (j < i - 1)  
	    {  
	        return;  
	    }  
		else if (j == i - 1)//���������  
	    {  
	        if (j < r)  
	        {  
	            System.out.println("d" + j + "��k" + r + "������");  
	        }  
	        else  
	        	System.out.println("d" + j + "��k" + r + "���Һ���");  
	        return;  
	    }  
	    else//�����ڲ����  
	    {  
	        if (rootNode < r)  
	        {  
	            System.out.println("k" + rootNode + "��k" + r + "������");  
	        }  
	        else  
	        	System.out.println("k" + rootNode + "��k" + r + "���Һ���"); 
	    }  
		printOptimalBTS(i, rootNode - 1, rootNode);
		printOptimalBTS(rootNode + 1, j, rootNode);
	}
	
	public static void main(String[] args){
		OptimalBTS(p.length - 1);// �ڵ����
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
