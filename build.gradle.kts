plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

allprojects {

    apply(plugin = "java")
    apply(plugin = "com.github.johnrengelman.shadow")

    group = "com.github.vanily"
    version = "1.0"

    repositories {
        mavenCentral()
    }

    dependencies {
        compileOnly("org.projectlombok:lombok:1.18.34")
        annotationProcessor("org.projectlombok:lombok:1.18.34")
    }

}