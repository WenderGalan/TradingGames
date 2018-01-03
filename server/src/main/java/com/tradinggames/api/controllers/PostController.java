package com.tradinggames.api.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.sun.org.apache.xpath.internal.operations.Mult;
import com.tradinggames.api.models.FilesModel;
import com.tradinggames.api.models.PostModel;
import com.tradinggames.api.repositories.FileRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import com.tradinggames.api.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.web.bind.annotation.*;

import com.tradinggames.api.models.PostModel;
import com.tradinggames.api.repositories.PostRepository;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserController userController;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private GridFsTemplate gridFsTemplate;

    public PostController() {

    }

    @GetMapping("")
    public String welcomeToPost() {
        return "Lord Of The Rings is awesome !";
    }

    @GetMapping("/loadFile")
    public String loadingFiles(HttpServletRequest request, HttpServletResponse response, @RequestParam("postId") String postId){
        try{
            FilesModel filesModel = fileRepository.findByPostId(postId);
            List<byte[]> filesOutput = new ArrayList<>();
            for ( Binary item: filesModel.getFile()) {
                response.reset();
                response.getOutputStream().write(item.getData());
            }
            //byte[] file = filesModel.getFile().getData();
            return "uhuu";
        }catch (Exception e){
            e.printStackTrace();
            return "false";
        }
    }

    @PostMapping("/upload")
    public String testingFiles(@RequestParam("file")List<MultipartFile> file,
                                @RequestParam("postId") String postId){
        try{
            FilesModel filesModel = new FilesModel();
            List<Binary> files = new ArrayList<>();

            for (MultipartFile item : file) {
                files.add(new Binary(item.getBytes()));
            }

            filesModel.setFile(files);
            filesModel.setPostId(postId);
            fileRepository.save(filesModel);
            return "true";
        }catch (Exception e){
            e.printStackTrace();
            return "false";
        }
    }

    // Registra um novo post - NÃO ESTÁ PRONTO AINDA
    @PostMapping(value = "/insert")
    private String insertPost(@Valid @RequestBody(required = true) PostModel postRequest) {

        PostModel postModel = new PostModel();

        try {
            postModel.setPostAuthor(postRequest.getPostAuthor());
            postModel.setPostTitle(postRequest.getPostTitle());
            postModel.setPostDescription(postRequest.getPostDescription());
            postModel.setPostTags(postRequest.getPostTags());
            postModel.setPostCategory(postRequest.getPostCategory());
            postModel.setProductPrice(postRequest.getProductPrice());
            postModel.setContactNumber((postModel.getContactNumber()));
            postRepository.save(postModel);
            return "true";

        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }

    }

    @PostMapping("/update")
    public String updatePost(@RequestBody PostModel postRequest) {

        try {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
    }

    // Busca os posts por string e categoria, informar a categoria é opcional
    @GetMapping("/search")
    public List<PostModel> loadPosts(@RequestParam(value = "query", required = true) String searchParam,
                                     @RequestParam(value = "category", required = true) String category) {

        List<PostModel> posts = postRepository.findDistinctByPostTitleOrPostDescriptionOrPostCategoryContainsIgnoreCase(
                searchParam, searchParam, category);

        List<PostModel> uniquePosts = posts.stream().distinct().collect(Collectors.toList());

        return uniquePosts;

    }

}
