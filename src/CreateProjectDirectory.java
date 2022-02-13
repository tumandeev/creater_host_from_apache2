import java.io.File;
import java.io.IOException;

public class CreateProjectDirectory {

    String SiteName;
    String FilePath;

    public CreateProjectDirectory(String SiteName) throws IOException {

        Props properties = new Props();
        this.FilePath = properties.getFilePaths("siteProjectsPath");
        this.SiteName = SiteName;
    }



    public void CreateCatalog() throws IOException{
        String path = FilePath + SiteName;
        File fileDir =  new File(path);

      if(fileDir.mkdir()){
          System.out.println("Создан каталог - "+ path);
      }else {
          System.out.println("Каталог уже существует - "+ path);
      }

    }


}
