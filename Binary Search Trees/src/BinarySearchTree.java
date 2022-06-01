import java.awt.*;

interface BinarySearchTree
{
    public boolean contains(Comparable targetValue);
    public void add(Comparable newValue);
    public void remove(Comparable targetValue);
    public boolean isEmpty();
    public void printInOrder();
    public void printPreOrder();
    public void printPostOrder();
    public void drawTree(Graphics pen, int screenWidth, int screenHeight);
}
