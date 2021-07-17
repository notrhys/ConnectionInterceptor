package me.rhys.agent.interceptor.api.module;

public class Module implements AgentInterface {

    private String name;

    public Module() {
        if (getClass().isAnnotationPresent(AgentAnnotation.class)) {
            AgentAnnotation agentAnnotation = getClass().getAnnotation(AgentAnnotation.class);
            this.name = agentAnnotation.name();
        }
    }

    @Override
    public void onConnection(String host, int port) {

    }
}
