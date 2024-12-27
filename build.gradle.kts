plugins {
    // id("java")
    id("application")
}

group = "org.example"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation ("org.hibernate:hibernate-core:5.6.15.Final")
    implementation ("mysql:mysql-connector-java:8.0.18")
    implementation ("org.apache.logging.log4j:log4j-core:2.12.1")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

application {
    mainClass.set("org.example.Main")
}


tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}

