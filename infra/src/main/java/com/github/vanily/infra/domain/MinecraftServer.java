package com.github.vanily.infra.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "minecraft_servers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MinecraftServer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id = 0;

    @JoinColumn(nullable = false)
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private MinecraftContainer container;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(nullable = false)
    private boolean maintenance;

    public MinecraftServer(MinecraftContainer container, Type type) {
        this(0, container, type, false);
    }

    public enum Type {

        AUTHENTICATION,
        LOBBY,
        RANKUP;

    }

}
