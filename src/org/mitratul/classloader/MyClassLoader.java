package org.mitratul.classloader;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created with IntelliJ IDEA.
 * User: Brinto
 * Date: 13/1/13
 * Time: 4:01 PM
 */
public class MyClassLoader extends URLClassLoader {

    public MyClassLoader(URL[] pClassPath) {
        super(pClassPath);
        System.out.println("MyClassLoader initialized.");
    }

    public Class<?> loadClass (String pName) throws ClassNotFoundException {
        System.out.println("Loading " + pName);
        Class<?> c = null;
        try {
            c = findClass(pName);
        } catch (SecurityException ex) {
            //* Throws SecurityException while loading super class - Object.
            // Load that with super class
            System.out.println(" Delegate loading of " + pName + " to parent");
            c = super.loadClass(pName);
        }
        System.out.println(" Loaded " + pName);
        return c;
    }

    public String toString () {
        return "org.mitratul.classloader.MyClassLoader loaded by " +
                this.getClass().getClassLoader();
    }
}
