package gmbs.view;

import gmbs.dto.ResultDTO;
import gmbs.dto.PlayerDTO;

public class Output {

    public void startDisplay() {
        System.out.println("게임에 참여할 사람 이름 입력");
    }

    public void hitDisplay(final String userName) {
        StringBuilder text = new StringBuilder(userName);
        text.append(", 한장의 카드를 더 받겠습니까?(y/n)");
        System.out.println(text);
    }

    public void showPlayerInfo(final PlayerDTO playerInfo) {
        StringBuilder infos = new StringBuilder(playerInfo.getName());
        infos.append(" : ");
        infos.append(playerInfo.getCards());
        System.out.println(infos);
    }

    public void showResultStatus(final ResultDTO resultInfo) {
        StringBuilder results = new StringBuilder(resultInfo.getName());
        results.append("의 카드 : ");
        results.append(resultInfo.getCards());
        results.append("    sum - ");
        results.append(resultInfo.getCardSum());
        System.out.println(results);
    }

    public void showDealerHit() {
        System.out.println("딜러는 한장의 카드를 더 받았습니다");
    }

    public void showResult(final ResultDTO resultInfo) {
        StringBuilder result = new StringBuilder(resultInfo.getName());
        result.append(" : ");
        result.append(resultInfo.getResult());
        System.out.println(result);
    }
}
