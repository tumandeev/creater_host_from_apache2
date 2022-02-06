import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class updateApache2Config {
    String siteName;
    public updateApache2Config(String siteName){
        this.siteName = siteName;
    }
    public void addRule(){
        String configFilePath = "/etc/apache2/apache2.conf";
        String config = "\n\n<Directory /srv/projects/"+siteName+">\n" +
                        "    Options -Indexes +FollowSymLinks\n" +
                        "    AllowOverride All\n" +
                        "</Directory>";

        try {
            FileWriter writer = new FileWriter(configFilePath, true);
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            bufferWriter.write(config);
            bufferWriter.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
}
