package game.ui;
import java.util.Collections;
import java.util.ArrayList;

public class Deck{

    private String[] suites = {"c","s","h","d"};
    private String[] rank = {"2","3","4","5","6","7","8","9","10","j","q","k","a"};
    private ArrayList<String> deck = new ArrayList<String>();
    private String[] trio = {null,null,null};

    //constructor
    public Deck(){

        //add all cards to the deck, unshuffled
        loadCards();

        //do an initial shuffle upon creating deck object
        shuffleDeck();
    }

    private void loadCards(){

        deck.clear();

        //iterate through all cards and add them to deck
        for( int r = 0; r < rank.length; r++){

            for(int s = 0; s < suites.length; s++){

                deck.add(rank[r] + suites[s]);
            }
        }
    }

    public void shuffleDeck()
    {
        loadCards();
        Collections.shuffle(deck);
    }

    private String dealCard(){

        //draw a single card, and pop it off the deck and deal it
        String cardDrawn = deck.get(0);
        deck.remove(0);

        return cardDrawn;
    }

    public String[] dealTrio(){
        trio[0] = dealCard();
        trio[1] = dealCard();
        trio[2] = dealCard();


        return trio;
    }



}
