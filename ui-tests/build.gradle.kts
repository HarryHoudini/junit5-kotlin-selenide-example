//https://guides.gradle.org/migrating-build-logic-from-groovy-to-kotlin/

import io.qameta.allure.gradle.AllureExtension
import io.qameta.allure.gradle.task.AllureReport
import org.gradle.internal.impldep.org.eclipse.jgit.api.CleanCommand


plugins {
    java
    `maven-publish`
    id("io.qameta.allure") version "2.5"
    // kotlin("jvm") version "1.2.60" -- Allready in the classpath
}


configure<AllureExtension> {
    version = "2.7.0"
}
//val allureVersion = "2.7.0"


allure {
    version = "2.7.0"
    autoconfigure = true
    configuration = "compile"
    aspectjweaver = true
    aspectjVersion = "1.8.10"
    resultsDir = file("$rootDir/ui-tests/build/allure-results")
    reportDir = file("$rootDir/ui-tests/allure-report")
    useJUnit5
    downloadLink = "https://dl.bintray.com/qameta/generic/io/qameta/allure/allure/${allure.version}/allure-${allure.version}.zip"
}

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib", "1.2.60"))
    compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    compile("com.codeborne", "selenide", "5.0.0")
    compile("io.qameta.allure:allure-selenide:${allure.version}")
    //Helper assertion library
    // https://github.com/npryce/hamkrest
    compile("com.natpryce:hamkrest:1.6.0.0")
    compile(project(":api-tests"))
}





tasks.getByName<Wrapper>("wrapper").gradleVersion = "4.8"

//
//val wrapper by tasks.getting(Wrapper::class){
//    gradleVersion = "4.8"
//}


tasks {

    getByName<Wrapper>("wrapper") {
        gradleVersion = "4.8"
    }

    getByName<Test>("test"){
        doFirst {
            println("Starting UI tests!")
        }

        useJUnitPlatform{
            //includeTags("fast") //, 'smoke & feature-a'
            excludeTags("slow") // excludeTags 'slow', 'ci'
            includeEngines("junit-jupiter")
            excludeEngines("junit-vintage")
        }
        failFast = true
        testLogging {
            showStandardStreams = true
            events ("PASSED", "FAILED", "SKIPPED")
        }
        systemProperty("junit.jupiter.conditions.deactivate", "*")
        systemProperty("junit.jupiter.testinstance.lifecycle.default", "per_class")
        systemProperty("it.jupiter.extensions.autodetection.enabled", "true")
        // Also you can configure this properties in /src/test/ersource/junit-platform.properties file

        doLast {
            println("Tests are finnished!")
        }

    }.finalizedBy("allureReport")


    getByName("clean"){
        delete("$rootDir/ui-tests/.allure")
        delete("$rootDir/ui-tests/allure-results")
        delete("$rootDir/ui-tests/allure-report")
        delete("$rootDir/ui-tests/bin")
        delete("$rootDir/ui-tests/build")
        delete("$rootDir/ui-tests/out")
    }
}