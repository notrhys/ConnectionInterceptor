package me.rhys.agent.interceptor.api.module;

import lombok.Getter;

@Getter
public abstract class Module implements IAgent {

    private final String name;

    public Module() {
        Class<? extends IAgent> clazz = getClass();
        if (!clazz.isAnnotationPresent(Agent.class)) {
            throw new RuntimeException(String.format("Class %s is not annotated with @Agent", clazz.getName()));
        }
        name = clazz.getDeclaredAnnotation(Agent.class).name();
    }
}
