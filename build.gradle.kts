import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.21"
}

group = "me.agirow"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    val logback_version="1.2.3"
    val logstashEncoderVersion="6.4"

    implementation ("ch.qos.logback:logback-classic:$logback_version")
    implementation("net.logstash.logback:logstash-logback-encoder:$logstashEncoderVersion")

    testImplementation(kotlin("test-junit"))

}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}