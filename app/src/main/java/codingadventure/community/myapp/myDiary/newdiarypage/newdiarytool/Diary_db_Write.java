package codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool;

import java.util.Date;

public class Diary_db_Write {
    String Title;
    String Content;
    Date diary_date;
    int Category;

    public Diary_db_Write(String title, String content, Date diary_date, int category) {
        Title = title;
        Content = content;
        this.diary_date = diary_date;
        Category = category;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Date getDiary_date() {
        return diary_date;
    }

    public void setDiary_date(Date diary_date) {
        this.diary_date = diary_date;
    }

    public int getCategory() {
        return Category;
    }

    public void setCategory(int category) {
        Category = category;
    }
}
