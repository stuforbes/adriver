package uk.co.stfo.adriver.util;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.openqa.selenium.By;

import uk.co.stfo.adriver.util.ByUtils;

public class ByUtilsTest {

    @Test
    public void asStringReturnsFriendlyText() {

        assertThat(ByUtils.asString(By.tagName("div")), is("by tagName=div"));
        assertThat(ByUtils.asString(By.id("an-id")), is("by id=an-id"));
        assertThat(ByUtils.asString(By.className("a-class")), is("by className=a-class"));
        assertThat(ByUtils.asString(By.cssSelector(".class")), is("by selector=.class"));
        assertThat(ByUtils.asString(By.linkText("a-link")), is("by linkText=a-link"));
        assertThat(ByUtils.asString(By.name("a-name")), is("by name=a-name"));
        assertThat(ByUtils.asString(By.xpath("//div[@id='an-id']")), is("by xpath=//div[@id='an-id']"));
    }
}
