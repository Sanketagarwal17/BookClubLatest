package com.example.android.bookclublatest.Member.AddBook;

public class AddBookModel
{
    String book,author,publisher,tags,hardsofy,ism,isbn;

    public String getBook() {
        return book;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getTags() {
        return tags;
    }

    public String getHardsofy() {
        return hardsofy;
    }

    public String getIsm() {
        return ism;
    }

    public String getIsbn() {
        return isbn;
    }

    public AddBookModel(String author, String book, String hardsofy, String isbn, String ism, String publisher, String tags) {
        this.book = book;
        this.author = author;
        this.publisher = publisher;
        this.tags = tags;
        this.hardsofy = hardsofy;
        this.ism = ism;
        this.isbn = isbn;
    }

    public AddBookModel()
    {

    }
}
