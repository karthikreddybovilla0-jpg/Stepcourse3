class LoanReceipt {

    private final String memberId;
    private final String[] bookIds;

    public LoanReceipt(String memberId, String[] bookIds) {
        this.memberId = memberId;
        this.bookIds = bookIds.clone();
    }

    public String[] getBookIds() {
        return bookIds.clone();
    }

    public LoanReceipt withCorrectedBookId(int index, String newId) {

        String[] newBooks = bookIds.clone();
        newBooks[index] = newId;

        return new LoanReceipt(memberId, newBooks);
    }
}

class ReferenceOnlyLoanReceipt extends LoanReceipt {

    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(
            String memberId,
            String[] bookIds,
            String roomNumber) {

        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }
}

public class LoanReceiptTest {

    static String processNightlyCirculation(
            LoanReceipt[] receipts) {

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (int i = 0; i < receipts.length; i++) {

            if (receipts[i] == null) {
                nullSkipped++;
            }
            else {
                processed++;

                if (receipts[i] instanceof ReferenceOnlyLoanReceipt) {
                    referenceOnly++;
                }
                else {
                    regular++;
                }
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + referenceOnly + " reference-only | "
                + regular + " regular";
    }

    public static void main(String[] args) {

        LoanReceipt r = new LoanReceipt(
                "LIB-8841",
                new String[]{"BK-100", "BK-101"}
        );

        // Test defensive copying
        String[] books = r.getBookIds();
        books[0] = "HACKED";

        System.out.println(
                "Original first ID: "
                + r.getBookIds()[0]
        );

        // Test with-style method
        LoanReceipt corrected =
                r.withCorrectedBookId(1, "BK-102");

        System.out.println(
                "Original: "
                + r.getBookIds()[0] + ", "
                + r.getBookIds()[1]
        );

        System.out.println(
                "Corrected: "
                + corrected.getBookIds()[0] + ", "
                + corrected.getBookIds()[1]
        );

        // Nightly batch
        LoanReceipt[] receipts = {
                new ReferenceOnlyLoanReceipt(
                        "LIB-001",
                        new String[]{"BK-200"},
                        "Reading Room 3"
                ),

                null,

                new LoanReceipt(
                        "LIB-002",
                        new String[]{"BK-201"}
                )
        };

        System.out.println(
                processNightlyCirculation(receipts)
        );
    }
}