package com.game.repository;

import com.game.entity.PlayerEntity;
import com.game.entity.Profession;
import com.game.entity.Race;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PlayerRepo extends JpaRepository<PlayerEntity, Long>,
        JpaSpecificationExecutor<PlayerEntity> {

    Page<PlayerEntity> findAll(Specification<PlayerEntity> spec, Pageable pageable);
}
