import javax.swing.*;

class TreeWindow
{
    TreeWindow(BinarySearchTree tree)
    {
        TreePanel treePanel = new TreePanel(tree);

        JFrame frame;
        frame = new JFrame("Binary Search Tree");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(treePanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.toFront();
    }

    public static void main(String[] args)
    {
        BinarySearchTree t = new MaximBST();

        t.add("M");
        t.add("F");
        t.add("Q");
        t.add("C");
        t.add("B");
        t.add("D");
        t.add("A");
        t.add("I");
        t.add("G");
        t.add("J");
        t.add("U");
        t.add("W");
        t.add("X");
        t.add("Z");
        t.add("Y");
        t.add("T");
        t.add("V");
        t.add("S");

        System.out.println("///////////////////////////////////");
        System.out.println("InOrder");
        t.printInOrder();
        System.out.println(" ");
        System.out.println("///////////////////////////////////");
        System.out.println("PreOrder");
        t.printPreOrder();
        System.out.println(" ");
        System.out.println("///////////////////////////////////");
        System.out.println("PostOrder");
        t.printPostOrder();
        System.out.println(" ");
        System.out.println("Height: "+((MaximBST)t).getHeight());
        System.out.println("Size: "+((MaximBST)t).getSize());
        TreeWindow treeWindow = new TreeWindow(t);
    }
}
