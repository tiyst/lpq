package st.tiy.lpq;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class SoundsWebParser {
	private String defaultUrl = "https://leagueoflegends.fandom.com";		// Url stem, all relative hrefs are from that url
	private String url = defaultUrl + "/wiki/Category:Audio";				// The main page for Audio
	private String browser = "Mozilla";										// Which browser we are using
	private Document doc = Jsoup.connect(url).userAgent(browser).get();		// Getting the main page
	Elements links = doc.select("a");								// Getting all the links
	public SoundsWebParser() throws IOException {
	}
	// moves to an url from the https://leagueoflegends.fandom.com"
	public void relativeUrl(String url) throws IOException {
		this.url = defaultUrl + url;										// Making url out of relative link
		doc = Jsoup.connect(this.url).userAgent(browser).get();				// Getting the page data
		links = doc.select("a");									// Getting all the links
	}
	// goes back to the main audio webpage
	public void resetUrl() throws IOException {
		this.url = defaultUrl + "/wiki/Category:Audio";
		doc = Jsoup.connect(url).userAgent(browser).get();
		links = doc.select("a");
	}
	// returns a specific element (link)
	public Element getLink(String caption) {
		for (Element element: this.links) {
			if (Objects.equals(element.text(), caption)) {				// returning the link that matches the input string
				return element;
			}
		}
		return null;													// return null if no links are matched
	}
	// returns all elements (links) containing a substring specified by user
 	public Elements getLinks(String category) {
		Elements inCategory = new Elements();
		for (Element element: this.links) {
			if (element.text().contains(category)) {   				// returning links that contain the input string
				inCategory.add(element);
			}
		}
		return inCategory;
	}
}
