package SomeInterviews.verkarda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DesignCardGameII {
    /*
    You are provided with the core class and enum definitions for a standard card game, including Suit, Rank, Card, Deck, and Player. Each card is uniquely identified by a suit and a rank. The deck contains all 52 distinct combinations of suits ("CLUBS", "DIAMONDS", "HEARTS", "SPADES") and ranks ("A", "2", ..., "10", "J", "Q", "K").

Your task is to implement a method that shuffles the deck, distributes all cards evenly among four players, and sorts each player's hand by suit and rank. Specifically, implement the dealAndSortHands method in the Game class. This method should:

Shuffle the deck.

Deal all 52 cards evenly to the four players, so that each player receives exactly 13 cards.

Sort each player's hand using the following order:

First by suit, in ascending order: "CLUBS" < "DIAMONDS" < "HEARTS" < "SPADES"
Then by rank, in ascending order: "A" < "2" < ... < "10" < "J" < "Q" < "K"
You may extend or modify the provided implementation as needed. After sorting, print each player's hand to verify correctness.

Print Rules

Header: Print a line indicating the player's ID and card count: "Player <x>: 13 cards", where <x> is the player's ID (1-indexed).

Hand Content: Print the sorted hand on a new line in the format: "Player <x>: [{Card_1}, {Card_2}, ..., {Card_13}]".

Card Format: Each card must be represented as "{<rank>:<suit>}".

<rank>: An integer from "1" to "13", corresponding to "A" through "K" respectively.

<suit>: The uppercase string of the suit ("CLUBS", "DIAMONDS", "HEARTS", or "SPADES").

For example, "{1:CLUBS}" represents the Ace of Clubs.

Within each player's hand, cards must already be sorted by suit and then by rank before printing.

The output should clearly indicate each player and list their cards in sorted order.

Constraints

The game always has exactly 4 players.
The deck contains exactly 52 unique cards, one for each combination of suit and rank.
Each player must receive exactly 13 cards.
Each card must appear in exactly one player's hand.
Example

Input: ["Player", "Player", "Player", "Player", "Deck", "Game", "dealAndSortHands"]
[[1], [2], [3], [4], [], [[player1, player2, player3, player4], deck], []]
Output: Expected to see 4 players, each with random cards are printed.


Hint 1
You need a custom comparator that prioritizes suit order (CLUBS < DIAMONDS < HEARTS < SPADES) over rank order.

Hint 2
Map the Rank enum to its corresponding integer value (A=1 ... K=13) to simplify both the sorting logic and the output formatting.
     */
    class Game {
        //难点，怎么写enum class，然后怎么在enum类上写两个for 循环创建52张卡在？用set去重的话需要重写hashcode方法吗，是的
        //这里主要是参考hack2hire的答案，但是没写player那个类
        List<Card> cards;
        public Game() {
            // TODO: Initialize Game
            cards=new ArrayList<>();
            for (Suit suit : Suit.values()) {
                for (Rank rank : Rank.values()) {
                    cards.add(new Card(suit, rank));
                }
            }
        }
        public void shuffle(){
            Collections.shuffle(cards);//如果不让用就看RandomlyPartitionArray得写法
        }
        public void dealAndSortHands(){
            List<List<Card>> al=deal(4);
            for (List<Card> hand:al){
                hand.sort(new Comparator<Card>() {//注意comparator的写法
                    @Override
                    public int compare(Card o1, Card o2) {
                        int suitCompare = o1.suit.compareTo(o2.suit);//注意enum类怎么比较
                        if(suitCompare==0){
                            return o1.rank.getValue()-o2.rank.getValue();
                        }
                        return suitCompare;
                    }
                });
            }
        }
        //round robin分牌
        public List<List<Card>> deal(int playerCount) {
            List<List<Card>> hands = new ArrayList<>();
            for (int i = 0; i < playerCount; i++) {
                hands.add(new ArrayList<>());
            }
            int cardIndex = 0;
            while (cardIndex < cards.size()) {//每个while发给playerCount个玩家
                for (int i = 0; i < playerCount && cardIndex < cards.size(); i++) {
                    hands.get(i).add(cards.get(cardIndex));
                    cardIndex++;
                }
            }
            return hands;
        }

    }
    class Card{
        Suit suit;
        Rank rank;
        public Card(Suit suit, Rank rank){
            this.suit=suit;
            this.rank=rank;
        }
        @Override
        public String toString() {
            return "{" + rank.getValue() + ":" + suit + "}";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Card card = (Card) o;
            return suit == card.suit && rank == card.rank;
        }

        @Override
        public int hashCode() {
            return 31 * suit.hashCode() + rank.hashCode();
        }
    }
    enum Suit {
        CLUBS, DIAMONDS, HEARTS, SPADES
    }
    enum Rank {
        A, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, J, Q, K;
        public int getValue() {//为啥需要转化成int呢？因为方便使写comparator的逻辑吗，不是，因为enum天然就是有这个列出来的顺序ascending的
            //这里写是因为题目说了输出的要转成123.。。因此suit就不需要写getvalue方法。
            switch (this) {
                case A:
                    return 1;
                case TWO:
                    return 2;
                case THREE:
                    return 3;
                case FOUR:
                    return 4;
                case FIVE:
                    return 5;
                case SIX:
                    return 6;
                case SEVEN:
                    return 7;
                case EIGHT:
                    return 8;
                case NINE:
                    return 9;
                case TEN:
                    return 10;
                case J:
                    return 11;
                case Q:
                    return 12;
                case K:
                    return 13;
                default:
                    return 0;
            }
        }
    }
}
