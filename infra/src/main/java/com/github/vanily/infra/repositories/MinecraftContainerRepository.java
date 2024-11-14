package com.github.vanily.infra.repositories;

import com.github.vanily.infra.domain.MinecraftContainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MinecraftContainerRepository extends JpaRepository<MinecraftContainer, String> {
}
