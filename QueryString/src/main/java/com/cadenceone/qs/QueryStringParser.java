package com.cadenceone.qs;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

public class QueryStringParser {

    private final Map<String, List<String>> params = new HashMap<String, List<String>>();

    public QueryStringParser(URL url) {
        if (url.getQuery() != null){
            String[] paramTuples = url.getQuery().split("&");
            for (String paramTuple:paramTuples){
                String[] keyAndValue = paramTuple.split("=");
                List<String> decoded =  decodeAll(Arrays.asList(keyAndValue[1].split(",")));
                params.put(keyAndValue[0],decoded);
            }
        }
    }

    public Map<String, List<String>> getParameters() {
        return params;
    }

    private List<String> decodeAll(List<String> toDecode){
        List<String> decoded = new ArrayList<String>();
        for (String stringToDecode:toDecode){
            decoded.add(decode(stringToDecode));
        }
        return decoded;
    }

    private String decode(String stringToDecode){
        try {
            return URLDecoder.decode(stringToDecode, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Could not URLDecode '"+stringToDecode+"'.", e);
        }
    }
}
