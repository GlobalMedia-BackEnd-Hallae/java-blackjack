package gmbs.domain.game;

import gmbs.domain.card.Card;
import gmbs.domain.card.CardDistributor;
import gmbs.domain.card.Cards;
import gmbs.domain.participant.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackGame {

    public static final String DEALER_NAME = "딜러";
    private static final int INIT_CARD_COUNT = 2;

    private final Participants participants;
    private final CardDistributor cardDistributor = new CardDistributor();

    public BlackjackGame(List<Name> names) {
        Dealer dealer = new Dealer(new Name(DEALER_NAME), drawInitialCards());
        List<Player> players = initializePlayers(new ArrayList<>(names));
        this.participants = new Participants(players, dealer);
    }

    private List<Player> initializePlayers(List<Name> names) {
        return names.stream()
                .map(name -> new Player(name, drawInitialCards()))
                .collect(Collectors.toUnmodifiableList());
    }

    private Cards drawInitialCards() {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < INIT_CARD_COUNT; i++) {
            cards.add(cardDistributor.distribute());
        }
        return new Cards(cards);
    }

    public void drawCard(Participant participant) {
        participants.drawCard(participant, cardDistributor.distribute());
    }

    public GameResult createGameResult() {
        return new GameResult(participants.getPlayers(), participants.getDealer());
    }

    public Participants getParticipants() {
        return participants;
    }

    @Override
    public String toString() {
        return "BlackjackGame{" +
                "participants=" + participants +
                ", cardDistributor=" + cardDistributor +
                '}';
    }
}
