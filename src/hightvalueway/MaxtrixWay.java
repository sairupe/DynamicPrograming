package hightvalueway;


public class MaxtrixWay {
	
	/**
	 * 5  3  3  7
	 * 2  4  1  5
	 * 4  4  1  5  
	 * 2  6  6  1
	 * 
	 * result : 29
	 */
	public static int[][] matrix =  {
										{5,3,3,7},
										{2,4,1,5},
										{4,4,1,5},
										{2,6,6,1}
									};
	// 数量
	static int N = 4;
	// 直接搜索法变量
	static int sumMax=0;    
	static int p1x=0;    
	static int p1y=0;    
	static int p2x=0;    
	static int p2y=0;    
	static int curMax=0;
	
	// 非优化前的动态规划
	static int[][][] dp = new int[2*N][N][N];
	static int[][] kk = new int[N][N];
	
	public static void main(String[] args) {
		System.out.println("===============单路求最大和递归算法==========");
		oneWaySum();
		System.out.println("===============单路求最大和非递归算法==========");
		getWaySum2();
	}
	
	// ============================  单程最大   =====================================
	public static void oneWaySum(){
		System.out.println(getWaySum(N-1, N-1));
	}
	
	// 递归方法
	public static int getWaySum(int i, int j){
		if(i < 0 || j < 0 || i > N || j > N){
			return 0;
		}
		return max(getWaySum(i - 1, j), getWaySum(i, j - 1)) + matrix[i][j];
	}
	
	// 非递归方法
	public static void getWaySum2(){
		for(int i = 0; i < N; i++){
			kk[i][i] = matrix[i][i];
		}
		for(int count = 1; count < N; count++){
			int j = count;
			for(int m = 0; m < N - count; m++){
				kk[m][j] = max(kk[m-1][j], kk[m][j-1]) + matrix[m][j];
				j++;
				printMatrix();
			}
		}
 		System.out.println(kk[N-1][N-1]);	
	}
	
	public static void printMatrix(){
		for(int i = 0; i < kk.length; i++){
			for(int j = 0; j < kk.length; j++){
				System.out.print(kk[i][j] + "\t");
			}
			System.out.println("");
		}
		System.out.println("=============================");
	}
	
	public static int max(int a, int b){
		return a > b? a : b;
	}
	// ============================  单程最大   =====================================
	public static void directSearch(){
		dfs(0);
		System.out.println(sumMax - matrix[N - 1][N - 1]);
	}
	
	public static void dfs(int index) {

		if (index == 2 * N - 2) {
			if (curMax > sumMax)
				sumMax = curMax;
			return;
		}

		if (!(p1x == 0 && p1y == 0) && !(p2x == N - 1 && p2y == N - 1)) {
			if (p1x >= p2x && p1y >= p2y)
				return;
		}

		// right right
		if (p1x + 1 < N && p2x + 1 < N) {
			p1x++;
			p2x++;
			int sum = matrix[p1x][p1y] + matrix[p2x][p2y];
			curMax += sum;
			dfs(index + 1);
			curMax -= sum;
			p1x--;
			p2x--;
		}

		// down down
		if (p1y + 1 < N && p2y + 1 < N) {
			p1y++;
			p2y++;
			int sum = matrix[p1x][p1y] + matrix[p2x][p2y];
			curMax += sum;
			dfs(index + 1);
			curMax -= sum;
			p1y--;
			p2y--;
		}

		// right down
		if (p1x + 1 < N && p2y + 1 < N) {
			p1x++;
			p2y++;
			int sum = matrix[p1x][p1y] + matrix[p2x][p2y];
			curMax += sum;
			dfs(index + 1);
			curMax -= sum;
			p1x--;
			p2y--;
		}

		// down right
		if (p1y + 1 < N && p2x + 1 < N) {
			p1y++;
			p2x++;
			int sum = matrix[p1x][p1y] + matrix[p2x][p2y];
			curMax += sum;
			dfs(index + 1);
			curMax -= sum;
			p1y--;
			p2x--;
		}
	}
	
	
	public static boolean isValid(int step, int x1, int x2, int n) { // 判断状态是否合法
		int y1 = step - x1, y2 = step - x2;
		return ((x1 >= 0) && (x1 < n) && (x2 >= 0) && (x2 < n) && (y1 >= 0)
				&& (y1 < n) && (y2 >= 0) && (y2 < n));
	}
	
	public static int getValue(int step, int x1, int x2, int n) { // 处理越界 不存在的位置 给负无穷的值
		return isValid(step, x1, x2, n) ? dp[step][x1][x2] : (Integer.MIN_VALUE); 
	}
	
	public static int getAnswer(int a[][],int n){
		return n;
		
	}
	
}