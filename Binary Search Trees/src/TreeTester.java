public class TreeTester {

    public static void main(String[] args) {

        BinarySearchTree tree = new MaximBST();

        tree.printInOrder();
        if (tree.contains("lemon")) {
            System.out.println("ERROR: empty tree should not contain 'lemon'");
        }

        tree.add("lemon");
        if (!tree.contains("lemon")) {
            System.out.println("ERROR: tree 1 should contain 'lemon'");
        }
        System.out.println("Should display: lemon");
        tree.printInOrder();

        tree.add("fig");
        if (!tree.contains("fig")) {
            System.out.println("ERROR: tree 2 should contain 'fig'");
        }
        if (!tree.contains("lemon")) {
            System.out.println("ERROR: tree 2 should contain 'lemon'");
        }
        System.out.println("Should display: fig  lemon");
        tree.printInOrder();

        tree.add("pear");
        if (!tree.contains("pear")) {
            System.out.println("ERROR: tree 3 should contain 'pear'");
        }
        if (!tree.contains("fig")) {
            System.out.println("ERROR: tree 3 should contain 'fig'");
        }
        if (!tree.contains("lemon")) {
            System.out.println("ERROR: tree 3 should contain 'lemon'");
        }
        if (tree.contains("pumpkin")) {
            System.out.println("ERROR: tree 3 should not contain 'pumpkin'");
        }
        System.out.println("Should display: fig  lemon  pear");
        tree.printInOrder();

        String[] fruits = new String[]{"orange", "grape", "coconut", "apricot", "banana", "apple", "strawberry", "raspberry", "pineapple"};
        for (String fruit : fruits) {
            tree.add(fruit);
            if (!tree.contains(fruit)) {
                System.out.println("ERROR: tree 4 should contain '" + fruit + "'");
            }
        }
        System.out.println("Should display: apple  apricot  banana  coconut  fig  grape  lemon  orange  pear  pineapple  raspberry  strawberry");
        tree.printInOrder();

        for (String fruit : fruits) {
            if (!tree.contains(fruit)) {
                System.out.println("ERROR: tree 5 should contain '" + fruit + "'");
            }
        }

        if (!tree.contains("pear")) {
            System.out.println("ERROR: tree 6 should contain 'pear'");
        }
        if (!tree.contains("fig")) {
            System.out.println("ERROR: tree 6 should contain 'fig'");
        }
        if (!tree.contains("lemon")) {
            System.out.println("ERROR: tree 6 should contain 'lemon'");
        }

        if (tree.contains("pumpkin")) {
            System.out.println("ERROR: tree 7 should not contain 'pumpkin'");
        }

        System.out.println("/////////////////////////"); //Maxim's code below
        MaximBST newTree= (MaximBST)tree;
        System.out.println("Actual Size: 12   |Found Size: "+ newTree.getSize());
        System.out.println("Actual Size: 5   |Found Size: "+newTree.getHeight());
        System.out.println("///////////////////////////////////");
        System.out.println("InOrder");
        newTree.printInOrder();
        System.out.println(" ");
        System.out.println("///////////////////////////////////");
        System.out.println("PreOrder");
        newTree.printPreOrder();
        System.out.println(" ");
        System.out.println("///////////////////////////////////");
        System.out.println("PostOrder");
        newTree.printPostOrder();
        TreeWindow treeWindow = new TreeWindow(newTree);


    }
}
