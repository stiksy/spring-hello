load("@rules_java//java:defs.bzl", "java_binary", "java_library")

java_library(
    name = "demo_lib",
    srcs = ["DemoApplication.java"],
    visibility = ["//visibility:public"],
    deps = [
        "@maven//:org_springframework_boot_spring_boot",
        "@maven//:org_springframework_boot_spring_boot_autoconfigure",
    ],
)

java_binary(
    name = "demo",
    main_class = "com.example.demo.DemoApplication",
    visibility = ["//visibility:public"],
    runtime_deps = [
        ":demo_lib",
        "@maven//:org_springframework_boot_spring_boot_starter",
    ],
)
