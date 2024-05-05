import java.awt.*;

public class acube {
    node[] all_vertices;
    double[] cam_to_vertice_dist = new double[]{0, 0, 0, 0, 0, 0, 0, 0};
    boolean[] face_visibibility = new boolean[]{true, true, true, true, true, true};
    face[] all_faces;
    double last_rotated_time = System.currentTimeMillis();


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

    void draw(Graphics g){
        g.setColor(Color.BLUE);
        for(face f: all_faces){
            f.update_node_adjuste();
            g.drawPolygon(f.all_x_adjusted, f.all_y_adjusted, 4);
            System.out.println(f.all_x_adjusted);
        }
    }



}
