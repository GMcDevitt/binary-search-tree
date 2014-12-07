/**
 * @author Gerard McDevitt
 */
public class Main {

    public static void main(String[] args) {

        //Array used for numbers 1-100
        int[] ascendingArray = new int[100];
        //Array used for numbers 100-1
        int[] descendingArray = new int[100];
        //Array used for random integers
        int[] randomArray = new int[100];

        //Array that will be used multiple times with 1000 random integers
        int[] randomArray_1000 = new int[1000];

        //Load with 0-100
        for (int i = 0; i < 100; i++) {
            ascendingArray[i] = i + 1;
        }

        //Load with 100-10
        for (int i = 99; i >= 0 - i; i--) {
            descendingArray[i] = i + 1;
        }

        //Load with 100 random integers
        for (int i = 0; i < 100; i++) {
            randomArray[i] = (int) (Math.random() * 100) + 1;
        }

        //Create a tree with 0-100
        BinarySearchTree bst = new BinarySearchTree(ascendingArray);
        System.out.println("Height of the Ascending tree: " + bst.getHeight());
        System.out.println("Size of the Ascending tree: " + bst.getSize());
        System.out.printf("Max and min value in the Ascending tree: %s, %s \n\n", bst.getMaxValue(), bst.getMinValue());

        //Create a tree with 100-0
        bst = new BinarySearchTree(descendingArray);
        System.out.println("Height of the Descending tree: " + bst.getHeight());
        System.out.println("Size of the Descending tree: " + bst.getSize());
        System.out.printf("Max and min value in the Descending tree: %s, %s \n\n", bst.getMaxValue(), bst.getMinValue());

        //Create a tree with 100 random ints
        bst = new BinarySearchTree(randomArray);
        System.out.println("Height of the Random tree: " + bst.getHeight());
        System.out.println("Size of the Random tree: " + bst.getSize());

        System.out.printf("Max and min value in the Random tree: %s, %s \n\n", bst.getMaxValue(), bst.getMinValue());

        //Load an array with 1000 integers 100 times to compare the size vs the height
        BinarySearchTree randomTree;
        //The min and max height for the random trees
        int minHeight = 100;
        int maxHeight = 0;
        int avgHeight = 0;

        //Load the tree 100 times
        for (int i = 0; i < 100; i++) {

            //Load the array with 1000 different items
            for (int j = 0; j < 1000; j++) {
                randomArray_1000[j] = (int) (Math.random() * 100) + 1;
            }

            //Initialize the tree
            randomTree = new BinarySearchTree(randomArray_1000);

            //If there is a new minimum height
            if (randomTree.getHeight() < minHeight) {
                 minHeight = randomTree.getHeight();
            }
            //If there is a new maximum height
            else if (randomTree.getHeight() > maxHeight) {
                maxHeight = randomTree.getHeight();
            }
            //Sum up for the average height
            avgHeight = randomTree.getHeight() + avgHeight;
        }
        //Calculate the average height
        avgHeight = avgHeight / 100;

        //Print out findings
        System.out.printf("With 100 different trees of 1000 items, the maximum height was %s," +
                " the minimum height was %s and the average height was %s", maxHeight, minHeight, avgHeight);

        //End
    }

}
