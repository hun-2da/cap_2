package codingadventure.community.myapp.myDiary;

import java.util.Date;

public class Diary_db_Write {
    String Title;
    String Content;
    Date diary_date;
    String Category;

    boolean user_choice;

    public Diary_db_Write() {
    }

    public Diary_db_Write(String title, String content, Date diary_date, String category, boolean user_choice) {
        Title = title;
        Content = content;
        this.diary_date = diary_date;
        Category = category;
        this.user_choice = user_choice;
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

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public boolean isUser_choice() {
        return user_choice;
    }

    public void setUser_choice(boolean user_choice) {
        this.user_choice = user_choice;
    }
}
