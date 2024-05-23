package codingadventure.community.myapp.FirebasePack.ObjectPack;

import java.util.Date;

public class DiaryCommentWrite {
    Date Date;
    String CommentWriterName;
    String Content;

    public DiaryCommentWrite() {
    }

    public DiaryCommentWrite(java.util.Date date, String commentWriterName, String content) {
        Date = date;
        CommentWriterName = commentWriterName;
        Content = content;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }

    public String getCommentWriterName() {
        return CommentWriterName;
    }

    public void setCommentWriterName(String commentWriterName) {
        CommentWriterName = commentWriterName;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
