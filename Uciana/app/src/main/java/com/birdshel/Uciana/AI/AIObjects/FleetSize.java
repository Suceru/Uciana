package com.birdshel.Uciana.AI.AIObjects;

public enum FleetSize {
    TINY, // 舰队规模为微小
    SMALL, // 舰队规模为小型
    MEDIUM, // 舰队规模为中型
    LARGE, // 舰队规模为大型
    HUGE, // 舰队规模为巨型
    MAXED; // 舰队规模为最大

    /**
     * 根据舰队的数量和舰队的最大容量计算舰队规模
     * @param fleetCount 舰队数量
     * @param maxCapacity 舰队最大容量
     * @return 舰队规模
     */
    public static FleetSize getFleetSize(int fleetCount, int maxCapacity) {
        if (fleetCount <= 0) { // 如果舰队数量小于等于0，返回最大舰队规模
            return MAXED;
        }
        if (fleetCount <= 4) { // 如果舰队数量小于等于4，返回巨型舰队规模
            return HUGE;
        }
        int fleetCapacityRatio = fleetCount / maxCapacity; // 计算舰队数量与舰队最大容量的比例
        if (fleetCapacityRatio < 0 || fleetCapacityRatio >= 0.2f) {
            float ratio = fleetCapacityRatio;
            if (ratio < 0.2f || ratio >= 0.4f) {
                if (ratio < 0.4f || ratio >= 0.6f) {
                    if (ratio < 0.6f || ratio >= 0.8f) {
                        if (ratio >= 0.8f && ratio < 0.95f) {
                            return HUGE;
                        }
                        return MAXED;
                    }
                    return LARGE;
                }
                return MEDIUM;
            }
            return SMALL;
        }
        return TINY;
    }
}

