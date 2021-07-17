package me.rhys.agent.interceptor.util;

public class Logger {
    public static void log(String s) {
        System.out.println("[INTERCEPTOR] (LOG) - " + s);
    }

    public static void warn(String s) {
        System.out.println("[INTERCEPTOR] (WARN) - " + s);
    }

    public static void error(String s) {
        System.out.println("[INTERCEPTOR] (ERROR) - " + s);
    }

    public static void debug(String s) {
        System.out.println("[INTERCEPTOR] (DEBUG) - " + s);
    }
}
