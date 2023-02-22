package gmbs.domain.game;

import gmbs.domain.card.Cards;
import gmbs.domain.card.CardStatus;
import gmbs.domain.participant.Dealer;
import gmbs.domain.participant.Player;

import java.util.*;

public class GameResult {

    private final Map<Player, MatchResult> gameResult = new LinkedHashMap<>();

    public GameResult(List<Player> players, Dealer dealer) {
        initGameResult(new ArrayList<>(players), dealer);
    }

    private void initGameResult(List<Player> players, Dealer dealer) {
        for (Player player : players) {
            gameResult.put(player, playResult(player, dealer));
        }
    }

    private MatchResult playResult(Player player, Dealer dealer) {
        Cards playerCards = player.getCards();
        Cards dealerCards = dealer.getCards();

        if (compareCards(playerCards, dealerCards)) {
            return MatchResult.WIN;
        }

        if (compareCards(dealerCards, playerCards)) {
            return MatchResult.LOSE;
        }

        return MatchResult.PUSH;
    }

    private boolean compareCards(Cards cards1, Cards cards2) {
        CardStatus cardStatus1 = cards1.getStatus();
        CardStatus cardStatus2 = cards2.getStatus();

        return (cardStatus1 == CardStatus.BLACKJACK && cardStatus2 != CardStatus.BLACKJACK)
                || (cardStatus1 != CardStatus.BUST && cardStatus2 == CardStatus.BUST)
                || (cardStatus1 == CardStatus.NONE && cardStatus2 == CardStatus.NONE && cards1.sum() > cards2.sum());
    }

    public MatchResult getMatchResult(Player player) {
        return gameResult.get(player);
    }

    public long calculateDealerMatchResultCount(MatchResult matchResult) {
        long matchCount = getMatchResultCount(matchResult);

        if (matchResult == MatchResult.PUSH) {
            return matchCount;
        }
        return gameResult.size() - matchCount;
    }

    private long getMatchResultCount(MatchResult matchResult) {
        return gameResult.entrySet().stream()
                .filter(entry -> entry.getValue() == matchResult)
                .count();
    }

    public Map<Player, MatchResult> getGameResult() {
        return Collections.unmodifiableMap(gameResult);
    }

    @Override
    public String toString() {
        return "GameResult{" +
                "gameResult=" + gameResult +
                '}';
    }
}
