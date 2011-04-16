package com.cadenceone.qsb;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class QueryStringParserTest {


    @Test
    public void whenConstructedWithNoQueryString_thenReturnsEmptyCollection() throws MalformedURLException {
        URL url = new URL("http://www.example.com/foo/bar");
        QueryStringParser parser = new QueryStringParser(url);

        assertThat(parser.getParameters().size(), is(0));
    }

    @Test
    public void whenConstructedWithOneSimpleQueryStringParameter_thenReturnsOneParameterList() throws MalformedURLException {
        URL url = new URL("http://www.example.com/foo/bar?goo=car");
        QueryStringParser parser = new QueryStringParser(url);

        assertThat(parser.getParameters().size(), is(1));
        assertThat(parser.getParameters().get("goo").size(), is(1));
        assertThat(parser.getParameters().get("goo"), contains("car"));
    }

    @Test
    public void whenConstructedWithSimpleQueryStringParameter_thenReturnsTwoParameterLists() throws MalformedURLException {
        URL url = new URL("http://www.example.com/foo/bar?goo=car&hoo=dar");
        QueryStringParser parser = new QueryStringParser(url);

        assertThat(parser.getParameters().size(), is(2));
        assertThat(parser.getParameters().get("goo").size(), is(1));
        assertThat(parser.getParameters().get("goo"), contains("car"));

        assertThat(parser.getParameters().get("hoo").size(), is(1));
        assertThat(parser.getParameters().get("hoo"), contains("dar"));
    }

     @Test
    public void whenConstructedWithMultipleValueQueryStringParameter_thenReturnsParameterListWithMultipleEntries() throws MalformedURLException {
        URL url = new URL("http://www.example.com/foo/bar?goo=car,dar");
        QueryStringParser parser = new QueryStringParser(url);

        assertThat(parser.getParameters().size(), is(1));
        assertThat(parser.getParameters().get("goo").size(), is(2));
        assertThat(parser.getParameters().get("goo"), contains("car", "dar"));
    }
}
