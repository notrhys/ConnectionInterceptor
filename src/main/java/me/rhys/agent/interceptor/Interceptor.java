package me.rhys.agent.interceptor;

import lombok.Getter;
import me.rhys.agent.interceptor.api.Manager;
import me.rhys.agent.interceptor.api.security.CustomSecurityManager;
import me.rhys.agent.interceptor.util.Logger;

import java.lang.instrument.Instrumentation;

@Getter
public class Interceptor {
    @Getter private static Interceptor instance;

    private final Manager manager = new Manager();
    private final CustomSecurityManager customSecurityManager;
    private boolean allowConnection = true;

    public Interceptor(String agentArguments) {
        instance = this;

        if (agentArguments != null && agentArguments.equalsIgnoreCase("disable")) {
            Logger.warn("Any connections will be blocked.");
            allowConnection = false;
        }

        customSecurityManager = new CustomSecurityManager();
        manager.setup();
    }
}
