package org.kodluyoruz.mybank;

import org.junit.jupiter.api.Test;
import org.kodluyoruz.mybank.card.Card;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MybankApplicationTests {

    @Test
    void testBoundaryEqual() {
        Card card=new Card();
        assertEquals(2000,card.getBoundary());
    }
}