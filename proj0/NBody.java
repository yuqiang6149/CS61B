public class NBody {

    public static double readRadius(String fileName){
        In in = new In(fileName);
        in.readInt();
        return in.readDouble();

}
    public static Planet[] readPlanets(String fileName){
        In in = new In(fileName);
        int num = in.readInt();
        Planet[] res = new Planet[num];
        in.readDouble();
        for(int i =0;i < num;i++){
            res[i] = new Planet(in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readString());
        //就这？
        }
        return res;
    }
    public static void main(String[] args){
        if(args.length != 3){
            return ;
        }
        double time = 0;
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = NBody.readPlanets(filename);
        double[] XForces = new double[planets.length];
        double[] YForces = new double[planets.length];

        StdDraw.setScale(-radius,radius);
        StdDraw.clear();
        StdDraw.picture(0,0,"images/starfield.jpg");
        StdDraw.show();

        for(Planet p:planets){
            p.draw();
        }
        StdDraw.enableDoubleBuffering();
        while(time <= T){
            for(int i = 0;i < planets.length; i++){
                XForces[i] = planets[i].calcNetForceExertedByX(planets);
                YForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for(int i = 0;i < planets.length; i++){
                planets[i].update(dt,XForces[i],YForces[i]);
            }
            StdDraw.picture(0,0,"images/starfield.jpg");
            for(Planet p:planets){
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);

            time += dt;
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
