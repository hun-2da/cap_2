package codingadventure.community.myapp.myDiary;

import java.util.Date;

public class Diary_db_Write {
    private String Title;
    private String Content;
    private Date diary_date;
    private String Category;

    private boolean user_publicityStatus;

    public Diary_db_Write() {
    }

    public Diary_db_Write(String title, String content, Date diary_date, String category, boolean user_publicityStatus) {
        Title = title;
        Content = content;
        this.diary_date = diary_date;
        Category = category;
        this.user_publicityStatus = user_publicityStatus;
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

    public boolean isUser_publicityStatus() {
        return user_publicityStatus;
    }

    public void setUser_publicityStatus(boolean user_publicityStatus) {
        this.user_publicityStatus = user_publicityStatus;
    }
}
