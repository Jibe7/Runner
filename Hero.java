import javafx.geometry.Rectangle2D;

public class Hero extends AnimatedThing {
    private int list[];
    private int compt;
    private double xaffichage;
    private double gravity=200;
    private double a_x=50;
    private double ax_prec=50;
    private double pos_x=200;
    private double x_prec=180;
    private double v_x;
    private double vx_prec;

    private double pos_y=250;
    private double y_prec=250;
    private double v_y=0;
    private double vy_prec=0;
    private double a_y=0;
    private double ay_prec=0;
    private double deltaT;
    private long pTime=0;

    public Hero(double xpos, double ypos, String filename, Integer x1, Integer y1, Integer length, Integer width, int maxI,int[] Lh) {
        super(xpos,ypos,filename,x1,y1,length,width);
        list = Lh;
        index=0;
        maxIndex=maxI;
        compt=0;
    }

    public void addList(int x1, int y1, int length, int width) {
        int[] tmp = new int[list.length+4];
        for (int i=0;i<list.length;i++) {
            tmp[i]=list[i];
        }
        tmp[list.length] = x1;
        tmp[list.length+1] = y1;
        tmp[list.length+2] = length;
        tmp[list.length+3] = width;
        list=tmp;
    }

    public void setList(int[] L) {
        list=L;
    }

    public void update(long time,int xcam) {
        //faire évoluer x et y puis :

        if (pTime==0) pTime=time;

        deltaT = 1e-9*(time-pTime);
        a_x=ax_prec;
        a_y=ay_prec;
        v_x=vx_prec+deltaT*a_x;
        v_y=vy_prec+deltaT*a_y;
        pos_x=x_prec+deltaT*v_x;
        pos_y=y_prec+deltaT*v_y;
        pTime=time;

        x_prec=pos_x;
        vx_prec=v_x;
        y_prec=pos_y;
        vy_prec=v_y;
        ax_prec=50; //accélération constante du héro, c'est un choix
        if (x>4500) ax_prec=0; // arrête d'accélerer à une certaine distance
        if (x>3000 & x<3100) {ay_prec=-200; a_y=-200; pos_y=248; System.out.println("QUOI\n \n \n \n \n \n \n \n \n \n");}
        if (x>3500) { if (pos_y<250) { a_y=gravity; ay_prec=gravity; } }
        if (pos_y>=250) {a_y=0; ay_prec=0; v_y=0; vy_prec=0;}
        x=pos_x;
        y=pos_y;
        sprite.setX(x-xcam);
        sprite.setY(y);

        if (compt==7) {
            //x+=15;
            //xaffichage=x-xcam; //x position du héro par rapport à un référentiel fixe, xaffichage position par rapport à la caméra
            //sprite.setX(xaffichage);
            //sprite.setY(this.y);
            index=index%(maxIndex);
            sprite.setViewport(new Rectangle2D(list[index*4],list[1+index*4],list[2+index*4],list[3+index*4]));
            index++;
            compt=compt%7;
        }
        compt++;
        System.out.println("CECI EST X : "+x);
        System.out.println("CECI EST Y : "+y);


    }


}
