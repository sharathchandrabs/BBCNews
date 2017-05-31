package com.example.shara.bbcnews;

import android.util.Log;
import android.util.Xml;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by shara on 3/18/2017.
 */

public class NewsUtil {
    static public class BBCNewsParser extends DefaultHandler {

        public ArrayList<News> getNewsArrayList() {
            return newsArrayList;
        }

        ArrayList<News> newsArrayList;
        News news = null;
        StringBuilder xmlInnerText;

        static public ArrayList<News> parseNewsPullParser(InputStream in) throws XmlPullParserException, IOException {
            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(in, Xml.Encoding.UTF_8.toString());
            News news = null;
            ArrayList<News> newsArrayList = new ArrayList<>();
            int event = parser.getEventType();
            boolean isElement = false;
            DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
            Date date = null;
            while (event != XmlPullParser.END_DOCUMENT) {
                switch (event) {
                    case XmlPullParser.START_TAG:
                        if (parser.getName().equals("item")) {
                            news = new News();
                            isElement = true;
                        } else if (parser.getName().equals("title") && isElement) {
                            news.setTitle(parser.nextText());
                        } else if (parser.getName().equals("link") && isElement) {
                            news.setNewsLink(parser.nextText().trim());
                        } else if (parser.getName().equals("media:thumbnail") && isElement) {
                            news.setThumbNailImage(parser.getAttributeValue(null, "url").trim());
                        } else if (parser.getName().equals("pubDate") && isElement) {
                            news.setPublishedDate(new Date(parser.nextText()));
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("item")) {
                            newsArrayList.add(news);
                            news = null;
                        }
                        break;
                }
                event = parser.next();
            }

            return newsArrayList;
        }
    }
}

