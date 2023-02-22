package gmbs.domain.participant;

import gmbs.domain.card.Cards;
import gmbs.domain.card.CardStatus;

public class Player extends Participant {

    public Player(Name name, Cards cards) {
        super(name, cards);
    }

    @Override
    public boolean isFinished() {
        return cards.getStatus() != CardStatus.NONE;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name=" + name +
                ", cards=" + cards +
                '}';
    }
}
