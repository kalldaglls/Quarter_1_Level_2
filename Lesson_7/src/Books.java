import java.math.BigDecimal;

public class Books {
    private int book_Id;
    private int author_Id;
    private String title;
    private int genre_Id;
    private BigDecimal price;
    private int amount;

    public Books(int book_Id, int author_Id, String title, int genre_Id, BigDecimal price, int amount) {
        this.book_Id = book_Id;
        this.author_Id = author_Id;
        this.title = title;
        this.genre_Id = genre_Id;
        this.price = price;
        this.amount = amount;
    }

    public int getBook_Id() {
        return book_Id;
    }

    public int getAuthor_Id() {
        return author_Id;
    }

    public String getTitle() {
        return title;
    }

    public int getGenre_Id() {
        return genre_Id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Books{" +
                "book_Id=" + book_Id +
                ", author_Id=" + author_Id +
                ", title='" + title + '\'' +
                ", genre_Id=" + genre_Id +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }
}
