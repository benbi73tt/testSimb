package ru.simbirsoft.Practice.URLprocessing;

import java.io.BufferedReader;
import java.io.IOException;

public interface InterfaceURL {

    BufferedReader buffer() throws IOException;
    String stringPageURL();
}
