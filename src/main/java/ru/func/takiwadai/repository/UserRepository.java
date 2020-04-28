package ru.func.takiwadai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.func.takiwadai.entity.user.User;

import java.util.Optional;

/**
 * @author func 23.04.2020
 * @project Takiwadai
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM user u WHERE u.perfect_points >= ?;")
    Long findPlaceByPerfectPoints(Long perfectPoints);
}
