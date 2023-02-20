package gmbs.dto;

import gmbs.model.BlackJackResult;
import gmbs.model.Card;
import gmbs.model.black_jack_enum.Result;
import gmbs.model.players.Player;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ResultDTO {

    private final String name;
    private final String cards;
    private final String result;
    private final int cardSum;

    public ResultDTO(Player player, BlackJackResult result) {
        this.name = player.getName();
        this.cards = extractCards(player);
        this.result = extractResults(result);
        this.cardSum = result.getResultHandSum();
    }

    private String extractResults(BlackJackResult result) {
        return result.getResultsData()
                .entrySet()
                .stream()
                .filter(a -> a.getValue() > 0)
                .map(extractResult())
                .collect(Collectors.joining());
    }

    private static Function<Map.Entry<Result, Integer>, String> extractResult() {
        return a -> a.getValue().toString() + a.getKey().getDescription();
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
