package com.github.vanily.infra.repositories;

import com.github.vanily.infra.domain.MinecraftServer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MinecraftServerRepository extends JpaRepository<MinecraftServer, Integer> {

    List<MinecraftServer> findAllByType(MinecraftServer.Type type);

}
