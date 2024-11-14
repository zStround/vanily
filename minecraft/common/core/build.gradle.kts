plugins {
    id("io.papermc.paperweight.userdev") version "1.7.3"
}

group = "com.github.vanily.minecraft.core"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    paperweight.paperDevBundle("1.21.1-R0.1-SNAPSHOT")
    implementation(project(":minecraft:common:token"))
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}