package com.ascend.training.j4qa.structure.services;

import com.ascend.training.j4qa.structure.entities.Toy;
import com.ascend.training.j4qa.structure.exceptions.ToyNotFoundException;
import com.ascend.training.j4qa.structure.repositories.ToyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ToyServiceTest {

    @Autowired
    private ToyService toyService;

    @MockBean
    private ToyRepository toyRepository;

    @Test
    public void whenValidId_thenToyShouldBeFound() {
        Toy expected = new Toy("LEGO", "60157", "Jungle Starter Set");
        expected.setId(1L);
        when(toyRepository.findOne(1L)).thenReturn(expected);

        Toy actual = toyService.getToy(1L);
        assertThat(actual).isEqualTo(expected);
    }

    @Test(expected = ToyNotFoundException.class)
    public void whenInvalidId_thenShouldThrowException() {
        when(toyRepository.findOne(1L)).thenReturn(null);

        toyService.getToy(1L);
    }
}
