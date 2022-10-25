import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.3"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	id("com.github.johnrengelman.processes") version "0.5.0"
	id("org.springdoc.openapi-gradle-plugin") version "1.3.3"
	//id("org.liquibase.gradle") version "2.1.1"
	kotlin("jvm") version "1.6.0"
	kotlin("plugin.spring") version "1.6.10"
	kotlin("plugin.allopen") version "1.6.10"
	kotlin("plugin.jpa") version "1.6.10"
}

description = "African names discovering app"
group = "com.tooflexdev"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.7.3")
	implementation("org.springframework.boot:spring-boot-starter-data-rest:2.7.3")
	implementation("org.postgresql:postgresql:42.4.2")
	implementation("org.springframework.boot:spring-boot-starter-security:2.7.3")
	implementation("org.springframework.boot:spring-boot-starter-web:2.7.3")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.3")
	implementation("org.jetbrains.kotlin:kotlin-reflect:1.6.0")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.6.0")
	implementation("com.sipios:spring-search:0.2.4")
	implementation("org.junit.jupiter:junit-jupiter:5.9.0")
	// https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-ui
	implementation("org.springdoc:springdoc-openapi-ui:1.6.10")
	// https://mvnrepository.com/artifact/com.opencsv/opencsv
	implementation("com.opencsv:opencsv:5.6")
	// https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt
	implementation("io.jsonwebtoken:jjwt:0.9.1")
	// https://mvnrepository.com/artifact/org.liquibase/liquibase-core
	//implementation("org.liquibase:liquibase-core:4.7.1")
	// https://mvnrepository.com/artifact/org.twitter4j/twitter4j-core
	implementation("org.twitter4j:twitter4j-core:4.1.0")
	runtimeOnly("com.h2database:h2:2.1.214")
	testImplementation("org.springframework.boot:spring-boot-starter-test:2.7.3") {
		exclude(group = "org.mockito", module = "mockito-core")
	}
	testImplementation("org.springframework.security:spring-security-test:5.7.3")
	testImplementation("com.ninja-squad:springmockk:3.1.1")
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

allOpen {
	annotation("javax.persistence.Entity")
	annotation("javax.persistence.Embeddable")
	annotation("javax.persistence.MappedSuperclass")
}