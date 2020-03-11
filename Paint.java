import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage; import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
//List of imported java libraries necessary public class STEMNotes extends JApplet{
private static JTextField txtTypeHere; //Decide to use public STEMNotes() {
}
public static void main(String[] args) {
JFrame frame = new JFrame("STEM Notes");
Panel content = new Panel(); frame.setContentPane(content);
frame.setExtendedState(JFrame.MAXIMIZED_BOTH); frame.setUndecorated(false);
frame.setVisible(true);
frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE ); frame.setVisible(true);
JButton btnNewButton = new JButton("Relevant Resources"); btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16)); btnNewButton.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent arg0) { openNewImage();
} });
btnNewButton.setBounds(0, 0, 200, 40); frame.add(btnNewButton);
txtTypeHere = new JTextField();
txtTypeHere.setFont(new Font("Tahoma", Font.BOLD, 12)); txtTypeHere.setText("Type Here:"); txtTypeHere.setBounds(210, 0, 400, 50); frame.add(txtTypeHere);
txtTypeHere.setColumns(10);
txtTypeHere.setBackground(Color.green); ; }
public static class Panel extends JPanel
implements MouseListener, MouseMotionListener {
private final static int BLACK = 0, RED = 1,
GREEN = 2, BLUE = 3, CYAN = 4, MAGENTA = 5, yellow = 6, WHITE = 111;
private static int StrokeSize = 2; private static boolean drawing = true; private static boolean erasing= false;
private int currentColor = BLACK; private int prevX, prevY;
private boolean dragging;
private Graphics graphicsForDrawing;
Panel() { setBackground(Color.WHITE); addMouseListener(this); addMouseMotionListener(this);
}
public void paintComponent(Graphics g) {
super.paintComponent(g);
int width = getWidth();
int height = getHeight();
int colorSpacing = (height - 56) / 13;
g.setColor(Color.GRAY); g.drawRect(0, 0, width-1, height-1); g.drawRect(1, 1, width-3, height-3); g.drawRect(2, 2, width-5, height-5);
g.fillRect(width - 56, 0, 56, height); g.setColor(Color.WHITE); g.fillRect(width-53, height-53, 50, 50); g.setColor(Color.BLACK); g.drawRect(width-53, height-53, 49, 49); g.drawString("CLEAR", width-48, height-23);
g.setColor(Color.BLACK);
g.fillRect(width-53, 3 + 0*colorSpacing, 50, colorSpacing-3); g.setColor(Color.RED);

g.fillRect(width-53, 3 + 1*colorSpacing, 50, colorSpacing-3); g.setColor(Color.GREEN);
g.fillRect(width-53, 3 + 2*colorSpacing, 50, colorSpacing-3); g.setColor(Color.BLUE);
g.fillRect(width-53, 3 + 3*colorSpacing, 50, colorSpacing-3); g.setColor(Color.CYAN);
g.fillRect(width-53, 3 + 4*colorSpacing, 50, colorSpacing-3); g.setColor(Color.MAGENTA);
g.fillRect(width-53, 3 + 5*colorSpacing, 50, colorSpacing-3); g.setColor(Color.yellow);
g.fillRect(width-53, 3 + 6*colorSpacing, 50, colorSpacing-3);
g.setColor(Color.white);
g.fillRect(width-53, 3 + 7*colorSpacing, 50, colorSpacing-3); g.setColor(Color.black);
g.drawString("Brush '+'", width-50, (7*colorSpacing) + (colorSpacing / 2) + 5);
g.setColor(Color.white);
g.fillRect(width-53, 3 + 8*colorSpacing, 50, colorSpacing-3); g.setColor(Color.black);
g.drawString("Brush '-'", width-50, (8*colorSpacing) + (colorSpacing / 2) + 5);
g.setColor(Color.white);
g.fillRect(width-53, 3 + 9*colorSpacing, 50, colorSpacing-3); g.setColor(Color.black);
g.drawString("Eraser '+'", width-50, (9*colorSpacing) + (colorSpacing / 2) + 5);
g.setColor(Color.white);
g.fillRect(width-53, 3 + 10*colorSpacing, 50, colorSpacing-3); g.setColor(Color.black);
g.drawString("Eraser '-'", width-50, (10*colorSpacing) + (colorSpacing / 2) + 5);
g.setColor(Color.white);
g.fillRect(width-53, 3 + 11*colorSpacing, 50, colorSpacing-3); g.setColor(Color.black);
g.drawString("Insert Ref", width-51, (11*colorSpacing) + (colorSpacing / 2) + 5);
g.setColor(Color.white);
g.fillRect(width-53, 3 + 12*colorSpacing, 50, colorSpacing-3); g.setColor(Color.black);
g.drawString("Save Image", width-53, (12*colorSpacing) + (colorSpacing / 2) + 5);
g.setColor(Color.WHITE);
g.drawRect(width-55, 1 + currentColor*colorSpacing, 53, colorSpacing); g.drawRect(width-54, 2 + currentColor*colorSpacing, 51, colorSpacing-2);
}
private void changeColor(int y) {

int width = getWidth();
int height = getHeight();
int colorSpacing = (height - 56) / 13; int newColor = y / colorSpacing;
if (newColor > -1 && newColor < 7) {
Graphics g = getGraphics();
g.setColor(Color.GRAY);
g.drawRect(width-55, 1 + currentColor*colorSpacing, 53, colorSpacing); g.drawRect(width-54, 2 + currentColor*colorSpacing, 51, colorSpacing-2); currentColor = newColor;
g.setColor(Color.WHITE);
g.drawRect(width-55, 1 + currentColor*colorSpacing, 53, colorSpacing); g.drawRect(width-54, 2 + currentColor*colorSpacing, 51, colorSpacing-2); g.dispose();
}
if (newColor==7) {
if(erasing)
StrokeSize =2;
StrokeSize++; drawing = true; erasing = false;
}
else if (newColor==8) {
if(erasing)
StrokeSize =2;
if(StrokeSize>1) StrokeSize--; drawing = true; erasing = false;
}
else if (newColor==9) {
if(drawing)
StrokeSize =2;
StrokeSize++;
currentColor = WHITE; drawing = false; erasing = true;
}

else if (newColor==10) {
if(drawing)
StrokeSize =2;
if(StrokeSize>1) StrokeSize--; currentColor = WHITE; drawing = false; erasing = true;
}
else if (newColor==11) {
try {
JFileChooser fc = new JFileChooser();
int result = fc.showOpenDialog(null);
if (result == JFileChooser.APPROVE_OPTION) {
File file = fc.getSelectedFile();
myPicture = ImageIO.read(file); }
if(myPicture != null){
graphicsForDrawing = getGraphics();
graphicsForDrawing.drawImage(myPicture, 0, 0, this); graphicsForDrawing.dispose();
}
} catch (IOException e) {
// TODO Auto-generated catch block
e.printStackTrace(); }
}
else if(newColor==12) {
String str = JOptionPane
.showInputDialog(null, "Enter File Name : ");
BufferedImage bufImage = new BufferedImage(this.getSize().width, this.getSize().height,BufferedImage.TYPE_INT_RGB);
this.paint(bufImage.createGraphics());
try {
ImageIO.write(bufImage, "jpg", new File(str + ".jpg"));
} catch (IOException ex) {
 BufferedImage myPicture = null ;

ex.printStackTrace(); }
} }
private void setUpDrawingGraphics() { graphicsForDrawing = getGraphics(); switch (currentColor) {
case BLACK:
 graphicsForDrawing.setColor(Color.BLACK);
break; case RED:
graphicsForDrawing.setColor(Color.RED);
break;
case GREEN:
graphicsForDrawing.setColor(Color.GREEN);
break; case BLUE:
graphicsForDrawing.setColor(Color.BLUE);
break; case CYAN:
graphicsForDrawing.setColor(Color.CYAN);
break;
case MAGENTA:
graphicsForDrawing.setColor(Color.MAGENTA);
break; case yellow:
graphicsForDrawing.setColor(Color.yellow);
break; case WHITE:
graphicsForDrawing.setColor(Color.WHITE); break;
}
 }
public void mousePressed(MouseEvent evt) {
int x = evt.getX(); int y = evt.getY();
int width = getWidth(); int height = getHeight();
if (dragging == true) return;
if (x > width - 53) { if (y > height - 53)
repaint(); else

changeColor(y); }
else if (x > 3 && x < width - 56 && y > 3 && y < height - 3) {
prevX = x;
prevY = y;
dragging = true; setUpDrawingGraphics();
} }
public void mouseReleased(MouseEvent evt) { if (dragging == false)
return; dragging = false;
graphicsForDrawing.dispose(); graphicsForDrawing = null;
this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); }
public void mouseDragged(MouseEvent evt) {
this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
if (dragging == false) return;
int x = evt.getX(); int y = evt.getY();
if (x < 3) x = 3;
if (x > getWidth() - 57) x = getWidth() - 57;
if (y < 3) y = 3;
if (y > getHeight() - 4) y = getHeight() - 4;
Graphics2D g2 = (Graphics2D) graphicsForDrawing; g2.setStroke(new BasicStroke(StrokeSize)); g2.draw(new Line2D.Float(prevX, prevY, x, y));
prevX = x;

prevY = y; }
public void mouseEntered(MouseEvent evt) { } public void mouseExited(MouseEvent evt) { } public void mouseClicked(MouseEvent evt) { } public void mouseMoved(MouseEvent evt) { }
}
private static void openNewImage() {
JFrame imageJframe = new JFrame("Formula") ;
imageJframe.setExtendedState(JFrame.MAXIMIZED_BOTH); imageJframe.setSize(600,699); imageJframe.setUndecorated(false); imageJframe.setVisible(true);
imageJframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); imageJframe.setVisible(true);
ImageIcon picture = new ImageIcon("img/Formulas.jpg");
JLabel imageLabel = new JLabel((Icon) picture); imageJframe.add(imageLabel);
} }