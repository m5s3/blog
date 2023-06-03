tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

dependencies {
    implementation(project(":support:logging"))
    implementation(project(":blog-data:mysql"))
    implementation("org.springframework.boot:spring-boot-starter-web")
}