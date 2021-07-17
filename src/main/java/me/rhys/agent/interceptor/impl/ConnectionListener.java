package me.rhys.agent.interceptor.impl;

import me.rhys.agent.interceptor.api.module.Agent;
import me.rhys.agent.interceptor.api.module.Module;
import me.rhys.agent.interceptor.util.Logger;

import java.util.HashMap;
import java.util.Map;

@Agent(name = "Connection")
public class ConnectionListener extends Module {

    private final Map<Integer, String> typePortMap = new HashMap<>();

    public ConnectionListener() {
        typePortMap.put(80, "HTTP");
        typePortMap.put(443, "HTTPS");
        typePortMap.put(-1, "SOCKET??");
    }

    @Override
    public void onConnection(String host, int port) {
        Logger.log(String.format("Intercepted connection %s:%d (%s)",
                host, port, typePortMap.getOrDefault(port, "UNKNOWN")));
    }
}
