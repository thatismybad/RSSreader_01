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

    private Color bgColor;
    private Color textColor;

    public CardView(RSSItem item){
        setLayout(new WrapLayout());
        setSize(ITEM_WIDTH, HEIGHT);
        bgColor = getRandomBgColor(item.getTitle());
        setBackground(bgColor);
        setTitle(item.getTitle());
        setDescription(item.getDescription());
        setInfo(String.format("%s - %s", item.getPubDate(), item.getAuthor()));
    }

    private void setTitle(String title) {
        JLabel lblTitle = new JLabel();
        lblTitle.setSize(COMPONENT_WIDTH, HEIGHT);
        lblTitle.setFont(new Font(Font.SERIF, Font.BOLD, 12));
        lblTitle.setText(String.format("%s%s%s", startHtml, title, endHtml));
        lblTitle.setForeground(textColor);
        add(lblTitle);
    }

    private void setDescription(String description) {
        JLabel lblDescription = new JLabel();
        lblDescription.setSize(COMPONENT_WIDTH, HEIGHT);
        lblDescription.setFont(new Font(Font.SERIF, Font.PLAIN, 11));
        lblDescription.setText(String.format("%s%s%s", startHtml, String.format("%s...", trimText(description)), endHtml));
        lblDescription.setForeground(textColor);
        add(lblDescription);
    }

    private void setInfo(String info) {
        JLabel lblInfo = new JLabel();
        lblInfo.setSize(COMPONENT_WIDTH, HEIGHT);
        lblInfo.setFont(new Font(Font.SERIF, Font.ITALIC, 10));
        lblInfo.setForeground(Color.LIGHT_GRAY);
        lblInfo.setText(String.format("%s%s%s", startHtml, info, endHtml));
        add(lblInfo);
    }

    private String trimText(String text) {
        return text.length() >= 90 ? text.substring(0, 90) : text.substring(0, text.length() / 2);
    }

    private Color getRandomBgColor(String title) {
        int length = title.length();
        String[] parts = new String[3];
        int[] colors = new int[3];
        parts[0] = title.substring(0, length / 3);
        parts[1] = title.substring(length / 3, 2 * (length / 3));
        parts[2] = title.substring(2 * (length / 3), length);

        colors[0] = Math.abs(parts[0].hashCode() / 10000000);
        colors[1] = Math.abs(parts[1].hashCode() / 10000000);
        colors[2] = Math.abs(parts[2].hashCode() / 10000000);

        Color color = new Color(colors[0], colors[1], colors[2]);
        setInverseTextColor(color);
        return color;
    }

    private void setInverseTextColor(Color bgColor) {
        textColor = new Color(255 - bgColor.getRed(), 255 - bgColor.getGreen(), 255 - bgColor.getBlue());
    }
}
