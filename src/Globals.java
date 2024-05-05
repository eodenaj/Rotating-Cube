public class Globals {
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 700;
    public static double[] cubeX = new double[]{-10, 10, 10, -10, -10, 10, 10, -10 };
    public static double[] cubeY = new double[]{-10, -10, 10, 10, -10, -10, 10, 10};
    public static double[] cubeZ = new double[]{10, 10, 10, 10, -10, -10, -10, -10};




    public static int cameraX = 0;
    public static int cameraY = 0;
    public static int cameraZ = -100;

    public static final int dist_camera_to_screen = 60;
    static double theta = Math.PI/16;


    static double[] light = new double[]{0, -1, 0};
    static double[] lighting = new double[] {-1 * 400, 400 *(-1 + (double)2/11), 400 * (-1 + (double)4/11), 400 * (-1 + (double)6/11), 400 * (-1 + (double)8/11), 400 * (-1 + (double)10/11 ), 400 * (-1 + (double) 12 /11), 400 * (-1 + (double)14/11), 400 * (-1 + (double)16/11), 400 * (-1 + (double)18/11), 400 * (-1 + (double)20/11), 400 *(-1 + (double)22/11) };
    static double[] lighting2 = new double[]{-400, -320, -240, -160, -80, 0, 80, 160, 240, 320, 400};
    static String[] characters = new String[]{".", ",", "-", "~", ":", "=", "!", "*", "#", "$", "@"};

    static double[][] Rx = new double[][]{
            {1, 0, 0 },
            {0, Math.cos(theta), - Math.sin(theta)},
            {0, Math.sin(theta), Math.cos(theta)}
    };


    static double[][] Ry = new double[][]{
            {Math.cos(theta), 0, Math.sin(theta)},
            {0, 1, 0},
            {- Math.sin(theta), 0, Math.cos(theta)}
    };

    static double[][] Rz = new double[][]{
            {Math.cos(theta), - Math.sin(theta), 0},
            {Math.sin(theta), Math.cos(theta), 0},
            {0, 0, 1}
    };

    public static double[] Project_3D_to_2D(double px, double py, double pz){
        return new double[]{((px - cameraZ) * dist_camera_to_screen/(pz  - cameraZ)), ((py - cameraZ) * dist_camera_to_screen/(pz - cameraZ)) };
    }



    public static double[] matrix_transform3D(double[][] matrix, double[]point){
        double a = matrix[0][0];
        double b = matrix[0][1];
        double c = matrix[0][2];
        double d = matrix[1][0];
        double e = matrix[1][1];
        double f = matrix[1][2];
        double g = matrix[2][0];
        double h = matrix[2][1];
        double i = matrix[2][2];

        double x = (double) point[0];
        double y = (double) point[1];
        double z = (double) point[2];


        return new double[] { a * x + b * y + c * z, d * x + e * y + f * z, g * x + h * y + i * z };
    }

    public static double dist_between_point(double p1x, double p1y, double p1z, double p2x, double p2y, double p2z) {
        double magnitude_square = Math.pow(p1x - p2x, 2) + Math.pow(p1y - p2y, 2) + Math.pow(p1z - p2z, 2);
        return Math.sqrt(magnitude_square);
    }

    public static double[] addv3d(double[] v1, double[] v2){
        return new double[] {
                v1[0] + v2[0],
                v1[1] + v2[1],
                v1[2] + v2[2]
        };
    }

    public static double[] subv3d(double[] v1, double[] v2){
        return new double[] {
                v1[0] - v2[0],
                v1[1] - v2[1],
                v1[2] - v2[2]
        };
    }

    public static double dotProduct(double[] v1, double[] v2){
        return ( v1[0] * v2[0] + v1[1] * v2[1] + v1[2] * v2[2]);
    }

    public static double[] crossProduct(double[] v1, double[] v2){
        double a = v1[0];
        double b = v1[1];
        double c = v1[2];
        double d = v2[0];
        double e = v2[1];
        double f = v2[2];

        return new double[] { b * f - c * e, c * d - a * f, a * e - b * d};
    }

    public static double[] vector_scale(double[] vec, double scale){
        return new double[] {
                vec[0] * scale,
                vec[1] * scale,
                vec[2] * scale
        };
    }


    public static double[] line_plane_intersectoin(double[] lineVec1, double[] lineVec2, double[] point, double[] vecNormal ) {
        double[] line = subv3d(lineVec1, lineVec2);
        double dotMagnitude = dotProduct(vecNormal, line);

        double epsilon = 10* (-6);

        if( Math.abs(dotMagnitude) >  epsilon) {
            double[] w = subv3d(lineVec1, point);
            double fac = - dotProduct(vecNormal, w);
            line = vector_scale(line, fac);
            return addv3d(lineVec1, line);
        }

        return null;
    }
}
