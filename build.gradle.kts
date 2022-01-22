import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.4.4"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	id("com.github.johnrengelman.processes") version "0.5.0"
	id("org.springdoc.openapi-gradle-plugin") version "1.3.3"
	kotlin("jvm") version "1.6.0"
	kotlin("plugin.spring") version "1.6.10"
	kotlin("plugin.allopen") version "1.6.10"
	kotlin("plugin.jpa") version "1.6.10"
}

group = "com.tooflexdev"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.6.1")
	implementation("org.postgresql:postgresql:42.3.1")
	implementation("org.springframework.boot:spring-boot-starter-security:2.6.1")
	implementation("org.springframework.boot:spring-boot-starter-web:2.6.1")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.0")
	implementation("org.jetbrains.kotlin:kotlin-reflect:1.6.0")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.6.0")
	implementation("com.sipios:spring-search:0.2.4")
	implementation("org.junit.jupiter:junit-jupiter:5.8.2")
	// https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-ui
	implementation("org.springdoc:springdoc-openapi-ui:1.5.13")
	// https://mvnrepository.com/artifact/com.opencsv/opencsv
	implementation("com.opencsv:opencsv:5.5.2")
	// https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt
	implementation("io.jsonwebtoken:jjwt:0.9.1")
	runtimeOnly("com.h2database:h2:2.0.202")
	//runtimeOnly("org.jetbrains.kotlin:kotlin-reflect:1.4.32")
	testImplementation("org.springframework.boot:spring-boot-starter-test:2.6.1")
	testImplementation("org.springframework.security:spring-security-test:5.5.1")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

//tasks.register<Copy>("copyApiDocs") {
//	println("copyApiDocs task running")
//	dependsOn(getTasksByName("generateOpenApiDocs", true))
//	from(layout.buildDirectory.dir("api-docs.yaml"))
//	into(layout.projectDirectory.dir("frontend/api-docs-test.yaml"))
//}
//
//tasks.named("clean") { finalizedBy("copyApiDocs") }

allOpen {
	annotation("javax.persistence.Entity")
	annotation("javax.persistence.Embeddable")
	annotation("javax.persistence.MappedSuperclass")
}