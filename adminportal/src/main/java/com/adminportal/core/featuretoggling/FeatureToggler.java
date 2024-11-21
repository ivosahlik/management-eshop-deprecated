package com.adminportal.core.featuretoggling;


/**
 * Intellij Idea
 * Created by ivosahlik on 24/12/2018
 */
public class FeatureToggler {

    private boolean featureTestEnabled = Boolean.parseBoolean(System.getProperty("feature.test.enabled"));

    public FeatureToggler() {
//        this.featureTestEnabled = ConfigurationManager.getConfigInstance().getBoolean(
//                FEATURE_TEST_ENABLED,
//                DEFAULT_FEATURE_TEST_ENABLED);
    }

    public boolean isFeatureTestEnabled() {
        return featureTestEnabled;
    }

}
