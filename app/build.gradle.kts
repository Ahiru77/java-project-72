plugins {
    id("application")
    id("org.sonarqube") version "7.0.1.6134"
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
    implementation("io.javalin:javalin:5.6.2")
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