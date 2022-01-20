/**  
* Manager.java - Class used to manage Media Rental System
*/

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Manager {

	private List<Media> mediaList = new ArrayList<Media>();

	/**
	 * Manager Constructor
	 */
	public Manager() {

	}
	
	/**
	 * Retrieve Media List. 
	 *  
	 * @return A data type of List<Media>. 
	 */
	public List<Media> getMediaList() {
		return mediaList;
	}

	/**
	 * Set Media List. 
	 *  
	 * @param mediaList - A variable type of List<Media>. 
	 */
	public void setMediaList(List<Media> mediaList) {
		this.mediaList = mediaList;
	}
	
	/**
	 * Method to load Media objects from XML file. 
	 * User provides file path within the Media Rental System user interface.  
	 *  
	 * @param filepath - A variable type of String.
	 * @return A data type of Document. 
	 */
	public Document loadMediaFile(String filepath) {
		File file = new File(filepath + "db.xml");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
			return doc;
		} catch (ParserConfigurationException | SAXException | IOException e) {
			return null;
		}
	}
	
	/**
	 * Static method used to save Media objects to an XML file.
	 * This static method is used inside rentMedia(). 
	 *  
	 * @param doc - A variable type of Document.
	 * @param filepath - A variable type of String.
	 */
	static private void saveMediaFile(Document doc, String filepath) {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer;
		try {
			transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath + "db.xml"));
			transformer.transform(source, result);
			System.out.println("Database saved successfully.");
		} catch (TransformerException e) {
			System.out.println("Database failed to save.");
		}

	}

	/**
	 * Static method used to update Media objects in the XML file, specifically the media object "availability". 
	 * This static method is used inside rentMedia(). 
	 *  
	 * @param doc - A variable type of Document.
	 * @param update - A variable type of String.
	 * @return A data type of Document.
	 */
	static private Document updateXML(Document doc, String update) {
		NodeList movieDVDs = doc.getElementsByTagName("MovieDVD");
		NodeList musicCDs = doc.getElementsByTagName("MusicCD");
		NodeList eBooks = doc.getElementsByTagName("EBook");
		if (movieDVDs.getLength() > 0) {
			Integer movieDVDsLength = movieDVDs.getLength();
			for (int i = 0; i < movieDVDsLength; i++) {
				String mdID = movieDVDs.item(i).getChildNodes().item(1).getTextContent();
				if (mdID.equals(update)) {
					movieDVDs.item(i).getChildNodes().item(9).setTextContent("false");
				}
			}
		}
		if (musicCDs.getLength() > 0) {
			Integer musicCDsLength = musicCDs.getLength();
			for (int i = 0; i < musicCDsLength; i++) {
				String mcdID = musicCDs.item(i).getChildNodes().item(1).getTextContent();
				if (mcdID.equals(update)) {
					musicCDs.item(i).getChildNodes().item(9).setTextContent("false");
				}
			}
		}
		if (eBooks.getLength() > 0) {
			Integer eBooksLength = eBooks.getLength();
			for (int i = 0; i < eBooksLength; i++) {
				String ebID = eBooks.item(i).getChildNodes().item(1).getTextContent();
				if (ebID.equals(update)) {
					eBooks.item(i).getChildNodes().item(9).setTextContent("false");
				}
			}
		}

		return doc;

	}
	
	/**
	 * Method used to to rent Media by Id and return rentalFee. 
	 * Uses two static methods: updateXML() and saveMediaFile().
	 *  
	 * @param doc - A variable type of Document.
	 * @param input - A variable type of String.
	 * @param filepath - A variable type of String.
	 * @return A data type of Double. 
	 */
	public Double rentMedia(Document doc, String input, String filepath) {
		Double rentalFee = 0.0;
		List<Media> mediaList = getMediaList();
		Boolean foundMedia = false;
		
		for (Media media : mediaList) {
			if (media.getId().equals(input)) {
				foundMedia = true;
				if (media.getAvailable() == true) {
					media.setAvailable(false);
					print(media.toString());
					rentalFee = media.calculateRentalFee();
					doc = updateXML(doc, input);
					saveMediaFile(doc, filepath);
				} else {
					print("Sorry, " + input + " is not available for rent at this time.");
				}
			}
		}
		
		if (foundMedia == false) {
			print("No Media found with the ID of: " + input);
		}
		setMediaList(mediaList);
		return rentalFee;
	}


	/**
	 * Method used to find all media objects for a specific title and returns a list.
	 *  
	 * @param input - A variable type of String.
	 * @return A data type of List<String>. 
	 */
	public List<String> findMedia(String input) {
		List<String> foundItems = new ArrayList<String>();
		for (Media media : mediaList) {
			if (media.getTitle().equals(input)) {
				foundItems.add(media.toString());
			}
		}
		return foundItems;
	}

	/**
	 * Method used to convert all media objects from the XML database to a List of Media Objects. 
	 * Uses setMediaList() and getMediaList() methods. 	
	 *    
	 * @param doc - A variable type of Document.
	 * @return A data type of List<Media>. 
	 */
	public List<Media> xmlToList(Document doc) {
		List<Media> mediaList = new ArrayList<Media>();
		if(doc != null) {
			NodeList movieDVDs = doc.getElementsByTagName("MovieDVD");
			NodeList musicCDs = doc.getElementsByTagName("MusicCD");
			NodeList eBooks = doc.getElementsByTagName("EBook");

			if (movieDVDs.getLength() > 0) {
				for (int i = 0; i < movieDVDs.getLength(); i++) {
					String mdID = movieDVDs.item(i).getChildNodes().item(1).getTextContent();
					String mdTitle = movieDVDs.item(i).getChildNodes().item(3).getTextContent();
					String mdYear = movieDVDs.item(i).getChildNodes().item(5).getTextContent();
					String mdSize = movieDVDs.item(i).getChildNodes().item(7).getTextContent();
					String mdAvailable = movieDVDs.item(i).getChildNodes().item(9).getTextContent();
					Media md = new MovieDVD(mdID, mdTitle, Integer.valueOf(mdYear), Double.valueOf(mdSize), Boolean.valueOf(mdAvailable));
					mediaList.add(md);
				}
			}
			
			if (musicCDs.getLength() > 0) {
				for (int i = 0; i < musicCDs.getLength(); i++) {
					String mcdID = musicCDs.item(i).getChildNodes().item(1).getTextContent();
					String mcdTitle = musicCDs.item(i).getChildNodes().item(3).getTextContent();
					String mcdYear = musicCDs.item(i).getChildNodes().item(5).getTextContent();
					String mcdLength = musicCDs.item(i).getChildNodes().item(7).getTextContent();
					String mcdAvailable = musicCDs.item(i).getChildNodes().item(9).getTextContent();
					Media mcd = new MusicCD(mcdID, mcdTitle, Integer.valueOf(mcdYear), Integer.valueOf(mcdLength), Boolean.valueOf(mcdAvailable));
					mediaList.add(mcd);
				}
			}
			
			if (eBooks.getLength() > 0) {
				for (int i = 0; i < eBooks.getLength(); i++) {
					String ebID = eBooks.item(i).getChildNodes().item(1).getTextContent();
					String ebTitle = eBooks.item(i).getChildNodes().item(3).getTextContent();
					String ebYear = eBooks.item(i).getChildNodes().item(5).getTextContent();
					String ebChapters = eBooks.item(i).getChildNodes().item(7).getTextContent();
					String ebAvailable = eBooks.item(i).getChildNodes().item(9).getTextContent();
					Media eb = new EBook(ebID, ebTitle, Integer.valueOf(ebYear), Integer.valueOf(ebChapters), Boolean.valueOf(ebAvailable));
					mediaList.add(eb);
				}
			}
			setMediaList(mediaList);
			return getMediaList();	
		}else {
			return null;
		}

	}

	/**
	 * Method used to shorten print statement, similar to Python.
	 * 
	 * @param string - A variable type of String.
	 */
	public static void print(String string) {
		System.out.println(string);
	}
}