package com.example.cats;

import com.example.cats.Model.Cat;
import com.example.cats.Repository.CatRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
public class CatControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    CatRepository repository;

    Cat cat = new Cat();

    @BeforeEach
    public void initCat(){
        this.cat.setName("Small Cat");
        this.cat.setBreed("Maine Coon");
        this.cat.setAge(3);
        this.cat.setAdopted(false);
    }

    @Transactional
    @Rollback
    @Test
    public void testCreate() throws Exception{

        ObjectMapper objectMapper = new ObjectMapper();

        RequestBuilder request = post("/cats")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(this.cat));

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.age", equalTo(3)));
    }

    @Transactional
    @Rollback
    @Test
    public void testRead() throws Exception{

        Cat test = this.repository.save(this.cat);

        RequestBuilder request = get("/cats/"+test.getId());

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.age", equalTo(3)));
    }

    @Transactional
    @Rollback
    @Test
    public void testUpdate() throws Exception{

        Cat updatedCat = new Cat();
        updatedCat.setAge(5);

        ObjectMapper objectMapper = new ObjectMapper();

        Cat test = this.repository.save(this.cat);

        RequestBuilder request = patch("/cats/"+test.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedCat));

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.age", equalTo(5)))
                .andExpect(jsonPath("$.name", equalTo("Small Cat")));
    }

    @Transactional
    @Rollback
    @Test
    public void testDelete() throws Exception{

        Cat test = this.repository.save(this.cat);

        RequestBuilder request = delete("/cats/"+test.getId());

        MvcResult result = this.mvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(result.getResponse().getContentAsString(), "Deleted");
    }

    @Transactional
    @Rollback
    @Test
    public void testList() throws Exception{

        Cat cat2 = new Cat();
        cat2.setName("Garfield");
        cat2.setBreed("Maine Coon");
        cat2.setAge(10);
        cat2.setAdopted(false);

        this.repository.save(this.cat);
        this.repository.save(cat2);

        RequestBuilder request = get("/cats");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].age", equalTo(10)));
    }
}
