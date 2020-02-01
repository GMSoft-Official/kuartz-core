package com.kuartz.env.factory;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("singleton")
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
