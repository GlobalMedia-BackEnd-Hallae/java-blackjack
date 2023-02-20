package gmbs.model.players;

import gmbs.model.CardHand;

public interface Player {

    Player draw(final CardHand drawHand);

    CardHand getCardHand();

    String getName();
}
