package lcs;

/**
 * @author SyrianaZh
 * 2015-8-5
 * 
 * �����������(������)
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
					b[i][j] = '�I';
					printB();
					//��b[i,j]������"�I"ʱ����ζ��xi=yi��LCS��һ��Ԫ�أ���
					//��ʾXi��Yj�����������������
					//Xi-1��Yj-1���������������β������xi�õ���������
				}
				else if(c[i-1][j] >= c[i][j-1]){// ע����� >= ����Ҫ�� LCS()��֧�����һ�£�=�ƶ��������֧Ҳ��
					c[i][j] = c[i-1][j];
					b[i][j] = '��';
					printB();
					//��b[i,j]������"��"ʱ��
					//��ʾXi��Yj������������к�Xi-1��Yj���������������ͬ
				}
				else{
					c[i][j] = c[i][j-1];
					b[i][j] = '��';
					printB();
					//��b[i,j]������"��"ʱ��
					//��ʾXi��Yj������������к�Xi��Yj-1���������������ͬ
				}
			}
		}
	}
	
	public static void printLCS(int i, int j){
		// ���IF�ж�ȥ��Ҳ���ԣ�ֻ������Ϊ�˼���������߼���֧�ж�
		if(i == 0 || j == 0){
			return;
		}
		if(b[i][j] == '�I'){
			printLCS(i-1, j-1);
			//X�Ǵ�����0��ʼ�����飬i��j�ķ�Χ�Ǵ�1��ʼ�ģ�����Ҫ-1
			System.out.print(new char[]{X[i-1]});
		}
		else if(b[i][j] == '��'){
			printLCS(i-1, j);
		}
		else if(b[i][j] == '��'){
			printLCS(i, j-1);
		}
	}
	
	public static void printLCS_WithoutB(int i, int j){
		// ���IF�ж�ȥ��Ҳ���ԣ�ֻ������Ϊ�˼���������߼���֧�ж�
		if(i == 0 || j == 0){
			return;
		}
		if(X[i-1] == Y[j-1]){// ��ʵ����LCS()�У���Щ��ͷ���������IF����ֵ�滻�Ϳ�����
			printLCS(i-1, j-1);
			//X�Ǵ�����0��ʼ�����飬i��j�ķ�Χ�Ǵ�1��ʼ�ģ�����Ҫ-1
			System.out.print(new char[]{X[i-1]});
		}
		else if(c[i-1][j] >= c[i][j-1]){// ע����� >= ����Ҫ�� LCS()��֧�����һ�£�=�ƶ��������֧Ҳ��
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
		System.out.println("\n==============>>> ����B�ĸ���������ӡ�����������");
		printLCS(X.length, Y.length);
		System.out.println("\n==============>>> ������B�ĸ���������ӡ�����������");
		printLCS_WithoutB(X.length, Y.length);
	}
}
