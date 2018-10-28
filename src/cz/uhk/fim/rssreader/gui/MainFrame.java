package cz.uhk.fim.rssreader.gui;

import cz.uhk.fim.rssreader.model.RSSItem;
import cz.uhk.fim.rssreader.model.RSSList;
import cz.uhk.fim.rssreader.utils.FileUtils;
import cz.uhk.fim.rssreader.utils.RSSParser;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;

public class MainFrame extends JFrame {

    private static final String VALIDATION_TYPE = "VALIDATION_TYPE";
    private static final String IO_LOAD_TYPE = "IO_LOAD_TYPE";
    private static final String IO_SAVE_TYPE = "IO_SAVE_TYPE";

    private JLabel lblErrorMessage;
    private JTextField txtPathField;
    private RSSList rssList;

    public MainFrame() {
        init();
    }

    private void init() {
        setTitle("RSS Reader");
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initContentUI();
    }

    private void initContentUI() {
        JPanel controlPanel = new JPanel(new BorderLayout());

        JButton btnLoad = new JButton("Load");
        txtPathField = new JTextField();
        JButton btnSave = new JButton("Save");
        lblErrorMessage = new JLabel();
        lblErrorMessage.setForeground(Color.RED);
        lblErrorMessage.setHorizontalAlignment(SwingConstants.CENTER);

        controlPanel.add(btnLoad, BorderLayout.WEST);
        controlPanel.add(txtPathField, BorderLayout.CENTER);
        controlPanel.add(btnSave, BorderLayout.EAST);
        controlPanel.add(lblErrorMessage, BorderLayout.SOUTH);

        add(controlPanel, BorderLayout.NORTH);

        JTextArea txtContent = new JTextArea();
        add(new JScrollPane(txtContent), BorderLayout.CENTER);

        btnLoad.addActionListener(e -> {
            if(validateInput()) {
                try {
                    txtContent.setText(FileUtils.loadStringFromFile(txtPathField.getText()));
                } catch (IOException e1) {
                    showErrorMessage(IO_LOAD_TYPE);
                    e1.printStackTrace();
                }
            }
        });

        btnSave.addActionListener(e -> {
            if(validateInput()) {
//                try {
//                    FileUtils.saveStringToFile(txtPathField.getText(), txtContent.getText().getBytes(StandardCharsets.UTF_8));
//                } catch (IOException e1) {
//                    showErrorMessage(IO_SAVE_TYPE);
//                    e1.printStackTrace();
//                }

                try {
                    rssList = new RSSParser().getParsedRSS(txtPathField.getText());
                    txtContent.setText("");
                    for(RSSItem item : rssList.getAllItems()) {
                        txtContent.append(String.format("%s - autor: %s%n", item.getTitle(), item.getAuthor()));
                    }
                } catch (IOException | SAXException | ParserConfigurationException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    private void showErrorMessage(String type) {
        String message;
        switch(type){
            case VALIDATION_TYPE:
                message = "Zadávací pole nemůže být prázdné!";
                break;
            case IO_LOAD_TYPE:
                message = "Chyba při načítání souboru!";
                break;
            case IO_SAVE_TYPE:
                message = "Chyba při ukládání souboru!";
                break;
            default:
                message = "Bůh ví, co se stalo";
                break;
        }
        lblErrorMessage.setText(message);
        lblErrorMessage.setVisible(true);
    }

    private boolean validateInput(){
        lblErrorMessage.setVisible(false);
        if(txtPathField.getText().trim().isEmpty()) {
            showErrorMessage(VALIDATION_TYPE);
            return false;
        }
        return true;
    }
}
