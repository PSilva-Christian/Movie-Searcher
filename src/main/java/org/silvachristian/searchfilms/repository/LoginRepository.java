package org.silvachristian.searchfilms.repository;

import lombok.NonNull;
import org.silvachristian.searchfilms.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByUsername(@NonNull String username);

    Optional<UserEntity> findByUsername(@org.jspecify.annotations.NonNull String username);
}
