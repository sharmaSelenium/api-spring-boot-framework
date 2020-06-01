/*
 * Copyright (c) 2018 The Emirates Group. All Rights Reserved. The information specified here is confidential and remains property of the Emirates Group.
 * groupId     - com.emirates.ocsl
 * artifactId  - profile-service
 * name        - profile-service
 * description - Profile Service
 * 2019
 */
package com.tajawal.apis.utils;

import java.util.Optional;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Component
@Getter
public class QueryParamUtil {
    public MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();

    public QueryParamUtil addQueryParam(String key, String value) {
        this.queryParams.add(key, value);
        return this;
    }

    public String getQueryKeyValue(String key) {
        return Optional
            .of(this.queryParams)
            .orElse(null)
            .getFirst(key);
    }

    public QueryParamUtil removeQueryParam(String key) {
        queryParams.remove(key);
        return this;
    }

    public QueryParamUtil addQueryParams(MultiValueMap<String, String> queryParams) {
        this.clearQueryParams();
        this.queryParams.addAll(queryParams);
        return this;
    }

    public void clearQueryParams() {
        queryParams.clear();
    }
}
