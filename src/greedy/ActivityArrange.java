package greedy;

public class ActivityArrange {

	// 活动开始时间，从下标1开始
	public static int[] s = new int[] { 0, 1, 3, 0, 5, 3, 5, 6, 8, 8, 2, 12 };

	// 活动结束时间，从下标1
	public static int[] f = new int[] { 0, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 };

	// 是否在最大活动安排表中
	public static boolean[] arrange = new boolean[s.length];
	

//	择活动i加入集合A中。贪心算法并不总能求得问题的整体最优解。但对于活动安排问题，
//	贪心算法greedySelector却总能求得的整体最优解，即它最终所确定的相容活动集合A的规模最大。
//	这个结论可以用数学归纳法证明。
//
//	证明如下：设E=｛0，1，2，…，n-1｝为所给的活动集合。
//	由于E中活动安排安结束时间的非减序排列，所以活动0具有最早完成时间。
//	首先证明活动安排问题有一个最优解以贪心选择开始，即该最优解中包含活动0.
//	设a是所给的活动安排问题的一个最优解，且a中活动也按结束时间非减序排列，
//	a中的第一个活动是活动k。如k=0，则a就是一个以贪心选择开始的最优解。
//	若k>0，则我们设b=a-｛k｝∪｛0｝。由于end[0] ≤end[k],且a中活动是互为相容的，
//	故b中的活动也是互为相容的。又由于b中的活动个数与a中活动个数相同，且a是最优的，故b也是最优的。
//	也就是说b是一个以贪心选择活动0开始的最优活动安排。
//	因此，证明了总存在一个以贪心选择开始的最优活动安排方案，也就是算法具有贪心选择性质。
	
	public static void activityArrange(){
		
		// 上一次最近加入过的活动索引
		int lastFi = 0;
		
		for(int i = 0; i < s.length; i++){
			if(s[i] >= f[lastFi]){
				arrange[i] = true;
				lastFi = i;
			}
			else{
				arrange[i] = false;
			}
		}
		
		for(int i = 1; i < s.length; i++){
			if(arrange[i]){
				System.out.println("活动 " + i + "被安排进行程，它的开始和结束时间是 【" + s[i] + "】-【" + f[i] + "】");
			}
		}
	}
	public static void main(String[] args) {
		activityArrange();
	}
}
