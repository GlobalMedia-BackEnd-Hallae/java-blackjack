package gmbs.model.players;

import gmbs.model.Card;
import gmbs.model.CardHand;

public interface Player {

    Player hit(Card card);
    CardHand getCardHand();
}
