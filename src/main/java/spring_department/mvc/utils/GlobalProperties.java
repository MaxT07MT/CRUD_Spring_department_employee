package spring_department.mvc.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class GlobalProperties {
    private static Properties properties = new Properties();

    public static Properties getProperties() {
        FileInputStream fis;

       if(properties.isEmpty()) {
           try {
               fis = new FileInputStream("C:\\Users\\user\\Maxim\\IT\\Max\\SpringDepartment_Enter\\src\\main\\resources\\application.properties");
               properties.load(fis);

           } catch (Exception e) {
               System.out.println("Cant init application properties " + e.getMessage());
           }
       }
        return properties;

    }
}