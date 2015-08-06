package best.bst;

import common.TreeNode;

public class BestBTSTreeNode extends TreeNode{

	// 命中查找概率
	public int prob;
	// 深度
	public int depth;
	
	public BestBTSTreeNode(int key) {
		super(key);
	}
}
