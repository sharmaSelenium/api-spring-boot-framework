/*
 * Copyright (c) 2018 The Emirates Group. All Rights Reserved. The information specified here is confidential and remains property of the Emirates Group.
 * groupId     - com.emirates.ocsl
 * artifactId  - profile-service
 * name        - profile-service
 * description - Profile Service
 * 2019
 */
package com.tajawal.apis.utils;

import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Component
@Getter
public class QueryParamUtil {
    public MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();

    public QueryParamUtil addQueryParam(String key, String val) {
        queryParams.add(key,val);
        return this;
    }

    public void clearQueryParams() {
        queryParams.clear();
    }
}
