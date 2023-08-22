/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cst8218.assignemnt2.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author anvp0
 */
@Entity
@XmlRootElement
public class Bouncer implements Serializable {

    //declare properties
    private static final long serialVersionUID = 1L;
    private static final int GRAVITY_ACCEL = 0;
    private static final int DECAY_RATE = 1;
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 500;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer x = 0;
    private Integer y = 0;
    private Integer ySpeed = 0;

    public Bouncer() {

    }

    public Bouncer(Integer x, Integer y, Integer ySpeed) {
        if (x == null || y == null || ySpeed == null) {
            throw new IllegalArgumentException("cant be null");
        }
        if (x < 0 || x > FRAME_WIDTH) {
            throw new IllegalArgumentException("x cant be more than 500 or less than 0");
        }
        if (y < 0 || y > FRAME_HEIGHT) {
            throw new IllegalArgumentException("y cant be more than 500 or less than 0");
        }
        this.x = x;
        this.y = y;
        this.ySpeed = ySpeed;

    }

    //implement getters setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        if (x == null || x < 0 || x > FRAME_WIDTH) {
            throw new IllegalArgumentException("bad input");
        }
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        if (y == null || y < 0 || y > FRAME_HEIGHT) {
            throw new IllegalArgumentException("bad input");
        }
        this.y = y;
    }

    public Integer getYSpeed() {
        return ySpeed;
    }

    public void setYSpeed(Integer ySpeed) {
        if (ySpeed == null) {
            throw new IllegalArgumentException("cant be null");
        }
        this.ySpeed = ySpeed;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public void updates(Bouncer newBouncer) {
        if (newBouncer.getId() != null) {
            this.setId(newBouncer.getId());
        }
        if (newBouncer.getX() != null) {
            this.setX(newBouncer.getX());
        }
        if (newBouncer.getY() != null) {
            this.setY(newBouncer.getY());
        }
        if (newBouncer.getYSpeed() != null) {
            this.setYSpeed(newBouncer.getYSpeed());
        }
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bouncer)) {
            return false;
        }
        Bouncer other = (Bouncer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * Advances the position of the bouncer by one frame.
     */
    public void advanceOneFrame() {
        this.ySpeed += GRAVITY_ACCEL;

        this.y += this.ySpeed;

        if (this.y > FRAME_HEIGHT) {
            this.y = FRAME_HEIGHT;
            this.ySpeed = -(this.ySpeed + DECAY_RATE);
        }

        if (this.y < 0) {
            this.y = 0;
            this.ySpeed = -(this.ySpeed - DECAY_RATE);
        }
    }

    @Override
    public String toString() {
        return "bouncer.an2.entity.Bouncer[ id=" + id + " ]";
    }

}
