package com.gdxgame.digdoug.tools;

import com.badlogic.gdx.physics.box2d.*;

import java.nio.channels.FileChannel;

public class CollisionDetector implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        if(fa == null || fb ==null) return;
        if(fa.getUserData() == null || fb.getUserData() == null) return;

        System.out.println("Contact began");

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

}
