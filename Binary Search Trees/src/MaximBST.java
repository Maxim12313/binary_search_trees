//Maxim Kim

import java.awt.*;
import java.util.ArrayList;

public class MaximBST implements BinarySearchTree{

    private TreeNode root=null;
    private int verticalGap;
    private Graphics pen;

    public boolean contains(Comparable targetValue){
        return findNodePair(targetValue,root,null)[1]!=null;
    }
    private TreeNode[] findNodePair(Comparable targetValue, TreeNode node, TreeNode prevNode){
        if (node==null || node.getValue().compareTo(targetValue)==0){
            TreeNode[] pair = new TreeNode[2];
            pair[0]=prevNode;
            pair[1]=node;
            return pair;

        }
        if (node.getValue().compareTo(targetValue)<0){ //if target value is greater than node value
            return findNodePair(targetValue,node.getRight(),node);
        }
        else{ //if target value is less than node value
            return findNodePair(targetValue,node.getLeft(),node);
        }
    }

    public void add(Comparable newValue){
        TreeNode node = new TreeNode(newValue);
        if (root==null){
            root=node;
            return;
        }
        TreeNode[] pair = findNodePair(newValue,root,null);
        TreeNode prevNode = pair[0];

        if (prevNode==null) {
            prevNode = pair[1];
        }

        if (prevNode.getValue().compareTo(newValue)<0){ //if newValue is greater than node value
            prevNode.setRight(node);
        }
        else { //if newValue is less than node value
            prevNode.setLeft(node);
        }
    }

    public void remove(Comparable targetValue) {
        if (!contains(targetValue)){ //if empty or value not there
            return;
        }

        TreeNode[] nodePair = findNodePair(targetValue, root, null);
        TreeNode prevNode = nodePair[0];
        TreeNode thisNode = nodePair[1];

        if (thisNode.getLeft() == null && thisNode.getRight() == null) { //if node is a leaf
            if (thisNode==root){
                root=null;
            }
            else if (isRightChild(prevNode,targetValue)){ //if this node is a right child
                prevNode.setRight(null);
            } else{ //if this node is a left child
                prevNode.setLeft(null);
        }

        } else if (thisNode.getLeft() == null && thisNode.getRight() != null) { //node has only a right child
            if (thisNode==root){
                root = thisNode.getRight();
            }
            else if (isRightChild(prevNode,targetValue)){ //is right child
                prevNode.setRight(thisNode.getRight());
            }else{ //is left child
                prevNode.setLeft(thisNode.getRight());
            }

        } else if (thisNode.getLeft() != null && thisNode.getRight() == null) { //node has only a left child
            if (thisNode==root){
                root = thisNode.getLeft();
            }
            else
                if (isRightChild(prevNode,targetValue)){ //is right child
                prevNode.setRight(thisNode.getLeft());
            }else{ //is left child
                prevNode.setLeft(thisNode.getLeft());
            }
        }

        //swap value with thisNode and foundNode then recursively call remove on foundNode
        else if (thisNode.getLeft()!=null && thisNode.getRight()!=null){ //node has a left child and a right child
            Comparable nodeSwapValue = removeOption(thisNode).getValue();
            remove(nodeSwapValue);
            thisNode.setValue(nodeSwapValue);
        }
    }

    private TreeNode removeOption(TreeNode thisNode){
        if (Math.random()<0.5){
            thisNode = thisNode.getLeft();
            while (thisNode.getRight()!=null){
                thisNode=thisNode.getRight();
            }
        }
        else{
            thisNode=thisNode.getRight();
            while (thisNode.getLeft()!=null) {
                thisNode = thisNode.getLeft();
            }
        }
        remove(thisNode.getValue());
        return thisNode;
    }

    private boolean isRightChild(TreeNode prevNode, Comparable value){

        if (prevNode.getLeft() != null && prevNode.getLeft().getValue().compareTo(value) == 0) {
            return false;//is left child
        }
        return true; //is right child
    }

    public boolean isEmpty(){
        return root==null; //empty if root is null
    }
    public void printInOrder(){
        recursivePIO(root);
    }
    private void recursivePIO(TreeNode node){
        if (node==null){
            return;
        }
        recursivePIO(node.getLeft());
        System.out.print(node.getValue()+", ");
        recursivePIO(node.getRight());
    }


    public void printPreOrder(){
        recursivePreO(root);
    }

    private void recursivePreO(TreeNode node){
        if (node==null){
            return;
        }
        System.out.print(node.getValue()+", ");
        recursivePreO(node.getLeft());
        recursivePreO(node.getRight());
    }



    public void printPostOrder(){
        recursivePostO(root);
    }

    private void recursivePostO(TreeNode node){
        if (node==null){
            return;
        }
        recursivePostO(node.getLeft());
        recursivePostO(node.getRight());
        System.out.print(node.getValue()+", ");
    }

    public int getSize(){
        return recursiveGetSize(root);
    }
    private int recursiveGetSize(TreeNode node){
        if (node==null){
            return 0;
        }
        return 1+recursiveGetSize(node.getLeft())+recursiveGetSize(node.getRight());
    }

    public int getHeight(){
        return recursiveGetHeight(root);
    }
    public int recursiveGetHeight(TreeNode node){
        if (node==null){
            return 0;
        }
        int rightHeight = recursiveGetHeight(node.getRight());
        int leftHeight = recursiveGetHeight(node.getLeft());
        if (rightHeight<=leftHeight){
            return leftHeight+1;
        }
        else{
            return rightHeight+1;
        }
    }
    public void drawTree(Graphics pen, int screenWidth, int screenHeight){
        if (root==null){
            return;
        }
        int horizontalGap = (screenWidth)/(getSize()+1);
        verticalGap = (screenHeight)/(getHeight()+1);
        this.pen=pen;
        int x = horizontalGap;
        int y = verticalGap;
        ArrayList<TreeNode> nodeList = new ArrayList<>();
        processNodeList(root,nodeList);
        for (TreeNode node:nodeList){
            node.x=x;
            x+=horizontalGap;
        }
        setNodeY(root,y,null);
        recursiveDraw(root);
    }
    private void drawNode(TreeNode node){
        int margin=4;
        String string = (String)node.getValue();
        int width = pen.getFontMetrics().stringWidth(string)+margin;
        int height = pen.getFontMetrics().getHeight()+margin;
        int x=node.x-width/2;
        int y=node.y-height/2;

        pen.setColor(Color.WHITE);
        pen.fillRect(x,y,width,height);

        pen.setColor(Color.BLACK);
        pen.drawRect(x,y,width,height);
        pen.drawString(node.getValue().toString(),x+margin/2,node.y+height/4+margin/2);

    }

    private void processNodeList(TreeNode node,ArrayList<TreeNode> nodeList){
        if (node==null){
            return;
        }
        processNodeList(node.getLeft(),nodeList);
        nodeList.add(node);
        processNodeList(node.getRight(),nodeList);
    }
    private void setNodeY(TreeNode node,int y,TreeNode prevNode){
        if (node==null){
            return;
        }
        node.y=y;
        y+=verticalGap;
        setNodeY(node.getLeft(),y,node);
        setNodeY(node.getRight(),y,node);
        if (prevNode!=null){
            pen.drawLine(node.x,node.y,prevNode.x,prevNode.y);
        }
    }
    private void recursiveDraw(TreeNode node){
        if (node==null){
            return;
        }
        drawNode(node);
        recursiveDraw(node.getLeft());
        recursiveDraw(node.getRight());

    }

    //extra
    public Comparable getRootValue(){
        if (root!=null){
            return root.getValue();
        }
        return null;
    }
}
