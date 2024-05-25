import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Finestra extends JFrame {
    private static final long serialVersionUID = 1L;
    private ImagePanel imagePanel;
    private ScheduledExecutorService executor;
    private ArrayList<Color[][]> buffer;
    private int fps;

    public Finestra(String titolo, int larghezza, int altezza, int fps) {
        setTitle(titolo);
        imagePanel = new ImagePanel(larghezza, altezza);
        add(imagePanel);

        buffer = new ArrayList<>();
        this.fps = fps;

        JPanel buttonPanel = new JPanel(new GridLayout(1, 5));
        for (int i = 0; i < 5; i++) {
            JButton button = new JButton("Es. " + (i + 1));
            final int index = i;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switch (index) {
                        case 0:
                            MainClass.es1();
                            break;
                        case 1:
                            MainClass.es2();
                            break;
                        case 2:
                            MainClass.es3();
                            break;
                        case 3:
                            MainClass.es4();
                            break;
                        case 4:
                            MainClass.es5();
                            break;
                        default:
                            break;
                    }
                }
            });
            buttonPanel.add(button);
        }
        add(buttonPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(larghezza, altezza+50);
    }

    // Metodo per disegnare con un ritardo tra i frame
    public void disegna(Color[][] immagine) {
        // Aggiungi il fotogramma al buffer
        buffer.add(immagine);

        // Se è la prima volta che viene chiamato, avvia il timer per eseguire il disegno
        if (executor == null) {
            executor = Executors.newSingleThreadScheduledExecutor();
            executor.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                	//System.out.println("frame residui: " + buffer.size());
                    disegnaFrameSuccessivo();
                }
            }, 0, 1000/fps, TimeUnit.MILLISECONDS);
        }
    }

    // Metodo per disegnare il fotogramma successivo
    private void disegnaFrameSuccessivo() {
        if (!buffer.isEmpty()) {
            imagePanel.disegna(buffer.get(0));
            
            //System.out.println("questo fotogramma ha il primo pixel: " + buffer.get(0)[0][0].getBlue());
            buffer.remove(0); // Rimuovi il fotogramma appena disegnato
        } else {
            // Se siamo arrivati all'ultimo fotogramma, ferma il timer
            stopAnimazione();
        }
    }

    // Metodo per fermare l'animazione
    private void stopAnimazione() {
        if (executor != null && !executor.isShutdown()) {
            executor.shutdown();
            executor = null;
        }
    }

    // Classe interna per il pannello dell'immagine
    private class ImagePanel extends JPanel {
        private static final long serialVersionUID = 1L;
        private BufferedImage bufferedImage;

        public ImagePanel(int w, int h) {
            setSize(w, h);
            bufferedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(bufferedImage, 0, 0, this);
        }

        // Metodo per disegnare un fotogramma sull'immagine
        public void disegna(Color[][] immagine) {
            Graphics2D g2d = bufferedImage.createGraphics();
            for (int i = 0; i < bufferedImage.getHeight(); i++) {
                for (int j = 0; j < bufferedImage.getWidth(); j++) {
                    g2d.setColor(immagine[i][j]);
                    g2d.fillRect(j, i, 1, 1);
                }
            }
            g2d.dispose();
            repaint();
        }
    }
}
