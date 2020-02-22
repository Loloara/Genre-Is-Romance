package com.loloara.genreisromance.repository;

import com.loloara.genreisromance.common.util.ProcessType;
import com.loloara.genreisromance.model.User;
import com.sun.tools.javac.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/* ToDo
    add order by and limit for findAllByProcess query
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    Optional<User> findByPhone(String phone);

    @Query("select id from User where process = :process")
    List<Long> findAllByProcess(@Param("process") ProcessType process);

    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
}
