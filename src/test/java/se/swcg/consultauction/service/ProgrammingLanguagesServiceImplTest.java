package se.swcg.consultauction.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import se.swcg.consultauction.dto.ProgrammingLanguagesDto;
import se.swcg.consultauction.entity.ProgrammingLanguages;
import se.swcg.consultauction.exception.ResourceNotFoundException;
import se.swcg.consultauction.repository.ProgrammingLanguagesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProgrammingLanguagesServiceImplTest {

    @TestConfiguration
    static class PrePopLanguagesServiceImplTestContextConfiguration {
        @Bean
        public ProgrammingLanguagesService prePopLanguagesService() {
            return new ProgrammingLanguagesServiceImpl();
        }
    }

    @Autowired
    private ProgrammingLanguagesService programmingLanguagesService;

    @MockBean
    private ProgrammingLanguagesRepository repo;

    ProgrammingLanguages java;
    ProgrammingLanguagesDto javaDto;


    @BeforeEach
    void setUp() {
        java = new ProgrammingLanguages("0","Java");
        javaDto = new ProgrammingLanguagesDto("0", "Java");
    }

    @Test
    void test_findAll_should_return_list_with_size_of_1() {
        List<ProgrammingLanguages> list = new ArrayList<>();
        list.add(java);

        when(repo.findAll()).thenReturn(list);

        List<ProgrammingLanguagesDto> found = programmingLanguagesService.findAll();
        int size = 1;

        assertEquals(size, found.size());
        assertTrue(found.get(0).getLanguage().contains("Java"));
    }

    @Test
    void test_findAll_should_return_empty_list_and_throw_exception() {
        List<ProgrammingLanguages> emptyList = new ArrayList();

        when(repo.findAll()).thenReturn(emptyList);

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> programmingLanguagesService.findAll());

        String expectedMessage = "Could not find";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void test_findById_with_valid_id_should_return_language() {
        when(repo.findById(anyString())).thenReturn(Optional.of(java));

        ProgrammingLanguagesDto found = programmingLanguagesService.findById(java.getLanguagesId());

        String expectedLanguage = "Java";
        String actualLanguage = found.getLanguage();

        assertEquals(expectedLanguage, actualLanguage);
    }

    @Test
    void test_findById_with_inValid_id_should_throw_exception() {
        when(repo.findById(anyString())).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> programmingLanguagesService.findById(java.getLanguagesId()));


        String expectedMessage = "Could not find";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void test_create_should_return_valid_dto() {

        when(repo.save(any(ProgrammingLanguages.class))).thenReturn(java);

        ProgrammingLanguagesDto found = programmingLanguagesService.create(javaDto);

        assertThat(javaDto.getLanguage().equals(found.getLanguage()));
    }

    @Test
    void test_update_should_return_valid_dto() {
        when(repo.save(any(ProgrammingLanguages.class))).thenReturn(java);
        when(repo.findById(anyString())).thenReturn(Optional.of(java));

        ProgrammingLanguagesDto found = programmingLanguagesService.update(javaDto);

        assertThat(found.getLanguage().contains(javaDto.getLanguage()));
    }

    @Test
    void test_update_with_inValid_object_should_throw_exception() {
        when(repo.save(any(ProgrammingLanguages.class))).thenReturn(java);

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> programmingLanguagesService.update(javaDto));

        String expectedMessage = "Could not find";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void test_delete_with_valid_fields_should_return_true() {
        when(repo.findById(anyString()))
                .thenReturn(Optional.of(java))
                .thenReturn(Optional.empty());

        boolean result = programmingLanguagesService.delete(javaDto.getLanguagesId());

        assertTrue(result);
    }

    @Test
    void test_delete_with_inValid_fields_should_return_false() {
        when(repo.findById(anyString()))
                .thenReturn(Optional.of(java));

        boolean result = programmingLanguagesService.delete(javaDto.getLanguagesId());

        assertFalse(result);
    }
}