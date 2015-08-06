package common;

public class TreeNode {
	
	public TreeNode parent;
	public TreeNode leftChild;
	public TreeNode rightChild;
	public int key;
	
	public TreeNode(int key){
		this.key = key;
	}
	
	/**
	 * 子树节点替换父节点(父节点只有一个子树)
	 * 改变其父类，子类的引用，不改变自身
	 * @param node
	 */
	public void singleChilNodeReplayceParentNode(TreeNode node){
		node.parent = parent;
		// 对原节点的父节点引用重新赋值
		if(parent != null){// 拷贝附加数据
			if(this == parent.leftChild){
				parent.leftChild = node;
			}
			else{
				parent.rightChild = node;
			}
		}
	}
	
	/**
	 * 替换自身的KEY值为传入节点的值
	 * @param node
	 */
	public void changeDataKey(TreeNode node){
		this.key = node.key;
	}
	
	@Override
	public String toString() {
		return key + " | ";
	}
}
