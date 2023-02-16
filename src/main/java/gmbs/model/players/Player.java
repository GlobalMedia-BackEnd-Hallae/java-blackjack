package gmbs.model.players;

import gmbs.model.CardHand;

public interface Player {

    Player draw(CardHand drawHand);
    CardHand getCardHand();

}
