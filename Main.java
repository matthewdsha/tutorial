import java.util.*;

class Card {
    String val;
    String suit;

    public Card(String val, String suit) {
        this.val = val;
        this.suit = suit;
    }
}

class Deck {
    List<Card> m_deck;
    Set<Hand> m_hands;
    public Deck(int numHands, int numCards) {
        m_deck = new ArrayList<>();
        String[] suits = new String[]{"heart", "spade", "club", "diamond"};
        String[] vals = new String[]{"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        for(String v : vals) {
            for(String s : suits) {
                Card tmp = new Card(v, s);
                m_deck.add(tmp);
            }
        }
        m_hands = new HashSet<>();
        this.shuffle();
        for(int i = 0; i < numHands; i++) {
            Set<Card> cards = new HashSet<>();
            for(int j = 0; j < numCards; j++) {
                cards.add(m_deck.get(0));
                m_deck.remove(0);
            }
            Hand hand = new Hand(cards, cards.size());
            m_hands.add(hand);
        }
    }

    public void shuffle() {
        Collections.shuffle(m_deck);
    }

    public void draw(Hand hand) {
        hand.add(m_deck.get(0));
        m_deck.remove(0);
    } 
}

class Hand {
    Set<Card> m_hand;
    int m_size;
    public Hand(Set<Card> hand, int start) {
        m_hand = hand;
        m_size = start;
    }

    public void add(Card card) {
        m_hand.add(card);
        m_size++;
    }

    public void remove() {
        for(Card card : m_hand){
            m_hand.remove(card);
            break;
        }
         m_size--;
    }

    public void print() {
        for(Card card : m_hand) {
            System.out.println(card.val + ", " + card.suit);
        }
    }
}

public class Main{
    public static void main(String[] args) {
        Deck deck = new Deck(2, 5);
        for(Hand hand : deck.m_hands) {
            hand.print();
            System.out.println();
            hand.remove();
            hand.print();
            System.out.println();
        }
    }
}