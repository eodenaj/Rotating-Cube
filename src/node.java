public class node {
    //3D coordinate
    double x, y, z;
    node[] neighbour = new node[]{};
    face[] faces = new face[]{};

    //2D coordinate
    double x2, y2;

    //adjust to view 2D coordinate
    int x_adjusted, y_adjusted;

    public node(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
        this.x2 = x;
        this.y2 = y;
    }

    void coordinate_convert_3D2D(){
        x2 = x * Globals.dist_camera_to_screen/(z  + Globals.cameraZ);
        y2 = y * Globals.dist_camera_to_screen/(z  + Globals.cameraZ);
    }

    void adjust_for_view(){
        x_adjusted = (int)((x2 - Globals.cameraX) * 10 )+ Globals.SCREEN_WIDTH/ 2 ;
        y_adjusted = (int)((y2 - Globals.cameraY) * 10) + Globals.SCREEN_HEIGHT/2 ;
    }


}