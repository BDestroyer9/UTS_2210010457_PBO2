// Nama Folder 
package CatatanApp;

// Komponenen Libraries GUI
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.event.*;

// Nama File
public class CatatanHarian {

    // Membuat list untuk menyimpan catatan
    private static ArrayList<String> catatanList = new ArrayList<>();

    public static void main(String[] args) {
        // Membuat frame utama aplikasi
        JFrame frame = new JFrame("Catatan Harian");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel untuk menampung komponen
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.white);

        // Text Area untuk menulis catatan
        JTextArea textArea = new JTextArea();
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);
        textArea.setBackground(Color.yellow);
        
        // Label untuk menampilkan jumlah kata
        JLabel wordCountLabel = new JLabel("Jumlah Kata: 0");
        panel.add(wordCountLabel, BorderLayout.SOUTH);

        // Panel untuk tombol
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // Tombol untuk simpan catatan
        JButton btnSimpan = new JButton("Simpan");
        buttonPanel.add(btnSimpan);

        // Tombol untuk menambah catatan
        JButton btnTambah = new JButton("Tambah");
        buttonPanel.add(btnTambah);

        // Tombol untuk mengubah catatan
        JButton btnUbah = new JButton("Ubah");
        buttonPanel.add(btnUbah);

        // Tombol untuk menghapus catatan
        JButton btnHapus = new JButton("Hapus");
        buttonPanel.add(btnHapus);

        panel.add(buttonPanel, BorderLayout.NORTH);
        frame.add(panel);

        // Menambahkan listener untuk tombol simpan
        btnSimpan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String catatan = textArea.getText();
                if (!catatan.isEmpty()) {
                    catatanList.add(catatan);
                    JOptionPane.showMessageDialog(frame, "Catatan disimpan!");
                    textArea.setText(""); // Kosongkan text area setelah menyimpan
                } else {
                    JOptionPane.showMessageDialog(frame, "Catatan tidak boleh kosong!");
                }
            }
        });

        // Menambahkan listener untuk tombol tambah
        btnTambah.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String catatan = textArea.getText();
                if (!catatan.isEmpty()) {
                    catatanList.add(catatan);
                    textArea.setText(""); // Kosongkan text area setelah menambah
                } else {
                    JOptionPane.showMessageDialog(frame, "Catatan tidak boleh kosong!");
                }
            }
        });

        // Menambahkan listener untuk tombol ubah
        btnUbah.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String catatan = textArea.getText();
                int selectedIndex = JOptionPane.showOptionDialog(frame, "Pilih catatan yang ingin diubah",
                        "Pilih Catatan", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                        null, catatanList.toArray(), null);

                if (selectedIndex >= 0) {
                    String newCatatan = JOptionPane.showInputDialog(frame, "Masukkan catatan baru:");
                    if (newCatatan != null && !newCatatan.trim().isEmpty()) {
                        catatanList.set(selectedIndex, newCatatan);
                        JOptionPane.showMessageDialog(frame, "Catatan berhasil diubah!");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Catatan baru tidak boleh kosong!");
                    }
                }
            }
        });

        // Menambahkan listener untuk tombol hapus
        btnHapus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = JOptionPane.showOptionDialog(frame, "Pilih catatan yang ingin dihapus",
                        "Pilih Catatan", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                        null, catatanList.toArray(), null);

                if (selectedIndex >= 0) {
                    catatanList.remove(selectedIndex);
                    JOptionPane.showMessageDialog(frame, "Catatan berhasil dihapus!");
                }
            }
        });

        // Menambahkan DocumentListener untuk menghitung jumlah kata saat pengetikan
        textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateWordCount();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateWordCount();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateWordCount();
            }

            // Method untuk menghitung jumlah kata
            private void updateWordCount() {
                String text = textArea.getText();
                String[] words = text.split("\\s+"); // Memisahkan kata berdasarkan spasi
                int wordCount = words.length;

                // Jika teks kosong, set jumlah kata menjadi 0
                if (text.trim().isEmpty()) {
                    wordCount = 0;
                }

                // Update label untuk menampilkan jumlah kata
                wordCountLabel.setText("Jumlah Kata: " + wordCount);
            }
        });

        // Frame visible
        frame.setVisible(true);

        // Menambahkan catatan awal jika ingin
        catatanList.add("Contoh catatan pertama!");
        catatanList.add("Contoh catatan kedua!");
    }
}
