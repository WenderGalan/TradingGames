package com.tradinggames.api.models;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document
public class FilesModel {

    @Id
    private String id;

    private String postId;
    private List<Binary> file;


   public FilesModel(){

   }

    public FilesModel(String postId, List<Binary> file) {
        this.postId = postId;
        this.file = file;
    }

    public String getId() {
        return id;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public List<Binary> getFile() {
        return file;
    }

    public void setFile(List<Binary> file) {
        this.file = file;
    }
}
