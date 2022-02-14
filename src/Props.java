import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Props extends Properties{


    public Properties getProps() throws IOException {


        InputStream inputProp = Thread.currentThread().getContextClassLoader().getResourceAsStream("resources/filepath.properties");

        Properties props = new Properties();
        props.load(inputProp);

        return props;

    }

    public String getFilePaths(String FileName) throws IOException {
        Properties filePaths = getProps();
        return filePaths.getProperty(FileName);
    }



}
