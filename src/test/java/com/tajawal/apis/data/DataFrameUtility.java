package com.tajawal.apis.data;

import com.zavtech.morpheus.frame.DataFrame;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class DataFrameUtility {

    private File ekOperatedFilePath = new File(System.getProperty("user.dir") +
        "/src/test/resources/DemoSheet1.csv");
    private static DataFrame<Object, String>  df;


    private DataFrame<Object,String> getDataFrameFromCsv(File fileName) {
        return DataFrame.read().csv(fileName);
    }

    public void loadAllDataFrames(){

//            Class.forName(creds.mysqlDriver);
//            df_ek_destinations = DataFrame.read().
//                db(options -> {
//                        options.withConnection(creds.url, creds.username, creds.password);
//                        options.withSql(SELECT_EK_DESTINATIONS.getQuery()); });
//           df_valid_city_pair = DataFrame.read().csv(validCityPairFilePath);


            df = getDataFrameFromCsv(ekOperatedFilePath);

    }

    private List<String> getInterlineAirportCodesOrigin() {
        List<String> interlineCodes = new ArrayList<>();
        df.rows().filter(row ->  row.getValue("CITY_ISONLINESALES").equals("Y") &&
            (row.getValue("DESTINATION_TYPE_INDICATOR").equals("2") || row.getValue("DESTINATION_TYPE_INDICATOR").equals(2))).
            forEach(code -> { String airportCode = code.getValue("CODE");
                interlineCodes.add(airportCode);});
        return interlineCodes.stream().distinct().collect(Collectors.toList());
    }


}
