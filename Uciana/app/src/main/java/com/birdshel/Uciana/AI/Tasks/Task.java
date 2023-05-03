package com.birdshel.Uciana.AI.Tasks;

import java.util.Map;

public interface Task {
    Object getData(String str);

    Map<String, Object> getData();

    AiTask getType();

    boolean isDone();

    void update();
}
