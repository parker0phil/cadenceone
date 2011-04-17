package com.cadenceone.qs;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class QueryStringBuilderTest {


    @Test
    public void givenAUrl_whenNoMethodsCalled_thenReturnsEqualUrl() throws MalformedURLException {
        URL url = new URL("http://www.example.com/foo/bar?goo=car&hoo=dar");
        QueryStringBuilder builder = new QueryStringBuilder(url);

        assertThat(builder.build(), is(url));
    }


    @Test
    public void givenAUrl_whenSimpleParameterIsAdded_thenParameterIsAddedToUrl() throws MalformedURLException {
        URL url = new URL("http://www.example.com/foo/bar");
        QueryStringBuilder builder = new QueryStringBuilder(url);
        builder.addParam("foo", "bar");

        assertThat(builder.build().toExternalForm(), is("http://www.example.com/foo/bar?foo=bar"));
    }

    @Test
    public void givenAUrlWithTrailingQuestionMark_whenSimpleParameterIsAdded_thenMultipleQuestionMarksArentAddedToUrl() throws MalformedURLException {
        URL url = new URL("http://www.example.com/foo/bar?");
        QueryStringBuilder builder = new QueryStringBuilder(url);
        builder.addParam("foo", "bar");

        assertThat(builder.build().toExternalForm(), is("http://www.example.com/foo/bar?foo=bar"));
    }

    @Test
    public void givenAUrlWithExistingQueryString_whenSimpleParameterIsAdded_thenParameterIsAddedToUrl() throws MalformedURLException {
        URL url = new URL("http://www.example.com/foo/bar?goo=car&hoo=dar");
        QueryStringBuilder builder = new QueryStringBuilder(url);
        builder.addParam("foo", "bar");

        assertThat(builder.build().toExternalForm(), is("http://www.example.com/foo/bar?goo=car&hoo=dar&foo=bar"));
    }

    @Test
    public void givenAUrl_whenMultipleParametersAreAdded_thenAllParametersAreAddedToUrlInCorrectOrder() throws MalformedURLException {
        URL url = new URL("http://www.example.com/foo/bar?goo=car&hoo=dar");
        QueryStringBuilder builder = new QueryStringBuilder(url);
        builder.addParam("foo", "bar");
        builder.addParam("joo", "ear");
        builder.addParam("koo", "far");

        assertThat(builder.build().toExternalForm(), is("http://www.example.com/foo/bar?goo=car&hoo=dar&foo=bar&joo=ear&koo=far"));

    }

    @Test
    public void giveAUrl_whenMultipleValuesAreAddedForAParameter_thenAllValuesAreAddedToUrlInCorrectOrder() throws MalformedURLException {
        URL url = new URL("http://www.example.com/foo/bar");
        QueryStringBuilder builder = new QueryStringBuilder(url);
        builder.addParam("goo", "car", "dar", "bar", "ear", "far");

        assertThat(builder.build().toExternalForm(), is("http://www.example.com/foo/bar?goo=car,dar,bar,ear,far"));

    }

    @Test
    public void giveAUrl_whenAParameterValueContainsASpace_thenValueIsCorrectlyEncoded() throws MalformedURLException {
        URL url = new URL("http://www.example.com/foo/bar");
        QueryStringBuilder builder = new QueryStringBuilder(url);
        builder.addParam("goo", "car dar");

        assertThat(builder.build().toExternalForm(), is("http://www.example.com/foo/bar?goo=car%20dar"));

    }
}
