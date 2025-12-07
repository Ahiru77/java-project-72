plugins {
    id("application")
    id("org.sonarqube") version "7.0.1.6134"
    id("io.freefair.lombok") version "8.13.1"
    id("com.github.johnrengelman.shadow") version "8.1.1"
	jacoco
	checkstyle
    application
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

 application {
     mainClass = "hexlet.code.App"
 }

dependencies {
    testImplementation("org.assertj:assertj-core:3.27.3")
    testImplementation(platform("org.junit:junit-bom:5.12.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("org.slf4j:slf4j-api:2.0.16")
    implementation("io.javalin:javalin:6.6.0")
    implementation("io.javalin:javalin-bundle:6.7.0")
    implementation("io.javalin:javalin-rendering:6.7.0")
    implementation("com.zaxxer:HikariCP:6.3.0")
    implementation("gg.jte:jte:3.2.1")
    implementation("org.postgresql:postgresql:42.7.2")
    implementation("com.h2database:h2:2.3.232")
    implementation("com.mashape.unirest:unirest-java:1.4.9")
    implementation("org.jsoup:jsoup:1.21.2")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.12.0")
}

tasks.test {
    useJUnitPlatform()
}
tasks.jacocoTestReport { reports { xml.required.set(true) } }

sonar {
    properties {
        property("sonar.projectKey", "Ahiru77_java-project-72")
        property("sonar.organization", "ahiru77")
    }
}