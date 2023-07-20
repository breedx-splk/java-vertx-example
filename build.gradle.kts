plugins {
    application
}

repositories {
    mavenCentral()
}

application {
    mainClass.set("com.splunk.example.VertxApplication")
    applicationDefaultJvmArgs = listOf(
        "-javaagent:splunk-otel-javaagent-1.26.0.jar",
        "-Dotel.javaagent.debug=true",
        "-Dotel.service.name=VertxExample"
    )
}

dependencies {
    implementation("io.vertx:vertx-web:4.4.4")
    implementation("io.vertx:vertx-codegen:4.4.4")
}