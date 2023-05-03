package com.birdshel.Uciana.AI.Tasks;

import java.util.HashMap;
import java.util.Map;

public class BuildTask implements Task {
    private boolean isDone = false;
    private final Map<String, Object> data = new HashMap();

    @Override
    public Object getData(String str) {
        return null;
    }

    @Override
    public Map<String, Object> getData() {
        return this.data;
    }

    @Override
    public AiTask getType() {
        return AiTask.BUILD;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public void update() {
    }
}
