import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class GamePanel extends JPanel implements KeyListener, ActionListener, MouseListener, MouseMotionListener {
    Timer timer;
    Image background;

    boolean mouseDown = false;
    boolean[] keys = new boolean[KeyEvent.KEY_LAST + 1];
    node[] cube_vertices;
    node node1, node2, node3, node4, node5, node6, node7, node8;

    face face1, face2, face3, face4, face5, face6;
    face[] cube_faces;

    acube CUBE;


    public GamePanel() {
        setFocusable(true);
        addKeyListener(this);
        addMouseListener(this);
        requestFocus();

        this.node1 = new node(-10, -10, 10);
        this.node2 = new node(10, -10, 10);
        this.node3 = new node(10, 10, 10);
        this.node4 = new node(-10, 10, 10);
        this.node5 = new node(-10, -10, -10);
        this.node6 = new node(10, -10, -10);
        this.node7 = new node(10, 10, -10);
        this.node8 = new node(-10, 10, -10);

        this.cube_vertices = new node[]{node1, node2, node3, node4, node5, node6, node7, node8};

        this.face1 = new face(node1, node2, node3, node4);
        this.face2 = new face(node2, node6, node7, node3);
        this.face3 = new face(node6, node5, node8, node7);
        this.face4 = new face(node5, node1, node4, node8);
        this.face5 = new face(node1, node2, node6, node5);
        this.face6 = new face(node4, node3, node7, node8);

        this.cube_faces = new face[]{face1, face2, face3, face4, face5, face6};

        this.CUBE = new acube(cube_vertices, cube_faces);




        timer = new Timer(1000/5, this);
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    public void move() {
        CUBE.node_update();
        CUBE.projection_3D2D();
        CUBE.node_to_cam_dist_update();
        CUBE.closest_node_to_cam();
        CUBE.update_visible_vertices();

    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(new Color(0,0, 0, 249));
        g.fillRect(0, 0, Globals.SCREEN_WIDTH, Globals.SCREEN_HEIGHT);
        try {
            CUBE.draw(g);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }



    public void mousePressed(MouseEvent e) {
        mouseDown = true;
        //give mouse coordinate on panel
        Point mouse = MouseInfo.getPointerInfo().getLocation();
        Point offset = getLocationOnScreen();
    }

    public void mouseReleased(MouseEvent e) {
        mouseDown = false;
    }
    public void mouseMoved(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        keys[code] = true;
    }

    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        keys[code] = false;
    }

    public void keyTyped(KeyEvent e) {
    }




}