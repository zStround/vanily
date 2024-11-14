plugins {
    java
    id("io.papermc.paperweight.userdev") version "1.7.3"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "com.github.vanily.minecraft.rankup"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    paperweight.paperDevBundle("1.21.1-R0.1-SNAPSHOT")

    compileOnly("io.lettuce:lettuce-core:6.3.2.RELEASE")
    compileOnly(project(":minecraft:common:core"))
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}