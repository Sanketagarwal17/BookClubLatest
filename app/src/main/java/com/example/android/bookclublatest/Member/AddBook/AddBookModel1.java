package com.example.android.bookclublatest.Member.AddBook;

import android.text.SpannableStringBuilder;

public class AddBookModel1
{
    SpannableStringBuilder book,author,publisher;
    String tags,hardsofy,ism,isbn,status,no,url,desc;

    public SpannableStringBuilder getBook() {
        return book;
    }

    public void setBook(SpannableStringBuilder book) {
        this.book = book;
    }

    public SpannableStringBuilder getAuthor() {
        return author;
    }

    public void setAuthor(SpannableStringBuilder author) {
        this.author = author;
    }

    public SpannableStringBuilder getPublisher() {
        return publisher;
    }

    public void setPublisher(SpannableStringBuilder publisher) {
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

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
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

    public AddBookModel1(SpannableStringBuilder book, SpannableStringBuilder author, SpannableStringBuilder publisher, String tags, String hardsofy, String ism, String isbn, String status, String no, String url, String desc) {
        this.book = book;
        this.author = author;
        this.publisher = publisher;
        this.tags = tags;
        this.hardsofy = hardsofy;
        this.ism = ism;
        this.isbn = isbn;
        this.status = status;
        this.no = no;
        this.url = url;
        this.desc = desc;
    }
}
