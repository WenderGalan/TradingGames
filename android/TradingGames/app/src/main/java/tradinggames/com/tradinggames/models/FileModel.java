package tradinggames.com.tradinggames.models;

import android.graphics.Bitmap;

import java.util.List;

/**
 * Created by João on 14/12/2017.
 */

public class FileModel {

    private String postId;

    private List<Bitmap> files;

    public FileModel() {
    }

    public FileModel(String postId, List<Bitmap> files) {
        this.postId = postId;
        this.files = files;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public List<Bitmap> getFiles() {
        return files;
    }

    public void setFiles(List<Bitmap> files) {
        this.files = files;
    }
}
