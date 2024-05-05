public class face {
    node vertice1, vertice2, vertice3, vertice4 ;
    node[] all_vertices;

    int[] all_x_adjusted;
    int[] all_y_adjusted;

    public face(node vertice1, node vertice2, node vertice3, node vertice4){
        this.vertice1 = vertice1;
        this.vertice2 = vertice2;
        this.vertice3 = vertice3;
        this.vertice4 = vertice4;
        this.all_vertices = new node[]{ vertice1, vertice2, vertice3, vertice4};
        this.all_x_adjusted = new int[]{vertice1.x_adjusted, vertice2.x_adjusted, vertice3.x_adjusted, vertice4.x_adjusted};
        this.all_y_adjusted = new int[]{vertice1.y_adjusted, vertice2.y_adjusted, vertice3.y_adjusted, vertice4.y_adjusted};
    }

    void update_node_adjuste(){
        all_x_adjusted = new int[]{vertice1.x_adjusted, vertice2.x_adjusted, vertice3.x_adjusted, vertice4.x_adjusted};
        all_y_adjusted = new int[]{vertice1.y_adjusted, vertice2.y_adjusted, vertice3.y_adjusted, vertice4.y_adjusted};
    }


}
