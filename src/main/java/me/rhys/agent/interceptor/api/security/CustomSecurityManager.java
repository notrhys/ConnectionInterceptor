package me.rhys.agent.interceptor.api.security;

import me.rhys.agent.interceptor.Interceptor;
import me.rhys.agent.interceptor.util.ClassUtil;
import me.rhys.agent.interceptor.util.SneakyThrow;

import java.net.SocketTimeoutException;
import java.security.Permission;

public class CustomSecurityManager extends SecurityManager {

    public CustomSecurityManager() {
        System.setSecurityManager(this);
    }

    private int currentBlocked;

    @Override
    public void checkConnect(String host, int port, Object context) {
        this.checkConnect(host, port);
    }

    @Override
    public void checkConnect(String host, int port) {
         Interceptor.getInstance().getManager().getModuleList()
               .forEach(module -> module.onConnection(host, port));

         if (!Interceptor.getInstance().isAllowConnection()) {
             StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
             StackTraceElement stackTraceElement = stackTraceElements[2];

             String clazz = stackTraceElement.getClassName();
             boolean blockConnection = false;
             boolean isRunningMinecraft = ClassUtil.doesClassExist("org.bukkit.craftbukkit.Main");

             //Fixes spigot issues
             if (isRunningMinecraft) {
                 if ((clazz.startsWith("sun.nio") || clazz.startsWith("java.net"))
                         && !clazz.startsWith("java.net.InetAddress")
                         && !clazz.startsWith("sun.nio.ch.SocketChannelImpl") && currentBlocked < 10) {
                     currentBlocked++;
                     blockConnection = true;
                 }

                 if (currentBlocked > 10) {
                     blockConnection = true;
                 }
             } else {
                 blockConnection = true;
             }

             if (blockConnection) {
                 SneakyThrow.sneakyThrow(new SocketTimeoutException("Connection timed out"));
             }
         }
    }

    @Override
    public void checkPermission(Permission perm) {
        //

        if (perm.getName().equalsIgnoreCase("setSecurityManager")
                || perm.getName().equalsIgnoreCase("SecurityManager")) {
            System.exit(-1);
        }
    }

    @Override
    public void checkPermission(Permission perm, Object context) {
        this.checkPermission(perm);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Throwable> void sneakyThrow(Throwable t) throws T {
        throw (T) t;
    }
}
