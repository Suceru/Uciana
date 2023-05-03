package com.birdshel.Uciana.AI.Tasks;

import java.util.HashMap;
import java.util.Map;

/* compiled from: MyApplication */
/* loaded from: classes.dex */
public class NoTask implements Task {
    @Override // com.birdshel.Uciana.AI.Tasks.Task
    public Object getData(String str) {
        return null;
    }

    @Override // com.birdshel.Uciana.AI.Tasks.Task
    public Map<String, Object> getData() {
        return new HashMap();
    }

    @Override // com.birdshel.Uciana.AI.Tasks.Task
    public AiTask getType() {
        return AiTask.NONE;
    }

    @Override // com.birdshel.Uciana.AI.Tasks.Task
    public boolean isDone() {
        return true;
    }

    @Override // com.birdshel.Uciana.AI.Tasks.Task
    public void update() {
    }
}
