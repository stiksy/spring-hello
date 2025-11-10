# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a simple Spring Boot 3.5.6 demo application using Java 17 and Bazel as the build system. The project demonstrates basic Spring Boot setup with a minimal application structure.

**Key Details:**
- Spring Boot version: 3.5.6
- Java version: 17 (configured via toolchain)
- Build tool: Bazel (with Bzlmod)
- Package structure: `com.example.demo`
- Test framework: JUnit 4 (compatible with Bazel's test runner)

## Build and Test Commands

All commands use Bazel. The project includes convenient aliases at the root for shorter commands.

### Building the Application
```bash
# Short form (using root aliases - recommended)
bazel build //:spring-hello      # Build the application
bazel run //:spring-hello        # Build and run the application

# Full paths (if needed)
bazel build //src/main/java/com/example/demo:demo       # Build the application
bazel build //src/main/java/com/example/demo:demo_lib   # Build just the library
bazel clean                                                # Clean build artifacts
```

### Running Tests
```bash
# Run all tests
bazel test //src/test/java/com/example/demo:DemoApplicationTests
bazel test //...                                          # Run all tests in project

# Test with specific output
bazel test //src/test/java/com/example/demo:DemoApplicationTests --test_output=all
```

**Note:** Tests use JUnit 4 (`@Test` from `org.junit.Test`) which is fully compatible with Bazel's default test runner.

### Running the Application
```bash
# Short form (recommended)
bazel run //:spring-hello  # Run the Spring Boot application

# Full path
bazel run //src/main/java/com/example/demo:demo
```

## Architecture

### Application Structure
- **Main class:** `src/main/java/com/example/demo/DemoApplication.java` - Standard Spring Boot entry point with `@SpringBootApplication`
- **Test class:** `src/test/java/com/example/demo/DemoApplicationTests.java` - Contains basic context loading test

### Test Configuration
The test suite includes:
1. `contextLoads()` - Basic context loading test with 3-second delay
2. `shouldFail()` - Placeholder test (currently commented out, can be used for CI/CD testing)

Tests use JUnit 4 (`org.junit.Test`) with `@SpringBootTest` annotation for Spring Boot integration testing.

### Bazel Build Structure
The Bazel build is organized as follows:
- **BUILD.bazel** (root) - Convenient aliases for shorter commands
  - `:spring-hello` - Alias to build/run the application
  - `:spring-hello-lib` - Alias to the library target
- **MODULE.bazel** - Defines the Bazel module and Maven dependencies (Bzlmod-based)
  - Spring Boot 3.5.6 (starter and starter-test)
  - JUnit 4.13.2
- **WORKSPACE** - Legacy workspace file (mostly unused with Bzlmod)
- **.bazelrc** - Bazel configuration
  - Java 17 runtime and language version
  - Test output verbosity (`--test_output=all`)
  - Bzlmod enabled
- **src/main/java/com/example/demo/BUILD** - Build rules for main application code
  - `demo_lib` - Java library target containing DemoApplication
  - `demo` - Java binary target for running the application
- **src/test/java/com/example/demo/BUILD** - Build rules for test code
  - `DemoApplicationTests` - Test target for the test suite

## CI/CD Configuration

The project uses Buildkite for CI/CD with a multi-step pipeline (`.buildkite/pipeline.yml`):

1. **Build step** - Builds the application using `bazel build //:spring-hello`
2. **Test step** - Runs all tests using `bazel test //...`
3. **Annotation step** - Uses JUnit annotate plugin to display test results from `bazel-testlogs/`

The pipeline runs in Docker containers using the official `gcr.io/bazel-public/bazel:latest` image which includes Bazel and all necessary build tools.

## Dependencies

The project has minimal dependencies managed via MODULE.bazel:
- `org.springframework.boot:spring-boot-starter:3.5.6` - Core Spring Boot functionality
- `org.springframework.boot:spring-boot-starter-test:3.5.6` - Testing dependencies
- `junit:junit:4.13.2` - JUnit 4 test framework

All dependencies are fetched from Maven Central via Bazel's rules_jvm_external.

## Common Development Tasks

### Adding a New Dependency
Edit `MODULE.bazel` and add to the `artifacts` list:
```python
maven.install(
    artifacts = [
        "org.springframework.boot:spring-boot-starter:3.5.6",
        "org.springframework.boot:spring-boot-starter-test:3.5.6",
        "junit:junit:4.13.2",
        "group:artifact:version",  # Your new dependency
    ],
    repositories = [
        "https://repo1.maven.org/maven2",
    ],
)
```

Then update the relevant BUILD file to include the dependency:
```python
deps = [
    "@maven//:group_artifact",  # Note: colons and dots become underscores
],
```

### Adding a New Test
1. Create test file in `src/test/java/com/example/demo/`
2. Use JUnit 4 annotations: `import org.junit.Test;`
3. Make test class and methods `public`
4. Add test target to `src/test/java/com/example/demo/BUILD`
