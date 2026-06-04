package Helper;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.geom.*;

public class UITheme {

    // Color Palette - Dark Gaming Theme
    public static final Color BG_DARK        = new Color(10, 12, 20);
    public static final Color BG_CARD        = new Color(18, 22, 35);
    public static final Color BG_INPUT       = new Color(25, 30, 48);
    public static final Color ACCENT_CYAN    = new Color(0, 212, 255);
    public static final Color ACCENT_GOLD    = new Color(255, 184, 0);
    public static final Color ACCENT_PURPLE  = new Color(120, 60, 220);
    public static final Color TEXT_PRIMARY   = new Color(220, 230, 255);
    public static final Color TEXT_SECONDARY = new Color(100, 120, 160);
    public static final Color BORDER_COLOR   = new Color(40, 55, 90);
    public static final Color SUCCESS        = new Color(0, 220, 130);
    public static final Color DANGER         = new Color(255, 70, 90);

    public static Font FONT_TITLE;
    public static Font FONT_LABEL;
    public static Font FONT_BUTTON;
    public static Font FONT_INPUT;
    public static Font FONT_SMALL;

    static {
        try {
            FONT_TITLE  = new Font("Segoe UI", Font.BOLD, 22);
            FONT_LABEL  = new Font("Segoe UI", Font.PLAIN, 13);
            FONT_BUTTON = new Font("Segoe UI", Font.BOLD, 12);
            FONT_INPUT  = new Font("Segoe UI", Font.PLAIN, 13);
            FONT_SMALL  = new Font("Segoe UI", Font.PLAIN, 11);
        } catch (Exception e) {
            FONT_TITLE  = new Font("SansSerif", Font.BOLD, 22);
            FONT_LABEL  = new Font("SansSerif", Font.PLAIN, 13);
            FONT_BUTTON = new Font("SansSerif", Font.BOLD, 12);
            FONT_INPUT  = new Font("SansSerif", Font.PLAIN, 13);
            FONT_SMALL  = new Font("SansSerif", Font.PLAIN, 11);
        }
    }

    public static void applyGlobalTheme() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {}
        UIManager.put("Panel.background", BG_DARK);
        UIManager.put("OptionPane.background", BG_CARD);
        UIManager.put("OptionPane.messageForeground", TEXT_PRIMARY);
        UIManager.put("Button.background", ACCENT_CYAN);
        UIManager.put("Button.foreground", BG_DARK);
    }

    // Styled JLabel for titles
    public static JLabel makeTitle(String text) {
        JLabel lbl = new JLabel(text, SwingConstants.CENTER);
        lbl.setFont(FONT_TITLE);
        lbl.setForeground(ACCENT_CYAN);
        return lbl;
    }

    // Section subtitle
    public static JLabel makeSubtitle(String text) {
        JLabel lbl = new JLabel(text, SwingConstants.CENTER);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lbl.setForeground(TEXT_SECONDARY);
        return lbl;
    }

    // Regular label
    public static JLabel makeLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(FONT_LABEL);
        lbl.setForeground(TEXT_PRIMARY);
        return lbl;
    }

    // Styled text field
    public static JTextField makeTextField() {
        JTextField tf = new JTextField();
        styleInputField(tf);
        return tf;
    }

    // Styled password field
    public static JPasswordField makePasswordField() {
        JPasswordField pf = new JPasswordField();
        styleInputField(pf);
        return pf;
    }

    // Styled combobox
    public static JComboBox<String> makeComboBox() {
        JComboBox<String> cb = new JComboBox<>();
        cb.setFont(FONT_INPUT);
        cb.setBackground(BG_INPUT);
        cb.setForeground(TEXT_PRIMARY);
        cb.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 1));
        cb.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                    int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setBackground(isSelected ? ACCENT_CYAN.darker() : BG_INPUT);
                setForeground(isSelected ? BG_DARK : TEXT_PRIMARY);
                setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
                return this;
            }
        });
        return cb;
    }

    // Primary button (cyan)
    public static JButton makePrimaryButton(String text) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isPressed()) {
                    g2.setColor(ACCENT_CYAN.darker());
                } else if (getModel().isRollover()) {
                    g2.setColor(ACCENT_CYAN.brighter());
                } else {
                    g2.setColor(ACCENT_CYAN);
                }
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2.setColor(BG_DARK);
                g2.setFont(FONT_BUTTON);
                FontMetrics fm = g2.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                g2.drawString(getText(), x, y);
                g2.dispose();
            }
        };
        btn.setPreferredSize(new Dimension(130, 38));
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    // Secondary button (outline)
    public static JButton makeSecondaryButton(String text) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Color bg = getModel().isRollover() ? new Color(40, 55, 90) : BG_CARD;
                g2.setColor(bg);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2.setColor(BORDER_COLOR);
                g2.setStroke(new BasicStroke(1.5f));
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 10, 10);
                g2.setColor(TEXT_PRIMARY);
                g2.setFont(FONT_BUTTON);
                FontMetrics fm = g2.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                g2.drawString(getText(), x, y);
                g2.dispose();
            }
        };
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    // Danger button (red)
    public static JButton makeDangerButton(String text) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Color bg = getModel().isRollover() ? DANGER.brighter() : DANGER;
                g2.setColor(bg);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2.setColor(Color.WHITE);
                g2.setFont(FONT_BUTTON);
                FontMetrics fm = g2.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                g2.drawString(getText(), x, y);
                g2.dispose();
            }
        };
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    // Success button (green)
    public static JButton makeSuccessButton(String text) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Color bg = getModel().isRollover() ? SUCCESS.brighter() : SUCCESS;
                g2.setColor(bg);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2.setColor(BG_DARK);
                g2.setFont(FONT_BUTTON);
                FontMetrics fm = g2.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                g2.drawString(getText(), x, y);
                g2.dispose();
            }
        };
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    // Big dashboard menu button
    public static JButton makeMenuButton(String text, String icon) {
        JButton btn = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Color bg = getModel().isRollover() ? new Color(30, 40, 65) : BG_CARD;
                g2.setColor(bg);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 14, 14);
                // Border
                GradientPaint border = getModel().isRollover()
                    ? new GradientPaint(0, 0, ACCENT_CYAN, getWidth(), getHeight(), ACCENT_PURPLE)
                    : new GradientPaint(0, 0, BORDER_COLOR, getWidth(), getHeight(), BORDER_COLOR);
                g2.setPaint(border);
                g2.setStroke(new BasicStroke(1.5f));
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 14, 14);
                // Icon
                g2.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 28));
                FontMetrics fmIcon = g2.getFontMetrics();
                int iconX = (getWidth() - fmIcon.stringWidth(icon)) / 2;
                g2.setColor(ACCENT_CYAN);
                g2.drawString(icon, iconX, getHeight()/2 - 4);
                // Text
                g2.setFont(FONT_BUTTON);
                FontMetrics fm = g2.getFontMetrics();
                int textX = (getWidth() - fm.stringWidth(text)) / 2;
                g2.setColor(TEXT_PRIMARY);
                g2.drawString(text, textX, getHeight()/2 + 20);
                g2.dispose();
            }
        };
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    // Styled JTable
    public static void styleTable(JTable table) {
        table.setBackground(BG_CARD);
        table.setForeground(TEXT_PRIMARY);
        table.setFont(FONT_INPUT);
        table.setRowHeight(32);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setSelectionBackground(new Color(0, 212, 255, 50));
        table.setSelectionForeground(ACCENT_CYAN);
        table.setGridColor(BORDER_COLOR);

        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(20, 28, 50));
        header.setForeground(ACCENT_CYAN);
        header.setFont(new Font("Segoe UI", Font.BOLD, 12));
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, ACCENT_CYAN));
        header.setReorderingAllowed(false);

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object value,
                    boolean isSelected, boolean hasFocus, int row, int col) {
                super.getTableCellRendererComponent(t, value, isSelected, hasFocus, row, col);
                setBackground(isSelected ? new Color(0, 212, 255, 40) : (row % 2 == 0 ? BG_CARD : new Color(22, 28, 44)));
                setForeground(isSelected ? ACCENT_CYAN : TEXT_PRIMARY);
                setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
                return this;
            }
        });
    }

    // Styled JScrollPane
    public static JScrollPane makeScrollPane(JTable table) {
        JScrollPane sp = new JScrollPane(table);
        sp.setBackground(BG_CARD);
        sp.getViewport().setBackground(BG_CARD);
        sp.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 1));
        sp.getVerticalScrollBar().setBackground(BG_DARK);
        sp.getHorizontalScrollBar().setBackground(BG_DARK);
        return sp;
    }

    private static void styleInputField(JTextField tf) {
        tf.setFont(FONT_INPUT);
        tf.setBackground(BG_INPUT);
        tf.setForeground(TEXT_PRIMARY);
        tf.setCaretColor(ACCENT_CYAN);
        tf.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 1),
            BorderFactory.createEmptyBorder(4, 10, 4, 10)
        ));
    }

    // Dark panel with gradient background
    public static JPanel makeGradientPanel() {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                // Background gradient
                GradientPaint gp = new GradientPaint(0, 0, BG_DARK, getWidth(), getHeight(), new Color(15, 20, 40));
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
                // Subtle grid pattern
                g2.setColor(new Color(255, 255, 255, 5));
                for (int i = 0; i < getWidth(); i += 40) {
                    g2.drawLine(i, 0, i, getHeight());
                }
                for (int i = 0; i < getHeight(); i += 40) {
                    g2.drawLine(0, i, getWidth(), i);
                }
                // Top glow line
                LinearGradientPaint glowPaint = new LinearGradientPaint(
                    0, 0, getWidth(), 0,
                    new float[]{0f, 0.5f, 1f},
                    new Color[]{
                        new Color(0, 212, 255, 0),
                        new Color(0, 212, 255, 80),
                        new Color(0, 212, 255, 0)
                    }
                );
                g2.setPaint(glowPaint);
                g2.setStroke(new BasicStroke(2));
                g2.drawLine(0, 0, getWidth(), 0);
                g2.dispose();
            }
        };
    }

    // Card panel (rounded rectangle)
    public static JPanel makeCardPanel() {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(BG_CARD);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);
                g2.setColor(BORDER_COLOR);
                g2.setStroke(new BasicStroke(1f));
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 16, 16);
                g2.dispose();
            }
            @Override
            public boolean isOpaque() { return false; }
        };
    }
}
