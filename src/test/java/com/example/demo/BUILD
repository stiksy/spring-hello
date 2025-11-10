load("@rules_java//java:defs.bzl", "java_test")

java_test(
    name = "DemoApplicationTests",
    srcs = ["DemoApplicationTests.java"],
    test_class = "com.example.demo.DemoApplicationTests",
    visibility = ["//visibility:public"],
    deps = [
        "//src/main/java/com/example/demo:demo_lib",
        "@maven//:org_springframework_boot_spring_boot_starter_test",
        "@maven//:junit_junit",
        "@maven//:org_springframework_boot_spring_boot_test",
    ],
)
