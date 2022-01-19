package ru.simbirsoft;

import ru.simbirsoft.Prictice.*;
import ru.simbirsoft.Prictice.Exception.InvalidURL;
import ru.simbirsoft.Prictice.Exception.NullPointer;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Simbirsoft {

    private static String websiteURL = "https://www.simbirsoft.com/";
    private static boolean verifyUrl(String url) {
        String urlRegex = "^(http|https)://[-a-zA-Z0-9+&@#/%?=~_|,!:.;]*[-a-zA-Z0-9+@#/%=&_|]";
        Pattern pattern = Pattern.compile(urlRegex);
        Matcher m = pattern.matcher(url);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean verify(String testURL) {
        try {
            URL url = new URL(testURL);
            URLConnection conn = url.openConnection();
            conn.connect();
            return true;
        } catch (MalformedURLException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    public static void main(String[] args) throws IOException, NullPointer, InvalidURL {

        if (verify(websiteURL)) {
            InterfaceURL read = new Reader(websiteURL);
            InterfaceParser parse = new Parser();
            ParsReaderService service = new ParsReaderService(read, parse);
            service.convert();
            service.getResult();
        } else
            throw new InvalidURL("Неверный URL адрес");
    }
}
