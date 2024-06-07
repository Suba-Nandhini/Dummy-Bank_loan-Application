package com.suba.Bankloanapplication.repository;
import com.suba.Bankloanapplication.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User, ObjectId> {
//    @Query(value = "SELECT * FROM USERS u WHERE u.username = ?1",nativeQuery = true)
    @Query("{'username': ?0}")
    User findByUsername(String username);
}
