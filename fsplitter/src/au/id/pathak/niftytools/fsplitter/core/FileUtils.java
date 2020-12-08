package au.id.pathak.niftytools.fsplitter.core;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {

  public static byte[] split(FileInputStream fis, int start, int size) throws IOException {
    int r = -1;
    byte[] buffer = new byte[size];
    while ((r = fis.read(buffer, start, size)) != -1) {

    }

    return null;
  }

  public static void split(int size, String fileName) throws Exception {
    File file = new File(fileName);
    boolean exists = file.exists();
    boolean isFile = file.isFile();
    boolean canRead = file.canRead();
    if (!exists || !isFile || !canRead) {

      throw new RuntimeException("File doesn't exists or insufficient permissions.");

    }

    long length = file.length();
    if (length <= size) {
      throw new RuntimeException("Partition size is equal or larger than file size.");
    }

    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file), 0x1000);
    byte[] buffer = new byte[1024];
    long partitions = length / size;
    long rem = length % size;
    long run = 0;
    BufferedOutputStream bos =  null;
    while (run++ < size) {
      // lets start the run.

      bos = new BufferedOutputStream(new FileOutputStream(new File(fileName.concat("." + run))));

      long chunks = partitions / 1024;
      int remain = (int) partitions % 1024;

      int p = 0;
      while (p++ < chunks) {
        bis.read(buffer);
        bos.write(buffer);
      }
      if (remain > 0) {
        bis.read(buffer, 0, remain);
        bos.write(buffer, 0, remain);
      }
      bos.close();
    }

    if (rem > 0) {
      //last element
      bos = new BufferedOutputStream(new FileOutputStream(new File(fileName.concat("." + run))));
      int r=-1;
      while((r=bis.read(buffer))!=-1){

        bos.write(buffer,0,r);

      }
      bos.close();
    }

  }
}
