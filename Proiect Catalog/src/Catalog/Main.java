package Catalog;
import Catalog.Database.Config.DatabaseConfiguration;
import Catalog.Database.Config.SetupData;
import Catalog.Database.Repository.ClassRepository;
import Catalog.Database.Repository.StudentRepository;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Sistem sis = new Sistem();
        try {
            sis.menu();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
