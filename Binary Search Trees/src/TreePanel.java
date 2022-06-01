import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

class TreePanel extends JPanel
{
    private BinarySearchTree tree;
    private TreeCanvas treeCanvas;
    private JTextField valueField;

    TreePanel(BinarySearchTree tree)
    {
        this.tree = tree;
        treeCanvas = new TreeCanvas();

        valueField = new JTextField(10);

        JButton insertButton = new JButton("Add");
        insertButton.addActionListener(new InsertListener());
        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(new RemoveListener());

        //Maxim
        JButton removeRoot = new JButton("Remove Root");
        removeRoot.addActionListener(new RemoveRoot());

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        controlPanel.add(new JLabel("Value: "));
        controlPanel.add(valueField);
        controlPanel.add(insertButton);
        controlPanel.add(removeButton);

        //Maxim
        controlPanel.add(removeRoot);

        setLayout(new BorderLayout());
        add(treeCanvas, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
    }


    class InsertListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            String newValue = valueField.getText();
            tree.add(newValue);
            treeCanvas.draw();
        }
    }

    class RemoveListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            String newValue = valueField.getText();
            tree.remove(newValue);
            treeCanvas.draw();
        }
    }

    class TreeCanvas extends ImageCanvas
    {
        TreeCanvas() {
            super(800,600);
        }

        void draw() {
            Graphics pen = getPen();
            int width =  getWidth();
            int height = getHeight();
            pen.setColor(Color.WHITE);
            pen.fillRect(0, 0, width, height);
            pen.setColor(Color.BLACK);
            tree.drawTree(pen, width, height);
            display();
        }

        public void resized() {
            draw();
        }
    }
    //MAXIM
    class RemoveRoot implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            tree.remove(((MaximBST)tree).getRootValue());
            treeCanvas.draw();
        }
    }


}
