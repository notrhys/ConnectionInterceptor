package me.rhys.agent.interceptor.api.security;

import me.rhys.agent.interceptor.Interceptor;
import me.rhys.agent.interceptor.util.ClassUtil;
import me.rhys.agent.interceptor.util.SneakyThrow;

import java.net.SocketTimeoutException;
import java.security.Permission;

public class CustomSecurityManager extends SecurityManager {

    private final Interceptor interceptor = Interceptor.getInstance();
    private int currentBlocked;

    public CustomSecurityManager() {
        System.setSecurityManager(this);
    }

    @Override
    public void checkConnect(String host, int port, Object context) {
        checkConnect(host, port);
    }

    @Override
    public void checkConnect(String host, int port) {
         interceptor.getManager().getModuleList()
               .forEach(module -> module.onConnection(host, port));

         if (!interceptor.isAllowConnection()) {
             StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
             StackTraceElement stackTraceElement = stackTraceElements[2];

             String clazz = stackTraceElement.getClassName();
             boolean blockConnection = false;

             //Fixes spigot issues
             if (ClassUtil.doesClassExist("org.bukkit.craftbukkit.Main")) {
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
        if (perm.getName().equalsIgnoreCase("setSecurityManager")
                || perm.getName().equalsIgnoreCase("SecurityManager")) {
            System.exit(-1);
        }
    }

    @Override
    public void checkPermission(Permission perm, Object context) {
        checkPermission(perm);
    }

}
