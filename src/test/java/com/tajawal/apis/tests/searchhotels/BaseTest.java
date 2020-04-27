package com.tajawal.apis.tests.searchhotels;

import com.tajawal.apis.commons.Endpoints;
import com.tajawal.apis.config.Config;
import com.tajawal.apis.steps.SearchHotelSteps;
import com.tajawal.apis.utils.HeaderUtil;
import com.tajawal.apis.utils.QueryParamUtil;
import com.tajawal.apis.utils.TestUtil;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = Config.class)
@ExtendWith(SpringExtension.class)
@TestPropertySource({"classpath:endpoints.properties"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public abstract class BaseTest {
    @Autowired
    public SearchHotelSteps searchHotelSteps;
    @Autowired
    public HeaderUtil headerUtil;
    @Autowired
    public QueryParamUtil queryParamUtil;
}
