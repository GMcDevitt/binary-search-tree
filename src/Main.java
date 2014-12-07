/**
 * @author Gerard McDevitt
 */
public class Main {

    public static void main(String[] args) {

        int[] ascendingArray = new int[100];
        int[] descendingArray = new int[100];
        int[] randomArray = new int[100];

        for (int i = 0; i < 100; i++) {
            ascendingArray[i] = i;
        }

        for (int i = 99; i > 0; i--) {
            descendingArray[i] = i;
        }

        for (int i = 0; i < 100; i++) {
            randomArray[i] = (int) (Math.random() * 100);
        }

        BinarySearchTree bst = new BinarySearchTree(ascendingArray);
        System.out.println("Height of the Ascending tree :" + bst.getHeight());
        System.out.println("Size of the Ascending tree :" + bst.getSize());
        System.out.printf("Max and min value in the Ascending tree: %s, %s \n\n", bst.getMaxValue(), bst.getMinValue());

        bst = new BinarySearchTree(descendingArray);
        System.out.println("Height of the Descending tree :" + bst.getHeight());
        System.out.println("Size of the Descending tree  :" + bst.getSize());
        System.out.printf("Max and min value in the Descending tree: %s, %s \n\n", bst.getMaxValue(), bst.getMinValue());

        bst = new BinarySearchTree(randomArray);
        System.out.println("Height of the Random tree :" + bst.getHeight());
        System.out.println("Size of the Random tree :" + bst.getSize());

        System.out.printf("Max and min value in the Random tree: %s, %s \n\n", bst.getMaxValue(), bst.getMinValue());

    }

}
