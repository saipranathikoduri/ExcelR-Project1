
package com.artforyou.store.repository;

import com.artforyou.store.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<AdminEntity, Long> {

    Optional<AdminEntity> findByUsername(String username);

    boolean existsByUsername(String username);
}
