package com.cadenceone.qs;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class QueryStringBuilder {

	private transient URL existingUrl;

	public QueryStringBuilder(final URL existingUrl) {
		this.existingUrl = existingUrl;
	}

	public QueryStringBuilder addParam(final String paramaterName, final String... paramaterValues) {
		existingUrl = addParamsToUrl(paramaterName, Arrays.asList(paramaterValues), existingUrl);
		return this;
	}

	public URL build() {
		return existingUrl;
	}

	private URL addParamsToUrl(final String paramaterName, final List<String> paramaterValues, final URL existingUrl) {

		String queryString = existingUrl.getQuery();
		queryString = addParamsToQueryString(paramaterName, paramaterValues, queryString);

		try {
			final URI withQueryString = new URI(existingUrl.getProtocol(), null, existingUrl.getHost(), 
					existingUrl.getPort(), existingUrl.getPath(), queryString, null);
			return withQueryString.toURL();
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException(String.format("Could not add parameter with name '%s' and values '%s'",
					paramaterName, paramaterValues), e);
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException(String.format("Could not add parameter with name '%s' and values '%s'",
					paramaterName, paramaterValues), e);
		}
	}

	private String addParamsToQueryString(final String paramaterName, final List<String> paramaterValues,
			final String existingQueryString) {
		String queryString = "";
		if (existingQueryString != null && existingQueryString.trim().length() > 0) {
			queryString = existingQueryString + "&";
		}
		queryString += String.format("%s=%s", paramaterName, join(paramaterValues, ","));
		return queryString;
	}

	public String join(final Collection<String> joinThese, final String seperatingThemWithThis) {
		final StringBuilder builder = new StringBuilder();
		final Iterator<String> iterator = joinThese.iterator();
		while (iterator.hasNext()) {
			builder.append(iterator.next());
			if (iterator.hasNext()) {
				builder.append(seperatingThemWithThis);
			}
		}
		return builder.toString();
	}
}