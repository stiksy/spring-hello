workspace(name = "spring_hello")

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

# Rules for JVM
http_archive(
    name = "rules_jvm_external",
    sha256 = "f86fd42a809e1871ca0aabe89db0d440451219c3ce46c58da240c7dcdc00125f",
    strip_prefix = "rules_jvm_external-5.2",
    url = "https://github.com/bazelbuild/rules_jvm_external/releases/download/5.2/rules_jvm_external-5.2.tar.gz",
)

load("@rules_jvm_external//:defs.bzl", "maven_install")

# Maven dependencies (note: this WORKSPACE file is mostly unused with Bzlmod)
maven_install(
    artifacts = [
        "org.springframework.boot:spring-boot-starter:3.5.6",
        "org.springframework.boot:spring-boot-starter-test:3.5.6",
        "org.junit.platform:junit-platform-launcher:1.10.1",
        "org.springframework.boot:spring-boot-autoconfigure:3.5.6",
        "org.springframework.boot:spring-boot:3.5.6",
        "org.springframework:spring-context:6.2.2",
        "org.springframework:spring-core:6.2.2",
        "org.springframework:spring-beans:6.2.2",
        "org.springframework:spring-test:6.2.2",
        "org.junit.jupiter:junit-jupiter-api:5.10.1",
        "org.junit.jupiter:junit-jupiter-engine:5.10.1",
    ],
    repositories = [
        "https://repo1.maven.org/maven2",
    ],
)
