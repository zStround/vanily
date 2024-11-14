plugins {
    java
    id("io.papermc.paperweight.userdev") version "1.7.3"
}

allprojects {

    apply(plugin = "java")
    apply(plugin = "io.papermc.paperweight.userdev")

    group = "com.github.vanily.minecraft"
    version = "1.0"

    repositories {
        mavenCentral()
        maven("https://repo.papermc.io/repository/maven-public/")
    }

    dependencies {
        paperweight.paperDevBundle("1.21.1-R0.1-SNAPSHOT")
        compileOnly(project(":minecraft:common:core"))
        compileOnly(project(":minecraft:common:token"))
    }

    tasks.withType<Jar> {
        destinationDirectory.set(file("$rootDir/minecraft/authentication/plugins"))
    }

}