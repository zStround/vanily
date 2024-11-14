allprojects {

    apply(plugin = "java")

    group = "com.github.vanily.minecraft.proxy"
    version = "1.0"

    repositories {
            maven("https://repo.papermc.io/repository/maven-public/")
    }

    dependencies {
        compileOnly("com.velocitypowered:velocity-api:3.4.0-SNAPSHOT")
        annotationProcessor("com.velocitypowered:velocity-api:3.4.0-SNAPSHOT")
    }

    tasks.withType<Jar> {
        destinationDirectory.set(file("$rootDir/minecraft/proxy/plugins"))
    }

}