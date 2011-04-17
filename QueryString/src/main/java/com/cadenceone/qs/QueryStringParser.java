package com.cadenceone.qs;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

public class QueryStringParser {
	private static final String UTF8_ENCODING = "UTF-8";
	private transient final Map<String, List<String>> params = new HashMap<String, List<String>>();

	public QueryStringParser(final URL url) {
		if (url.getQuery() != null) {
			final String[] paramTuples = url.getQuery().split("&");
			for (String paramTuple : paramTuples) {
				final String[] keyAndValue = paramTuple.split("=");
				final List<String> decoded = decodeAll(Arrays.asList(keyAndValue[1].split(",")));
				params.put(keyAndValue[0], decoded);
			}
		}
	}

	public Map<String, List<String>> getParameters() {
		return params;
	}

	private List<String> decodeAll(final List<String> toDecode) {
		final List<String> decoded = new ArrayList<String>();
		for (String stringToDecode : toDecode) {
			decoded.add(decode(stringToDecode));
		}
		return decoded;
	}

	private String decode(final String stringToDecode) {

		try {
			return URLDecoder.decode(stringToDecode, UTF8_ENCODING);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException("This should never happen (apparently '" + UTF8_ENCODING
					+ "' is no longer a supported encoding???) ", e);
		}
	}
}
