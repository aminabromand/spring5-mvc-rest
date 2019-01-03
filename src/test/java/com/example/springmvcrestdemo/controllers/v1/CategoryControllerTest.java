package com.example.springmvcrestdemo.controllers.v1;

import com.example.springmvcrestdemo.api.v1.model.CategoryDTO;
import com.example.springmvcrestdemo.api.v1.model.CustomerDTO;
import com.example.springmvcrestdemo.controllers.RestResponseEntityExceptionHandler;
import com.example.springmvcrestdemo.domain.Category;
import com.example.springmvcrestdemo.services.CategoryService;
import com.example.springmvcrestdemo.services.ResourceNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CategoryControllerTest {

    public static final String NAME = "Jim";
    public static final long ID = 1L;
    public static final String NAME2 = "Bob";
    public static final long ID2 = 2L;
    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoryController categoryController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void testListCategories() throws Exception {
        CategoryDTO category1 = new CategoryDTO();
        category1.setId(ID);
        category1.setName(NAME);

        CategoryDTO category2 = new CategoryDTO();
        category2.setId(ID2);
        category2.setName(NAME2);

        List<CategoryDTO> categories = Arrays.asList(category1, category2);

        when(categoryService.getAllCategories()).thenReturn(categories);

        mockMvc.perform(get(CategoryController.BASE_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories", hasSize(categories.size())));
    }

    @Test
    public void testGetByNameCategories() throws Exception {
        CategoryDTO category1 = new CategoryDTO();
        category1.setId(1L);
        category1.setName(NAME);

        when(categoryService.getCategoryByName(anyString())).thenReturn(category1);

        mockMvc.perform(get(CategoryController.BASE_URL + "/" + NAME).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)));
    }

    @Test
    public void testGetByNameNotFound() throws Exception {
        when(categoryService.getCategoryByName(anyString())).thenThrow(ResourceNotFoundException.class);
        mockMvc.perform(get(CategoryController.BASE_URL + "/foo")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}