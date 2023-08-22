/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cst8218.assignemnt2;


import cst8218.assignemnt2.entity.Bouncer;
import cst8218.assignemnt2.BouncerFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author anvp0
 */
@Singleton
@Startup
public class BouncerGame {

    @Inject
    private BouncerFacade bouncerFacade; 
    private List<Bouncer> bouncers;
    private final int CHANGE_RATE = 60; 

  @PostConstruct
  public void go() {
    new Thread(new Runnable() {
        public void run() {
            // the game runs indefinitely
            while (true) {
                //update all the bouncers and save changes to the database
                bouncers = bouncerFacade.findAll();
                for (Bouncer bouncer : bouncers) {
                    bouncer.advanceOneFrame();
                    bouncerFacade.edit(bouncer);
                }
                //sleep while waiting to process the next frame of the animation
                try {
                    // wake up roughly CHANGE_RATE times per second
                    Thread.sleep((long)(1.0/CHANGE_RATE*1000));                               
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }).start();
}

}
