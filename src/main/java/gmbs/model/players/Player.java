package gmbs.model.players;

import gmbs.model.first_class_collection.CardHand;

public interface Player {

    Player draw(final CardHand drawHand);

    CardHand getCardHand();

    String getName();
}
