import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.Robot;
import java.util.*;

public class acube {
    node[] all_vertices;
    double[] cam_to_vertice_dist = new double[]{0, 0, 0, 0, 0, 0, 0, 0};
    boolean[] face_visibibility = new boolean[]{false, false, false, false, false, false};
    face[] all_faces;
    double last_rotated_time = System.currentTimeMillis();

    ArrayList<node> closest_nodes = new ArrayList<node>();


    public acube(node[] all_vertices, face[] all_faces){
        this.all_vertices = all_vertices;
        this.all_faces = all_faces;
    }


    void node_update(){
        double current_time = System.currentTimeMillis();
        if( current_time - last_rotated_time <10){
            return;
        }

        for(node NODE: all_vertices){
            double[] newCo = Globals.matrix_transform3D(Globals.Rx, new double[]{NODE.x, NODE.y, NODE.z});
            NODE.x = newCo[0];
            NODE.y = newCo[1];
            NODE.z = newCo[2];
            newCo = Globals.matrix_transform3D(Globals.Ry, new double[] {NODE.x, NODE.y, NODE.z});
            NODE.x = newCo[0];
            NODE.y = newCo[1];
            NODE.z = newCo[2];
            newCo = Globals.matrix_transform3D(Globals.Rz, new double[] {NODE.x, NODE.y, NODE.z});
            NODE.x = newCo[0];
            NODE.y = newCo[1];
            NODE.z = newCo[2];
        }

    }


    void projection_3D2D(){
        for(node i: all_vertices){
            i.coordinate_convert_3D2D();
            i.adjust_for_view();
        }
    }


    void node_to_cam_dist_update(){
        for(int i = 0; i < 8; i++){
            node this_node = all_vertices[i];
            cam_to_vertice_dist[i] = Math.abs(this_node.z - Globals.cameraZ);
        }
    }

    ArrayList<node> closest_node_to_cam(){
        ArrayList<node> closest_nodes = new ArrayList<node>();
        // node with minimum distance
        double min_dist =  Arrays.stream(cam_to_vertice_dist).max().getAsDouble();
        for(int i = 0; i < 8; i++){
            if( cam_to_vertice_dist[i] == min_dist ){
                closest_nodes.add(all_vertices[i]);
            }
        }
        return closest_nodes;
    }

    void update_visible_vertices(){
        face_visibibility = new boolean[] { false, false, false, false, false, false};
        closest_nodes = closest_node_to_cam();
        int num_closest_nodes = closest_nodes.size();

        //find the 3 faces which uses this point
        if(num_closest_nodes == 1){

            for(int i = 0; i < 6; i++){
                if(all_faces[i].has_this_1_vertice(closest_nodes.get(0)) ){
                    face_visibibility[i] = true;
                }
            }

        }

        //find the 2 faces which uses these 2 points
        if(num_closest_nodes == 2){
            for(int i = 0; i < 6; i++){
                if(all_faces[i].has_these_2_vertice(closest_nodes.get(0), closest_nodes.get(1)) ){
                    face_visibibility[i] = true;
                }
            }
        }

        //find the 1 face which uses these four points
        if(num_closest_nodes == 4){
            for(int i = 0; i < 6; i++){
                if(all_faces[i].has_these_4_vertice(closest_nodes.get(0), closest_nodes.get(1), closest_nodes.get(2), closest_nodes.get(3)) ){
                    face_visibibility[i] = true;
                }
            }
        }


    }


    void draw(Graphics g) throws AWTException {
        for(int i = 0; i < 6; i++){
            if(! face_visibibility[i]){
                continue;
            }

            all_faces[i].find_light_exposure();
            int scaler = all_faces[i].light_exposure;
            scaler = 10 - scaler;
            g.setColor(new Color(20 + 16 * scaler, 20 + 16 * scaler, 20 + 16 * scaler, 20 + 16 * scaler));



            all_faces[i].update_node_adjuste();
            g.fillPolygon(all_faces[i].all_x_adjusted, all_faces[i].all_y_adjusted, 4);
            g.drawPolygon(all_faces[i].all_x_adjusted, all_faces[i].all_y_adjusted, 4);
        }




//        for( int i = 0; i < Globals.SCREEN_WIDTH; i += 20){
//            g.drawLine(i, 0, i, Globals.SCREEN_HEIGHT);
//        }
//
//        for( int i = 0; i < Globals.SCREEN_HEIGHT; i += 20){
//            g.drawLine(0, i, Globals.SCREEN_WIDTH, i);
//        }

//        Robot r = new Robot();
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        Rectangle area = new Rectangle(0, 0, Globals.SCREEN_WIDTH, Globals.SCREEN_HEIGHT);
//        BufferedImage screenshot = r.createScreenCapture(area);
//        for(int i = 0; i < Globals.SCREEN_WIDTH; i= i +20){
//
//            for(int j = 0; j < Globals.SCREEN_HEIGHT; j = j +20){
//
//                Color c = new Color(screenshot.getRGB(i,j));// r.getPixelColor(i, j);
//                int blue = c.getBlue();
//                System.out.println(blue);
//                int index = blue/16;
//                if(index > 10){
//                    index = 10;
//                }
//
//                Font font = new Font("Arial", Font.PLAIN, 20);
//                g.setFont(font);
//                g.setColor(new Color(255, 255, 255, 255));
//                if (index != 0){
//                    g.drawString(Globals.characters[index], i, j);
//                }
//                System.out.println(Globals.characters[index]);
//                System.out.println(i);
//                System.out.println(j);
//
//
//                //Rectangle screenRect = new Rectangle(0, 0, Globals.SCREEN_WIDTH, Globals.SCREEN_HEIGHT);
//                ;
//
//            }


        //}

//        for(node i: all_vertices){
//            if( closest_nodes.contains(i)){
//                i.draw(g);
//            }
//        }

    }



}
