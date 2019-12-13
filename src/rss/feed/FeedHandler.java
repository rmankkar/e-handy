package rss.feed;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import android.content.Context;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;

import rss.feed.common.Enclosure;
import rss.feed.common.Feed;
import rss.feed.common.Item;

public class FeedHandler extends DefaultHandler {

	private static final String LOG_TAG = "FeedHandler";

	private Feed mFeed;
	private Item mItem;
	private Enclosure mEnclosure;
	private static final String DATE_FORMATS[] = {
			"EEE, dd MMM yyyy HH:mm:ss Z", "EEE, dd MMM yyyy HH:mm:ss z",
			"yyyy-MM-dd'T'HH:mm:ssz", "yyyy-MM-dd'T'HH:mm:ssZ",
			"yyyy-MM-dd'T'HH:mm:ss'Z'", "yyyy-MM-dd'T'HH:mm:ss.SSSZ" };
	private SimpleDateFormat mSimpleDateFormats[] = new SimpleDateFormat[DATE_FORMATS.length];

	private static final Set<String> NAMESPACES = new HashSet<String>(
			Arrays.asList(new String[] { "",
					"http://purl.org/rss/1.0/modules/content/",
					"http://www.w3.org/2005/Atom", "http://purl.org/rss/1.0/",
					"http://purl.org/dc/elements/1.1/" }));

	private boolean isType = false;
	private boolean isFeed = false;
	private boolean isItem = false;
	private boolean isTitle = false;
	private boolean isLink = false;
	private boolean isPubdate = false;
	private boolean isGuid = false;
	private boolean isDescription = false;
	private boolean isContent = false;
	private boolean isSource = false;
	private boolean isEnclosure = false;

	private String mHrefAttribute;
	private String mMimeAttribute;
	private int maxItems = 0;
	private int mNbrItems = 0;
	private StringBuffer mSb;

	public FeedHandler(Context ctx) {
		maxItems = 20;
		for (int i = 0; i < DATE_FORMATS.length; i++) {
			mSimpleDateFormats[i] = new SimpleDateFormat(DATE_FORMATS[i],
					Locale.US);
			mSimpleDateFormats[i].setTimeZone(TimeZone.getTimeZone("GMT"));
		}
	}

	public void startDocument() throws SAXException {
		mFeed = new Feed();
	}

	public void endDocument() throws SAXException {
		Date now = new Date();
		mFeed.setRefresh(now);
	}

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (NAMESPACES.contains(uri)) {
			mSb = new StringBuffer();
			String value = localName.trim();

			if (value.equalsIgnoreCase("rss") || value.equalsIgnoreCase("rdf")) {
				isType = true;
			} else if (value.equalsIgnoreCase("feed")) {
				isType = true;
				isFeed = true;
			} else if (value.equalsIgnoreCase("channel")) {
				isFeed = true;
			} else if (value.equalsIgnoreCase("item")
					|| value.equalsIgnoreCase("entry")) {
				mItem = new Item();
				isItem = true;
				mNbrItems++;
			} else if (value.equalsIgnoreCase("title"))
				isTitle = true;
			else if (value.equalsIgnoreCase("link")) {
				// Get attributes from link element for Atom format
				if (attributes != null) {
					// Enclosure for Atom format
					if (attributes.getValue("rel") != null
							&& attributes.getValue("rel").equalsIgnoreCase(
									"enclosure")) {
						mEnclosure = new Enclosure();
						mMimeAttribute = attributes.getValue("type");
						isEnclosure = true;
					}
					mHrefAttribute = attributes.getValue("href");
				}
				isLink = true;
			} else if (value.equalsIgnoreCase("pubDate")
					|| value.equalsIgnoreCase("published")
					|| value.equalsIgnoreCase("date"))
				isPubdate = true;
			else if (value.equalsIgnoreCase("guid")
					|| value.equalsIgnoreCase("id"))
				isGuid = true;
			else if (value.equalsIgnoreCase("description")
					|| value.equalsIgnoreCase("summary"))
				isDescription = true;
			else if (value.equalsIgnoreCase("encoded")
					|| value.equalsIgnoreCase("content"))
				isContent = true;
			else if (value.equalsIgnoreCase("source"))
				isSource = true;
			else if (value.equalsIgnoreCase("enclosure")) {
				// Enclosure for RSS format
				if (attributes != null) {
					mEnclosure = new Enclosure();
					mMimeAttribute = attributes.getValue("type");
					mHrefAttribute = attributes.getValue("url");
					isEnclosure = true;
				}
			}
		}
	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (NAMESPACES.contains(uri)) {
			String value = localName.trim();

			if (value.equalsIgnoreCase("rss")) {
				mFeed.setType(Feed.TYPE_RSS);
				isType = false;
			} else if (value.equalsIgnoreCase("feed")) {
				mFeed.setType(Feed.TYPE_ATOM);
				isType = false;
				isFeed = false;
			} else if (value.equalsIgnoreCase("RDF")) {
				mFeed.setType(Feed.TYPE_RDF);
				isType = false;
			} else if (value.equalsIgnoreCase("channel")) {
				isFeed = false;
			} else if (value.equalsIgnoreCase("item")
					|| value.equalsIgnoreCase("entry")) {
				if (mNbrItems <= maxItems) {
					if (mItem.getGuid() == null)
						mItem.setGuid(mItem.getLink().toString());
					mFeed.addItem(mItem);
				}
				isItem = false;
			} else if (value.equalsIgnoreCase("title") && !isSource) {
				if (isItem)
					mItem.setTitle(Html.fromHtml(mSb.toString().trim())
							.toString());
				else if (isFeed)
					mFeed.setTitle(Html.fromHtml(mSb.toString().trim())
							.toString());
				isTitle = false;
			} else if (value.equalsIgnoreCase("link") && !isSource) {
				if (isItem) {
					try {
						if (isEnclosure) {
							// Enclosure for Atom format
							mEnclosure.setMime(mMimeAttribute);
							mEnclosure.setURL(new URL(mHrefAttribute));
							mItem.addEnclosure(mEnclosure);
							mMimeAttribute = null;
							isEnclosure = false;
						} else if (mHrefAttribute != null)
							mItem.setLink(new URL(mHrefAttribute));
						else
							mItem.setLink(new URL(mSb.toString().trim()));
					} catch (MalformedURLException mue) {
						throw new SAXException(mue);
					}
				} else if (isFeed && mFeed.getHomePage() == null) {
					try {
						if (mSb != null && mSb.toString() != "") // RSS
							mFeed.setHomePage(new URL(mSb.toString().trim()));
						else if (mMimeAttribute == "text/html") // Atom
							mFeed.setHomePage(new URL(mHrefAttribute));
					} catch (MalformedURLException mue) {
						throw new SAXException(mue);
					}
				}
				mHrefAttribute = null;
				isLink = false;
			} else if (value.equalsIgnoreCase("pubDate")
					|| value.equalsIgnoreCase("published")
					|| value.equalsIgnoreCase("date")) {
				if (isItem) {
					for (int i = 0; i < DATE_FORMATS.length; i++) {
						try {

							mItem.setPubdate(mSimpleDateFormats[i].parse(mSb
									.toString().trim()));
							break;
						} catch (ParseException pe) {
							if (i == DATE_FORMATS.length - 1) {
								throw new SAXException(pe);
							}
						}
					}
				}
				isPubdate = false;
			} else if ((value.equalsIgnoreCase("guid") || value
					.equalsIgnoreCase("id")) && !isSource) {
				if (isItem)
					mItem.setGuid(mSb.toString().trim());
				isGuid = false;
			} else if (value.equalsIgnoreCase("description")
					|| value.equalsIgnoreCase("summary")) {
				if (isItem)
					mItem.setContent(removeContentSpanObjects(mSb).toString()
							.trim() + System.getProperty("line.separator"));
				isDescription = false;
			} else if (value.equalsIgnoreCase("encoded")
					|| value.equalsIgnoreCase("content")) {
				if (isItem)
					mItem.setContent(removeContentSpanObjects(mSb).toString()
							.trim() + System.getProperty("line.separator"));
				isContent = false;
			} else if (value.equalsIgnoreCase("source"))
				isSource = false;
			else if (value.equalsIgnoreCase("enclosure")) {
				if (isItem) {
					try {

						mEnclosure.setMime(mMimeAttribute);
						mEnclosure.setURL(new URL(mHrefAttribute));
						mItem.addEnclosure(mEnclosure);
						mMimeAttribute = null;
						mHrefAttribute = null;
					} catch (MalformedURLException mue) {
						throw new SAXException(mue);
					}
				}
				isEnclosure = false;
			}
		}
	}

	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (isType || isTitle || isLink || isPubdate || isGuid || isDescription
				|| isContent)
			mSb.append(new String(ch, start, length));
	}

	public Feed handleFeed(URL url) throws IOException, SAXException,
			ParserConfigurationException {
		getParser().parse(new InputSource(url.openStream()));

		Collections.reverse(mFeed.getItems());
		mFeed.setURL(url);
		if (mFeed.getHomePage() == null)
			mFeed.setHomePage(url);
		return mFeed;
	}

	private XMLReader getParser() throws SAXException,
			ParserConfigurationException {
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp = spf.newSAXParser();
		XMLReader xr = sp.getXMLReader();
		xr.setContentHandler(this);
		return xr;
	}

	private Spanned removeContentSpanObjects(StringBuffer sb) {
		SpannableStringBuilder spannedStr = (SpannableStringBuilder) Html
				.fromHtml(sb.toString().trim());
		Object[] spannedObjects = spannedStr.getSpans(0, spannedStr.length(),
				Object.class);
		for (int i = 0; i < spannedObjects.length; i++) {

			if (spannedObjects[i] instanceof ImageSpan)
				spannedStr.replace(spannedStr.getSpanStart(spannedObjects[i]),
						spannedStr.getSpanEnd(spannedObjects[i]), "");

		}

		return spannedStr;
	}
}
