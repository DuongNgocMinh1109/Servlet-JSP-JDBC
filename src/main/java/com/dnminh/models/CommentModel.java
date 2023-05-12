package com.dnminh.models;

public class CommentModel extends AbstractModel<CommentModel> {
    private String content;
    private Long userId;
    private Long newsId;

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getNewId() {
        return newsId;
    }
    public void setNewId(Long newsId) {
        this.newsId = newsId;
    }
}
