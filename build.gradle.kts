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
        "-Dotel.instrumentation.netty.enabled=false",
        "-Dotel.instrumentation.netty-4.1.enabled=false",
        "-Dotel.service.name=VertxExample"
    )
}

dependencies {
    implementation("io.vertx:vertx-web:4.4.4")
//    implementation("io.vertx:vertx-web:3.9.16")
    implementation("io.vertx:vertx-codegen:4.4.4")
//    implementation("io.vertx:vertx-codegen:3.9.16")
    implementation("io.vertx:vertx-opentelemetry:4.4.4")
}