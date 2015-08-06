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
	 * �����ڵ��滻���ڵ�(���ڵ�ֻ��һ������)
	 * �ı��丸�࣬��������ã����ı�����
	 * @param node
	 */
	public void singleChilNodeReplayceParentNode(TreeNode node){
		node.parent = parent;
		// ��ԭ�ڵ�ĸ��ڵ��������¸�ֵ
		if(parent != null){// ������������
			if(this == parent.leftChild){
				parent.leftChild = node;
			}
			else{
				parent.rightChild = node;
			}
		}
	}
	
	/**
	 * �滻�����KEYֵΪ����ڵ��ֵ
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
