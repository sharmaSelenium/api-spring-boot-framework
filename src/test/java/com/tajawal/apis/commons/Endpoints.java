package com.tajawal.apis.commons;

import java.util.function.Supplier;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@RequiredArgsConstructor
public class Endpoints {
    @Value("${baseURI}")
    private String baseURI;

    @Value("${searchGeoSuggestEndpoint}")
    private String searchGeoSuggestEndpoint;
    @Value("${searchRequestEndpoint}")
    private String searchRequestEndpoint;

    public Supplier<String> getSearchGeoSuggestFullPath = () -> baseURI + searchGeoSuggestEndpoint;
    public Supplier<String> getSearchRequestFullPath = () -> baseURI + searchRequestEndpoint;

}
