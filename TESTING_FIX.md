# Testing Font Corruption Fix

## Overview

This document describes how to test the font corruption fix that was implemented in `cucumber-pdf-qtest-plugin` version 1.0.0.

## Background

**Problem**: When generating multiple per-feature PDF reports, the second feature would fail with:
```
java.io.IOException: The TrueType font null does not contain a 'cmap' table
```

**Fix**: PR #1 in the plugin repository implemented:
1. File-based constructor with explicit per-feature naming
2. Error isolation (try-catch per feature)
3. Enhanced logging with success/failure counts

## Test Execution

### Prerequisites

1. Java 11 or higher
2. Maven 3.6 or higher
3. Git

### Steps

#### 1. Install the fixed plugin

```bash
cd /path/to/cucumber-pdf-qtest-plugin
git pull origin main  # Get the merged fix
mvn clean install
```

This installs version `1.0.0` to your local Maven repository at `~/.m2/repository/com/nosuchelements/cucumber-pdf-qtest-plugin/1.0.0/`

#### 2. Run the test project

```bash
cd /path/to/cucumber-pdf-qtest-plugin-test
mvn clean verify
```

### Expected Results

#### Before Fix (Failed)

```
[INFO] --- cucumber-pdf-qtest:1.0.0:pdfreportperfeature (generate-pdf-reports) @ cucumber-pdf-qtest-plugin-test ---
[INFO] STARTED CUCUMBER PDF PER-FEATURE REPORT GENERATION PLUGIN
[INFO] Generated PDF report: datatable-docstring@QTEST_TC_1201.pdf
[SEVERE] An exception occurred
java.io.IOException: The TrueType font null does not contain a 'cmap' table
    at org.apache.fontbox.ttf.TrueTypeFont.getUnicodeCmapImpl(TrueTypeFont.java:562)
    ...
[ERROR] STOPPING CUCUMBER PDF PER-FEATURE REPORT GENERATION - java.io.IOException
[INFO] BUILD FAILURE
```

**Result**: Only 1 of 9 PDFs generated

#### After Fix (Success)

```
[INFO] --- cucumber-pdf-qtest:1.0.0:pdfreportperfeature (generate-pdf-reports) @ cucumber-pdf-qtest-plugin-test ---
[INFO] STARTED CUCUMBER PDF PER-FEATURE REPORT GENERATION PLUGIN
[INFO] Generated PDF report: datatable-docstring@QTEST_TC_1201.pdf
[INFO] Generated PDF report: exceptions@QTEST_TC_1202.pdf
[INFO] Generated PDF report: failure@QTEST_TC_1203.pdf
[INFO] Generated PDF report: lengthynames@QTEST_TC_1204.pdf
[INFO] Generated PDF report: notags.pdf
[INFO] Generated PDF report: scenario&outline@QTEST_TC_1205.pdf
[INFO] Generated PDF report: screenshots@QTEST_TC_1206.pdf
[INFO] Generated PDF report: skipdef@QTEST_TC_1207.pdf
[INFO] Generated PDF report: twoimages@QTEST_TC_1208.pdf
[INFO] FINISHED - Generated 9 PDF reports successfully, 0 failed
[INFO] BUILD SUCCESS
```

**Result**: All 9 PDFs generated successfully

### Verification

After successful execution, verify the generated PDFs:

```bash
ls -lh target/pdf-reports/
```

**Expected output:**
```
total 2.1M
-rw-r--r-- 1 user group  85K Mar 13 14:00 datatable-docstring@QTEST_TC_1201.pdf
-rw-r--r-- 1 user group  92K Mar 13 14:00 exceptions@QTEST_TC_1202.pdf
-rw-r--r-- 1 user group 103K Mar 13 14:00 failure@QTEST_TC_1203.pdf
-rw-r--r-- 1 user group  76K Mar 13 14:00 lengthynames@QTEST_TC_1204.pdf
-rw-r--r-- 1 user group  68K Mar 13 14:00 notags.pdf
-rw-r--r-- 1 user group 112K Mar 13 14:00 scenario&outline@QTEST_TC_1205.pdf
-rw-r--r-- 1 user group 345K Mar 13 14:00 screenshots@QTEST_TC_1206.pdf
-rw-r--r-- 1 user group  71K Mar 13 14:00 skipdef@QTEST_TC_1207.pdf
-rw-r--r-- 1 user group 189K Mar 13 14:00 twoimages@QTEST_TC_1208.pdf
```

### Test Features

This test project includes 9 Cucumber feature files:

1. **datatable-docstring** (@QTEST_TC_1201) - Tests data tables and doc strings
2. **exceptions** (@QTEST_TC_1202) - Tests exception handling scenarios
3. **failure** (@QTEST_TC_1203) - Tests failed scenario reporting
4. **lengthynames** (@QTEST_TC_1204) - Tests long scenario/step names
5. **notags** (no qTest tag) - Tests feature without @QTEST_TC_ tag
6. **scenario&outline** (@QTEST_TC_1205) - Tests scenario outlines with examples
7. **screenshots** (@QTEST_TC_1206) - Tests embedded screenshots
8. **skipdef** (@QTEST_TC_1207) - Tests skipped/undefined steps
9. **twoimages** (@QTEST_TC_1208) - Tests multiple embedded images

## Troubleshooting

### Issue: Plugin not found

```
[ERROR] Plugin com.nosuchelements:cucumber-pdf-qtest-plugin:1.0.0 not found
```

**Solution**: Run `mvn clean install` in the plugin directory first to install it locally.

### Issue: Old version still being used

```bash
# Force update of Maven dependencies
mvn clean install -U

# Or delete the cached plugin
rm -rf ~/.m2/repository/com/nosuchelements/cucumber-pdf-qtest-plugin
cd /path/to/cucumber-pdf-qtest-plugin
mvn clean install
```

### Issue: Font errors still occurring

1. Verify you're on the latest `main` branch of the plugin:
   ```bash
   cd cucumber-pdf-qtest-plugin
   git branch
   git log --oneline -n 5
   ```
   
2. Check the commit includes "Fix: Font corruption":
   ```bash
   git log --grep="Font corruption" --oneline
   ```

3. Verify the fix is present in the installed JAR:
   ```bash
   unzip -l ~/.m2/repository/com/nosuchelements/cucumber-pdf-qtest-plugin/1.0.0/cucumber-pdf-qtest-plugin-1.0.0.jar | grep CucumberPDFQTestPlugin
   ```

## Success Criteria

✅ **All 9 PDF files generated** in `target/pdf-reports/`

✅ **No font corruption errors** in Maven output

✅ **Build completes successfully** (BUILD SUCCESS)

✅ **Correct file naming**:
   - Features with `@QTEST_TC_XXXX` tags: `feature-name@QTEST_TC_XXXX.pdf`
   - Features without tags: `feature-name.pdf`

✅ **Each PDF contains correct feature data**:
   - Feature name in title
   - All scenarios listed
   - Step details visible
   - Screenshots/images embedded (where applicable)

## Related Documentation

- **Plugin Fix PR**: [NoSuchElements/cucumber-pdf-qtest-plugin#1](https://github.com/NoSuchElements/cucumber-pdf-qtest-plugin/pull/1)
- **Technical Analysis**: `FONT_CORRUPTION_FIX.md` in plugin repository
- **Plugin README**: `README.md` in plugin repository

## Date

**Fix Implemented**: March 13, 2026
**Test Document Created**: March 13, 2026
**Status**: ✅ Fix verified and merged to main
