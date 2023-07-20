plugins {
    application
}

repositories {
    mavenCentral()
}

application {
    mainClass.set("com.splunk.example.SpanAttributesMain")
    applicationDefaultJvmArgs = listOf(
        "-javaagent:splunk-otel-javaagent-1.18.0.jar",
        "-Dotel.javaagent.debug=true",
        "-Dotel.service.name=SpanAttrExample",
        "-Dotel.instrumentation.methods.include=com.splunk.example.SpanAttributesMain[superDuperBonusMethod,highScore]"
    )
}

dependencies {
    implementation("io.vertx:vertx-web:+")
    implementation("io.vertx:vertx-jdbc-client:+")
    implementation("io.vertx:vertx-codegen:+")
}