package org.mitratul.classloader;

/**
 * Created with IntelliJ IDEA.
 * User: Brinto
 * Date: 13/1/13
 * Time: 4:19 PM
 */
public class ClassToLoad {

    public ClassToLoad () {
        System.out.println(this);
    }

    public static ClassToLoad getNewInstance (ClassLoader pClassLoader)
            throws Exception {
        System.out.println("Initializing ClassToLoad...");

        return (ClassToLoad) pClassLoader.loadClass(
                "org.mitratul.classloader.ClassToLoad").newInstance();
    }

    public String toString () {
        return "ClassToLoad loaded by [" +
                this.getClass().getClassLoader() + "]";
    }
}
