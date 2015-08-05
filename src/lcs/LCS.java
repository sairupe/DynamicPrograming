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
	public static char[] Y = {'B', 'D', 'C', 'A', 'A', 'B', 'A'};
	
	public static int[][] c = new int[X.length + 1][Y.length + 1];
	public static char[][] b = new char[X.length + 1][Y.length + 1];
	
	public static void LCS(){
		for(int i = 1; i < X.length; i++){
			for(int j = 1; j < Y.length; j++){
				if(X[i] == Y[j]){
					c[i][j] = c[i-1][j-1] + 1;
					b[i][j] = '�I';
					//��b[i,j]������"�I"ʱ����ζ��xi=yi��LCS��һ��Ԫ�أ���
					//��ʾXi��Yj�����������������
					//Xi-1��Yj-1���������������β������xi�õ���������
				}
				else if(c[i-1][j] > c[i][j-1]){
					c[i][j] = c[i-1][j];
					b[i][j] = '��';
					//��b[i,j]������"��"ʱ��
					//��ʾXi��Yj������������к�Xi-1��Yj���������������ͬ
				}
				else{
					c[i][j] = c[i][j-1];
					b[i][j] = '��';
					//��b[i,j]������"��"ʱ��
					//��ʾXi��Yj������������к�Xi��Yj-1���������������ͬ
				}
			}
		}
	}
	
	public static void printLCS(int i, int j){
		if(i == 0 || j == 0){
			return;
		}
		if(b[i][j] == '�I'){
			printLCS(i-1, j-1);
			System.out.print(new char[]{X[i]});
		}
		else if(b[i][j] == '��'){
			printLCS(i-1, j);
		}
		else if(b[i][j] == '��'){
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
	}
	
	public static void printB(){
		for(int i = 0; i < b.length; i++){
			for(int j = 0; j < b[i].length; j++){
				System.out.print(b[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		LCS();
		printC();
		printB();
		printLCS(X.length - 1, Y.length - 1);
	}
}
