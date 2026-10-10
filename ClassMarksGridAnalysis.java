
public class ClassMarksGridAnalysis {
    public static void main(String[] args) {
        String[] names = {"Asha", "Ravi", "Neha"};

        int[][] marks = {
            {78, 85, 90},
            {88, 92, 79},
            {65, 70, 95}
        };

        int[] subjectTotal = new int[3];
        int topper = 0;
        int maxTotal = -1;

        System.out.print("Totals ");

        for (int i = 0; i < marks.length; i++) {
            int total = 0;

            for (int j = 0; j < 3; j++) {
                total += marks[i][j];
                subjectTotal[j] += marks[i][j];
            }

            System.out.print(names[i] + " " + total);

            if (i < marks.length - 1) {
                System.out.print(", ");
            }

            if (total > maxTotal) {
                maxTotal = total;
                topper = i;
            }
        }

        System.out.println();

        System.out.printf("averages %.2f, %.2f, %.2f%n",
            subjectTotal[0] / (double) marks.length,
            subjectTotal[1] / (double) marks.length,
            subjectTotal[2] / (double) marks.length);

        System.out.println("topper " + names[topper] +
            " (" + maxTotal + ")");
    }
}
