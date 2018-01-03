package com.tradinggames.api.models;

import java.math.BigInteger;
import java.util.List;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Classe modelo para os Posts
 *
 * @author joao
 */
@Document
public class PostModel {

    @Id
    private String postId;

    private String postTitle;
    private String postDescription;
    private Double productPrice;
    private String postCategory;
    private String[] postTags;
    private String postAuthor;
    private String contactNumber;

    public PostModel() {

    }

    public PostModel(String postTitle, String postDescription, Double productPrice, String postCategory, String[] postTags, String postAuthor) {
        this.postTitle = postTitle;
        this.postDescription = postDescription;
        this.productPrice = productPrice;
        this.postCategory = postCategory;
        this.postTags = postTags;
        this.postAuthor = postAuthor;
    }


    public String[] getPostTags() {
        return postTags;
    }

    public void setPostTags(String... postTags) {
        this.postTags = postTags;
    }

    public String getPostAuthor() {
        return postAuthor;
    }

    public void setPostAuthor(String postAuthor) {
        this.postAuthor = postAuthor;
    }

    public String getPostId() {
        return postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public String getPostCategory() {
        return postCategory;
    }

    public void setPostCategory(String postCategory) {
        this.postCategory = postCategory;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
