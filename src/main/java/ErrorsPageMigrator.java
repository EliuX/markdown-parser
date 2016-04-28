import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ehernandez on 28/04/16.
 */
public class ErrorsPageMigrator implements Runnable {

    private WebDriver driver = new FirefoxDriver();

    private final String DEBUGGING_CMS_BASEURL = "http://twilio-docs-prod.us-east-1.elasticbeanstalk.com/docs/admin/pages/91/";

    private final WebDriverBackedSelenium selenium;

    public ErrorsPageMigrator() {
        selenium = new WebDriverBackedSelenium(driver, DEBUGGING_CMS_BASEURL);
        readErrorFiles().forEach(System.out::println);
    }

    public String getGitHubRawUrl(int errorId) {
        return String.format("https://github.com/twilio/markdown/raw/master/docs/api/errors/%d.md", errorId);
    }

    public List<ErrorModel> readErrorFiles() {
        String ERROR_FILES_PATH = System.getenv("ERROR_FILES_PATH");
        if (ERROR_FILES_PATH == null) {
            System.err.println("No existe la variable ERROR_FILES_PATH en el sistema");
        }
        File errsFolder = new File(ERROR_FILES_PATH);
        File[] errFiles = errsFolder.listFiles();
        List result = new LinkedList<>();
        for (int i = 0; i < errFiles.length; i++) {
            if (errFiles[i].isFile())
            {
                try{
                    result.add(new ErrorModel(errFiles[i]));
                }catch(ParsingErrFileException e)
                {
                    System.err.println(e.getMessage());
                }
                catch(Throwable e)
                {
                    System.err.println("Unexpected error: " + e.getMessage());
                }
            }
        }
        return result;
    }

    @Override
    public void run() {
        driver.get(DEBUGGING_CMS_BASEURL);
        //elements.forEach(System.out::println);
        driver.close();
    }
}
