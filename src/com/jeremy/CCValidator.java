package com.jeremy;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jeremy on 10/25/16.
 */
public class CCValidator extends JFrame {
    //variables for GUI
    private JTextField creditCardNumberTextField;
    private JButton validateButton;
    private JButton quitButton;
    private JPanel rootPanel;
    private JLabel validMessageLabel;

    public CCValidator(){
        //adding information to the GUI
        super("Credit Card Validator");
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        //a validate button to check if the card number is good or not
        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ccNumber = creditCardNumberTextField.getText();

                boolean valid = isCreditCardValid(ccNumber);

                if (valid) {
                    validMessageLabel.setText("Credit card number is valid");
                }

                else {
                    validMessageLabel.setText("Credit card number is NOT valid");
                }
            }

        });

        //quit button to close the program
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    //credit card checking method
    public static boolean isCreditCardValid(String cc) {

        if (!cc.startsWith("4")) {
            System.out.println("Doesn't start with 4, invalid");
            return false;
        }

        if (cc.length() != 16) {
            System.out.println("Credit card must be 16 numbers, invalid");
            return false;
        }


        int sum = 0;

        for (int i = 0; i < 16 ; i++ ) {
            int thisDigit = Integer.parseInt((cc.substring(i, i+1)));
            if (i % 2 == 1) {
                sum = sum + thisDigit;
            } else {
                int doubled = thisDigit * 2;
                if (doubled > 9 ) {
                    int toAdd = 1 + (doubled % 10);
                    sum = sum + toAdd;
                } else {
                    sum = sum + (thisDigit * 2);
                }
            }
        }

        if (sum % 10 == 0) {
            return true;
        }

        System.out.println("Check digit is wrong, card number is invalid");
        return false;

    }

}


