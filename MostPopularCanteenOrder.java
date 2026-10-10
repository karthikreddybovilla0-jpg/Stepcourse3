import java.util.HashMap;

public class MostPopularCanteenOrder {
    static void mostPopular(String[] orders) {
        HashMap<String, Integer> count = new HashMap<>();

        for (String item : orders) {
            count.put(item, count.getOrDefault(item, 0) + 1);
        }

        String popular = orders[0];
        int maxCount = count.get(popular);

        for (String item : orders) {
            if (count.get(item) > maxCount) {
                popular = item;
                maxCount = count.get(item);
            }
        }

        System.out.println("(\"" + popular + "\", " + maxCount + ")");
    }

    public static void main(String[] args) {
        String[] orders = {
            "dosa", "idli", "vada", "dosa",
            "idli", "dosa", "tea"
        };

        mostPopular(orders);
    }
}