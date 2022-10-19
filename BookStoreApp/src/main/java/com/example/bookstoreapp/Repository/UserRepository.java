package com.example.bookstoreapp.Repository;

import com.example.bookstoreapp.Model.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDetail,Integer> {


    Optional<UserDetail> findByEmail(String email);
}
