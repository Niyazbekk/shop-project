package com.example.shopproject;
import com.example.shopproject.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class AdminControllerTest {
    private final MockMvc mockMvc;

    @Autowired
    private AdminControllerTest(MockMvc mockMvc){
        this.mockMvc = mockMvc;
    }

    @MockBean
    ProductRepository productRepository;

    @Test
    void getProducts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product/all"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}


