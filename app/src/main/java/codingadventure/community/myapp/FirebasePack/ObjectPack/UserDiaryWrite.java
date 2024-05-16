package codingadventure.community.myapp.FirebasePack.ObjectPack;

import java.util.Date;

public class UserDiaryWrite {
    private String Title;
    private String Content;
    private Date DiaryDate;
    private String Category;

    //---------------------------------------.
    private boolean publicityStatus;

    //--------------------------------------

    private int limit;
    private int commentCount;


    public UserDiaryWrite() {
    }

    // 공통되는 부분.
    public UserDiaryWrite(String title, String content, Date diaryDate, String category) {
        Title = title;
        Content = content;
        DiaryDate = diaryDate;
        Category = category;
    }

    // user diary의 경우 이렇게 처리된다.
    public UserDiaryWrite(String title, String content, Date diaryDate, String category, boolean publicityStatus) {
        Title = title;
        Content = content;
        DiaryDate = diaryDate;
        Category = category;
        this.publicityStatus = publicityStatus;
    }

    // community의 경우 이렇게 처리된다.


    public UserDiaryWrite(String title, String content, Date diaryDate, String category, int limit, int commentCount) {
        Title = title;
        Content = content;
        DiaryDate = diaryDate;
        Category = category;
        this.limit = limit;
        this.commentCount = commentCount;
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

    public Date getDiaryDate() {
        return DiaryDate;
    }

    public void setDiaryDate(Date diaryDate) {
        DiaryDate = diaryDate;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public boolean isPublicityStatus() {
        return publicityStatus;
    }

    public void setPublicityStatus(boolean publicityStatus) {
        this.publicityStatus = publicityStatus;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }
}
