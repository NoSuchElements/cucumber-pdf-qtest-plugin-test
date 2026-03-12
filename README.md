# Cucumber PDF QTest Plugin - Test Implementation

This project demonstrates the usage of the [cucumber-pdf-qtest-plugin](https://github.com/NoSuchElements/cucumber-pdf-qtest-plugin) for generating individual PDF reports per feature file with `@QTEST_TC_` naming conventions.

## Overview

The plugin extends the standard Cucumber PDF reporting by:
- Generating one PDF report per feature file
- Naming reports using `@QTEST_TC_` tags found in features
- Supporting features without tags (default naming)
- Maintaining all standard PDF report features

## Prerequisites

- Java 11 or higher
- Maven 3.6 or higher
- cucumber-pdf-qtest-plugin 1.0.0 installed locally or in Maven repository

## Project Structure

```
src/test/
├── java/com/nosuchelements/test/
│   ├── RunCukeIT.java          # TestNG runner
│   └── StepDefinitions.java    # Step implementations
└── resources/features/
    ├── datatable-docstring.feature  # @QTEST_TC_1201
    ├── exceptions.feature           # @QTEST_TC_1202
    ├── failure.feature              # @QTEST_TC_1203
    ├── lengthynames.feature         # @QTEST_TC_1204
    ├── notags.feature               # No tag (default naming)
    ├── scenario-outline.feature     # @QTEST_TC_1205
    ├── screenshots.feature          # @QTEST_TC_1206
    ├── skipdef.feature              # @QTEST_TC_1207
    └── twoimages.feature            # @QTEST_TC_1208
```

## Installation

### 1. Clone the Repository

```bash
git clone https://github.com/NoSuchElements/cucumber-pdf-qtest-plugin-test.git
cd cucumber-pdf-qtest-plugin-test
```

### 2. Install the Plugin Dependency

The plugin must be available in your Maven repository. Install it locally:

```bash
git clone https://github.com/NoSuchElements/cucumber-pdf-qtest-plugin.git
cd cucumber-pdf-qtest-plugin
mvn clean install
cd ../cucumber-pdf-qtest-plugin-test
```

## Usage

### Run Tests and Generate PDF Reports

```bash
mvn clean verify
```

This command will:
1. Compile the test code
2. Execute Cucumber integration tests
3. Generate JSON report at `target/cucumber-json.json`
4. Trigger the PDF QTest plugin in post-integration-test phase
5. Generate individual PDF reports in `target/pdf-reports/`

### Expected Output

After successful execution, you'll find 9 PDF files in `target/pdf-reports/`:

```
target/pdf-reports/
├── datatable-docstring@QTEST_TC_1201.pdf
├── exceptions@QTEST_TC_1202.pdf
├── failure@QTEST_TC_1203.pdf
├── lengthynames@QTEST_TC_1204.pdf
├── notags.pdf
├── scenario-outline@QTEST_TC_1205.pdf
├── screenshots@QTEST_TC_1206.pdf
├── skipdef@QTEST_TC_1207.pdf
└── twoimages@QTEST_TC_1208.pdf
```

### Run Only Tests (No Reports)

```bash
mvn clean test
```

### Generate Reports from Existing JSON

```bash
mvn cucumber-pdf-qtest:pdfreportperfeature
```

## Configuration

### Plugin Configuration in pom.xml

```xml
<plugin>
    <groupId>com.nosuchelements</groupId>
    <artifactId>cucumber-pdf-qtest-plugin</artifactId>
    <version>1.0.0</version>
    <configuration>
        <cucumberJsonReportDirectory>${project.build.directory}</cucumberJsonReportDirectory>
        <cucumberPdfReportDirectory>${project.build.directory}/pdf-reports</cucumberPdfReportDirectory>
        <qtestTagPrefix>@QTEST_TC_</qtestTagPrefix>
        <title>QTest Integration Report</title>
        <displayExpanded>true</displayExpanded>
    </configuration>
</plugin>
```

### Available Configuration Options

| Parameter | Default | Description |
|-----------|---------|-------------|
| `cucumberJsonReportDirectory` | *required* | Directory containing cucumber JSON reports |
| `cucumberPdfReportDirectory` | `pdf-report` | Output directory for PDF reports |
| `qtestTagPrefix` | `@QTEST_TC_` | Tag prefix for QTest identification |
| `title` | *none* | Report title (appended with feature name) |
| `titleColor` | *default* | Hex color for title |
| `passColor` | *default* | Hex color for passed scenarios |
| `failColor` | *default* | Hex color for failed scenarios |
| `skipColor` | *default* | Hex color for skipped scenarios |
| `displayExpanded` | `false` | Expand all scenarios by default |
| `skipHooks` | `false` | Skip hook information in report |

## Feature File Naming Convention

The plugin extracts feature names and QTEST tags to build PDF filenames:

### With QTEST Tag

```gherkin
@QTEST_TC_1201
Feature: DataTable And DocString
```

**Generated PDF:** `datatable-docstring@QTEST_TC_1201.pdf`

### Without QTEST Tag

```gherkin
Feature: No tag feature
```

**Generated PDF:** `notags.pdf`

### Naming Rules

1. Feature name is converted to lowercase
2. Spaces replaced with hyphens
3. Special characters removed
4. QTEST tag appended with `@` separator if present
5. `.pdf` extension added

## Validation

### Verify Report Generation

```bash
# Check report directory
ls -la target/pdf-reports/

# Count generated PDFs (should be 9)
ls target/pdf-reports/*.pdf | wc -l
```

### View Feature-PDF Mapping

See `docs/feature-pdf-mapping.csv` for the complete mapping of features to generated PDFs.

## Integration with CI/CD

### Jenkins Example

```groovy
stage('Run Tests') {
    steps {
        sh 'mvn clean verify'
    }
}

stage('Archive Reports') {
    steps {
        archiveArtifacts artifacts: 'target/pdf-reports/*.pdf'
        publishHTML([
            reportDir: 'target/pdf-reports',
            reportFiles: '*.pdf',
            reportName: 'QTest PDF Reports'
        ])
    }
}
```

### GitLab CI Example

```yaml
test:
  stage: test
  script:
    - mvn clean verify
  artifacts:
    paths:
      - target/pdf-reports/*.pdf
    expire_in: 30 days
```

## Troubleshooting

### No PDFs Generated

1. Check JSON report was created: `ls target/cucumber-json.json`
2. Verify plugin executed: Look for "STARTED CUCUMBER PDF PER-FEATURE REPORT GENERATION" in logs
3. Check output directory exists: `ls -la target/pdf-reports/`

### Missing QTEST Tags in Filename

1. Verify tag format matches `qtestTagPrefix` configuration
2. Check tag is at feature level, not scenario level
3. Confirm tag includes underscore: `@QTEST_TC_1201` not `@QTEST-TC-1201`

### Plugin Not Found

Install the plugin locally:

```bash
git clone https://github.com/NoSuchElements/cucumber-pdf-qtest-plugin.git
cd cucumber-pdf-qtest-plugin
mvn clean install
```

## Contributing

Contributions are welcome! Please submit issues and pull requests to:
- Plugin: https://github.com/NoSuchElements/cucumber-pdf-qtest-plugin
- Test Project: https://github.com/NoSuchElements/cucumber-pdf-qtest-plugin-test

## License

This project uses the same license as the cucumber-pdf-qtest-plugin.

## References

- [cucumber-pdf-qtest-plugin](https://github.com/NoSuchElements/cucumber-pdf-qtest-plugin)
- [cucumber-pdf-plugin](https://github.com/grasshopper7/cucumber-pdf-plugin)
- [Cucumber Documentation](https://cucumber.io/docs/cucumber/)
