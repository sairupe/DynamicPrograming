package matrixchain;

import java.util.Arrays;

/**
 * @author SyrianaZh
 * 矩阵链乘法
 * 2015-8-5
 */
public class MatrixChainMultiple {
	
	public static int counts = 0;
	
	// 辅助记录表
	// i~j连乘最小次数
	public static int[][] m = new int[7][7];
	// i~j连乘断点位置
	public static int[][] s = new int[7][7];
	/**
	 * 矩阵维数表
	 * 注意算法导论上对于Ai的维数表示有一个定义
	 * 即Ai = p[i-1] * p[i](i >= 1)
	 * 现在这个p是满足这个条件的
	 * p[i-1] - 行
	 * p[i] - 列
	 * 如下可理解
	 * p[i-1] * p[k] * p[j];
	 */
	public static int[] p = new int[m.length];//[30, 35, 15, 5, 10, 20, 25]
	
	
	static{
		// 初始化矩阵记录表
		intialP(1, 30, 35);
		intialP(2, 35, 15);
		intialP(3, 15, 5);
		intialP(4, 5 , 10);
		intialP(5, 10, 20);
		intialP(6, 20, 25);
	}
	
	/**
	 * A1 : 30 * 35 |
	 * A2 : 35 * 15 | 
	 * A3 : 15 * 5  |
	 * A4 : 5  * 10 |
	 * A5 : 10 * 20 |
	 * A6 : 20 * 25 |
	 * 
	 * 结果 ((A(AA))((AA)A))
	 */
	
	/**
	 * 递归树(重复计算过程多)
	 * 							1:4
	 * 		1:1		2:4		1:2		3:4		1:3		4:4
	 * 		....	
	 * 分解，先得到矩阵对角线之和，然后合并
	 */
	
	/**
	 * 递归算法
	 * @param i
	 * @param j
	 * @return
	 */
	public static int MatrixChainMinCountsRecursive(int i, int j){
		if(i == j){
			return 0;
		}
		// 初始化一个值，可设置为INTEGER.MAXINTVALUE
		m[i][j] = Integer.MAX_VALUE;
		for(int k = i; k < j; k++){
			int t = MatrixChainMinCountsRecursive(i, k) + MatrixChainMinCountsRecursive(k + 1, j)
				  + p[i-1] * p[k] * p[j];
			counts++;
			if(t < m[i][j]){
				m[i][j] = t;
				s[i][j] = k;
			}
		}
		return m[i][j];
	}
	
	/**
	 * 求连乘矩阵P的最小连乘次数
	 * @param n N个可连乘矩阵的维数P
	 * @return 最小连乘次数
	 */
	public static void MatrixChainUnRecursive(int n){
		// i是矩阵行坐标，j是矩阵列坐标
		for(int i = 0; i <= n; i++){
			m[i][i] = 0;// 自己本身的连乘次数为0
		}
		// 按照矩阵乘积链长度增长的顺序自底向上计算m[1][n]
		for(int l = 2; l <= n; l++){// l是要分割的矩阵链的长度
			// 从第一个矩阵A1开始，逐渐往后移动
			// l = 2; 最后一次循环 i=5, 则A5 * A6是满足条件的
			for(int i = 1; i <= n - l + 1; i++){// PS:(可是这里不也还是会重复计算吗？？)
				int j = i + l - 1;// 根据l和i计算出 l长度的矩阵，j的索引是多少
				m[i][j] = Integer.MAX_VALUE;
				// 对 i<k<j，逐个计算A[i:j] = A[i:k]A[k+1:j],k是断点位置 
				for(int k = i; k < j; k++){
					int t = m[i][k] + m[k+1][j] + p[i-1] * p[k] * p[j];
					counts++;
					if(t < m[i][j]){
						m[i][j] = t;
						s[i][j] = k;
						printMatrix();
					}
				}
			}
		}
	}
	
	
	public static void main(String[] args){
		System.err.println("    注意算法导论上对于Ai的维数表示有一个定义\n，即Ai = p[i-1] * p[i](i >= 1), 现在这个p\n是满足这个条件的" +
				"");
		System.out.println(Arrays.toString(p));
		int result = MatrixChainMinCountsRecursive(1, 6);
		System.out.println("=======>>> 递归求解最小次数 Min Counts : " + result);
		System.out.println("=======>>> 总计算次数 Counts : " + counts);
		System.out.println("=======>>>");
		counts = 0;
		System.out.println("=======>>>");
		System.out.println("=======>>> 下面进行最优括号位置求解");
		MatrixChainUnRecursive(6);
		System.out.println("=======>>> 总计算次数 Counts : " + counts);
		printBrokenK();
		printOptimalParens(1, 6);
	}
	
	/**
	 * 初始化P的矩阵元素
	 * @param i
	 * @param p
	 * @param q
	 */
	public static void intialP(int i, int m , int n){
		p[i - 1] = m;
		p[i] = n;
	}
	
	public static void printMatrix(){
		for(int i = 1; i < m.length; i++){
			for(int j = 1; j < m.length; j++){
				System.out.print(m[i][j] + "\t");
			}
			System.out.println("");
		}
		System.out.println("=============================");
	}
	
	public static void printBrokenK(){
		for(int i = 1; i < s.length; i++){
			for(int j = 1; j < s.length; j++){
				System.out.print(s[i][j] + "\t");
			}
			System.out.println("");
		}
		System.out.println("=============================");
	}
	
	/**
	 * 输出A[i:j]在s[i][j]处断开为A[i,s[i][j]]和A[s[i][j]+1 j]
	 * @param 开始位置
	 * @param 结束位置
	 */
	public static void printOptimalParens(int i, int j){
		if(i == j){
			System.out.print("A");
		}
		else{
			System.out.print("(");
			printOptimalParens(i, s[i][j]);
			printOptimalParens(s[i][j] + 1, j);
			System.out.print(")");
		}
			
	}
}
