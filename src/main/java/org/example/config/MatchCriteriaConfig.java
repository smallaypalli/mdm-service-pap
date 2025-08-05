package org.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "match")
public class MatchCriteriaConfig {
    private Map<String, Integer> weights;

    public Map<String, Integer> getWeights() {
        return weights;
    }

    public void setWeights(Map<String, Integer> weights) {
        this.weights = weights;
    }
}

