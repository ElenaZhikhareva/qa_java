import com.example.Feline;
import com.example.Lion;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@RunWith(Parameterized.class)
public class LionTest {

    @Parameterized.Parameter()
    public String sex;
    @Parameterized.Parameter(1)
    public boolean expected;

    @Mock
    Feline feline;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Parameterized.Parameters(name = "Sex: {0}, expected: {1} ")
    public static Object[][] params() {
        return new Object[][]{
                {"Самец", true},
                {"Самка", false}
        };
    }

    @Test
    public void haveManeTest() throws Exception {
        Lion lion = new Lion(sex, feline);
        assertEquals("Используйте допустимые значения пола животного - самец или самка", expected, lion.doesHaveMane());
    }

    @Test
    public void getKittensTest() throws Exception {
        Lion lion = new Lion("Самец", feline);
        Mockito.when(feline.getKittens()).thenReturn(1);
        assertEquals(1, lion.getKittens());
    }

    @Test
    public void getFoodTest() throws Exception {
        List<String> food = List.of("Животные", "Птицы", "Рыба");
        Mockito.when(feline.getFood("Хищник")).thenReturn(food);
        Lion lion = new Lion("Самец", feline);
        assertEquals(food, lion.getFood());
    }

    @Test
    public void exceptionTest() {
        Exception actualException = assertThrows(Exception.class, () -> new Lion("--", feline));
        assertEquals("Используйте допустимые значения пола животного - самец или самка", actualException.getMessage());
    }
}
