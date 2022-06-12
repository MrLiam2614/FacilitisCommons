package me.mrliam2614.facilitiscommons.utils;

import me.mrliam2614.facilitiscommons.FacilitisCommons;
import org.bukkit.Bukkit;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FilesManager {
    public static void transfer(File from, File to, TransferCallback transferCallback) {
        //UNLOAD                  /Is-....    /worldIslands/Is-....
        //LOAD                     /worldIslands/Is-... /Is-...
        Bukkit.getScheduler().runTaskLaterAsynchronously(FacilitisCommons.getFacilitisCommons().getJavaPlugin(), () -> {
            try {
                for (File file : getFiles(from)) {
                    File fromFile = new File(file.getPath());
                    String fileSubPath = fromFile.getPath().replaceAll(from.getPath(), "");

                    File destinationFile = new File(to.getPath() + fileSubPath);

                    destinationFile.getParentFile().mkdirs();

                    FileInputStream fis = new FileInputStream(fromFile);
                    FileOutputStream fos = new FileOutputStream(destinationFile);

                    int c;
                    while ((c = fis.read()) != -1) {
                        fos.write(c);
                    }
                }
                transferCallback.onDone();
            } catch (IOException exception) {
                transferCallback.onError(exception);
            }
        }, 1);
    }

    private static List<File> getFiles(File file) {
        List<File> fileList = new ArrayList<>();
        if (file.isDirectory()) {

            Objects.requireNonNull(file.listFiles(), "from");
            for (File found : file.listFiles()) {
                if (!found.isDirectory()) {
                    fileList.add(found);
                } else {
                    fileList.addAll(getFiles(found));
                }
            }
        }

        return fileList;
    }

    private static void writeIntArrToStream(OutputStream os, int[] buffer) throws IOException {
        for (int i = 0; i < buffer.length; i++) {
            os.write(buffer[i]);
        }
    }

    private static int[] getBytesFromStream(InputStream is, int size) throws IOException {
        int[] b = new int[size];
        int count = 0;
        while (count < size) {
            b[count++] = is.read();
        }
        return b;
    }

    public static void deleteAll(File file) {
        if (file.isDirectory()) {
            Objects.requireNonNull(file.listFiles(), "from");
            if (file.delete()) {
                return;
            }
            for (File found : file.listFiles()) {
                if (!found.isDirectory()) {
                    found.delete();
                } else {
                    deleteAll(found);
                }
            }
            if (file.delete()) {
                return;
            }
        }
    }


    public interface TransferCallback {
        public void onDone();

        public void onError(Exception exception);
    }
}
