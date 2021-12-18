plugins {
    kotlin("jvm") version "1.5.10"//这句话依赖下方repositories
}

group "cn.eseals"
version "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = uri("https://jitpack.io")
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.github.shaunxiao:kotlinGameEngine:v0.0.4")
}