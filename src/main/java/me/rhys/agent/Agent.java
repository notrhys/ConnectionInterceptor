package me.rhys.agent;

import me.rhys.agent.interceptor.Interceptor;

import java.lang.instrument.Instrumentation;

public class Agent {
    public static void premain(String args, Instrumentation instrumentation) {
        new Interceptor(args);
    }

    public static void agentmain(String args, Instrumentation instrumentation) {
        new Interceptor(args);
    }
}
