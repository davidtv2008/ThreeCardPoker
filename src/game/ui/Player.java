package game.ui;

public class Player{

    private String name;
    private int cash = 0;
    private int pairPlus = 0;
    private int ante = 0;
    private int play = 0;
    private String[] hand;
    private String[] rank = {null,null,null};
    private String[] suite = {null,null,null};
    private int[] value = {0,0,0};
    public boolean fold = false;

    //constructor
    public Player(String name){
        this.name = name;
    }

    public void pairPlusWin(int winAmount){
        this.cash += winAmount;
    }

    public void anteWin(int winAmount){
        this.cash += winAmount;
    }

    public void playWin(int winAmount){
        this.cash += winAmount;
    }

    public void setCash(int cash){
        this.cash = cash;
    }

    public void deduct(int amount){
        this.cash -= amount;
    }

    public void setPairPlus(int pairPlus){
        this.pairPlus = pairPlus;
    }

    public void setAnte(int ante){
        this.ante = ante;
    }

    public void setPlay(int play){
        this.play = play;
    }

    public void setHand(String[] hand){

        this.hand = hand;

        //evaluate and split each cards rank, suite, value
        for(int i = 0; i < this.hand.length; i++){

            //check if rank is 10, means string has 3 characters
            if(this.hand[i].length() > 2){
                rank[i] = this.hand[i].substring(0,2);
                suite[i] = this.hand[i].substring(2);
            }
            //all other strings rank will be 2 characters only
            else{
                rank[i] = this.hand[i].substring(0,1);
                suite[i] = this.hand[i].substring(1);

            }

            switch(rank[i]){
                case "j": value[i] = 11;
                break;
                case "q": value[i] = 12;
                break;
                case "k": value[i] = 13;
                break;
                case "a":
                        //only check if A should be a value of 1 or 14 if and only if the other 2 cards are a 2 and 3
                        if(hand[0].substring(0,1) == "2" && hand[1].substring(0,1) == "3" ||
                            hand[0].substring(0,1) == "3" && hand[1].substring(0,1) == "2" ||
                            hand[0].substring(0,1) == "2" && hand[2].substring(0,1) == "3" ||
                            hand[0].substring(0,1) == "3" && hand[2].substring(0,1) == "2" ||
                            hand[1].substring(0,1) == "2" && hand[2].substring(0,1) == "3" ||
                            hand[1].substring(0,1) == "3" && hand[2].substring(0,1) == "2"){
                                value[i] = 1;
                                //System.out.println(hand[0].substring(0));
                                //System.out.println(hand[1].substring(0));

                                //System.out.println(value[i]);
                        }
                        else{
                            value[i] = 14;

                            System.out.println(hand[0].substring(0,1));
                            System.out.println(hand[1].substring(0,1));
                            System.out.println(hand[2].substring(0,1));


                            //System.out.println(value[i]);
                        }
                break;
                default:
                        value[i] = Integer.parseInt(rank[i]);
            }
        }
    }

    public String getRank(int i){
        return rank[i];

    }

    public String[] getRanks(){
        return rank;
    }

    public String getSuite(int i){
        return suite[i];

    }

    public String[] getSuites(){
        return suite;
    }

    public int getValue(int i){
        return value[i];
    }

    public int[] getValues(){
        return value;
    }

    public int getCash(){
        return cash;
    }

    public String getCard(int i){
        return rank[i]+""+suite[i]+".png";
    }

}