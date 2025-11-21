plugins {
    id("java")
    id("application")
    id("org.sonarqube") version "7.0.1.6134"
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
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.slf4j:slf4j-api:2.0.16")
    implementation("io.javalin:javalin:5.6.2")
}

sonar {
    properties {
        property("sonar.projectKey", "Ahiru77_java-project-72")
        property("sonar.organization", "ahiru77")
    }
}

test {
    useJUnitPlatform()
}
tasks.jacocoTestReport { reports { xml.required.set(true) } }

sonar {
  properties {
    property "sonar.projectKey", "Ahiru77_java-project-78"
    property "sonar.organization", "ahiru77"
    property "sonar.host.url", "https://sonarcloud.io"
  }
}