package model;

public class Book extends BaseModel {
    private String bookName;
    private String description;
    private Genre genre;

    public Book() {
    }

    public Book(String bookName, String description, Genre genre) {
        this.bookName = bookName;
        this.description = description;
        this.genre = genre;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
