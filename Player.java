import java.util.ArrayList;
import java.util.List;

class Player {
    private List<Card> hand; // Список для хранения карт

    public Player() {
        hand = new ArrayList<>();
    }
    // Получение карты в руку игрока
    public void ruka(int cardValue) {
        if (hand.size() < Deck.getMaxCards()) {
            hand.add(new Card(cardValue)); // Динамическое выделение карты
        }
    }
    // Вывод руки игрока
    public void myvivod() {
        int sum = 0;
        System.out.print("\nМои карты: ");
        for (Card card : hand) {
            System.out.print(card.getValue() + ", ");
            sum += card.getValue();
        }
        System.out.println("Сумма: " + sum + "/21");
    }
    // Вывод руки противника без первой карты
    public void opvivodhide() {
        int sum = 0;
        System.out.print("\nКарты противника: ?, ");
        for (int i = 1; i < hand.size(); i++) {
            System.out.print(hand.get(i).getValue() + ", ");
            sum += hand.get(i).getValue();
        }
        System.out.println("Сумма: ? + " + sum + "/21");
    }
    // Вывод полной руки противника
    public void opvivodopen() {
        int sum = 0;
        System.out.print("\nКарты противника: ");
        for (Card card : hand) {
            System.out.print(card.getValue() + ", ");
            sum += card.getValue();
        }
        System.out.println("Сумма: " + sum + "/21");
    }
    // Сумма очков
    public int getTotalValue() {
        int sum = 0;
        for (Card card : hand) {
            sum += card.getValue();
        }
        return sum;
    }
    // Вывод результата игры
    public GameResult vivodrez(Player opponent) {
        int playerScore = this.getTotalValue();
        int opponentScore = opponent.getTotalValue();
        String resultMessage;

        if (playerScore > 21 && opponentScore < 22) {
            resultMessage = "У вас перебор. Вы проиграли.";
        } else if (opponentScore > 21 && playerScore < 22) {
            resultMessage = "У противника перебор. Вы выиграли.";
        } else if (playerScore > 21 && opponentScore > 21) {
            if (playerScore > opponentScore) {
                resultMessage = "У вас и противника перебор. Вы проиграли, так как имеете больше очков.";
            } else {
                resultMessage = "У вас и противника перебор. Вы выиграли, так как имеете меньше очков.";
            }
        } else if (playerScore < 22 && opponentScore < 22) {
            if (playerScore > opponentScore) {
                resultMessage = "Вы выиграли. Вы ближе к 21 очку.";
            } else if (playerScore < opponentScore) {
                resultMessage = "Вы проиграли. Противник ближе к 21 очку.";
            } else {
                resultMessage = "Ничья.";
            }
        } else {
            resultMessage = "Результат не определен.";
        }

        return new GameResult(resultMessage);
    }
    // Простейший искусственный интеллект для противника, который берет карту, если у него меньше 17 очков
    public boolean reshenie_ai() {
        return this.getTotalValue() < 17; // Возвращаем true (взять карту) или false (остановиться)
    }

}
