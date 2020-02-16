package com.kuartz.core.common.domain;

import java.io.Serializable;
import java.util.List;

public class KzSort implements Serializable {
    private static final long serialVersionUID = -7943852320107985222L;

    private List<KzOrder> kzOrderList;

    public KzSort() {
        //    bos yapici
    }

    public KzSort(List<KzOrder> kzOrderList) {
        this.kzOrderList = kzOrderList;
    }

    public List<KzOrder> getKzOrderList() {
        return kzOrderList;
    }

    public void setKzOrderList(List<KzOrder> kzOrderList) {
        this.kzOrderList = kzOrderList;
    }

    public static class KzOrder implements Serializable {
        private static final long serialVersionUID = -5001150159671661556L;

        private NullHandling nullHandling;
        private String       property;
        private Direction    direction;

        public KzOrder() {
            //    bos yapici
        }

        public KzOrder(String property, Direction direction) {
            this.property  = property;
            this.direction = direction;
        }

        public KzOrder(NullHandling nullHandling, String property, Direction direction) {
            this.nullHandling = nullHandling;
            this.property     = property;
            this.direction    = direction;
        }

        public NullHandling getNullHandling() {
            return nullHandling;
        }

        public void setNullHandling(NullHandling nullHandling) {
            this.nullHandling = nullHandling;
        }

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }

        public Direction getDirection() {
            return direction;
        }

        public void setDirection(Direction direction) {
            this.direction = direction;
        }
    }

    public enum NullHandling {
        DEFAULT,
        NULLS_FIRST,
        NULLS_LAST
    }

    public enum Direction {
        ASC,
        DESC
    }
}
