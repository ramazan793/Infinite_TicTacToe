import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class TicTacToe extends PApplet {

 
final int CYAN = color(0,191,255); 
int Scale =50; 
ArrayList<Toe> jesus; 
int count =2; 
int acc2=0;
int xTrans=0;
int yTrans=0;
public void setup() { 
   
   
   jesus = new ArrayList(); 
} 
class Toe{ 
   int x; 
   int y; 
   int status; 
   public void display(){ } 
   public int getX(){ 
      return x; 
   } 
   public int getY(){ 
      return y; 
   } 
   public int getStatus(){ 
      return status; 
   } 
} 
class Tic extends Toe { 
   int status =1; 
   float d = Scale/6.25f; 
   int c = color(255,0,0,300); 
   int w = 3; 
   Tic(int mx, int my,int c, int w){ 
      this.x=(mx/25)*25; 
      this.y=(my/25)*25; 
      this.c=c; 
      this.w=w; 
   } 
   Tic(){} 
   Tic(int mx, int my){ 
      this.x=mx; 
      this.y=my; 
   } 
   public void display(){ 
      
      stroke(c); 
      strokeWeight(w); 
      line(this.x+d,this.y+d,this.x-d+Scale,this.y-d+Scale); 
      line(this.x-d+Scale,this.y+d,this.x+d, this.y-d+Scale); 
      strokeWeight(1); 
      stroke(CYAN); 
      
   } 
   public int getX(){ 
      return x; 
   } 
   public int getY(){ 
      return y; 
   } 
   public int getStatus(){ 
      return status; 
   } 
} 

class Tac extends Toe { 
   int status =2; 
   int c = color(255,0,0,300); 
   int w = 3; 
   Tac(int mx, int my,int c, int w){ 
      this.x=(mx/25)*25; 
      this.y=(my/25)*25; 
      this.c=c; 
      this.w=w; 
   } 
   Tac(){} 
   Tac(int mx, int my){ 
      this.x=mx; 
      this.y=my; 
   } 
   public void display(){ 
      
      stroke(1,1,1); 
      strokeWeight(w); 
      ellipse((float)this.x+(float)Scale/2, (float)this.y+(float)Scale/2, Scale/1.56f, Scale/1.56f); 
      strokeWeight(1); 
      stroke(CYAN); 
      
   } 
   public int getX(){ 
      return x; 
   } 
   public int getY(){ 
      return y; 
   } 
   public int getStatus(){ 
      return status; 
   } 
} 

public boolean exchecker(int x, int y){ 
   for (int i=0;i<jesus.size();i++){ 
      if (x==jesus.get(i).getX() && y==jesus.get(i).getY()){ 
         return true; 
      } 
   } 
   return false; 
} 

public boolean ToeChecker(int x, int y, int status){ 
   for (int i=0;i<jesus.size();i++){ 
      if (x==jesus.get(i).getX() && y==jesus.get(i).getY() && status == jesus.get(i).getStatus()){ 
         return true; 
      } 
   } 
   return false; 
} 

public void mousePressed(){ 
   
   double x = Math.floor(( mouseX+xTrans )/Scale); 
   double y = Math.floor(( mouseY+yTrans )/Scale); 
   
   if (exchecker((int)(x*Scale),(int)(y*Scale))==false){ 
      if (count%2==0){ 
         Tic a = new Tic((int)(x*Scale),(int)(y*Scale)); 
         if(acc2<5){jesus.add(a); }
         count++; 
      } 
      else { 
         Tac a = new Tac((int)(x*Scale),(int)(y*Scale)); 
         if (acc2<5){jesus.add(a);}
         count++; 
      } 
      
   } 
   
   
   
} 

public boolean Rogue(int x,int y, int status,int acc,int dy,int dx){ 
   
   
   if (ToeChecker(x,y,status) && acc <5){ 
      acc++; 
      return Rogue(x+dx*Scale,y+dy*Scale,status,acc,dy,dx); 
      } else if (acc < 5) { 
         return false; 
         } else if (acc==5){ 
            acc2=acc;
            acc=0; 
            return true; 
            
         } 
         return false; 
      } 
 




public void checker(Toe a){ 
   int x = a.getX(); 
   int y = a.getY(); 
   
   int dx = - 1; 
   int dx2 = 0; 
   int dx3 = 1; 
   int status = a.getStatus(); 
   int dy = - 1; 
   int dy2 = 0; 
   int dy3 = 1; 
   
   //1) dx dy 2) dx2 dy 3) dx3 dy 4) dx dy2 5) dx3 dy2 6) dx dy3 7) dx2 dy3 8) dx3 dy3 
   int acc = 0; 
   if (Rogue(x,y,a.getStatus(),acc,dx,dy)){ 
      if (status == 2){ stroke(0,0,0);} else { stroke (255,0,0);} 
      strokeWeight(3); 
      line(x+Scale,y+Scale,x+dx*Scale*4,y+dy*Scale*4); 
      stroke(CYAN); 
      strokeWeight(1); 
   } 
   if (Rogue(x,y,a.getStatus(),acc,dx2,dy3)){ 
      if (status == 2){ stroke(0,0,0);} else { stroke (255,0,0);} 
      strokeWeight(3); 
      line(x,y+(float)Scale/2,x+dx3*Scale*5,y+dy2*Scale*5+(float)Scale/2); 
      stroke(CYAN); 
      strokeWeight(1); 
   } 
   if (Rogue(x,y,a.getStatus(),acc,dx3,dy2)){ 
      if (status == 2){ stroke(0,0,0);} else { stroke (255,0,0);} 
      strokeWeight(3); 
      line(x+(float)Scale/2,y,x+dx2*Scale*5+(float)Scale/2,y+dy3*Scale*5); 
      stroke(CYAN); 
      strokeWeight(1); 
   } 
   if (Rogue(x,y,a.getStatus(),acc,dx,dy3)){ 
      if (status == 2){ stroke(0,0,0);} else { stroke (255,0,0);} 
      strokeWeight(3); 
      line(x,y+Scale,x+dx3*Scale*4+Scale,y+dy*Scale*4); 
      stroke(CYAN); 
      strokeWeight(1); 
   } 
} 


public void draw(){ 
   
   
   background(255,255,255); 
   
   //horiz
   for (int i=0;i < 2000/Scale+1;i++){ 
      stroke(CYAN); line(-1000,-1000+i*Scale,1000,-1000+i*Scale); 
      
   } 
   //vertical
   for (int i=0;i<2000/Scale+1;i++){ 
      line(-1000+i*Scale,-1000,-1000+i*Scale,1000); 
      stroke(CYAN); 
   } 
   for (Toe i : jesus){ 
      
      i.display(); 
      checker(i);
      
   } 
   
   
   
   
}
  public void settings() {  size(1000,1000); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "TicTacToe" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
