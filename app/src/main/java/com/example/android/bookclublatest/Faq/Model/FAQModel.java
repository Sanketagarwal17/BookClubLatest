package com.example.android.bookclublatest.Faq.Model;

public class FAQModel
{
    String question,content;

    public FAQModel(String question, String content) {
        this.question = question;
        this.content = content;
    }

    public String getQuestion() {
        return question;
    }

    public String getContent() {
        return content;
    }
}
