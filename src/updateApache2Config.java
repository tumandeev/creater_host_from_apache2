import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class updateApache2Config {
    private final String ProjectsPath;
    private final String siteName;
    private final String FilePath;

    public updateApache2Config(String siteName) throws IOException {

        Props properties = new Props();
        this.FilePath = properties.getFilePaths("apache2.conf");
        this.ProjectsPath = properties.getFilePaths("siteProjectsPath");
        this.siteName = siteName;


    }
    public void addRule() throws IOException {

        String fileString = new String(Files.readAllBytes(Paths.get(FilePath)));
        if(fileString.contains(ProjectsPath + siteName)){
            System.out.println("Сайт "+siteName+" с директорией "+ProjectsPath+" уже прописан в конфиге apache2");
        }else{

            Templates template = new Templates(ProjectsPath, siteName);
            String config = template.getTemplate("apache_conf");

            this.writeToFileAppend(FilePath, config);
        }
    }
    public void writeToFileAppend(String FilePath, String text){
        try {
            FileWriter writer = new FileWriter(FilePath, true);
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            bufferWriter.write(text);
            bufferWriter.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

}
