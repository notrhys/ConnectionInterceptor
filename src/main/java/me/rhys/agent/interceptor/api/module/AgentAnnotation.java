package me.rhys.agent.interceptor.api.module;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface AgentAnnotation {
    String name() default "Module";
}
