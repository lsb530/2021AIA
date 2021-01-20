import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Properties;

class FileViewer {
  public static void main(String args[]) throws IOException{
  Properties p = System.getProperties();
  System.out.println(p.get("sun.jnu.encoding")); // UTF-8
  FileInputStream fis = new FileInputStream("encodingtest.txt");
  FileOutputStream fos = new FileOutputStream("korean_euc_kr.txt");
  InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
  OutputStreamWriter osw = new OutputStreamWriter(fos, "EUC-KR");
  int data = 0;
  while((data = isr.read())!=-1){
    osw.write(data);
  }
  osw.close();
  FileInputStream fis2 = new FileInputStream("korean_euc_kr.txt");
  FileOutputStream fos2 = new FileOutputStream("korean_utf8.txt");
  InputStreamReader isr2 = new InputStreamReader(fis2, "EUC-KR");
  OutputStreamWriter osw2 = new OutputStreamWriter(fos2, "UTF-8");
  data = 0;
  while((data = isr2.read())!=-1){
    osw2.write(data);
  }
  osw2.close();
  } 
}
