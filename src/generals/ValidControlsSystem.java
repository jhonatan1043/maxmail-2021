/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generals;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Programador 1
 */
public class ValidControlsSystem {

    private static void disableControls(javax.swing.JPanel form) {
        for (int i = 0; i < form.getComponentCount(); i++) {
            if (form.getComponent(i) instanceof JTextField) {
                ((JTextField) form.getComponent(i)).setEnabled(false);
            } else if (form.getComponent(i) instanceof JComboBox) {
                ((JComboBox) form.getComponent(i)).setEnabled(false);
            } else if (form.getComponent(i) instanceof JSpinner) {
                ((JSpinner) form.getComponent(i)).setEnabled(false);
            } else if (form.getComponent(i) instanceof JPanel) {
                disableControls(((JPanel) form.getComponent(i)));
            } else if (form.getComponent(i) instanceof JLayeredPane) {
                disableControls(((JLayeredPane) form.getComponent(i)));
            } else if (form.getComponent(i) instanceof JButton) {
                ((JButton) form.getComponent(i)).setEnabled(false);
            } else if (form.getComponent(i) instanceof JCheckBox) {
                ((JCheckBox) form.getComponent(i)).setEnabled(false);
            } else if (form.getComponent(i) instanceof JTextArea) {
                ((JTextArea) form.getComponent(i)).setEditable(false);
            } else if (form.getComponent(i) instanceof JEditorPane) {
                ((JEditorPane) form.getComponent(i)).setEnabled(false);
            }
        }
    }

    public static void disableControls(javax.swing.JLayeredPane form) {
        for (int i = 0; i < form.getComponentCount(); i++) {
            if (form.getComponent(i) instanceof JTextField) {
                ((JTextField) form.getComponent(i)).setEnabled(false);
            } else if (form.getComponent(i) instanceof JComboBox) {
                ((JComboBox) form.getComponent(i)).setEnabled(false);
            } else if (form.getComponent(i) instanceof JSpinner) {
                ((JSpinner) form.getComponent(i)).setEnabled(false);
            } else if (form.getComponent(i) instanceof JPanel) {
                disableControls(((JPanel) form.getComponent(i)));
            } else if (form.getComponent(i) instanceof JLayeredPane) {
                disableControls(((JLayeredPane) form.getComponent(i)));
            } else if (form.getComponent(i) instanceof JButton) {
                ((JButton) form.getComponent(i)).setEnabled(false);
            } else if (form.getComponent(i) instanceof JCheckBox) {
                ((JCheckBox) form.getComponent(i)).setEnabled(false);
            } else if (form.getComponent(i) instanceof JTextArea) {
                ((JTextArea) form.getComponent(i)).setEditable(false);
            } else if (form.getComponent(i) instanceof JEditorPane) {
                ((JEditorPane) form.getComponent(i)).setEnabled(false);
            }
        }
    }

    private static void enabledControls(javax.swing.JPanel form) {
        for (int i = 0; i < form.getComponentCount(); i++) {
            if (form.getComponent(i) instanceof JTextField) {
                ((JTextField) form.getComponent(i)).setEnabled(true);
            } else if (form.getComponent(i) instanceof JComboBox) {
                ((JComboBox) form.getComponent(i)).setEnabled(true);
            } else if (form.getComponent(i) instanceof JSpinner) {
                ((JSpinner) form.getComponent(i)).setEnabled(true);
            } else if (form.getComponent(i) instanceof JPanel) {
                enabledControls(((JPanel) form.getComponent(i)));
            } else if (form.getComponent(i) instanceof JLayeredPane) {
                enabledControls(((JLayeredPane) form.getComponent(i)));
            } else if (form.getComponent(i) instanceof JButton) {
                ((JButton) form.getComponent(i)).setEnabled(true);
            } else if (form.getComponent(i) instanceof JCheckBox) {
                ((JCheckBox) form.getComponent(i)).setEnabled(true);
            } else if (form.getComponent(i) instanceof JTextArea) {
                ((JTextArea) form.getComponent(i)).setEditable(true);
            } else if (form.getComponent(i) instanceof JEditorPane) {
                ((JEditorPane) form.getComponent(i)).setEnabled(true);
            }
        }
    }

    public static void enabledControls(javax.swing.JLayeredPane form) {
        for (int i = 0; i < form.getComponentCount(); i++) {
            if (form.getComponent(i) instanceof JTextField) {
                ((JTextField) form.getComponent(i)).setEnabled(true);
            } else if (form.getComponent(i) instanceof JComboBox) {
                ((JComboBox) form.getComponent(i)).setEnabled(true);
            } else if (form.getComponent(i) instanceof JSpinner) {
                ((JSpinner) form.getComponent(i)).setEnabled(true);
            } else if (form.getComponent(i) instanceof JPanel) {
                enabledControls(((JPanel) form.getComponent(i)));
            } else if (form.getComponent(i) instanceof JLayeredPane) {
                enabledControls(((JLayeredPane) form.getComponent(i)));
            } else if (form.getComponent(i) instanceof JButton) {
                ((JButton) form.getComponent(i)).setEnabled(true);
            } else if (form.getComponent(i) instanceof JCheckBox) {
                ((JCheckBox) form.getComponent(i)).setEnabled(true);
            } else if (form.getComponent(i) instanceof JTextArea) {
                ((JTextArea) form.getComponent(i)).setEditable(true);
            } else if (form.getComponent(i) instanceof JEditorPane) {
                ((JEditorPane) form.getComponent(i)).setEnabled(true);
            }
        }
    }

}
