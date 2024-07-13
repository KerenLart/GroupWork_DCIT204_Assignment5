package assignment5;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JPanel contentPane;
    private JTextField txtInput;
    private JTextField txtTarget;

    public MainFrame() {
        setTitle("Greedy & Divide and Conquer Algorithms");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1024, 768);

        contentPane = new BackgroundPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblCategory = new JLabel("Select Category:");
        lblCategory.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        lblCategory.setBounds(362, 50, 300, 30);
        contentPane.add(lblCategory);

        JComboBox<String> categoryComboBox = new JComboBox<>();
        categoryComboBox.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        categoryComboBox.setBounds(362, 90, 300, 30);
        categoryComboBox.addItem("Divide and Conquer");
        categoryComboBox.addItem("Greedy algorithms");
        contentPane.add(categoryComboBox);

        JLabel lblSelect = new JLabel("Select Algorithm:");
        lblSelect.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        lblSelect.setBounds(362, 140, 300, 30);
        contentPane.add(lblSelect);

        JComboBox<String> algoComboBox = new JComboBox<>();
        algoComboBox.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        algoComboBox.setBounds(362, 180, 300, 30);
        contentPane.add(algoComboBox);

        categoryComboBox.addActionListener(e -> {
            String category = (String) categoryComboBox.getSelectedItem();
            algoComboBox.removeAllItems();
            if ("Divide and Conquer".equals(category)) {
                algoComboBox.addItem("QuickSort");
                algoComboBox.addItem("MergeSort");
                algoComboBox.addItem("Closest-Pair Problem");
                algoComboBox.addItem("Strassen's Matrix Multiplication");
                algoComboBox.addItem("Quickhull");
            } else if ("Greedy algorithms".equals(category)) {
                algoComboBox.addItem("Prim's MST");
                algoComboBox.addItem("Traveling Salesman Problem");
                algoComboBox.addItem("Kruskal's MST");
                algoComboBox.addItem("Dijkstra's Shortest Path");
                algoComboBox.addItem("Huffman Codes");
            }
        });

        JLabel lblInput = new JLabel("Enter Input (comma separated for array):");
        lblInput.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        lblInput.setForeground(Color.red);
        lblInput.setBackground(Color.white);
        lblInput.setBounds(262, 230, 500, 50);
        contentPane.add(lblInput);

        JTextArea instructions = new JTextArea();
        instructions.setBounds(0, 34, 250, 500);
        instructions.setLineWrap(true);
        instructions.setWrapStyleWord(true);
        instructions.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        instructions.setForeground(Color.black);
        instructions.setBackground(Color.white);
        contentPane.add(instructions);

        JLabel instructionsLabel = new JLabel("Instructions:");
        instructionsLabel.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        instructionsLabel.setBounds(0, 0, 250, 30);
        contentPane.add(instructionsLabel);

        algoComboBox.addActionListener(e -> {
            switch ((String) algoComboBox.getSelectedItem()) {
                case "QuickSort":
                    instructions.setText(
                            "Enter a list of numbers separated by commas. The algorithm will sort the numbers in ascending order.");
                    break;
                case "MergeSort":
                    instructions.setText(
                            "Enter a list of numbers separated by commas. The algorithm will sort the numbers in ascending order.");
                    break;
                case "Closest-Pair Problem":
                    instructions.setText(
                            "Enter a list of x and y coordinates separated by commas. Add a semi-colon between two points (e.g., 1,2;3,4). The algorithm will find the closest pair of points.");
                    break;
                case "Strassen's Matrix Multiplication":
                    instructions.setText(
                            "Enter two matrices separated by a semi-colon. Each matrix should be comma-separated (e.g., 1,2;3,4; 5,6;7,8). The algorithm will multiply the matrices.");
                    break;
                case "Quickhull":
                    instructions.setText(
                            "Enter a list of x and y coordinates separated by commas. Add a semi-colon between two points (e.g., 1,2;3,4). The algorithm will find the convex hull of the points.");
                    break;
                case "Prim's MST":
                    instructions.setText(
                            "Enter a matrix of distances between nodes separated by commas. Add a semi-colon between two nodes (e.g., 0,2,3;4,0,6;1,2,0). The algorithm will find the Minimum Spanning Tree.");
                    break;
                case "Traveling Salesman Problem":
                    instructions.setText(
                            "Enter a matrix of distances between cities separated by commas. Add a semi-colon between two cities (e.g., 0,2,3;4,0,6;1,2,0). Enter the starting city in the target input field. The algorithm will find the shortest route that visits each city exactly once and returns to the starting city.");
                    break;
                case "Kruskal's MST":
                    instructions.setText(
                            "Enter a matrix of distances between nodes separated by commas. Add a semi-colon between two nodes (e.g., 0,2,3;4,0,6;1,2,0). The algorithm will find the Minimum Spanning Tree.");
                    break;
                case "Dijkstra's Shortest Path":
                    instructions.setText(
                            "Enter a matrix of distances between nodes separated by commas. Add a semi-colon between two nodes (e.g., 0,2,3;4,0,6;1,2,0). Enter the starting node in the target input field. The algorithm will find the shortest path from the starting node to all other nodes.");
                    break;
                case "Huffman Codes":
                    instructions.setText(
                            "Enter a list of characters and their frequencies separated by commas. Add a semi-colon between two characters (e.g., a,3;b,2;c,1). The algorithm will generate Huffman codes for the characters.");
                    break;
                default:
                    instructions.setText(
                            "No algorithm selected. Please select a category and an algorithm.");
                    break;
            }
        });

        txtInput = new JTextField();
        txtInput.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        txtInput.setBounds(262, 270, 500, 30);
        contentPane.add(txtInput);
        txtInput.setColumns(10);

        JLabel lblTarget = new JLabel("Enter Target Value (if applicable):");
        lblTarget.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        lblTarget.setBounds(262, 310, 500, 30);
        contentPane.add(lblTarget);

        txtTarget = new JTextField();
        txtTarget.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        txtTarget.setBounds(262, 350, 500, 30);
        contentPane.add(txtTarget);
        txtTarget.setColumns(10);

        JButton btnRun = new JButton("Run");
        btnRun.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnRun.setBounds(462, 400, 100, 30);
        contentPane.add(btnRun);

        JLabel lblResult = new JLabel("");
        lblResult.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblResult.setBounds(262, 450, 500, 30);
        contentPane.add(lblResult);

        btnRun.addActionListener(e -> {
            String input = txtInput.getText();
            String targetText = txtTarget.getText();
            int target = 0;
            String result = "";
            try {
                switch ((String) algoComboBox.getSelectedItem()) {
                    case "QuickSort":
                        //result = QuickSort.quickSort(input);
                        break;
                    case "MergeSort":
                        //result = MergeSort.mergeSort(input);
                        break;
                    case "Closest-Pair Problem":
                        result = assignment5.ClosestPairProblem.closestPairProblem(input);
                        break;
                    case "Strassen's Matrix Multiplication":
                        //result = StrassensMatrixMultiplication.strassensMatrixMultiplication(input);
                        break;
                    case "Quickhull":
                       result = Quickhull.quickhull(input);
                        break;
                    case "Prim's MST":
                        //result = PrimsMST.primsMST(input);
                        break;
                    case "Traveling Salesman Problem":
                        target = targetText.isEmpty() ? 1 : Integer.parseInt(targetText);
                        //result = TravelingSalesmanProblem.travelingSalesmanProblem(input, target);
                        break;
                    case "Kruskal's MST":
                        //result = KruskalsMST.kruskalsMST(input);
                        break;
                    case "Dijkstra's Shortest Path":
                        try {
                            target = Integer.parseInt(targetText);
                            result = assignment5.DijkstraShortestPath.dijkstra(input, target);
                        } catch (NumberFormatException ex) {
                            result = "Invalid target node. Please enter a valid integer.";
                        }
                        break;
                    case "Huffman Codes":
                        //result = HuffmanCodes.huffmanCodes(input);
                        break;
                    default:
                        result = "No algorithm selected. Please select a category and an algorithm.";
                        break;
                }
            } catch (Exception ex) {
                result = "An error occurred: " + ex.getMessage();
            }
            lblResult.setText(result);
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MainFrame frame = new MainFrame();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
