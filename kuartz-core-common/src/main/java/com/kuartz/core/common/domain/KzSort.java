package com.kuartz.core.common.domain;

import java.io.Serializable;
import java.util.Map;

public class KzSort implements Serializable {
    private static final long serialVersionUID = -7943852320107985222L;

    private Map<String, Direction> orderMap;

    public KzSort(Map<String, Direction> orderMap) {
        this.orderMap = orderMap;
    }

    public Map<String, Direction> getOrderMap() {
        return orderMap;
    }

    public void setOrderMap(Map<String, Direction> orderMap) {
        this.orderMap = orderMap;
    }

    public static enum NullHandling {
        DEFAULT,
        NULLS_FIRST,
        NULLS_LAST
    }

    public static enum Direction {
        ASC,
        DESC
    }
}
