package org.api.dto.responsDto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostsDto {
    @JsonProperty("_id")
    private String id;
    private String title;
    private String body;
    @JsonProperty("select1")
    private String select1;
    private String uniquePost;
    private String createdDate;
    private AuthorDto author;
    private Boolean isVisitorOwner;

    public  PostsDto(){

    }

    public PostsDto(String title, String body, String select1, String uniquePost, AuthorDto author, Boolean isVisitorOwner) {
        this.title = title;
        this.body = body;
        this.select1 = select1;
        this.uniquePost = uniquePost;
        this.author = author;
        this.isVisitorOwner = isVisitorOwner;
    }

    public void set_id(String _id) {
        this.id = _id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setSelect1(String select1) {
        this.select1 = select1;
    }

    public void setUniquePost(String uniquePost) {
        this.uniquePost = uniquePost;
    }



    public void setAuthor(AuthorDto author) {
        this.author = author;
    }

    public void setIsVisitorOwner(Boolean visitorOwner) {
        isVisitorOwner = visitorOwner;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String get_id() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getSelect1() {
        return select1;
    }

    public String getUniquePost() {
        return uniquePost;
    }



    public AuthorDto getAuthor() {
        return author;
    }

    public Boolean getIsVisitorOwner() {
        return isVisitorOwner;
    }

    @Override
    public String toString() {
        return "PostsDto{" +
                "_id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", select1='" + select1 + '\'' +
                ", uniquePost='" + uniquePost + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", author=" + author +
                ", isVisitorOwner=" + isVisitorOwner +
                '}';
    }
}
