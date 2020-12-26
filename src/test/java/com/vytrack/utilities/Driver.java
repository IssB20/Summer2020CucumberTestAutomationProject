package com.vytrack.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Driver{

    private Driver(){}

    public static ThreadLocal  <WebDriver> driverPool = new ThreadLocal<>();

    public static WebDriver getDriver(){
        if (driverPool.get() == null){
            //it will make that 2 threads cannot access this piece of code at the same time
            //only 1 thread at the time
            synchronized (Driver.class) {
                String browser = ConfigurationReader.getProperty("browser");
                //            jenkins command: test -Dcucumber.filter.tags="@smoke" -Dbrowser="chrome"
//            custom environment variables: -Dbrowser
//            -Dproperty  = then read in java System.getProperty("property")
//            if env variable was specified
                if (System.getProperty("browser") != null) {
//                then change browser type
//                regardless on value configuration.properties
                    System.out.println("Browser type was changed to: " + System.getProperty("browser"));
                    browser = System.getProperty("browser");
                }
                switch (browser) {
                    case "chrome":
                        WebDriverManager.chromedriver().setup();
                        ChromeOptions chromeOptions = new ChromeOptions();
                        chromeOptions.addArguments("--headless");
                        driverPool.set(new ChromeDriver(chromeOptions));
                        break;
                    case "firefox":
                        WebDriverManager.firefoxdriver().setup();
                        driverPool.set(new FirefoxDriver());
                        break;
                    case "remote-chrome":
                        try {
                            //    ChromeOptions chromeOptions = new ChromeOptions();
                            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                            desiredCapabilities.setBrowserName("chrome");
                            URL gridUrl = new URL("http://100.26.226.105/:4444/wd/hub");
                            driverPool.set(new RemoteWebDriver(gridUrl, desiredCapabilities));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "remote-firefox":
                        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                        desiredCapabilities.setBrowserName("firefox");
                        try {
                            URL gridUrl = new URL("http://100.26.226.105/:4444/wd/hub/");
                            driverPool.set(new RemoteWebDriver(gridUrl, desiredCapabilities));
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        throw new RuntimeException(("No such a browser yet!"));
                }
            }
        }
        return  driverPool.get();
    }


    public static void closeDriver(){
        if (driverPool.get() != null){
            driverPool.get().quit();
            driverPool.remove();

        }
    }



}
