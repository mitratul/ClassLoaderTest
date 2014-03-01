import org.mitratul.classloader.ClassToLoad;
import org.mitratul.classloader.MyClassLoader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;

public class Main {

    public static void main (String[] args) {
        ClassToLoad mClass;
        MyClassLoader myClassLoader;

        System.out.println("Initializing MyClassloader...");
        myClassLoader = new MyClassLoader(getSystemClassPath());

        HashMap<String, ClassLoader> loaderHashMap
                = new HashMap<String, ClassLoader>(2);
        loaderHashMap.put("default loader", Main.class.getClassLoader());
        loaderHashMap.put("MyClassLoader", myClassLoader);

        for (String loaderName: loaderHashMap.keySet()) {
            System.out.println("\nLoading class with " + loaderName + "...");
            try{
                //* ClassCastException while assigning for the custom loader.
                mClass = ClassToLoad.getNewInstance(
                        loaderHashMap.get(loaderName));
                System.out.println(
                        "Loading class with " + loaderName + ": DONE.");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private static URL[] getSystemClassPath () {
        HashSet<URL> urls = new HashSet<URL>();
        for (String path: System.getProperty("java.class.path").split(";")) {
            try{
                urls.add(new File(path).toURI().toURL());
            } catch (MalformedURLException ex) {
                //* Ignore invalid path
            }
        }
        return urls.toArray(new URL[0]);
    }
}
