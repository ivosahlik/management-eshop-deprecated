package com.adminportal.core.featuretoggling;


import com.netflix.config.ConfigurationManager;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.NoSuchElementException;

/**
 * Intellij Idea
 * Created by ivosahlik on 24/12/2018
 */
public class TestEnabledCondition implements Condition {

    static final String FEATURE_TEST_ENABLED = "feature.test.enabled";
    static final boolean DEFAULT_FEATURE_TEST_ENABLED = false;

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        try {
            return ConfigurationManager.getConfigInstance().getBoolean(FEATURE_TEST_ENABLED);
        } catch (NoSuchElementException ex) {
            return DEFAULT_FEATURE_TEST_ENABLED;
        }
    }
}
