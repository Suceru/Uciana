package com.birdshel.Uciana.AI.Tasks;

import java.util.HashMap;
import java.util.Map;

public class NoTask implements Task {
    @Override
    public Object getData(String str) {
        return null;
    }

    @Override
    public Map<String, Object> getData() {
        return new HashMap();
    }

    @Override
    public AiTask getType() {
        return AiTask.NONE;
    }

    @Override
    public boolean isDone() {
        return true;
    }

    @Override
    public void update() {
    }
}
