plugins {
    application
    kotlin("jvm") version Dependencies.Kotlin.version
    id("io.ktor.plugin") version "2.1.2"
    id(Dependencies.Kotlin.serialization) version Dependencies.Kotlin.version
}

group = "pro.liga"
version = "0.0.1"
application {
    mainClass.set("pro.liga.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(Dependencies.Ktor.coreJvm)
    implementation(Dependencies.Ktor.loggingJvm)
    implementation(Dependencies.Ktor.nettyJvm)
    implementation(Dependencies.Ktor.callIdJvm)
    implementation(Dependencies.Ktor.metricsJvm)
    implementation(Dependencies.Ktor.metricsMicrometerJvm)
    implementation(Dependencies.Ktor.negotiationJvm)
    implementation(Dependencies.Ktor.kotlinxJsonJvm)

    implementation(Dependencies.Prometheus.prometheus)

    implementation(Dependencies.Exposed.core)
    implementation(Dependencies.Exposed.dao)
    implementation(Dependencies.Exposed.jdbc)
    implementation(Dependencies.Exposed.javaTime)

    implementation(Dependencies.Slf4j.api)
    implementation(Dependencies.Slf4j.log4j12)

    implementation(Dependencies.Koin.koin)
    implementation(Dependencies.Koin.koinLogger)

    implementation(Dependencies.Logback.logback)

    implementation(Dependencies.Postgresql.postgresql)

    implementation(Dependencies.Kotlin.coroutines)

    implementation(Dependencies.Jsoup.jsoup)

    testImplementation(Dependencies.Ktor.testsJvm)

    testImplementation(Dependencies.Kotlin.junit)
    testImplementation(Dependencies.Koin.koinJUnit)
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
}