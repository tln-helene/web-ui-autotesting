package org.gb.lesson4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class BoxTest {
    Box box;

    @Nested
    class WhenEmptyBox {
        @BeforeEach
        void createEmptyBox() {
            box = new Box();
        }

        @Test
        void whenDeleteBallThenException() {
            Assertions.assertThrows(BoxIsEmptyException.class, () -> box.deleteBall());
            // проверяем, что при выполнении box.deleteBall()) для пустой коробки будет исключение
        }

        @Nested
        class WhenOneBall {
            @BeforeEach
            void addBall() {
                box.addBall();
            }

            @Test
            void deleteBall() throws BoxIsEmptyException {
                box.deleteBall();
                Assertions.assertEquals(0, box.getBallsCount());
                // проверяем, что после выполнения box.deleteBall()) в исходно непустой коробке с 1 мячом останется 0 мячей
            }
        }
    }
}
