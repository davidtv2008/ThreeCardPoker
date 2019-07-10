package game.ui;import java.awt.Color;import java.awt.Dimension;import java.awt.FlowLayout;import java.awt.Font;import java.awt.Toolkit;import java.awt.event.ActionEvent;import java.awt.event.ActionListener;import java.util.ArrayList;import javax.swing.Timer;import javax.swing.ImageIcon;import javax.swing.JButton;import javax.swing.JFrame;import javax.swing.JLabel;import javax.swing.JPanel;import javax.swing.JTextField;import javax.swing.border.EmptyBorder;import game.ui.Deck;import game.ui.Player;import game.ui.HandAnalyzer;public final class App{    private static ArrayList<JButton> allBtns = new ArrayList<JButton>();    private static ArrayList<JTextField> allFields = new ArrayList<JTextField>();    public static ImageIcon[] dealerCardIcon = new ImageIcon[3];    public static ImageIcon[] playerCardIcon = new ImageIcon[3];    public static JLabel[] dealerCardLabel = new JLabel[3];    public static JLabel[] playerCardLabel = new JLabel[3];    public static JLabel message;    public static JLabel potCash;    public static Timer alertTimer;    public static Timer dealTimerPlayer;    public static Timer dealTimerDealer;    public static Player player;    public static Player dealer;    public static int imageIndexPlayer = 0;    public static int imageIndexDealer = 0;    public static HandAnalyzer analyzer;    public static Deck deck;    private App() {    }    /**     *     * @param args The arguments of the program.     */    public static void main(String[] args) {        //create our game deck        deck = new Deck();        //create user player and dealer        player = new Player("David");        dealer = new Player("Dealer");        //GUI window for gameplay        JFrame window = new JFrame();        //-----------------------------------PANEL SETUPS---------------------------------------------------        JPanel tablePanel = new JPanel();        JPanel buttonPanel = new JPanel();        JPanel cashPanel = new JPanel();        JPanel labelPanel = new JPanel();        JPanel potPanel = new JPanel();        JPanel potCashPanel = new JPanel();        JPanel betIncrementsPanel = new JPanel();        JPanel betFieldsPanel = new JPanel();        JPanel dealerCardPanel = new JPanel();        JPanel playerCardPanel = new JPanel();        tablePanel.setLayout(null);        buttonPanel.setLayout(new FlowLayout());        buttonPanel.setBounds(249,665,500,50);        buttonPanel.setBorder(new EmptyBorder(0,0,50,0));        buttonPanel.setOpaque(false);        cashPanel.setLayout(new FlowLayout());        cashPanel.setBounds(249,380,500,50);        cashPanel.setBorder(new EmptyBorder(0,0,50,0));        cashPanel.setOpaque(false);        labelPanel.setLayout(new FlowLayout());        labelPanel.setBounds(249,290,500,50);        labelPanel.setBorder(new EmptyBorder(0,0,50,0));        labelPanel.setOpaque(false);        potPanel.setLayout(new FlowLayout());        potPanel.setBounds(0,290,200,50);        potPanel.setBorder(new EmptyBorder(0,0,50,0));        potPanel.setOpaque(false);        potCashPanel.setLayout(new FlowLayout());        potCashPanel.setBounds(0,330,200,50);        potCashPanel.setBorder(new EmptyBorder(0,0,50,0));        potCashPanel.setOpaque(false);        betIncrementsPanel.setLayout(new FlowLayout());        betIncrementsPanel.setBounds(670,420,300,50);        betIncrementsPanel.setBorder(new EmptyBorder(0,0,50,0));        betIncrementsPanel.setOpaque(false);        betFieldsPanel.setLayout(new FlowLayout());        betFieldsPanel.setBounds(764,505,120,250);        betFieldsPanel.setBorder(new EmptyBorder(0,0,50,0));        betFieldsPanel.setOpaque(false);        dealerCardPanel.setLayout(new FlowLayout());        dealerCardPanel.setBounds(315,95,360,160);        dealerCardPanel.setBorder(new EmptyBorder(0,0,50,0));        dealerCardPanel.setOpaque(false);        playerCardPanel.setLayout(new FlowLayout());        playerCardPanel.setBounds(315,450,360,160);        playerCardPanel.setBorder(new EmptyBorder(0,0,50,0));        playerCardPanel.setOpaque(false);        //-------------------------------icon setup------------------------------------------------------        dealerCardIcon[0] = new ImageIcon(Toolkit.getDefaultToolkit().getImage(App.class.getResource("/PokerCards/BackSide.png")));        dealerCardIcon[1] = new ImageIcon(Toolkit.getDefaultToolkit().getImage(App.class.getResource("/PokerCards/BackSide.png")));        dealerCardIcon[2] = new ImageIcon(Toolkit.getDefaultToolkit().getImage(App.class.getResource("/PokerCards/BackSide.png")));        playerCardIcon[0] = new ImageIcon(Toolkit.getDefaultToolkit().getImage(App.class.getResource("/PokerCards/BackSide.png")));        playerCardIcon[1] = new ImageIcon(Toolkit.getDefaultToolkit().getImage(App.class.getResource("/PokerCards/BackSide.png")));        playerCardIcon[2] = new ImageIcon(Toolkit.getDefaultToolkit().getImage(App.class.getResource("/PokerCards/BackSide.png")));        //---------------------------------LABEL SETUPS---------------------------------------------------        dealerCardLabel = new JLabel[3];        dealerCardLabel[0] = new JLabel(dealerCardIcon[0]);        dealerCardLabel[1] = new JLabel(dealerCardIcon[1]);        dealerCardLabel[2] = new JLabel(dealerCardIcon[2]);        playerCardLabel = new JLabel[3];        playerCardLabel[0] = new JLabel(playerCardIcon[0]);        playerCardLabel[1] = new JLabel(playerCardIcon[1]);        playerCardLabel[2] = new JLabel(playerCardIcon[2]);        message = new JLabel("SELECT STARTING CASH AMOUNT");        JLabel pot = new JLabel("Your Cash");        potCash = new JLabel("$" + player.getCash());        JLabel betIncrements = new JLabel("Bet in $5 increments");        dealerCardPanel.add(dealerCardLabel[0]);        dealerCardPanel.add(dealerCardLabel[1]);        dealerCardPanel.add(dealerCardLabel[2]);        playerCardPanel.add(playerCardLabel[0]);        playerCardPanel.add(playerCardLabel[1]);        playerCardPanel.add(playerCardLabel[2]);        betIncrementsPanel.add(betIncrements);        potCashPanel.add(potCash);        potPanel.add(pot);        labelPanel.add(message);        message.setFont(new Font("Serif", Font.BOLD, 24));        message.setForeground(new Color(0,0,0));        pot.setFont(new Font("Serif", Font.BOLD, 24));        pot.setForeground(new Color(0,0,0));        potCash.setFont(new Font("Serif", Font.BOLD, 24));        potCash.setForeground(new Color(0,0,0));        betIncrements.setFont(new Font("Serif", Font.BOLD, 24));        betIncrements.setForeground(new Color(0,0,0));        //---------------------------------END LABEL SETUP-------------------------------------------------        //---------------------------textfield setup-------------------------------------------------------        JTextField pairPlusBet = new JTextField("",4);        JTextField anteBet = new JTextField("",4);        JTextField playBet = new JTextField("",4);        JButton space1 = new JButton("");        JButton space2 = new JButton("");        //add all textfields to arraylist        allFields.add(pairPlusBet);        allFields.add(anteBet);        allFields.add(playBet);        space1.setOpaque(true);        space1.setContentAreaFilled(false);        space1.setBorderPainted(false);        space2.setOpaque(true);        space2.setContentAreaFilled(false);        space2.setBorderPainted(false);        pairPlusBet.setHorizontalAlignment(JTextField.CENTER);        anteBet.setHorizontalAlignment(JTextField.CENTER);        playBet.setHorizontalAlignment(JTextField.CENTER);        pairPlusBet.setFont(new Font("Serif", Font.BOLD, 24));        pairPlusBet.setForeground(new Color(0,0,0));        pairPlusBet.setBounds(0,0,200,50);        pairPlusBet.setEnabled(false);        anteBet.setFont(new Font("Serif", Font.BOLD, 24));        anteBet.setForeground(new Color(0,0,0));        anteBet.setBounds(0,0,200,50);        anteBet.setEnabled(false);        playBet.setFont(new Font("Serif", Font.BOLD, 24));        playBet.setForeground(new Color(0,0,0));        playBet.setBounds(0,0,200,50);        playBet.setEnabled(false);        space1.setFont(new Font("Serif", Font.BOLD, 10));        space1.setForeground(new Color(0,0,0));        space1.setBounds(0,0,200,50);        space1.setPreferredSize(new Dimension(25,46));        space2.setFont(new Font("Serif", Font.BOLD, 24));        space2.setForeground(new Color(0,0,0));        space2.setBounds(0,0,200,50);        space2.setPreferredSize(new Dimension(25,35));        betFieldsPanel.add(pairPlusBet);        betFieldsPanel.add(space1);        betFieldsPanel.add(anteBet);        betFieldsPanel.add(space2);        betFieldsPanel.add(playBet);        //------------------------end testfield setup        //---------------------------------------END PANELS SETUP-------------------------------------------        //table background image//        ImageIcon tableBackground = new ImageIcon("resources/PokerCards/Table.png");        ImageIcon tableBackground = new ImageIcon(Toolkit.getDefaultToolkit().getImage(App.class.getResource("/PokerCards/Table.png")));        JLabel background = new JLabel(tableBackground);        //---------------------------------BUTTON SETUPS----------------------------------------------------        JButton dealBtn = new JButton("DEAL");        dealBtn.setBorder(new EmptyBorder(10, 10, 10, 10));        dealBtn.setEnabled(false);        listenerSetup(dealBtn,player,potCash);        allBtns.add(dealBtn);        JButton playBtn = new JButton("PLAY");        playBtn.setBorder(new EmptyBorder(10, 10, 10, 10));        playBtn.setEnabled(false);        listenerSetup(playBtn,player,potCash);        allBtns.add(playBtn);        JButton foldBtn = new JButton("FOLD");        foldBtn.setBorder(new EmptyBorder(10, 10, 10, 10));        foldBtn.setEnabled(false);        listenerSetup(foldBtn,player,potCash);        allBtns.add(foldBtn);        JButton spacing1 = new JButton("");        spacing1.setBorder(new EmptyBorder(10, 10, 10, 10));        JButton spacing2 = new JButton("");        spacing2.setBorder(new EmptyBorder(10, 10, 10, 10));        JButton cash100 = new JButton("$100");        cash100.setBorder(new EmptyBorder(10, 10, 10, 10));        listenerSetup(cash100,player,potCash);        allBtns.add(cash100);        JButton cash1000 = new JButton("$1000");        cash1000.setBorder(new EmptyBorder(10, 10, 10, 10));        listenerSetup(cash1000,player,potCash);        allBtns.add(cash1000);        JButton cash10000 = new JButton("$10000");        cash1000.setBorder(new EmptyBorder(10, 10, 10, 10));        listenerSetup(cash10000,player,potCash);        allBtns.add(cash10000);        JButton resetBtn = new JButton("Reset");        resetBtn.setBorder(new EmptyBorder(10, 10, 10, 10));        listenerSetup(resetBtn,player,potCash);        allBtns.add(resetBtn);        //set button size        dealBtn.setPreferredSize(new Dimension(110,40));        playBtn.setPreferredSize(new Dimension(110,40));        foldBtn.setPreferredSize(new Dimension(110,40));        cash100.setPreferredSize(new Dimension(110,40));        cash1000.setPreferredSize(new Dimension(110,40));        cash10000.setPreferredSize(new Dimension(110,40));        resetBtn.setPreferredSize(new Dimension(110,40));        spacing1.setPreferredSize(new Dimension(25,40));        spacing1.setOpaque(false);        spacing1.setContentAreaFilled(false);        spacing1.setBorderPainted(false);        spacing2.setPreferredSize(new Dimension(25,40));        spacing2.setOpaque(false);        spacing2.setContentAreaFilled(false);        spacing2.setBorderPainted(false);        //-------------------------------------END BUTTON SETUPS-------------------------------------------        buttonPanel.add(dealBtn);        buttonPanel.add(spacing1);        buttonPanel.add(playBtn);        buttonPanel.add(spacing2);        buttonPanel.add(foldBtn);        cashPanel.add(cash100);        cashPanel.add(cash1000);        cashPanel.add(cash10000);        cashPanel.add(resetBtn);        //set size and table placement for all components        //setBound(x-coordinate,y-coordinate,width,height)        background.setBounds(0,0,1000,720);        //add all components to table panel        tablePanel.add(dealerCardPanel);        tablePanel.add(playerCardPanel);        tablePanel.add(betFieldsPanel);        tablePanel.add(betIncrementsPanel);        tablePanel.add(potCashPanel);        tablePanel.add(potPanel);        tablePanel.add(labelPanel);        tablePanel.add(cashPanel);        tablePanel.add(buttonPanel);        tablePanel.add(background);        //setup timer        //setup all timer used in the game        alertTimer = new Timer(1000,new ActionListener()        {            @Override           public void actionPerformed(ActionEvent e)           {               if(message.isVisible()){                    message.setVisible(false);               }               else{                   message.setVisible(true);               }           }        });        dealTimerPlayer = new Timer(600,new ActionListener()        {            @Override            public void actionPerformed(ActionEvent e){                //playerCardLabel[imageIndexPlayer].setIcon(new ImageIcon("resources/PokerCards/"+player.getCard(imageIndexPlayer)));                playerCardLabel[imageIndexPlayer].setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(App.class.getResource("/PokerCards/"+player.getCard(imageIndexPlayer)))));                                imageIndexPlayer++;                //stop timer after the 3rd card                if(imageIndexPlayer > 2){                    dealTimerPlayer.stop();                    for(int i = 0; i < allBtns.size(); i++){                        if(allBtns.get(i).getText() == "Reset" || allBtns.get(i).getText() == "DEAL"){                            allBtns.get(i).setEnabled(false);                        }                        if(allBtns.get(i).getText() == "PLAY" || allBtns.get(i).getText() == "FOLD"){                            allBtns.get(i).setEnabled(true);                        }                    }                    imageIndexPlayer = 0;                }            }        });        dealTimerDealer = new Timer(600,new ActionListener()        {            @Override            public void actionPerformed(ActionEvent e){//                dealerCardLabel[imageIndexDealer].setIcon(new ImageIcon("resources/PokerCards/"+dealer.getCard(imageIndexDealer)));                dealerCardLabel[imageIndexDealer].setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(App.class.getResource("/PokerCards/"+dealer.getCard(imageIndexDealer)))));                                imageIndexDealer++;                //stop timer after the 3rd card                if(imageIndexDealer > 2){                    dealTimerDealer.stop();                    imageIndexDealer = 0;                    //enable reset and deal buttons                    for(int i = 0; i < allBtns.size(); i++){                        if(allBtns.get(i).getText() == "Reset" || allBtns.get(i).getText() == "DEAL"){                            allBtns.get(i).setEnabled(true);                        }                    }                    allFields.get(0).setEnabled(true);                    allFields.get(1).setEnabled(true);                    analyzer = new HandAnalyzer(player.getSuites(), player.getRanks(), player.getValues(), dealer.getSuites(), dealer.getRanks(), dealer.getValues());                    String[] winner = analyzer.getWinner();                    String[] playerHand = analyzer.getPlayerHand();                    int anteAmount = Integer.parseInt(allFields.get(1).getText());                    int pairPlusAmount = 0;                    if(allFields.get(0).getText().equals("")){                        pairPlusAmount = 0;                    }                    else{                        pairPlusAmount = Integer.parseInt(allFields.get(1).getText());                    }                    int playAmount = 0;                    if(!player.fold){                        playAmount = Integer.parseInt(allFields.get(2).getText());                    }                    //calculate pair plus winnings                    int ppWin = 0;                    int aWin = 0;                    int pWin = 0;                    switch(playerHand[1]){                        case "PAIR": ppWin = pairPlusAmount + (pairPlusAmount * 1);                        break;                        case "FLUSH": ppWin = pairPlusAmount + (pairPlusAmount * 4);                        break;                        case "STRAIGHT": ppWin = pairPlusAmount + (pairPlusAmount * 6);                                        aWin = anteAmount + (anteAmount * 1);                        break;                        case "THREEOFAKIND": ppWin = pairPlusAmount + (pairPlusAmount * 30);                                        aWin = anteAmount + (anteAmount * 4);                        break;                        case "STRAIGHTFLUSH": ppWin = pairPlusAmount + (pairPlusAmount * 40);                                        aWin = anteAmount + (anteAmount * 5);                        break;                    }                    //set players cash                    player.pairPlusWin(ppWin);                    //calculate ante winnings                    if(winner[0].equals("player") && analyzer.dealerQualified()){                        aWin += anteAmount + (anteAmount * 1);                        pWin += playAmount + (anteAmount * 1);                    }                    else if(winner[0].equals("player") && !analyzer.dealerQualified()){                        aWin += anteAmount + (anteAmount * 1);                        pWin += playAmount;                    }                    if(!player.fold){                        player.anteWin(aWin);                        player.playWin(pWin);                    }                    if(analyzer.dealerQualified()){                        message.setText(winner[0] + " wins " + winner[1]);                    }                    else if(!analyzer.dealerQualified()){                        message.setText("dealer not qualified, " + playerHand[0] + " " + playerHand[1]);                    }                    if(player.fold){                        message.setText("player folded");                    }                    potCash.setText("$"+player.getCash());                }            }        });        //GUI window attributes        window.add(tablePanel);        window.setSize(tableBackground.getIconWidth(),tableBackground.getIconHeight() + 30); // sets the size of the windows        window.setLocationRelativeTo(null); // center the frame on the screen        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // used to close upon clicking on the X button        window.setResizable(true); // do not allow user to adjust the frame size        window.setVisible(true); // needed to make the frame visible    }    private static void listenerSetup(JButton btn, Player player,JLabel pot){        //-----------------------------SETUP EVENT LISTENERS---------------------------------        String btnName = btn.getActionCommand();        btn.addActionListener(new ActionListener(){            @Override            public void actionPerformed(ActionEvent e){                //performed corresponding action based on which button was clicked                switch(btnName){                    case "$100":{                        int cash = Integer.parseInt(btnName.substring(1,btnName.length()));                        setCash(player,cash,pot);                        message.setText("");                    }break;                    case "$1000":{                        int cash = Integer.parseInt(btnName.substring(1,btnName.length()));                        setCash(player,cash,pot);                        message.setText("");                    }break;                    case "$10000":{                        int cash = Integer.parseInt(btnName.substring(1,btnName.length()));                        setCash(player,cash,pot);                        message.setText("");                    }break;                    case "Reset":{                        int cash = 0;                        setCash(player,cash,pot);                        //disable other cash options once users has selected this option                        for(int i = 0; i < allBtns.size();i++){                            allBtns.get(i).setEnabled(false);                            if(allBtns.get(i).getText() == "$100" ||                                allBtns.get(i).getText() == "$1000" ||                                allBtns.get(i).getText() == "$10000"){                                allBtns.get(i).setEnabled(true);                            }                        }                        for(int i = 0; i < allFields.size(); i++){                            allFields.get(i).setEnabled(false);                        }                    }break;                    case "DEAL":{                        deal();                    }break;                    case "PLAY":{                        play();                    }break;                    case "FOLD":{                        fold();                    }break;                    default: break;                }            }        });    }    private static void setCash(Player player,int cash,JLabel pot){        player.setCash(cash);        pot.setText("$" + player.getCash());        //disable other cash options once users has selected this option        for(int i = 0; i < allBtns.size();i++){            if(allBtns.get(i).getText() == "Reset"){                continue;            }            else if(allBtns.get(i).getText() == "DEAL"){                allBtns.get(i).setEnabled(true);            }            else{                allBtns.get(i).setEnabled(false);            }        }        //enable textfields once user has selected his starting cash        allFields.get(0).setEnabled(true);        allFields.get(1).setEnabled(true);    }    private static void play(){        JTextField play = allFields.get(2);        JTextField ante = allFields.get(1);        //play must equal ante        play.setText(ante.getText());        //deduct play from player        player.deduct(Integer.parseInt(play.getText()));        //update the cash label        potCash.setText("$" + player.getCash());        for(int i = 0; i < allBtns.size(); i++){            if(allBtns.get(i).getText() == "PLAY" || allBtns.get(i).getText() == "FOLD"){                allBtns.get(i).setEnabled(false);            }        }        dealTimerDealer.start();        deck.shuffleDeck();    }    private static void deal(){        player.fold = false;        //regex pattern to match        String patternBet = "[1-9]{1}[0-9]{0,4}";        String patternPair = "[1-9]{0,1}[0-9]{0,4}";        JTextField pairPlus = allFields.get(0);        JTextField bet = allFields.get(1);        allFields.get(2).setText("");        //if the bets have been placed, we are ok to deal the cards        if(pairPlus.getText().matches(patternPair) && bet.getText().matches(patternBet)){            //check if player has enough money to play            int ppBet = 0;            if(pairPlus.getText().equals("")){                ppBet = 0;            }            else{                ppBet = Integer.parseInt(pairPlus.getText());            }            int ante = Integer.parseInt(bet.getText());            int pbet = ante;            int checkBet = ppBet + ante + pbet;            if(checkBet <= player.getCash()){                //player is ok to play, he has enough cash                message.setText("");                //deduct bet amount from players cash                player.deduct(ppBet);                player.deduct(ante);                //update the cash label                potCash.setText("$" + player.getCash());                pairPlus.setEnabled(false);                bet.setEnabled(false);                //deal three cards to player                player.setHand(deck.dealTrio());                dealer.setHand(deck.dealTrio());                for(int i = 0; i < allBtns.size(); i++){                    if(allBtns.get(i).getText() == "Reset" || allBtns.get(i).getText() == "DEAL"){                        allBtns.get(i).setEnabled(false);                    }                }                //reset cards back to backside image                playerCardLabel[0].setIcon(new ImageIcon("resources/PokerCards/BackSide.png"));                playerCardLabel[1].setIcon(new ImageIcon("resources/PokerCards/BackSide.png"));                playerCardLabel[2].setIcon(new ImageIcon("resources/PokerCards/BackSide.png"));                dealerCardLabel[0].setIcon(new ImageIcon("resources/PokerCards/BackSide.png"));                dealerCardLabel[1].setIcon(new ImageIcon("resources/PokerCards/BackSide.png"));                dealerCardLabel[2].setIcon(new ImageIcon("resources/PokerCards/BackSide.png"));                dealTimerPlayer.start();            }            else{                //player does not have enough cash                message.setText("Not enough money, lower bet");                for(int i = 0; i < allBtns.size(); i++){                    if(allBtns.get(i).getText() == "Reset" || allBtns.get(i).getText() == "DEAL"){                        allBtns.get(i).setEnabled(true);                    }                    if(allBtns.get(i).getText() == "PLAY" || allBtns.get(i).getText() == "FOLD"){                        allBtns.get(i).setEnabled(false);                    }                }            }            //stop timer            alertTimer.stop();            message.setVisible(true);        }        else{            message.setText("Must place PairPlus and Ante Bets");            message.setForeground(new Color(255,255,255));            //flash a message to let user know he needs to place a correct bet            alertTimer.start();        }    }    private static void fold(){        player.fold = true;        dealTimerDealer.start();        for(int i = 0; i < allBtns.size(); i++){            allBtns.get(i).setEnabled(false);            if(allBtns.get(i).getText() == "DEAL" ||                allBtns.get(i).getText() == "Reset"){                allBtns.get(i).setEnabled(true);            }        }    }}