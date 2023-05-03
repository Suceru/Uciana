package com.birdshel.Uciana.AI.Tasks;

import java.util.HashMap;
import java.util.Map;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class BuildTask implements Task {
    private boolean isDone = false;
    private final Map<String, Object> data = new HashMap();

    @Override // com.birdshel.Uciana.AI.Tasks.Task
    public Object getData(String str) {
        return null;
    }

    @Override // com.birdshel.Uciana.AI.Tasks.Task
    public Map<String, Object> getData() {
        return this.data;
    }

    @Override // com.birdshel.Uciana.AI.Tasks.Task
    public AiTask getType() {
        return AiTask.BUILD;
    }

    @Override // com.birdshel.Uciana.AI.Tasks.Task
    public boolean isDone() {
        return false;
    }

    @Override // com.birdshel.Uciana.AI.Tasks.Task
    public void update() {
    }
}
