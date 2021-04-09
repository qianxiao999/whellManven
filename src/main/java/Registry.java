import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author
 * @create
 */
public class Registry {
    public static void registerUrlHandler() throws Exception {
        Runtime runtime = Runtime.getRuntime();
        //程序安装路径
       String curPath = "C:\\Program Files\\Logitech29";
//        String curPath = Paths.get("./").toAbsolutePath().getParent().toString();
        String registerKey = "HKEY_CLASSES_ROOT\\myurl\\shell\\open\\command";
        // 通过runtime执行cmd命令
        Process proc = runtime.exec("reg query " + registerKey);
        String content = readStream(proc.getInputStream());
        System.out.println(content);
        // 如果注册的执行路径是当前路径, 表示已经注册好了, 退出, 否则表示移动了软件位置, 重新注册.
        if (content.contains(curPath)) {
            return;
        }
        // String javaPath = System.getenv("JAVA_HOME") + "\\bin\\java.exe";
        // 添加 URL Protocol项
        String cmd0 = "reg add \"HKEY_CLASSES_ROOT\\myurl\" /v \"URL Protocol\" /d \"\" /f";
        runtime.exec(cmd0);
        // 添加命令
        String cmd = "reg add \"" + registerKey + "\" /ve /d \"\\\"" + curPath
                + "\\罗技G29.exe\\\" \\\"%1\\\"\" /f";
        // System.out.println(cmd);
        Process procAdd = runtime.exec(cmd);
        procAdd.waitFor();
        System.out.println(readStream(procAdd.getErrorStream()));
        System.out.println(readStream(procAdd.getInputStream()));
    }

    public static String readStream(InputStream is) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is));) {
            String content = "";
            String line = "";
            while ((line = br.readLine()) != null) {
                content += line;
            }
            return content;
        }
    }
}
