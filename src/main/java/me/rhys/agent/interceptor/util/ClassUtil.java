package me.rhys.agent.interceptor.util;

public class ClassUtil {
    public static boolean doesClassExist(String className) {
        try {
            Class.forName(className);
            return true;
        } catch (Exception ignored) {}

        return false;
    }
}
