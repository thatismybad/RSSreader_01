package cz.uhk.fim.rssreader.gui;

import cz.uhk.fim.rssreader.model.RSSItem;

import javax.swing.*;
import java.awt.*;

public class CardView extends JPanel {

    private static final int ITEM_WIDTH = 180;
    private static final int COMPONENT_WIDTH = 160;
    private static final int HEIGHT = 1;

    final String startHtml = "<html><p style='width: "+ COMPONENT_WIDTH +" px'>";
    final String endHtml = "</p></html>";


    public CardView(RSSItem item) {
        setLayout(new WrapLayout());
        setSize(ITEM_WIDTH, HEIGHT);
        setTitle(item.getTitle());
        setBackground(Color.CYAN);
        setDescription(item.getDescription());
        setAdditionalInfo(String.format("%s - %s", item.getAuthor(), item.getPubDate()));
    }

    private void setTitle(String title) {
        JLabel lblTitle = new JLabel();
        lblTitle.setFont(new Font("Courier", Font.BOLD, 12));
        lblTitle.setSize(COMPONENT_WIDTH, HEIGHT);
        lblTitle.setText(String.format("%s%s%s", startHtml, title, endHtml));
        add(lblTitle);
    }

    private void setDescription(String description) {
        JLabel lblDescription = new JLabel();
        lblDescription.setFont(new Font("Courier", Font.PLAIN, 11));
        lblDescription.setSize(COMPONENT_WIDTH, HEIGHT);
        lblDescription.setText(String.format("%s%s%s", startHtml, description, endHtml));
        add(lblDescription);
    }

    private void setAdditionalInfo(String info) {
        JLabel lblInfo = new JLabel();
        lblInfo.setFont(new Font("Courier", Font.ITALIC, 10));
        lblInfo.setSize(COMPONENT_WIDTH, HEIGHT);
        lblInfo.setText(String.format("%s%s%s", startHtml, info, endHtml));
        lblInfo.setForeground(Color.LIGHT_GRAY);
        add(lblInfo);
    }
}
