package com.cadenceone.qsb;

import java.net.URL;
import java.util.*;

public class QueryStringParser {

    private final Map<String, List<String>> params = new HashMap<String, List<String>>();

    public QueryStringParser(URL url) {
        if (url.getQuery() != null){
            String[] paramTuples = url.getQuery().split("&");
            for (String paramTuple:paramTuples){
                String[] keyAndValue = paramTuple.split("=");
                params.put(keyAndValue[0], Arrays.asList(keyAndValue[1].split(",")));
            }
        }
    }

    public Map<String, List<String>> getParameters() {
        return params;
    }
}
