package me.rhys.agent.interceptor.impl;

import me.rhys.agent.interceptor.api.module.AgentAnnotation;
import me.rhys.agent.interceptor.api.module.Module;
import me.rhys.agent.interceptor.util.Logger;

@AgentAnnotation(
        name = "Connection"
)
public class ConnectionListener extends Module {

    @Override
    public void onConnection(String host, int port) {
        Logger.log("Intercepted connection: " + host + ":" + port + " (" + this.typeFromPort(port) + ")");
    }

    String typeFromPort(int port) {

        switch (port) {
            case 80: {
                return "HTTP";
            }

            case 443: {
                return "HTTPS";
            }

            case -1: {
                return "SOCKET??";
            }
        }

        return "UNKNOWN";
    }
}
