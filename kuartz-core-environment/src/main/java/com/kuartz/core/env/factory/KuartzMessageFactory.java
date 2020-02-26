package com.kuartz.core.env.factory;

import java.util.ArrayList;
import java.util.List;

public class KuartzMessageFactory {
    private List<String> bundleDir;

    public KuartzMessageFactory() {
        this.bundleDir = new ArrayList<>();
    }

    public void add(String bundlePath) {
        bundleDir.add(bundlePath);
    }

    public List<String> getBundleDir() {
        return bundleDir;
    }
}
