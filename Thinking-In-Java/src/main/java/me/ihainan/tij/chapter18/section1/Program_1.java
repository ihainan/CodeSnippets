package me.ihainan.tij.chapter18.section1;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * 获取目录列表，并设置过滤器
 */
public class Program_1 {
    public static void main(String[] args) {
        File file = new File(".");
        final String regex = ".*";
        // String[] list = file.list();
        // String[] list = file.list(new DataFilter(regex));
        if (!file.isFile()) {
            String[] list = file.list(new FilenameFilter() {    // 匿名内部类，regex 必须是 final`
                @Override
                public boolean accept(File file, String name) {
                    return Pattern.compile(regex).matcher(name).matches();
                }
            });
            Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
            for (String fileName : list) {
                File tmpFile = new File(fileName);
                System.out.println(fileName + " " + tmpFile.length());
            }
            System.out.println(Arrays.toString(list));
        }
    }

    /* Output:
      [.DS_Store, .idea, ThingingInJava.iml, Thinking-In-Java.iml, pom.xml, src, target]
      [.DS_Store, .idea, pom.xml, src, target, ThingingInJava.iml, Thinking-In-Java.iml]
     */

    private static class DataFilter implements FilenameFilter {
        Pattern pattern;

        public DataFilter(String regex) {
            pattern = Pattern.compile(regex);
        }

        @Override
        public boolean accept(File file, String name) {
            return pattern.matcher(name).matches();
        }
    }
}
