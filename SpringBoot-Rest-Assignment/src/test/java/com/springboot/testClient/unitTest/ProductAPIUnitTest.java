package com.springboot.testClient.unitTest;

import com.springboot.controller.ProductRestController;
import com.springboot.service.ProductService;
import com.springboot.util.Constants;
import com.springboot.vo.Product;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;

//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ProductAPIUnitTest {

    @Mock
    ProductService productService;

    @Mock
    Constants messages;

    @Before
    public void configMock() {
        MockitoAnnotations.initMocks(this);
        RestAssuredMockMvc.standaloneSetup(new ProductRestController(productService, messages));
        Mockito.when(messages.getMessage(anyObject())).thenReturn("Mocked Message");
    }

    @Test
    public void testGetProductFromDB(){
        Mockito.when(productService.findById(anyLong())).thenReturn(new Product(1l,"test name", 20, 10000d));
        given().accept("application/json").get("/api/Product/1").peek().
                then().assertThat()
                .statusCode(200)
                .body(Matchers.equalTo("{\"id\":1,\"name\":\"test name\",\"age\":20,\"price\":10000.0}"));
    }

    @Test
    public void testGetProductFromEmptyDB(){
        Mockito.when(productService.findById(anyInt())).thenReturn(null);
        given().accept("application/json").get("/api/product/1").peek().
                then().assertThat()
                .statusCode(404)
                .body("errorCode",Matchers.equalTo(404));
    }

    @Test
    public void createProduct(){
        Product testProduct = new Product(null,"test name", 20, 10000d);
        Product savedProduct = new Product(1l,"test name", 20, 10000d);
        Mockito.when(productService.saveProduct(any())).thenReturn(savedProduct);
        given().accept("application/json").contentType("application/json").body(testProduct).post("/api/Product").peek().
                then().assertThat()
                .statusCode(201)
                .body("data.id",Matchers.is(1));
    }

    @Test
    public void createProductButExceptionRaised(){
        Product testProduct = new Product(null,"test name", 20, 10000d);
        Product savedProduct = new Product(1l,"test name", 20, 10000d);
        Mockito.when(productService.saveProduct(anyObject())).thenThrow(new RuntimeException("dummy error"));
        given().accept("application/json").contentType("application/json").body(testProduct).post("/api/Product").peek().
                then().assertThat()
                .statusCode(500)
                .body("message",Matchers.is("dummy error"));
    }
}
