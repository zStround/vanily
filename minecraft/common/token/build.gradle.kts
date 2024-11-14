plugins {
    id("java")
}

group = "com.github.vanily.common.token"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.lettuce:lettuce-core:6.3.2.RELEASE")
    implementation("com.google.code.gson:gson:2.11.0")
}

tasks.test {
    useJUnitPlatform()
}