package au.id.pathak.niftytools.fsplitter.core;

import java.io.FileInputStream;
import java.io.IOException;

public class FileUtils {


  public static byte[] split(FileInputStream fis , int start, int size) throws IOException  {
    int r = -1;
    byte[] buffer = new byte[size];
    while((r = fis.read(buffer,start,size))!=-1){

    }
  }
}
