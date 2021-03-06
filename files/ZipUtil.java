package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {

    private ZipUtil() {
    }

    public static void doCompress(String srcFile, String zipFile,String rootdDirectory)
            throws IOException {
        doCompress(new File(srcFile), new File(zipFile),rootdDirectory);
    }

    /**
     * 文件压缩
     * 
     * @param srcFile
     *            目录或者单个文件
     * @param zipFile
     *            压缩后的ZIP文件
     */
    public static void doCompress(File srcFile, File zipFile,String rootdDirectory)
            throws IOException {
        ZipOutputStream out = null;
        try {
            out = new ZipOutputStream(new FileOutputStream(zipFile));
            doCompress(srcFile, out,rootdDirectory);
        } catch (Exception e) {
            throw e;
        } finally {
            out.close();// 记得关闭资源
        }
    }

    public static void doCompress(File file, ZipOutputStream out,String rootdDirectory)
            throws IOException {
        doCompress(file, out, "",rootdDirectory);
    }

    public static void doCompress(File inFile, ZipOutputStream out, String dir,String rootdDirectory)
            throws IOException {
        if (inFile.isDirectory()) {
            File[] files = inFile.listFiles();
            if (files != null && files.length > 0) {
                for (File file : files) {
                    String name = inFile.getName();
                    if (!"".equals(dir)) {
                        name = dir + "/" + name;
                    }
                    ZipUtil.doCompress(file, out, name,rootdDirectory);
                }
            }
        } else {
　　　　　　　　//将根目录干掉，否则无法打开OFD文件
            dir = dir.replaceAll(rootdDirectory, "");
            ZipUtil.doZip(inFile, out, dir);
        }
    }

    public static void doZip(File inFile, ZipOutputStream out, String dir)
            throws IOException {
        String entryName = null;
        if (!"".equals(dir)) {
            entryName = dir + "/" + inFile.getName();
        } else {
            entryName = inFile.getName();
        }
        ZipEntry entry = new ZipEntry(entryName);
        out.putNextEntry(entry);

        int len = 0;
        byte[] buffer = new byte[1024];
        FileInputStream fis = new FileInputStream(inFile);
        while ((len = fis.read(buffer)) > 0) {
            out.write(buffer, 0, len);
            out.flush();
        }
        out.closeEntry();
        fis.close();
    }

    public static void main(String[] args) throws IOException {
        String rootdDirectory = "OFD";
        doCompress("E:/OFD/", "E:/OFD.ofd",rootdDirectory);
    }

}