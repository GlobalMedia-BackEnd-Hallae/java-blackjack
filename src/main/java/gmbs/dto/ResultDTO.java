package gmbs.dto;

import gmbs.model.BlackJackResult;
import gmbs.model.Card;
import gmbs.model.players.Player;

import java.util.stream.Collectors;

public class ResultDTO {

    private final String name;
    private final String cards;
    private final String result;
    private final int cardSum;

    public ResultDTO(Player player, BlackJackResult result) {
        this.name = player.getName();
        this.cards = extractCards(player);
        this.result = result.getResult()
                .getDescription();
        this.cardSum = result.getResultHandSum();
    }

    private String extractCards(Player player) {
        return player.getCardHand()
                .getCards()
                .stream()
                .map(Card::getName)
                .collect(Collectors.joining());
    }

    public String getName() {
        return name;
    }

    public String getCards() {
        return cards;
    }

    public String getResult() {
        return result;
    }

    public int getCardSum() {
        return cardSum;
    }
}
