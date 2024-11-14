plugins {
    id("java")
}

group = "com.github.vanily.authentication.login"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("io.lettuce:lettuce-core:6.3.2.RELEASE")
}