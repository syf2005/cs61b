public class NBody {
    public static double readRadius(String filename){
        In in = new In(filename);
        in.readInt(); /* PASS THE FIRST LINE */
        return in.readDouble();
    }

    public static Planet[] readPlanets(String filename){
        In in = new In(filename);
        int items = in.readInt();
        Planet[] array = new Planet[items];
        in.readDouble();
        for (int i=0;i<items;i++){
            array[i] = new Planet(in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readString());
        }

        return array;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet[] array = readPlanets(filename);
        double radius = readRadius(filename);

        StdDraw.setScale(-radius, radius);
        StdDraw.picture(0,0,"images/starfield.jpg");

        for (Planet element:array){
            element.draw();
        }

        StdDraw.enableDoubleBuffering();
        double time=0;
        while (time < T){
            double[] xForces = new double[array.length];
            double[] yForces = new double[array.length];
            for (int i=0;i<array.length;i++){
                xForces[i]=array[i].calcNetForceExertedByX(array);
                yForces[i]=array[i].calcNetForceExertedByY(array);
            }
            for (int i=0;i<array.length;i++){
                array[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0,0,"images/starfield.jpg");
            for (Planet element:array){
                element.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }

    StdOut.printf("%d\n", array.length);
    StdOut.printf("%.2e\n", radius);
    for (int i = 0; i < array.length; i++) {
        StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            array[i].xxPos, array[i].yyPos, array[i].xxVel,
            array[i].yyVel, array[i].mass, array[i].imgFileName);   
    }
    }
}
