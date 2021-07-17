package me.rhys.agent.interceptor.api;

import lombok.Getter;
import me.rhys.agent.interceptor.api.module.Module;
import me.rhys.agent.interceptor.impl.ConnectionListener;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Manager {
    private final List<Module> moduleList = new ArrayList<>();

    public void setup() {
        this.moduleList.add(new ConnectionListener());
    }
}
