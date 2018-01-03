package com.tradinggames.api.repositories;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.tradinggames.api.models.PostModel;

@Repository
public interface PostRepository extends MongoRepository<PostModel, BigInteger> {
	
	List<PostModel> findByPostTitleLike(String title);

	List<PostModel> findByPostCategoryLike(String title);
	
	List<PostModel> findByPostDescriptionLike(String description);

	List<PostModel> findByPostTitleOrPostDescriptionLike(String title, String description);

	List<PostModel> findDistinctByPostTitleOrPostDescriptionOrPostCategoryContainsIgnoreCase(String title, String description, String category);

	List<PostModel> findByPostTitleContainsIgnoreCase(String title);

	List<PostModel> findByPostCategoryContainsIgnoreCase(String category);

	List<PostModel> findByPostDescriptionContainsIgnoreCase(String description);

	PostModel findByPostId(String id);

}
