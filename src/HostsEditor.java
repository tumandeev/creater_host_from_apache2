import java.io.*;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;

public class HostsEditor {

    private String SiteName;
    private String FilePath;


    public HostsEditor(String SiteName) throws IOException {


        Props properties = new Props();
        this.FilePath = properties.getFilePaths("hosts");

        this.SiteName = SiteName;

    }

    public void editHosts(){

        try {
            FileReader reader = new FileReader(FilePath);
            BufferedReader bufferedReader = new BufferedReader(reader);
            Scanner scanner = new Scanner(bufferedReader);

            int lines = this.countStrings();
            String[] HostsStrings = new String[lines];

            int ite = 0;
            while (scanner.hasNextLine()) {
                HostsStrings[ite++] = scanner.nextLine();
            }


            int maxValue = 0;
            boolean check = false;
           for (int i = 0; i < HostsStrings.length; i++){
               if(HostsStrings[i].contains("127.0")){
                   String Str = new String(HostsStrings[i]);

                   for (String retval : Str.split(" ")) {
                       if (retval.contains("127.0")) {

                           String[] sectors = retval.split("\\.");
                           if(sectors[2].equals("1")){
                               if(maxValue <  Integer.parseInt(sectors[3])){
                                   maxValue = Integer.parseInt(sectors[3]);
                               }
                           }
                       }else if(!retval.equals("") && retval.equals(SiteName)){
                           check = true;
                           break;
                       }
                   }
               }
           }


           if(check){
               System.out.println("Адрес " +SiteName+ " уже записан в hosts");
           }else if(maxValue < 255){
               String newIp = "\n127.0.1." + (maxValue + 1) + " "+ SiteName;


                updateApache2Config updater = new updateApache2Config(SiteName);
               updater.writeToFileAppend(FilePath, newIp);
               System.out.println(newIp );

           }else{
               System.out.println("Что то пошло не так, проверьте класс HostsEditor");
           }
            bufferedReader.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }


    public int countStrings() throws IOException {
        FileReader reader = new FileReader(FilePath);
        BufferedReader bufferedReader = new BufferedReader(reader);


        int lines = 0;
        while (bufferedReader.readLine() != null) lines++;


        return lines;
    }


}
