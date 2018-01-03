package com.tradinggames.api.repositories;

import com.tradinggames.api.models.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface UserRepository extends MongoRepository<UserModel, BigInteger> {

    UserModel findByUserEmail(String userEmail);
    
}
