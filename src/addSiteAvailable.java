import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class addSiteAvailable {

    private final String siteName;
    private final String FilePath;
    private final String ProjectsPath;

    public addSiteAvailable(String siteName) throws IOException {

        Props properties = new Props();

        this.FilePath = properties.getFilePaths("site_available_dir");
        this.ProjectsPath = properties.getFilePaths("site_projects_path");
        this.siteName = siteName;

    }

    public void createAvailableConfig() throws IOException {


        String siteAvailablePath = FilePath + siteName + ".conf";
        File fileConfig = new File(siteAvailablePath);
        boolean created = fileConfig.createNewFile();
        if(created){
            System.out.println("Файл создан успешно " + siteAvailablePath);
        }else{
            System.out.println("Не удалось создать файл " + siteAvailablePath + " возможно он уже существует");
        }

        try(FileWriter writer = new FileWriter(siteAvailablePath, false))
        {
            Templates template = new Templates(ProjectsPath, siteName);
            String text = template.getTemplate("site_available");
            writer.write(text);
            writer.flush();
            System.out.println("Данные записаны в файл " + siteAvailablePath);
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
