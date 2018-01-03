package com.tradinggames.api.repositories;

import com.tradinggames.api.models.FilesModel;
import org.bson.types.Binary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends MongoRepository<FilesModel, String>{

    FilesModel findByPostId(String postId);
}
