package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class copyReportToDownloads {

    public static void copyReportToDownloads1() {
        try {
            File src = new File("test-output/emailable-report.html");

            // Generate timestamp
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

            // Set destination path with timestamp
            String userHome = System.getProperty("user.home");
            File dest = new File(userHome + "/Downloads/emailable-report_" + timestamp + ".html");

            Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Report copied to Downloads folder as: emailable-report_" + timestamp + ".html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
