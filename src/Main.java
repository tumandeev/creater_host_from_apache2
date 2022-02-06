import java.io.IOException;

public class Main {
    public static void main(String[] siteNames) throws IOException {
        for (String s : siteNames) {

            addSiteAvailable site =  new addSiteAvailable(s);
            site.createAvailableConfig();

            updateApache2Config config = new updateApache2Config(s);
            config.addRule();

        }
    }
}
