package com.cadenceone.qs;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class QueryStringBuilder {

    private URL url;
    private final Map<String, List<String>> params = new TreeMap<String, List<String>>();

    public QueryStringBuilder(URL url) {
        this.url = url;
    }

    public URL build() {
        if (params.isEmpty()){
                return url;
        }
        return addParamsToUrl(params, url);

    }

    private URL addParamsToUrl(Map<String, List<String>> params, URL url) {
        assert !params.isEmpty();
        String queryString = url.getQuery();

        queryString = addParamsToQueryString(params, queryString);

        try {
            URI withQueryString = new URI(url.getProtocol(), null, url.getHost(), url.getPort(), url.getPath(), queryString, null);
            return withQueryString.toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException("Could not build URL", e);
        } catch (URISyntaxException e) {
            throw new RuntimeException("Could not build URL", e);
        }
    }

    private String addParamsToQueryString(Map<String,List<String>> params, String queryString) {
        String returnQueryString = (queryString==null || "".equals(queryString.trim()))? "":queryString+"&";
        Collection<String> tuples = new ArrayList<String>();
        for (String key:params.keySet()){
            tuples.add(String.format("%s=%s", key, join(params.get(key), ",")));
        }
        return returnQueryString + join(tuples, "&");
    }

    public String join(Collection coll, String separator){
        StringBuilder builder = new StringBuilder();
        Iterator iterator = coll.iterator();
        while (iterator.hasNext()) {
            builder.append(iterator.next());
            if (iterator.hasNext()) {
                builder.append(separator);
            }
        }
        return builder.toString();
    }

    public QueryStringBuilder addParam(String key, String... values){
        params.put(key, Arrays.asList(values));
        return this;
    }
}