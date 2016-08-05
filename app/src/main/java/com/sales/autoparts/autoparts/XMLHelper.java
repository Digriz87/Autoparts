package com.sales.autoparts.autoparts;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class XMLHelper extends DefaultHandler {







    String URL_MAIN;


    /**
     * The URL to be parsed
     */
    String TAG = "XMLHelper";

    Boolean currTag = false;
    String currTagVal = "";
    private PostValue post = null;
    private ArrayList<PostValue> posts = new ArrayList<PostValue>();

    public ArrayList<PostValue> getPostsList() {
        return this.posts;
    }


    public void get(String link) {
        URL_MAIN =  "https://tehnomir.com.ua/ws/xml.php?act=GetPrice&usr_login=procenko&usr_passwd=dnn857965&Number="+link+"&Currency=UAH";
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser mSaxParser = factory.newSAXParser();
            XMLReader mXmlReader = mSaxParser.getXMLReader();
            mXmlReader.setContentHandler(this);
            InputStream mInputStream = new URL(URL_MAIN).openStream();
            mXmlReader.parse(new InputSource(mInputStream));
        } catch (Exception e) {
            // Exceptions can be handled for different types
            // But, this is about XML Parsing not about Exception Handling
            Log.e(TAG, "Exception: " + e.getMessage());
        }
    }

    // This method receives notification character data inside an element
    // e.g. <post_title>Add EditText Pragmatically - Android</post_title>
    // It will be called to read "Add EditText Pragmatically - Android"
    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        if (currTag) {
            currTagVal = currTagVal + new String(ch, start, length);
            currTag = false;
        }
    }

    // Receives notification of end of element
    // e.g. <post_title>Add EditText Pragmatically - Android</post_title>
    // It will be called when </post_title> is encountered
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        currTag = false;

        if (localName.equalsIgnoreCase("Brand"))
            post.setBrand(currTagVal);

        else if (localName.equalsIgnoreCase("Price"))
            post.setPrice(currTagVal);

        else if (localName.equalsIgnoreCase("Name"))
            post.setDescription(currTagVal);

        else if (localName.equalsIgnoreCase("DeliveryTime"))
            post.setDeliveryTime(currTagVal);

        else if (localName.equalsIgnoreCase("Number"))
            post.setArticle(currTagVal);

        else if (localName.equalsIgnoreCase("Currency"))
            post.setDate(currTagVal);

        else if (localName.equalsIgnoreCase("Detail"))
            posts.add(post);
    }

    // Receives notification of start of an element
    // e.g. <post_title>Add EditText Pragmatically - Android</post_title>
    // It will be called when <post_title> is encountered
    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        Log.i(TAG, "TAG: " + localName);

        currTag = true;
        currTagVal = "";
        // Whenever <post> element is encountered it will create new object of PostValue
        if (localName.equals("Detail"))
            post = new PostValue();
    }


}