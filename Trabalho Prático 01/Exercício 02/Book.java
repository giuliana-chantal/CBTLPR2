public class Book {
    private String name;
    private Author[] authors;
    private double price;
    private int qty;

    public Book(String name, Author[] authors, double price, int qty) {
        this.name = name;
        this.authors = authors;
        this.price = price;
        this.qty = qty;
    }

    @Override
    public String toString() {
        StringBuilder authorsString = new StringBuilder();
        for (int i = 0; i < authors.length; i++) {
            authorsString.append(authors[i].toString());
            if (i < authors.length - 1) {
                authorsString.append(", ");
            }
        }
        return "Book[name=" + name + ",authors={" + authorsString + "},price=" + price + ",qty=" + qty + "]";
    }
}
