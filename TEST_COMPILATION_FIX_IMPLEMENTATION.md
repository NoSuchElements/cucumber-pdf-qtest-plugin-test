# Test Compilation Error Fix - Implementation Guide

**Branch**: `fix/test-compilation-errors`
**Status**: Ready for Implementation
**Date**: March 19, 2026

## Executive Summary

This document provides step-by-step implementation instructions to fix four test compilation errors in `ConsolidatedFixupIT.java`:

1. **Missing PNG1 constant** (lines 121-123) - 3 errors
2. **Missing getResult() method** (line 310) - 1 error

## Error Details

### Build Error Output

```
[ERROR] ConsolidatedFixupIT.java:[121,56] cannot find symbol - variable PNG1
[ERROR] ConsolidatedFixupIT.java:[122,56] cannot find symbol - variable PNG1
[ERROR] ConsolidatedFixupIT.java:[123,56] cannot find symbol - variable PNG1
[ERROR] ConsolidatedFixupIT.java:[310,59] cannot find symbol - method getResult()
```

## Fix #1: Add PNG1 Test Fixture Constant

### File Location
`src/test/java/com/nosuchelements/pdf/test/ConsolidatedFixupIT.java`

### Implementation

Add static final constants in test class:

```java
public class ConsolidatedFixupIT {
    // Test fixture constants for PNG comparison tests
    private static final String PNG1 = "src/test/resources/fixtures/test-image-1.png";
    private static final String PNG2 = "src/test/resources/fixtures/test-image-2.png";
    private static final String PNG3 = "src/test/resources/fixtures/test-image-3.png";
    // ... rest of class ...
}
```

### Test Resource Directory Structure

```
src/test/resources/
└── fixtures/
    ├── test-image-1.png
    ├── test-image-2.png
    └── test-image-3.png
```

## Fix #2: Add getResult() Method to CucumberStep

### File Location

**Plugin Project** (cucumber-pdf-qtest-plugin):
`src/main/java/com/nosuchelements/cucumber/model/CucumberStep.java`

### Implementation

```java
/**
 * Returns the execution result status of this step.
 * Possible values: PASSED, FAILED, SKIPPED, PENDING, UNDEFINED
 *
 * @return the step result status, or "UNKNOWN" if not set
 */
public String getResult() {
    return result != null ? result : "UNKNOWN";
}
```

## Implementation Steps

### Step 1: Switch to Fix Branch

```bash
cd /path/to/cucumber-pdf-qtest-plugin-test
git fetch origin
git checkout fix/test-compilation-errors
```

### Step 2: Add PNG Constants to ConsolidatedFixupIT.java

```bash
# Open the file
vim src/test/java/com/nosuchelements/pdf/test/ConsolidatedFixupIT.java

# Add the PNG1, PNG2, PNG3 constants at class level
```

### Step 3: Create Test Fixture Directory and Images

```bash
mkdir -p src/test/resources/fixtures

# Create placeholder PNG files using ImageMagick or Python
convert -size 100x100 xc:blue src/test/resources/fixtures/test-image-1.png
convert -size 100x100 xc:red src/test/resources/fixtures/test-image-2.png
convert -size 100x100 xc:green src/test/resources/fixtures/test-image-3.png
```

### Step 4: Update Plugin Project (Separate Repository)

**In cucumber-pdf-qtest-plugin project**:

```bash
cd /path/to/cucumber-pdf-qtest-plugin
git checkout -b fix/add-getresult-method

# Edit CucumberStep.java to add getResult() method
vim src/main/java/com/nosuchelements/cucumber/model/CucumberStep.java

# Commit and push
git add -A
git commit -m "feat: Add getResult() accessor method to CucumberStep"
git push origin fix/add-getresult-method

# Create PR and merge to main
# Then: mvn clean install (to update local Maven repository)
```

### Step 5: Verify Test Compilation

```bash
cd /path/to/cucumber-pdf-qtest-plugin-test
mvn clean test-compile
```

**Expected**:
```
[INFO] Compiling 4 source files...
[INFO] BUILD SUCCESS
```

### Step 6: Run Full Test Suite

```bash
mvn clean verify
```

**Expected**:
```
[INFO] Tests run: X, Failures: 0, Errors: 0
[INFO] BUILD SUCCESS
```

### Step 7: Commit and Push

```bash
git add -A
git commit -m "fix: Resolve test compilation errors in ConsolidatedFixupIT

- Add PNG1, PNG2, PNG3 constants for test fixtures
- Add test fixture PNG files to src/test/resources/fixtures/
- Resolves: cannot find symbol - variable PNG1 (lines 121-123)
- Requires: getResult() method in CucumberStep (merged from plugin)

Closes #XX"

git push origin fix/test-compilation-errors
```

### Step 8: Create Pull Request on GitHub

Navigate to: [https://github.com/NoSuchElements/cucumber-pdf-qtest-plugin-test/pull/new/fix/test-compilation-errors](https://github.com/NoSuchElements/cucumber-pdf-qtest-plugin-test)

**Title**: "Fix: Resolve test compilation errors in ConsolidatedFixupIT"

**Description**:
```markdown
## Summary
Resolves 4 test compilation errors in the Maven test phase.

## Changes
- Add PNG1, PNG2, PNG3 constants to ConsolidatedFixupIT.java
- Add test fixture PNG files to src/test/resources/fixtures/
- (Depends on: getResult() method addition in plugin project)

## Errors Fixed
- [x] cannot find symbol: variable PNG1 (line 121)
- [x] cannot find symbol: variable PNG1 (line 122)
- [x] cannot find symbol: variable PNG1 (line 123)
- [x] cannot find symbol: method getResult() (line 310)

## Testing
- [x] mvn clean test-compile (succeeds)
- [x] mvn clean verify (succeeds)
- [x] All tests pass
```

## Verification Checklist

- [ ] Branch `fix/test-compilation-errors` created
- [ ] PNG constants added to ConsolidatedFixupIT.java
- [ ] Test fixture PNG files created in `src/test/resources/fixtures/`
- [ ] getResult() method added to CucumberStep in plugin project
- [ ] Plugin project built and installed: `mvn clean install`
- [ ] `mvn clean test-compile` succeeds
- [ ] `mvn clean verify` succeeds
- [ ] All tests pass
- [ ] Changes committed and pushed
- [ ] Pull request created and merged

## Build Verification

### Test Compilation
```bash
mvn clean test-compile
```

### Full Verification
```bash
mvn clean verify
```

### Expected Success Output
```
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

## Files Changed

| File | Changes |
|------|----------|
| `src/test/java/com/nosuchelements/pdf/test/ConsolidatedFixupIT.java` | Add PNG1, PNG2, PNG3 constants (+3 lines) |
| `src/test/resources/fixtures/test-image-1.png` | New file (test fixture) |
| `src/test/resources/fixtures/test-image-2.png` | New file (test fixture) |
| `src/test/resources/fixtures/test-image-3.png` | New file (test fixture) |

**Note**: CucumberStep.java changes are in the separate plugin repository.

## Troubleshooting

### "Cannot find symbol: PNG1"
- Verify constants added to ConsolidatedFixupIT class
- Check class file was saved properly
- Run: `mvn clean test-compile`

### "Cannot find symbol: method getResult()"
- Verify getResult() method added to CucumberStep in plugin project
- Run: `mvn clean install` in plugin project
- Run: `mvn clean test-compile` in test project

### "PNG files not found"
- Verify directory exists: `ls -la src/test/resources/fixtures/`
- Create PNG files if missing
- Verify file paths match constants

## Related Issues & PRs

- **Plugin PR**: (getResult() method addition)
- **Test Project PR**: (this branch)

---

**Status**: Ready for Implementation  
**Last Updated**: March 19, 2026
