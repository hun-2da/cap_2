package codingadventure.community.myapp.FirebasePack.ObjectPack;

import java.util.Date;

public class DiaryCommentWrite {
    Date Date;
    String CommentWriterName;
    String Content;
    int like;
    int dislike;

    public DiaryCommentWrite() {
    }

    public DiaryCommentWrite(java.util.Date date, String commentWriterName, String content, int like, int dislike) {
        Date = date;
        CommentWriterName = commentWriterName;
        Content = content;
        this.like = like;
        this.dislike = dislike;
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

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }
}
