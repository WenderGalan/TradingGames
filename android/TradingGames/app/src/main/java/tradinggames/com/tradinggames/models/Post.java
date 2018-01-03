package tradinggames.com.tradinggames.models;

/**
 * Created by toppeiradasgalaxias on 20/11/2017.
 */

public class Post {

    private String postId;

    private String postTitle;//
    private String postDescription;//
    private Double productPrice;//
    private String postCategory;//
    private String[] postTags;
    private String postAuthor;
    private String contactNumber;//

    public Post() {

    }

    public Post (Post p) {
        this.postId = p.postId;
        this.postTitle = p.postTitle;
        this.postDescription = p.postDescription;
        this.productPrice = p.productPrice;
        this.postCategory = p.postCategory;
        this.postTags = p.postTags;
        this.postAuthor = p.postAuthor;
        this.contactNumber = p.contactNumber;
    }


    public Post( String postTitle, String postDescription, Double productPrice, String postCategory,
                String[] postTags, String postAuthor, String contactNumber) {
        this.postTitle = postTitle;
        this.postDescription = postDescription;
        this.productPrice = productPrice;
        this.postCategory = postCategory;
        this.postTags = postTags;
        this.postAuthor = postAuthor;
        this.contactNumber = contactNumber;
    }


    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
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
}
