package com.nosuchelements.test;

import static org.testng.Assert.assertEquals;

//import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.SkipException;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.PendingException;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/*import cucumber.api.*;
import cucumber.api.java.*;
import cucumber.api.java.en.*;*/

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Step definitions for QTest plugin test scenarios.
 * Provides implementations for all steps used across feature files.
 */
public class StepDefinitions {
    
	private Scenario scenario;

	@Given("Hello background")
	public void backgrond() {
	}

	/*
	 * @Before(value = "not @failure") public void before() throws
	 * InterruptedException { Thread.sleep(100); // }
	 * 
	 * @After(value = "not @failure") public void after() throws
	 * InterruptedException { Thread.sleep(100); // }
	 */
	@BeforeStep
	public void beforeStep() throws InterruptedException {
		Thread.sleep(100);
	}

	@AfterStep
	public void afterStep() throws InterruptedException {
		Thread.sleep(100);
	}

	@Before(value = "@failure")
	public void beforeFailure(Scenario scenario) { //
		this.scenario = scenario;
		scenario.log("FAILURE HI");
		scenario.log("FAILURE HELLO");
		throw new RuntimeException();
	}

	@After(value = "@failure")
	public void afterFailure() { //
		scenario.log("FAILURE HI");
		scenario.log("FAILURE HELLO");
		throw new RuntimeException();
	}

	@Given("Hook failure step")
	public void hook_failure_step() throws InterruptedException {
		// System.out.println("Failure step");
		Thread.sleep(500);
		scenario.log("FAILURE STEP HI");
		scenario.log("FAILURE STEP HELLO");
	}

	@Given("Skip hook failure step")
	public void skip_hook_failure_step() throws InterruptedException {
		Thread.sleep(250);
	}

	@Given("{string} background")
	public void background(String type) {
		//System.out.format("%s type background. \n", type);
	}

	@Given("Write a {string} step with precondition in {string}")
	@When("Complete action in {string} step in {string}")
	@Then("Validate the outcome in {string} step in {string}")
	public void step(String step, String scenario) throws InterruptedException {
		// System.out.format("%s step from %s.\n", step.toUpperCase(),
		// scenario.toUpperCase());
		Thread.sleep(500);
	}

	@Then("Raise exception")
	public void raiseExcep() {

		Random r = new Random();
		boolean flag = r.nextBoolean();
		assertEquals(flag, true);
	}

	@Then("Do not raise exception")
	public void doNotRaiseExcep() {
		assertEquals(true, true);
	}

	@Given("Customer orders the dishes")
	public void dataTable(List<List<String>> table) {
	}

	@Given("the doc string is")
	public void docStr(String docStr) {
	}

	private WebDriver driver;
	private String site;
	static {
        // Use Java 11+ HTTP/WebSocket client instead of Netty
        System.setProperty("webdriver.http.factory", "jdk-http-client");
    }
	@And("Go to {word}")
    public void visitweb(String site) throws Exception {
        driver.get(site);
        this.site = site;
        scenario.log("scenario website name - " + site);
        Thread.sleep(3000);
    }

    @Before(value = "@website or @twoimg")
    public void beforeSite(Scenario scenario) {
        this.scenario = scenario;

        ChromeOptions options = new ChromeOptions();
        // Use only if you still see DevTools / origin issues:
        // options.addArguments("--remote-allow-origins=*");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @After(value = "@website or @twoimg")
    public void afterSite() {
        if (driver != null) {
            try {
                TakesScreenshot ts = (TakesScreenshot) driver;
                byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", this.site);

                scenario.log("first log");
                scenario.log("second log second log");
                scenario.log("third log third log third log");
                scenario.log("fourth log fourth log fourth log fourth log");
                scenario.log("fifth log fifth log fifth log fifth log fifth log");
                scenario.log("sixth log sixth log sixth log sixth log sixth log sixth log");
                scenario.log("seventh log seventh log seventh log seventh log "
                        + "seventh log seventh log seventh log seventh log seventh log "
                        + "seventh log seventh log seventh log seventh log seventh log "
                        + "seventh log seventh log seventh log seventh log seventh log");

            } catch (Exception e) {
                scenario.log("Failed to capture screenshot: " + e.getMessage());
            } finally {
                driver.quit();
            }
        } else {
            if (scenario != null) {
                scenario.log("Driver was null in @After; skipping screenshot and quit");
            }
        }
    }

    @Given("Pending step definition")
    public void pending_step_definition_methods() {
        throw new io.cucumber.java.PendingException();
    }

    @Given("Skipped step definition")
    public void skippedStep() {
        throw new SkipException("Skip it");
    }

    @Given("Go to capture 2 images in one step")
    public void twoImages() throws Exception {

        driver.get("https://github.com/");
        Thread.sleep(500);
        TakesScreenshot ts = (TakesScreenshot) driver;
        byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", "github");

        driver.get("https://stackoverflow.com/");
        Thread.sleep(500);
        ts = (TakesScreenshot) driver;
        screenshot = ts.getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", "stackoverflow");
    }
}
