package com.example.android.bookclublatest.Member.AddBook;

public class AddBookModel
{
    String book,author,publisher,tags,hardsofy,ism,isbn,status,url ,desc;

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getHardsofy() {
        return hardsofy;
    }

    public void setHardsofy(String hardsofy) {
        this.hardsofy = hardsofy;
    }

    public String getIsm() {
        return ism;
    }

    public void setIsm(String ism) {
        this.ism = ism;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public AddBookModel(String book, String author, String publisher, String tags, String hardsofy, String ism, String isbn, String status, String url, String desc) {
        this.book = book;
        this.author = author;
        this.publisher = publisher;
        this.tags = tags;
        this.hardsofy = hardsofy;
        this.ism = ism;
        this.isbn = isbn;
        this.status = status;
        this.url = url;
        this.desc = desc;
    }

    public AddBookModel() {
    }
}
