package com.cms.repo;

import com.cms.vo.CmsUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<CmsUser, String> {

    @Query("{'$or':[ {'username':?0}, {'email':?0} ] }")
    CmsUser findByUserNameOrEmail(String userName);

    @Query("{'username': ?0}")
    boolean existsByUserName (String userName);

    @Query("{'email': ?0}")
    boolean existsByUserEmail (String userEmail);

}
