package Configuration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.SkipException;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class WebDriverFactory {

    WebDriver driver =null;
    public WebDriverFactory(){

    }

    public WebDriver createWebDriver(String browser, String url){

        try {
            if (StringUtils.endsWithIgnoreCase(browser,"CHROME")){
                System.out.println("Creating chrome session...");
                Map<String, Object> prefs = new HashMap<String, Object>();
                System.setProperty("webdriver.chrome.driver", "src/test/resources/bin/chromedriver.exe");
                ChromeOptions options = new ChromeOptions();

                //capabilities
                //options.addArguments("--headless");
                //options.addArguments("--disable-gpu"); //deshabilitar animaciones.
                //options.addArguments("--window-size=1400,800"); //para aplicar se debe comentar o desahabilitar driver.manage().window().maximize(); mas abajo
                //options.addArguments("--no-sandbox", "--disable-dev-shm-usage");

                //preferencias experimentales
                prefs.put("download.default_directory", getCurrentPath() + "\\src\\test\\resources\\downloads");
                prefs.put("download.prompt_for_download", false);
                options.setExperimentalOption("prefs", prefs);


                driver = new ChromeDriver(options);
            }else if (StringUtils.endsWithIgnoreCase(browser,"CHROME_LOCAL")){
                // con options.setBinary("src/test/resources/bin/chrome-win64/chrome.exe"); configuramos chrome local
                System.out.println("Creating chrome local session...");
                Map<String, Object> prefs = new HashMap<String, Object>();
                System.setProperty("webdriver.chrome.driver", "src/test/resources/bin/chromedriver.exe");
                ChromeOptions options = new ChromeOptions();

                //con options.setBinary("src/test/resources/bin/chrome-win64/chrome.exe"); configuramos chrome de manera local o externa (local dentro de chrome-win64 o el que tenemos instalado en la pc)
                options.setBinary("src/test/resources/bin/chrome-win64/chrome.exe");

                //capabilities
                //options.addArguments("--headless");
                //options.addArguments("--disable-gpu"); //deshabilitar animaciones.
                //options.addArguments("--window-size=1400,800"); //para aplicar se debe comentar o desahabilitar driver.manage().window().maximize(); mas abajo
                //options.addArguments("--no-sandbox", "--disable-dev-shm-usage");

                //preferencias experimentales
                prefs.put("download.default_directory", getCurrentPath() + "\\src\\test\\resources\\downloads");
                prefs.put("download.prompt_for_download", false);
                options.setExperimentalOption("prefs", prefs);


                driver = new ChromeDriver(options);

            }else if (StringUtils.endsWithIgnoreCase(browser,"FIREFOX")){
                System.out.println("Creating firefox session...");
                System.setProperty("webdriver.firefox.driver", "./geckodriver.exe");
                driver = new FirefoxDriver();

            }else if (StringUtils.endsWithIgnoreCase(browser,"CHROME_WDM")){
                //webdrivermanaer es una dependencia para instalar la ultima version del driver automaticamente pero necesita internet y suele dar errores
                System.out.println("Creating chrome local wdm session...");
                Map<String, Object> prefs = new HashMap<String, Object>();

                WebDriverManager.chromedriver().setup();

                ChromeOptions options = new ChromeOptions();

                //con options.setBinary("src/test/resources/bin/chrome-win64/chrome.exe"); configuramos chrome de manera local o externa (local dentro de chrome-win64 o el que tenemos instalado en la pc)
                options.setBinary("src/test/resources/bin/chrome-win64/chrome.exe");

                //capabilities
                //options.addArguments("--headless");
                //options.addArguments("--disable-gpu"); //deshabilitar animaciones.
                //options.addArguments("--window-size=1400,800"); //para aplicar se debe comentar o desahabilitar driver.manage().window().maximize(); mas abajo
                //options.addArguments("--no-sandbox", "--disable-dev-shm-usage");

                //preferencias experimentales
                prefs.put("download.default_directory", getCurrentPath() + "\\src\\test\\resources\\downloads");
                prefs.put("download.prompt_for_download", false);
                options.setExperimentalOption("prefs", prefs);


                driver = new ChromeDriver(options);

            }else if (StringUtils.endsWithIgnoreCase(browser,"FIREFOX_WDM")){
                System.out.println("Creating firefox wdm session...");
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }else{
                System.out.printf(String.format("No drivers configured for this param %s", browser));
            }

        }catch (Exception e){
            throw new SkipException("Skipping the test case, driver is broken");
        }


        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get(url);

        return driver;

    }

    private Object getCurrentPath() {
        return Paths.get("").toAbsolutePath().toString();
    }

}
