package lcs;

/**
 * @author SyrianaZh
 * 2015-8-5
 * 
 * 最长公共子序列(不连续)
 */
public class LCS {

	// B, C, B, A
	public static char[] X = {'A', 'B', 'C', 'B', 'D', 'A', 'B'};
	public static char[] Y = {'B', 'D', 'C', 'A', 'B', 'A'};
	
	public static int[][] c = new int[X.length + 1][Y.length + 1];
	public static char[][] b = new char[X.length + 1][Y.length + 1];
	
	public static void LCS(){
		for(int i = 1; i <= X.length; i++){
			for(int j = 1; j <= Y.length; j++){
				if(X[i-1] == Y[j-1]){
					c[i][j] = c[i-1][j-1] + 1;
					b[i][j] = '↖';
					printB();
					//当b[i,j]中遇到"↖"时（意味着xi=yi是LCS的一个元素），
					//表示Xi与Yj的最长公共子序列是由
					//Xi-1与Yj-1的最长公共子序列在尾部加上xi得到的子序列
				}
				else if(c[i-1][j] >= c[i][j-1]){// 注意这个 >= 符号要和 LCS()分支进入的一致，=移动到下面分支也行
					c[i][j] = c[i-1][j];
					b[i][j] = '↑';
					printB();
					//当b[i,j]中遇到"↑"时，
					//表示Xi与Yj的最长公共子序列和Xi-1与Yj的最长公共子序列相同
				}
				else{
					c[i][j] = c[i][j-1];
					b[i][j] = '←';
					printB();
					//当b[i,j]中遇到"←"时，
					//表示Xi与Yj的最长公共子序列和Xi与Yj-1的最长公共子序列相同
				}
			}
		}
	}
	
	public static void printLCS(int i, int j){
		// 这个IF判断去掉也可以，只不过是为了减少下面的逻辑分支判断
		if(i == 0 || j == 0){
			return;
		}
		if(b[i][j] == '↖'){
			printLCS(i-1, j-1);
			//X是从索引0开始的数组，i和j的范围是从1开始的，所以要-1
			System.out.print(new char[]{X[i-1]});
		}
		else if(b[i][j] == '↑'){
			printLCS(i-1, j);
		}
		else if(b[i][j] == '←'){
			printLCS(i, j-1);
		}
	}
	
	public static void printLCS_WithoutB(int i, int j){
		// 这个IF判断去掉也可以，只不过是为了减少下面的逻辑分支判断
		if(i == 0 || j == 0){
			return;
		}
		if(X[i-1] == Y[j-1]){// 其实利用LCS()中，这些箭头条件进入的IF成立值替换就可以了
			printLCS(i-1, j-1);
			//X是从索引0开始的数组，i和j的范围是从1开始的，所以要-1
			System.out.print(new char[]{X[i-1]});
		}
		else if(c[i-1][j] >= c[i][j-1]){// 注意这个 >= 符号要和 LCS()分支进入的一致，=移动到下面分支也行
			printLCS(i-1, j);
		}
		else if(c[i-1][j] < c[i][j-1]){// 
			printLCS(i, j-1);
		}
	}
	
	public static void printC(){
		for(int i = 0; i < c.length; i++){
			for(int j = 0; j < c[i].length; j++){
				System.out.print(c[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void printB(){
		for(int i = 0; i < b.length; i++){
			for(int j = 0; j < b[i].length; j++){
				if(b[i][j] == '\0')
					System.out.print(0 + "\t");
				else
					System.out.print(b[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		LCS();
		printC();
		printB();
		System.out.println("\n==============>>> 根据B的辅助表来打印最长公共子序列");
		printLCS(X.length, Y.length);
		System.out.println("\n==============>>> 不根据B的辅助表来打印最长公共子序列");
		printLCS_WithoutB(X.length, Y.length);
	}
}
