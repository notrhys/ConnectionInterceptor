# Connection-Interceptor
This tool allows you to intercept connections made from a java application

## How to use
You can run the agent using
```bash
java -javaagent:ConnectionInterceptor-1.0-SNAPSHOT.jar -jar application.jar
```

to disable connections you can use:
```bash
java -javaagent:ConnectionInterceptor-1.0-SNAPSHOT.jar=disable -jar application.jar
```

and yes this can be dynamically attached from another application.
