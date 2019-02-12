//package model;
//
//import view.GamePanel;
//import java.awt.Color;
//import java.awt.Graphics2D;
//import java.awt.geom.Rectangle2D;
//
//public class Bomb extends GameFigure {
//
//    private int radius;
//    private final Color color;
//    private int dx = 3;
//    private int dy = 3;
//    private final int explode;
//    private boolean expand = true;
//
//
//    public Bomb(float x, float y, int radius, Color color) {
//        super(x, y);
//        super.state = GameFigureState.STATE_ACTIVE;
//        this.radius = radius;
//        this.color = color;
//        explode = 3 * radius;
//        
//    }
//
//    @Override
//    public void render(Graphics2D g) {
//        g.setColor(color);
//        // Note: use drawOval() to draw outline only
//        if(state == GameFigureState.STATE_ACTIVE){
//        g.fillOval((int)(x - radius), (int)(y - radius), 
//                radius * 2, radius * 2);
//        }
//        else if(state == GameFigureState.STATE_DYING)
//        {
//            g.fillOval((int)(x - 10.0F), (int)(y), radius * 2, radius * 2);
//            g.fillOval((int)(x + 10.0F), (int)(y), radius * 2, radius * 2);
//            g.fillOval((int)(x), (int)(y + 10.0F), radius * 2, radius * 2);
//        }
//    }
//
//    @Override
//    public void update() {
//        // ball bounces on the wall
//        if(state == GameFigureState.STATE_ACTIVE)
//        {
//            super.x += dx;
//            super.y += dy;
//        }
//        else if(state == GameFigureState.STATE_DYING)
//        {
//            if(expand && radius < explode)
//            {
//                radius++;
//            }
//            else
//            {
//                expand = false;
//                if(radius > 0)
//                {
//                    radius --;
//                }
//                else
//                {
//                    state = GameFigureState.STATE_DONE;
//                }
//            }
//        }
//
//        if (super.x + radius > GamePanel.width) {
//            dx = -dx;
//            super.x = GamePanel.width - radius;
//        } else if (super.x - radius < 0) {
//            dx = -dx;
//            super.x = radius;
//        }
//
//        if (super.y + radius > GamePanel.height) {
//            dy = -dy;
//            super.y = GamePanel.height - radius;
//        } else if (super.y - radius < 0) {
//            dy = -dy;
//            super.y = radius;
//        }
//    }
//    
//    @Override
//    public Rectangle2D.Float getCollisionBox()
//    {
//        return new Rectangle2D.Float(x - radius, y - radius, (radius * 2) * .9F, (radius * 2) * .9F);
//    }
//    
//}
