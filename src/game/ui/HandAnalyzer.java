package game.ui;


enum Hand{
    HIGH,PAIR,FLUSH,STRAIGHT,THREEOFAKIND,STRAIGHTFLUSH;
}

public class HandAnalyzer{

    private String[] p_suites = {null,null,null};
    private String[] p_ranks = {null,null,null};
    private int[] p_values = {0,0,0};

    private String[] d_suites = {null,null,null};
    private String[] d_ranks = {null,null,null};
    private int[] d_values = {0,0,0};

    private int[] playerHandMatrix = {0,0,0,0,0,0,0,0,0,0,0,0,0};
    private int[] dealerHandMatrix = {0,0,0,0,0,0,0,0,0,0,0,0,0};

    private int[] playerFlushMatrix = {0,0,0,0};
    private int[] dealerFlushMatrix = {0,0,0,0};

    private Hand p_hand = Hand.HIGH;
    private Hand d_hand = Hand.HIGH;

    //constructor
    HandAnalyzer(String[] p_suites, String[] p_ranks, int[] p_values, String[] d_suites, String[] d_ranks, int[] d_values){
        for(int i = 0; i < p_suites.length; i++){

            this.p_suites[i] = p_suites[i];
            this.p_ranks[i] = p_ranks[i];
            this.p_values[i] = p_values[i];

            this.d_suites[i] = d_suites[i];
            this.d_ranks[i] = d_ranks[i];
            this.d_values[i] = d_values[i];
        }

        checkHands();
    }

    private void checkHands(){

        //sort players hand highest to lowest
        for(int i = 0; i < p_values.length; i ++){
            for(int j = i + 1; j < p_values.length; j++){

                if(p_values[i] < p_values[j]){
                    int temp = p_values[i];
                    p_values[i] = p_values[j];
                    p_values[j] = temp;
                }
            }
        }

        //sort dealers hand highest to lowest
        for(int i = 0; i < d_values.length; i ++){
            for(int j = i + 1; j < d_values.length; j++){

                if(d_values[i] < d_values[j]){
                    int temp = d_values[i];
                    d_values[i] = d_values[j];
                    d_values[j] = temp;
                }
            }
        }

        for (int i = 0; i < p_values.length; i++){

            playerHandMatrix[p_values[i] - 2] += 1;
            dealerHandMatrix[d_values[i] - 2] += 1;
        }

        playerFlushMatrix[0] = 0;
        playerFlushMatrix[1] = 0;
        playerFlushMatrix[2] = 0;
        playerFlushMatrix[3] = 0;
        dealerFlushMatrix[0] = 0;
        dealerFlushMatrix[1] = 0;
        dealerFlushMatrix[2] = 0;
        dealerFlushMatrix[3] = 0;

        for(int i = 0; i < p_suites.length; i++){
            switch(p_suites[i]){
                case "h": playerFlushMatrix[0] += 1;
                break;
                case "c": playerFlushMatrix[1] += 1;;
                break;
                case "d": playerFlushMatrix[2] += 1;
                break;
                case "s": playerFlushMatrix[3] += 1;
                break;
                default: break;
            }
        }

        for(int i = 0; i < d_suites.length; i++){
            switch(d_suites[i]){
                case "h": dealerFlushMatrix[0] += 1;
                break;
                case "c": dealerFlushMatrix[1] += 1;;
                break;
                case "d": dealerFlushMatrix[2] += 1;
                break;
                case "s": dealerFlushMatrix[3] += 1;
                break;
                default: break;
            }
        }

        for(int i = 0; i < playerHandMatrix.length; i++){
            if(playerHandMatrix[i] == 2){
                p_hand = Hand.PAIR;
            }
            else if(playerHandMatrix[i] == 3){
                p_hand = Hand.THREEOFAKIND;
            }
        }

        for(int i = 0; i < dealerHandMatrix.length; i++){
            if(dealerHandMatrix[i] == 2){
                d_hand = Hand.PAIR;
            }
            else if(dealerHandMatrix[i] == 3){
                d_hand = Hand.THREEOFAKIND;
            }
        }

        //check if player has a flush
        for(int i = 0; i < playerFlushMatrix.length; i++){
            if(playerFlushMatrix[i] == 3){
                p_hand = Hand.FLUSH;
            }
        }


        for(int i = 0; i < dealerFlushMatrix.length; i++){
            if(dealerFlushMatrix[i] == 3){
                d_hand = Hand.FLUSH;
            }
        }

        //check if players have a straight
        for(int i = 0; i < playerHandMatrix.length - 2; i++){
            if(playerHandMatrix[i] == 1 && playerHandMatrix[i+1] == 1 && playerHandMatrix[i+2] == 1){
                if(p_hand == Hand.FLUSH){
                    p_hand = Hand.STRAIGHTFLUSH;
                }
                else{
                    p_hand = Hand.STRAIGHT;
                }
            }
        }

        //check if player has a straight with a,2,3
        if(playerHandMatrix[0] == 1 && playerHandMatrix[1] == 1 && playerHandMatrix[12] == 1){
            if(p_hand == Hand.FLUSH){
                p_hand = Hand.STRAIGHTFLUSH;
            }
            else{
                p_hand = Hand.STRAIGHT;
            }
        }



        //check if players have a straight
        for(int i = 0; i < dealerHandMatrix.length - 2; i++){
            if(dealerHandMatrix[i] == 1 && dealerHandMatrix[i+1] == 1 && dealerHandMatrix[i+2] == 1){
                if(d_hand == Hand.FLUSH){
                    d_hand = Hand.STRAIGHTFLUSH;
                }
                else{
                    d_hand = Hand.STRAIGHT;
                }
            }
        }

        //check if player has a straight with a,2,3
        if(dealerHandMatrix[0] == 1 && dealerHandMatrix[1] == 1 && dealerHandMatrix[12] == 1){
            if(d_hand == Hand.FLUSH){
                d_hand = Hand.STRAIGHTFLUSH;
            }
            else{
                d_hand = Hand.STRAIGHT;
            }
        }
    }

    public String[] getPlayerHand(){
        String[] playerHand = {"",""};

        playerHand[0] = "player";
        playerHand[1] = p_hand.name();

        return playerHand;
    }

    public boolean dealerQualified(){

        boolean qualified = false;

        if(d_hand.ordinal() > 0){
            qualified = true;
        }
        else{

            for(int i = 0; i < d_ranks.length; i++){
                if(d_values[i] >= 12){
                    qualified = true;
                }
            }
        }

        return qualified;
    }

    public String[] getWinner(){

        //once hands are compared return a 2d array naming player, and his hand
        String[] winner = {"dealer","hand"};

        //compare their hands
        if(p_hand.ordinal() > d_hand.ordinal()){
            winner[0] = "player";
            winner[1] = p_hand.name();
        }
        else if(p_hand.ordinal() == d_hand.ordinal()){

            //players have same hand, check each card individually
            for(int i = 0; i < p_values.length; i++){
                if(p_values[i] > d_values[i]){
                    winner[0] = "player";
                    winner[1] = p_hand.name();
                    break;
                }
                else if(p_values[i] < d_values[i]){
                    winner[0] = "dealer";
                    winner[1] = d_hand.name();
                    break;
                }
                else{
                    winner[0] = "draw";
                    winner[1] = p_hand.name();
                }
            }
        }
        else if(p_hand.ordinal() < d_hand.ordinal()){
            winner[0] = "dealer";
            winner[1] = d_hand.name();
        }

        return winner;
    }
}
