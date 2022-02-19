import java.io.IOException;

public class Main {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";



    public static void main(String[] siteNames) throws IOException {


        for (String s : siteNames) {

            addSiteAvailable site =  new addSiteAvailable(s);
            site.createAvailableConfig();

            updateApache2Config config = new updateApache2Config(s);
            config.addRule();

            HostsEditor hostsEdit = new HostsEditor(s);
            hostsEdit.editHosts();

            CreateProjectDirectory createProjectDirectory = new CreateProjectDirectory(s);
            createProjectDirectory.CreateCatalog();

        }


        System.out.println(ANSI_YELLOW + "Используйте эти команды для активации виртуального хоста:" );
        System.out.print(ANSI_CYAN);

        for (String site : siteNames) {
            System.out.println("sudo a2ensite "+site);
        }

        Props properties = new Props();
        String projectPath = properties.getFilePaths("site_projects_path");
        for (String site : siteNames) {
            System.out.println("sudo chown $USER "+projectPath+site);
        }


        System.out.println("sudo systemctl restart apache2");
        System.out.print(ANSI_RESET);
    }

}
