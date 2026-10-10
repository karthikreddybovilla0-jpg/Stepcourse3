import java.util.Arrays;

public class MergingTwoTokenQueues {
    static int[] mergeTokens(int[] a, int[] b) {
        int m = a.length;
        int n = b.length;
        int[] result = new int[m + n];

        int i = 0, j = 0, k = 0;

        while (i < m && j < n) {
            if (a[i] <= b[j]) {
                result[k++] = a[i++];
            } else {
                result[k++] = b[j++];
            }
        }

        while (i < m) {
            result[k++] = a[i++];
        }

        while (j < n) {
            result[k++] = b[j++];
        }

        return result;
    }

    public static void main(String[] args) {
        int[] counterA = {3, 8, 15, 20};
        int[] counterB = {5, 8, 12};

        System.out.println(Arrays.toString(
            mergeTokens(counterA, counterB)));
    }
}