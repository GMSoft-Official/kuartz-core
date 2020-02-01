package com.kuartz.env.messages;

import org.springframework.stereotype.Component;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Component
@Singleton
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
