package gmbs.utils;

import gmbs.domain.card.Card;
import gmbs.domain.card.Cards;
import gmbs.domain.card.CardNumber;
import gmbs.domain.card.CardPattern;

import java.util.ArrayList;
import java.util.List;

public class TestUtil {

    public static Cards getCards(CardNumber... arguments) {
        List<Card> list = new ArrayList<>();
        for (CardNumber cardNumber : arguments) {
            list.add(new Card(cardNumber, CardPattern.CLOVER));
        }
        return new Cards(list);
    }

}
