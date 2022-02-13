import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Templates {

    final String ProjectsPath;
    final String SiteName;


    public Templates(String ProjectsPath, String SiteName){

        this.ProjectsPath = ProjectsPath;
        this.SiteName = SiteName;
    }

    public String getTemplate(String templateName) throws IOException {
        InputStream inputProp = Thread.currentThread().getContextClassLoader().getResourceAsStream("templates/" + templateName);

        assert inputProp != null;
        String template = new String(inputProp.readAllBytes());

        String formattedString = template.replace("%site_name%", SiteName);
        formattedString = formattedString.replace("%projects_path%", ProjectsPath);

        return formattedString;
    }



}
