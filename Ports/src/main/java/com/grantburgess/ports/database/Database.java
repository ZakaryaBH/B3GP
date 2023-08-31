package com.grantburgess.ports.database;

public interface Database {
    TaskGateway taskGateway();
    FeatureGateway featureGateway();
}
