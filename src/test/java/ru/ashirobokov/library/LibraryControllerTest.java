package ru.ashirobokov.library;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.ashirobokov.library.model.Author;
import ru.ashirobokov.library.model.Book;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.ashirobokov.library.utils.TestUtils.objectToJson;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
//public class LibraryControllerTest extends AbstractTestNGSpringContextTests {
public class LibraryControllerTest extends AbstractTransactionalTestNGSpringContextTests {

    private String appBasicUrl;
    private Author testAuthor1;

    @Autowired
    private MockMvc mockMvc;

    @BeforeTest
    public void setUp() {
        logger.info("LibraryControllerTest started .. ");
        appBasicUrl = "http://localhost:8085/library/";
//  Add some testing data
        testAuthor1 = new Author("Test1", "Test-test1");
        Book testAuthorBook1 = new Book("TestAuthor1Book1");
        Book testAuthorBook2 = new Book("TestAuthor1Book2");

        Author testAuthor2 = new Author("Test2", "Test-test2");
        Book testAuthor2Book1 = new Book("TestAuthor2Book1");
        Book testAuthor2Book2 = new Book("TestAuthor2Book2");

    }

    @Test(enabled = true)
    public void testHome() throws Exception {
        logger.info("LibraryControllerTest/testHome started ... ");

        mockMvc.perform(get(appBasicUrl +"test2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value("test2, test2!")
        );
    }

    @Test(enabled = true)
    public void testHome2() throws Exception {
        logger.info("LibraryControllerTest/testHome2 started ... ");

        ResultActions result = mockMvc.perform(get(appBasicUrl +"test2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        logger.info("testHome2 returned = " + result.andReturn().getResponse().getContentAsString());
    }

    @Test(enabled = true)
    @Rollback(value = false)
    public void testCreateAuthor() {
        logger.info("LibraryControllerTest/testCreateAuthor started ... ");

        try {
            mockMvc.perform(post(appBasicUrl + "authors")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(objectToJson(testAuthor1)))
    //        .header("Authorization", getBasicAuthHeader("John", "secr3t")))
                    .andExpect(status().isOk());

        } catch (Exception e) {
            logger.error("LibraryControllerTest/testCreateAuthor error ", e);
        }
    }

    @AfterTest
    public void tearDown() {
        logger.info("/LibraryControllerTest");
    }

}
