package de.uni_passau.facultyinfo.server.test;

import java.util.Date;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import de.uni_passau.facultyinfo.server.dto.News;

@RunWith(JUnit4.class)
public class NewsTest {

	@Test
	public void testNews() {
		String id = UUID.randomUUID().toString();
		String title = "Testtitle";
		String description = "Testdescription";
		String url = "http://www.abc.de/def/ghi.php?jkl=mno";
		String text = "TestTestTest TestTestTest TestTestTest TestTestTest TestTestTest TestTestTest TestTestTest TestTestTest TestTestTest TestTestTest TestTestTest TestTestTest TestTestTest TestTestTest TestTestTest TestTestTest TestTestTest TestTestTest ";
		Date publicationDate = new Date(2013, 11, 12);

		News news = new News(id, title, description, url, text, publicationDate);

		Assert.assertEquals("Getter for Id not working correctly.",
				news.getId(), id);
		Assert.assertEquals("Getter for Title not working correctly.",
				news.getTitle(), title);
		Assert.assertEquals("Getter for Description not working correctly.",
				news.getDescription(), description);
		Assert.assertEquals("Getter for Url not working correctly.",
				news.getUrl(), url);
		Assert.assertEquals("Getter for Text not working correctly.",
				news.getText(), text);
		Assert.assertEquals(
				"Getter for Publication Date not working correctly.",
				news.getPublicationDate(), publicationDate);
	}

}
