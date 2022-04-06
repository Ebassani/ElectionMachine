package com.github.ebassani.electionmachine;

import com.github.ebassani.electionmachine.servlet.Login;
import org.junit.Assert;
import org.junit.Test;

public class HelloAppEngineTest {

  @Test
  public void test() throws Exception {
    MockHttpServletResponse response = new MockHttpServletResponse();
    new Login().doPost(null, response);
    Assert.assertEquals("text/plain", response.getContentType());
    Assert.assertEquals("UTF-8", response.getCharacterEncoding());
    Assert.assertEquals("Hello App Engine!\r\n", response.getWriterContent().toString());
  }
}
