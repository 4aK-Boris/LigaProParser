object Dependencies {

    object Ktor {

        private const val version = "2.1.2"

        const val coreJvm = "io.ktor:ktor-server-core-jvm:$version"
        const val loggingJvm = "io.ktor:ktor-server-call-logging-jvm:$version"
        const val callIdJvm = "io.ktor:ktor-server-call-id-jvm:$version"
        const val metricsJvm = "io.ktor:ktor-server-metrics-jvm:$version"
        const val metricsMicrometerJvm = "io.ktor:ktor-server-metrics-micrometer-jvm:$version"
        const val negotiationJvm = "io.ktor:ktor-server-content-negotiation-jvm:$version"
        const val kotlinxJsonJvm = "io.ktor:ktor-serialization-kotlinx-json-jvm:$version"
        const val nettyJvm = "io.ktor:ktor-server-netty-jvm:$version"

        const val testsJvm = "io.ktor:ktor-server-tests-jvm:$version"
    }

    object Prometheus {

        private const val version = "1.9.4"

        const val prometheus = "io.micrometer:micrometer-registry-prometheus:$version"
    }

    object Exposed {

        private const val version = "0.40.1"

        const val core = "org.jetbrains.exposed:exposed-core:$version"
        const val dao = "org.jetbrains.exposed:exposed-dao:$version"
        const val jdbc = "org.jetbrains.exposed:exposed-jdbc:$version"
        const val javaTime = "org.jetbrains.exposed:exposed-java-time:$version"
    }

    object Slf4j {

        private const val version = "2.0.1"

        const val api = "org.slf4j:slf4j-api:$version"
        const val log4j12 = "org.slf4j:slf4j-log4j12:$version"
    }

    object Logback {

        private const val version = "1.4.1"

        const val logback = "ch.qos.logback:logback-classic:$version"
    }

    object Kotlin {

        const val version = "1.7.20"
        private const val coroutines_version = "1.6.4"

        const val junit = "org.jetbrains.kotlin:kotlin-test-junit:$version"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version"
        const val serialization = "org.jetbrains.kotlin.plugin.serialization"
        const val reflect = "org.jetbrains.kotlin:kotlin-reflect:$version"
    }

    object Postgresql {

        private const val version = "42.5.0"

        const val postgresql = "org.postgresql:postgresql:$version"
    }

    object Jsoup {

        private const val version = "1.15.3"

        const val jsoup = "org.jsoup:jsoup:$version"
    }

    object KStore {

        private const val version = "0.1"

        const val kStore = "io.github.xxfast:kstore:$version"
    }

    object Koin {

        private const val version = "3.2.2"

        const val koin = "io.insert-koin:koin-ktor:$version"
        const val koinLogger = "io.insert-koin:koin-logger-slf4j:$version"
        const val koinJUnit = "io.insert-koin:koin-test-junit4:$version"
    }
}