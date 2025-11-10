load("@rules_java//java:defs.bzl", "java_test")

# List of test files - when you add a new test, add it here
TEST_FILES = [
    "DemoApplicationTests.java",
]

# Automatically generate a java_test target for each test file
[
    java_test(
        name = test_file[:-5],  # Remove .java extension
        srcs = [test_file],
        test_class = "com.example.demo." + test_file[:-5],
        visibility = ["//visibility:public"],
        deps = [
            "//src/main/java/com/example/demo:demo_lib",
            "@maven//:org_springframework_boot_spring_boot_starter_test",
            "@maven//:junit_junit",
            "@maven//:org_springframework_boot_spring_boot_test",
        ],
    )
    for test_file in TEST_FILES
]

# Test suite that runs all tests
test_suite(
    name = "all",
    tests = [":" + test_file[:-5] for test_file in TEST_FILES],
)
