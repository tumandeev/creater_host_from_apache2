import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class addSiteAvailable {

    private final String siteName;

    public addSiteAvailable(String siteName){
        this.siteName = siteName;

    }

    public void createAvailableConfig() throws IOException {
        String siteAvailablePath = "/etc/apache2/sites-available/" + siteName + ".conf";
        File fileConfig = new File(siteAvailablePath);

        boolean created = fileConfig.createNewFile();
        if(created){
            System.out.println("Файл создан успешно " + siteAvailablePath);
        }else{
            System.out.println("Не удалось создать файл " + siteAvailablePath);
        }

        try(FileWriter writer = new FileWriter(siteAvailablePath, false))
        {
            // запись всей строки
            String text =   "<VirtualHost *:80>\n" +
                            "    ServerName "+siteName+"\n" +
                            "    ServerAlias www."+siteName+"\n" +
                            "    ServerAdmin webmaster@"+siteName+"\n" +
                            "    DocumentRoot /srv/projects/"+siteName+"\n" +
                            "    ErrorLog ${APACHE_LOG_DIR}/"+siteName+"-error.log\n" +
                            "    CustomLog ${APACHE_LOG_DIR}/"+siteName+"-access.log combined\n" +
                            "</VirtualHost>";
            writer.write(text);
            writer.flush();
            System.out.println("Данные записаны в файл " + siteAvailablePath);
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
