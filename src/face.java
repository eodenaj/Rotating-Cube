import java.util.*;

public class face {
    node vertice1, vertice2, vertice3, vertice4 ;
    ArrayList<node> all_vertices = new ArrayList<node>();
    int[] all_x_adjusted;
    int[] all_y_adjusted;
    int light_exposure;

    public face(node vertice1, node vertice2, node vertice3, node vertice4){
        this.vertice1 = vertice1;
        this.vertice2 = vertice2;
        this.vertice3 = vertice3;
        this.vertice4 = vertice4;
        all_vertices.add(vertice1);
        all_vertices.add(vertice2);
        all_vertices.add(vertice3);
        all_vertices.add(vertice4);
        this.all_x_adjusted = new int[]{vertice1.x_adjusted, vertice2.x_adjusted, vertice3.x_adjusted, vertice4.x_adjusted};
        this.all_y_adjusted = new int[]{vertice1.y_adjusted, vertice2.y_adjusted, vertice3.y_adjusted, vertice4.y_adjusted};
    }

    void update_node_adjuste(){
        all_x_adjusted = new int[]{vertice1.x_adjusted, vertice2.x_adjusted, vertice3.x_adjusted, vertice4.x_adjusted};
        all_y_adjusted = new int[]{vertice1.y_adjusted, vertice2.y_adjusted, vertice3.y_adjusted, vertice4.y_adjusted};
    }

    double[] normal_vec(){
        double[] plane_vec1 = new double[]{vertice1.x - vertice2.x, vertice1.y - vertice2.y, vertice1.z - vertice2.z};
        double[] plane_vec2 = new double[]{vertice1.x - vertice3.x, vertice1.y - vertice3.y, vertice1.z - vertice3.z};
        return Globals.crossProduct(plane_vec1, plane_vec2);
    }

    void find_light_exposure(){
        double magnitude = Globals.dotProduct(normal_vec(), Globals.light);
        System.out.println(magnitude);

        for( int i = 0; i < 11; i++){
            if(magnitude < Globals.lighting2[i]){
                light_exposure = i - 1;
                System.out.println(String.format("(%.2f, %d)", magnitude, light_exposure));
                break;
            }
        }
    }

    boolean has_this_1_vertice(node node1){
        if(all_vertices.contains(node1)){
            return true;
        }
        return false;
    }

    boolean has_these_2_vertice(node node1, node node2){
        if(all_vertices.contains(node1) && all_vertices.contains(node2)){
            return true;
        }
        return false;
    }

    boolean has_these_4_vertice(node node1, node node2, node node3, node node4){
        if(all_vertices.contains(node1) && all_vertices.contains(node2) && all_vertices.contains(node3) && all_vertices.contains(node4) ){
            return true;
        }
        return false;
    }






}
