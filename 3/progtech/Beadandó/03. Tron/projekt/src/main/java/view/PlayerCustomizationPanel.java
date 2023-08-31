package main.java.view;

import javax.swing.*;
import java.awt.*;

/** Customization Panel for players */
public class PlayerCustomizationPanel extends JPanel {
    /** Input for player name. */
    private final JTextField playerNameInput;

    /** Label that shows player color. */
    private final JLabel playerColorLabel;

    /** Number of players. */
    private static short playerNumber = 1;

    /**
     * Constructor.
     * @param defaultColor default color for player
     * @param controls     image of the players controls
     */
    public PlayerCustomizationPanel(Color defaultColor, ImageIcon controls) {
        // set component size
        this.setPreferredSize(new Dimension(150, 350));

        // create title for component
        JLabel playerNumberLabel = new JLabel("Játékos #" + PlayerCustomizationPanel.playerNumber);
        playerNumberLabel.setFont(playerNumberLabel.getFont().deriveFont(24f));
        this.add(playerNumberLabel);

        // create prompt for name input
        JLabel nameLabel = new JLabel("Név", SwingConstants.CENTER);
        nameLabel.setFont(nameLabel.getFont().deriveFont(16f));
        this.add(nameLabel);

        // create name input
        this.playerNameInput = new JTextField("P" + PlayerCustomizationPanel.playerNumber);
        this.playerNameInput.setPreferredSize(new Dimension(125, 20));
        this.add(this.playerNameInput);

        // create title for color input
        JLabel colorLabel = new JLabel("Szín", SwingConstants.CENTER);
        colorLabel.setPreferredSize(new Dimension(150,18));
        colorLabel.setFont(colorLabel.getFont().deriveFont(16f));
        this.add(colorLabel);

        // create color label
        this.playerColorLabel = new JLabel();
        this.playerColorLabel.setOpaque(true);
        this.playerColorLabel.setPreferredSize(new Dimension(75,75));
        this.playerColorLabel.setBackground(defaultColor);
        this.add(this.playerColorLabel);

        // create color picker
        JButton playerColorPickerButton = new JButton("Kiválaszt");
        playerColorPickerButton.setPreferredSize(new Dimension(100,20));
        this.add(playerColorPickerButton);
        playerColorPickerButton.addActionListener(e -> playerColorLabel.setBackground(JColorChooser.showDialog(null,"Válaszd ki a színt.", playerColorLabel.getBackground())));

        // create keymap
        JLabel controlLabel = new JLabel(controls);
        this.add(controlLabel);

        // increase player number counter
        PlayerCustomizationPanel.playerNumber++;
    }

    /** @return text inputted in {@code JTextField playerNameInput} */
    public String getPlayerName() {
        return playerNameInput.getText();
    }

    /** @return background color of {@code JLabel playerColorLabel} */
    public Color getPlayerColor() {
        return playerColorLabel.getBackground();
    }
}
